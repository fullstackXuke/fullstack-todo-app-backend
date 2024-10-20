package com.example.backend.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

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
        try (FileInputStream fis = new FileInputStream(filePath)) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(fis, fis.available(), -1)
                            .contentType("application/octet-stream")
                            .build()
            );
        } catch (IOException e) {
            throw new RuntimeException("Error uploading file to MinIO: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("MinIO error: " + e.getMessage(), e);
        }
    }
}








//package com.example.demo.service;

//import io.minio.MinioClient;
//import io.minio.PutObjectArgs;
//import org.springframework.stereotype.Service;

//@Service
//public class FileStorageService {

  //  private final MinioClient minioClient;

    //public FileStorageService() {
      //  this.minioClient = MinioClient.builder()
        //        .endpoint("http://localhost:9000")
          //      .credentials("YOUR-ACCESSKEY", "YOUR-SECRETKEY")
            //    .build();
    //}

    //public void uploadFile(String bucketName, String objectName, String filePath) {
      //  try {
        //    minioClient.putObject(
          //          PutObjectArgs.builder()
            //                .bucket(bucketName)
              //              .object(objectName)
                //            .filename(filePath)
                  //          .build()
  //          );
    //    } catch (Exception e) {
      //      throw new RuntimeException("Error uploading file to MinIO: " + e.getMessage());
//        }
  //  }
//}
