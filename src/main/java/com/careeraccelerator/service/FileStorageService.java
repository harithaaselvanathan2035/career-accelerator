package com.careeraccelerator.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileStorageService {

    private final String uploadDir =
            System.getProperty("user.dir")
                    + File.separator
                    + "uploads"
                    + File.separator
                    + "resumes"
                    + File.separator;

    public String saveFile(
            MultipartFile file)
            throws Exception {

        File directory =
                new File(uploadDir);

        if (!directory.exists()) {

            directory.mkdirs();
        }

        String fileName =
                UUID.randomUUID()
                        + "_"
                        + file.getOriginalFilename()
                        .replace(" ", "_");

        String fullPath =
                uploadDir + fileName;

        file.transferTo(
                new File(fullPath));

        return fullPath;
    }
}