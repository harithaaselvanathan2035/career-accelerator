package com.careeraccelerator.service;

import com.careeraccelerator.dto.ParsedResumeResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class ResumeParserService {

    public ParsedResumeResponse parsePdf(String filePath)
            throws Exception {

        PDDocument document =
                Loader.loadPDF(
                        new File(filePath));

        PDFTextStripper stripper =
                new PDFTextStripper();

        String text =
                stripper.getText(document);

        document.close();

        log.info("=========== RESUME TEXT ===========");
        log.info(text);
        log.info("===================================");

        return ParsedResumeResponse.builder()

                .fullName(
                        extractName(text))

                .email(
                        extractEmail(text))

                .phone(
                        extractPhone(text))

                .linkedin(
                        extractLinkedIn(text))

                .github(
                        extractGithub(text))

                .summary(
                        extractSection(
                                text,
                                new String[]{
                                        "ABOUT",
                                        "CAREER OBJECTIVE",
                                        "OBJECTIVE",
                                        "PROFILE"
                                },
                                new String[]{
                                        "EDUCATION"
                                }
                        ))

                .education(
                        extractSection(
                                text,
                                new String[]{
                                        "EDUCATION",
                                        "ACADEMIC QUALIFICATION"
                                },
                                new String[]{
                                        "SKILLS",
                                        "TECHNICAL SKILLS",
                                        "KEY SKILLS"
                                }
                        ))

                .skills(
                        extractSection(
                                text,
                                new String[]{
                                        "SKILLS",
                                        "TECHNICAL SKILLS",
                                        "KEY SKILLS"
                                },
                                new String[]{
                                        "INTERNSHIP",
                                        "INTERNSHIPS",
                                        "EXPERIENCE",
                                        "WORK EXPERIENCE",
                                        "PROJECTS"
                                }
                        ))

                .experience(
                        extractSection(
                                text,
                                new String[]{
                                        "INTERNSHIP",
                                        "INTERNSHIPS",
                                        "EXPERIENCE",
                                        "WORK EXPERIENCE"
                                },
                                new String[]{
                                        "PROJECTS",
                                        "PROJECT"
                                }
                        ))

                .projects(
                        extractSection(
                                text,
                                new String[]{
                                        "PROJECTS",
                                        "PROJECT"
                                },
                                new String[]{
                                        "CERTIFICATIONS",
                                        "ACHIEVEMENTS",
                                        "EXTRACURRICULAR",
                                        "EXTRACURRICULAR ACTIVITIES"
                                }
                        ))

                .certifications(
                        extractSection(
                                text,
                                new String[]{
                                        "CERTIFICATIONS",
                                        "ACHIEVEMENTS"
                                },
                                new String[]{
                                        "EXTRACURRICULAR",
                                        "EXTRACURRICULAR ACTIVITIES"
                                }
                        ))

                .activities(
                        extractSection(
                                text,
                                new String[]{
                                        "EXTRACURRICULAR",
                                        "EXTRACURRICULAR ACTIVITIES"
                                },
                                new String[]{
                                        "REFERENCES",
                                        "DECLARATION",
                                        "END"
                                }
                        ))

                .build();
    }

    // =====================================
    // NAME
    // =====================================

    private String extractName(
            String text) {

        String[] lines =
                text.split("\\r?\\n");

        for (String line : lines) {

            line = line.trim();

            if (line.length() > 3
                    && line.length() < 40
                    && !line.contains("@")
                    && !line.matches(".*\\d.*")) {

                return line;
            }
        }

        return "";
    }

    // =====================================
    // EMAIL
    // =====================================

    private String extractEmail(
            String text) {

        Pattern pattern =
                Pattern.compile(
                        "[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+");

        Matcher matcher =
                pattern.matcher(text);

        if (matcher.find()) {

            return matcher.group();
        }

        return "";
    }

    // =====================================
    // PHONE
    // =====================================

    private String extractPhone(
            String text) {

        Pattern pattern =
                Pattern.compile(
                        "(\\+91[- ]?)?[6-9]\\d{9}");

        Matcher matcher =
                pattern.matcher(text);

        if (matcher.find()) {

            return matcher.group();
        }

        return "";
    }

    // =====================================
    // LINKEDIN
    // =====================================

    private String extractLinkedIn(
            String text) {

        Pattern pattern =
                Pattern.compile(
                        "(linkedin\\.com/in/[^\\s]+)");

        Matcher matcher =
                pattern.matcher(text);

        if (matcher.find()) {

            return matcher.group();
        }

        return "";
    }

    // =====================================
    // GITHUB
    // =====================================

    private String extractGithub(
            String text) {

        Pattern pattern =
                Pattern.compile(
                        "(github\\.com/[^\\s]+)");

        Matcher matcher =
                pattern.matcher(text);

        if (matcher.find()) {

            return matcher.group();
        }

        return "";
    }

    // =====================================
    // SECTION EXTRACTION
    // =====================================

    private String extractSection(
            String text,
            String[] startKeywords,
            String[] endKeywords) {

        try {

            String upperText =
                    text.toUpperCase();

            int start = -1;

            for (String keyword :
                    startKeywords) {

                start =
                        upperText.indexOf(
                                keyword.toUpperCase());

                if (start != -1) {

                    start =
                            start +
                                    keyword.length();

                    break;
                }
            }

            if (start == -1) {

                return "";
            }

            int end =
                    text.length();

            for (String keyword :
                    endKeywords) {

                int temp =
                        upperText.indexOf(
                                keyword.toUpperCase(),
                                start);

                if (temp != -1
                        && temp < end) {

                    end = temp;
                }
            }

            String result =
                    text.substring(
                            start,
                            end);

            result =
                    result.replaceAll(
                            "\\n{2,}",
                            "\n");

            return result.trim();

        } catch (Exception e) {

            return "";
        }
    }
}