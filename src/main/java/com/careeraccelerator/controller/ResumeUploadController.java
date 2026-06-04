package com.careeraccelerator.controller;

import com.careeraccelerator.dto.ParsedResumeResponse;
import com.careeraccelerator.service.FileStorageService;
import com.careeraccelerator.service.ResumeParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ResumeUploadController {

    private final FileStorageService
            fileStorageService;

    private final ResumeParserService
            parserService;

    @PostMapping("/upload")
    public ParsedResumeResponse uploadResume(
            @RequestParam("file")
            MultipartFile file)
            throws Exception {

        String path =
                fileStorageService
                        .saveFile(file);

        return parserService
                .parsePdf(path);
    }
}