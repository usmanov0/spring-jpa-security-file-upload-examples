package com.example.hibernateexample.service;

import com.example.hibernateexample.entity.FileStorage;
import com.example.hibernateexample.entity.enums.FileStorageStatus;
import com.example.hibernateexample.repository.FileStorageRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
public class FileStorageService {
    private final FileStorageRepository fileStorageRepository;

    @Value("${upload.server},${upload.folder}")
    private String serverFolderPath;

    private final Hashids hashids;

    @Autowired
    public FileStorageService(FileStorageRepository fileStorageRepository) {
        this.fileStorageRepository = fileStorageRepository;
        this.hashids = new Hashids(getClass().getName(), 2);
    }

    public FileStorage saveFile(MultipartFile multipartfile) {
        FileStorage fileStorage = new FileStorage();
        fileStorage.setFileName(multipartfile.getOriginalFilename());
        fileStorage.setFileType(getFileType(multipartfile.getOriginalFilename()));
        fileStorage.setFileSize(multipartfile.getSize());
        fileStorage.setContentType(multipartfile.getContentType());
        fileStorage.setFileStorageStatus(FileStorageStatus.DRAFT);
        fileStorage = fileStorageRepository.save(fileStorage);

        // / serverFolderPath/upload_folder/2022/02/20/20:14fileName.pdf

        Date now = new Date();
        File path = new File(this.serverFolderPath + "/upload_folder" +
                1900+now.getYear()+ "/"+ 1+now.getMonth()+ "/"+ now.getDate()+ "/" +now.getHours() + ":" +now.getMinutes());

        File uploadFolder = new File(path.getAbsolutePath());
        if (!uploadFolder.exists() && uploadFolder.mkdirs()) {
            System.out.println("Folder created");
        }

        fileStorage.setHashId(hashids.encode(fileStorage.getId()));
        File pathLocal = new File("/upload_files/" +
                (1900 + now.getYear()) + "/" +
                String.format("%02d", (1 + now.getMonth())) + "/" +
                String.format("%02d", now.getDate()) + "/" +
                String.format("%02d:%02d", now.getHours(), now.getMinutes()) + "/" +
                hashids.encode(fileStorage.getId()) + "." + getFileType(fileStorage.getFileName())
        );

        fileStorage.setUploadFolder(pathLocal.getAbsolutePath());
        fileStorageRepository.save(fileStorage);

        uploadFolder = uploadFolder.getAbsoluteFile();
        File file = new File(uploadFolder, String.format("%s.%s",
                fileStorage.getHashId(), fileStorage.getFileType()));

        try {
            multipartfile.transferTo(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fileStorage;
    }

    private String getFileType(String fileName){
        String extension = null;
        if (fileName != null && !fileName.isEmpty()){
            int dot = fileName.lastIndexOf(".");
            if (dot > 0 && dot < fileName.length()-2){
                extension = fileName.substring(dot+1);
            }
        }
        return extension;
    }

    public FileStorage getFByHashId(String hashId){
        return fileStorageRepository.findByHashId(hashId);
    }

    public void deleteFile(String hashId){
        FileStorage fileStorage = fileStorageRepository.findByHashId(hashId);
        File file = new File(String.format("%s/%s", this.serverFolderPath, fileStorage.getUploadFolder()));

        if (file.delete()) {
            fileStorageRepository.delete(fileStorage);
        }
    }
}
