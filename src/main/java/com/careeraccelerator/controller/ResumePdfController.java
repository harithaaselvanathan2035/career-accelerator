package com.careeraccelerator.controller;

import com.careeraccelerator.dto.ResumeRequest;
import com.careeraccelerator.service.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pdf")
@RequiredArgsConstructor
public class ResumePdfController {

    private final PdfService pdfService;

    @PostMapping("/download")
    public ResponseEntity<byte[]>
    downloadResume(
            @RequestBody
            ResumeRequest resume)
            throws Exception {

        byte[] pdf =
                pdfService
                        .generateResumePdf(
                                resume);

        return ResponseEntity
                .ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=resume.pdf")
                .contentType(
                        MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}