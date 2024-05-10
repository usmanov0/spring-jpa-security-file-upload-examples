package com.example.hibernateexample.entity;

import com.example.hibernateexample.entity.enums.FileStorageStatus;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileStorage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String fileType; // extensions

    private Long fileSize;

    private String contentType;

    private String hashId;

    private String uploadFolder;

    @Enumerated(EnumType.STRING)
    private FileStorageStatus fileStorageStatus;
}
