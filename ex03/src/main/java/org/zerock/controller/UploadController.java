package org.zerock.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UploadController {

	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("upload Form .....");
	}
	
	@PostMapping("/uploadFormAction")
	public void uploadFormAction(MultipartFile[] uploadFile, Model model,
			HttpServletRequest request) throws IllegalStateException, IOException {
		
		// 저장할 상대 위치 정하기 /webapp/upload - servlet-context.xml에서 허용되어진 폴더이여야 한다.
		// 실제적으로 폴더도 만들어 놔야 한다. 파일 한개를 넣어 두면 반드시 폴더를 만든다. 자원이 없으면 폴더를 안 만들 수도 있다.
		String path = "/upload";
		
		// request.getServletContext().realpath() -> DS에서 ServletContext는 주지 않는다. request를 받아서 쓴다.
		log.info(request);
		
		// 실제적으로 저장이되는 절대 위치 찾기.
		String realPath = request.getServletContext().getRealPath(path);
		log.info("실제적인 저장 위치 : " + realPath);
		
		
		for(MultipartFile mf : uploadFile) {
			log.info("-------------------------------------------");
			log.info("Upload file name : " + mf.getOriginalFilename());
			log.info("Upload file size : " + mf.getSize());
			
			// 저장할 파일 객체 생성
			File saveFile = new File(realPath, mf.getOriginalFilename());
			
			if(saveFile.exists()) log.info("저장할 파일이 존재합니다.");
			
			// 저장을 실행한다.
			mf.transferTo(saveFile);
		}
		
		// jsp에 첨부파일을 보낸다.
		model.addAttribute("uploadFile", uploadFile);
		
	}
}
