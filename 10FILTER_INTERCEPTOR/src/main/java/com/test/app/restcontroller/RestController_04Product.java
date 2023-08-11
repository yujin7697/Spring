package com.test.app.restcontroller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.test.app.domain.dto.ProductDto;
import com.test.app.domain.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/product")
@Slf4j
public class RestController_04Product {
	private String uploadDir = "c:\\upload\\products";
	
	@Autowired
	private ProductService productService;

	@PostMapping("/upload")
	public String upload(ProductDto dto,Model model) throws IllegalStateException, IOException {
		
		System.out.println("upload File Count : " + dto.getFiles().length);
		System.out.println("ProductDto : " + dto);
		
		//Upload Dir 미존재시 생성
		String path = uploadDir+File.separator+dto.getProductType()+File.separator+UUID.randomUUID();
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		for(MultipartFile file :dto.getFiles()) {
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
		
		productService.addProduct(dto, path);
		
		return "좋았어! 영차~~";
	}
}
