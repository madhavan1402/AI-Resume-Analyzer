package com.maddy.backend.service;

import org.springframework.web.multipart.MultipartFile;

public interface ResumeService {
	//upload the file/resume
	String uploadFileName(MultipartFile file);
	
	//save the file/resume
	String saveFile(MultipartFile file);
	
	//extract the pdf
	String extractTextFromPDF(String filePath);
}
