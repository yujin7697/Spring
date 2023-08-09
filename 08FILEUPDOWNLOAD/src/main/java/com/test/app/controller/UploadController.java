package com.test.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/upload")
public class UploadController {
	
	private String uploadDir = "c:\\upload";
	
	@GetMapping("/form")
	public void uploadForm() {
		log.info("GET /upload/form..");
	}
	
	@PostMapping("/reqUpload")
	public String upload(@RequestParam("files") MultipartFile[] uploadfiles,Model model) throws IllegalStateException, IOException {
		
		System.out.println("upload File Count : " + uploadfiles.length);
		
		//Upload Dir 미존재시 생성
		String path = uploadDir+File.separator+UUID.randomUUID();
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		for(MultipartFile file :uploadfiles) {
			System.out.println("------------------------------");
			System.out.println("File name : " + file.getOriginalFilename());
			System.out.println("File size : " + String.format("%.2f",(double)file.getSize()/1024) + "Byte");
			System.out.println("------------------------------");
			
			//파일명 추출
			String filename = file.getOriginalFilename();
			//파일 객체 생성
			File fileobj = new File(path,filename);
			//업로드
			file.transferTo(fileobj);
		}
		model.addAttribute("msg","upload ㅅㄱ");
		return "upload/form";
	}
	
	@GetMapping("/list")
	public void showFileList(Model model) {
		File root = new File(uploadDir);
		File[] uuidDirs = root.listFiles();
		for(File dir : uuidDirs) {
			System.out.println("----------------------");
			System.out.println("Folder : " + dir);
			System.out.println("----------------------");
			
			
			File subDir = new File(dir.getPath());
			for(File file : subDir.listFiles()) {
				System.out.println("File : " + file);
			}
			
		}
		model.addAttribute("rootDir",root);
	}
}
