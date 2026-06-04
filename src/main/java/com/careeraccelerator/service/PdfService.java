package com.careeraccelerator.service;

import com.careeraccelerator.dto.ResumeRequest;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public byte[] generateResumePdf(
            ResumeRequest resume)
            throws Exception {

        Document document =
                new Document();

        ByteArrayOutputStream outputStream =
                new ByteArrayOutputStream();

        PdfWriter.getInstance(
                document,
                outputStream);

        document.open();

        document.add(
                new Paragraph(
                        resume.getFullName()));

        document.add(
                new Paragraph(
                        resume.getEmail()));

        document.add(
                new Paragraph(
                        resume.getPhone()));

        document.add(
                new Paragraph(
                        resume.getLinkedin()));

        document.add(
                new Paragraph(
                        resume.getGithub()));

        document.add(
                new Paragraph(
                        resume.getLocation()));

        document.add(
                new Paragraph(" "));

        document.add(
                new Paragraph(
                        "ABOUT"));

        document.add(
                new Paragraph(
                        resume.getSummary()));

        document.add(
                new Paragraph(" "));

        document.add(
                new Paragraph(
                        "EDUCATION"));

        document.add(
                new Paragraph(
                        resume.getEducation()));

        document.add(
                new Paragraph(" "));

        document.add(
                new Paragraph(
                        "SKILLS"));

        document.add(
                new Paragraph(
                        resume.getSkills()));

        document.add(
                new Paragraph(" "));

        document.add(
                new Paragraph(
                        "CERTIFICATIONS"));

        document.add(
                new Paragraph(
                        resume.getCertifications()));

        document.add(
                new Paragraph(" "));

        document.add(
                new Paragraph(
                        "PROJECTS"));

        document.add(
                new Paragraph(
                        resume.getProjects()));

        document.add(
                new Paragraph(" "));

        document.add(
                new Paragraph(
                        "EXTRACURRICULAR ACTIVITIES"));

        document.add(
                new Paragraph(
                        resume.getActivities()));

        document.close();

        return outputStream.toByteArray();
    }
}