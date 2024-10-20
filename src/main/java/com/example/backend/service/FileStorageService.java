package com.example.demo.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.stereotype.Service;

@Service
public class FileStorageService {

    private final MinioClient minioClient;

    public FileStorageService() {
        this.minioClient = MinioClient.builder()
                .endpoint("http://localhost:9000")
                .credentials("YOUR-ACCESSKEY", "YOUR-SECRETKEY")
                .build();
    }

    public void uploadFile(String bucketName, String objectName, String filePath) {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .filename(filePath)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Error uploading file to MinIO: " + e.getMessage());
        }
    }
}
