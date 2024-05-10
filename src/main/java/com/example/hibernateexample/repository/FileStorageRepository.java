package com.example.hibernateexample.repository;

import com.example.hibernateexample.entity.FileStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileStorageRepository extends JpaRepository<FileStorage, Long> {
    FileStorage findByHashId(String hashId);
    void deleteByHashId(String hashId);
}
