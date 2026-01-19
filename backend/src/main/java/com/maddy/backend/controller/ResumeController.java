package com.maddy.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.maddy.backend.service.ResumeService;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ResumeController {
	
	private final ResumeService resumeService;
	
	public ResumeController(ResumeService resumeService) {
		this.resumeService = resumeService;
	}
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			String result = resumeService.saveFile(file);
			return ResponseEntity.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(result);
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.contentType(MediaType.APPLICATION_JSON)
					.body("{\"error\": \"Unexpected error\"}");
		}
	}



	
	
}
