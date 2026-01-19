package com.maddy.backend.service.impl;

import java.io.File;
import java.nio.file.*;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maddy.backend.service.MLClient;
import com.maddy.backend.service.ResumeService;

@Service
public class ResumeServiceImpl implements ResumeService {

    private final MLClient mlClient;
    private final ObjectMapper mapper = new ObjectMapper();

    public ResumeServiceImpl(MLClient mlClient) {
        this.mlClient = mlClient;
    }

    @Override
    public String uploadFileName(MultipartFile file) {
        return System.currentTimeMillis() + "_" + file.getOriginalFilename();
    }

    @Override
    public String saveFile(MultipartFile file) {
        Path uploadPath = Paths.get("uploads");

        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = uploadFileName(file);
            Path filePath = uploadPath.resolve(fileName);

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // 1️⃣ Extract text
            String extracted = extractTextFromPDF(filePath.toString());

            // 2️⃣ Clean text
            String cleaned = cleanText(extracted);

            // 3️⃣ Call ML API
            String mlResponse = mlClient.analyzeResume(cleaned);

            // 4️⃣ Return ML JSON string
            return mlResponse;

        } catch (Exception e) {
            try {
                return mapper.writeValueAsString(Map.of("error", e.getMessage()));
            } catch (Exception ex) {
                return "{\"error\": \"Internal server error\"}";
            }
        }
    }

    @Override
    public String extractTextFromPDF(String filePath) {
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        } catch (Exception e) {
            throw new RuntimeException("Error extracting text from PDF", e);
        }
    }

    private String cleanText(String text) {
        return text
                .replaceAll("\\s+", " ")
                .replaceAll("[^\\x00-\\x7F]", "")
                .trim();
    }
}
