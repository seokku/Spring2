package org.zerock.image.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.image.service.ImageService;
import org.zerock.image.vo.ImageVO;
import org.zerock.member.vo.LoginVO;

import com.webjjang.util.PageObject;
import com.webjjang.util.file.FileUtil;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/image")
@Log4j
public class ImageController {

	private final String MODULE = "image";
	
	// 저장할 위치 - 운영되는 서버에서 부터 찾는 상대 위치
	String path = "/upload/image/";
			
	@Autowired
	@Qualifier("isi")
	private ImageService service;
	
	// 1. 이미지 겔러리 리스트
	@GetMapping("/list.do")
	public String list(@ModelAttribute PageObject pageObject, Model model, HttpSession session) 
			throws Exception {
		
		if(pageObject.getPerPageNum() == 10)
			pageObject.setPerPageNum(8);
		
		log.info("list().pageObject : " + pageObject);
		
		
		// 강제 로그인 시킨다.
		LoginVO vo = new LoginVO();
		vo.setId("test");
		vo.setGradeNo(1);
		session.setAttribute("login", vo);
		
		model.addAttribute("list", service.list(pageObject));
		
		//jsp의 정보 리턴
		return MODULE + "/list";
	}
	
	// 2. 이미지 보기
	@GetMapping("/view.do")
	public String view(Long no, @ModelAttribute PageObject pageObject, 
			Model model) throws Exception {
		log.info("view().no : " + no);
		
		model.addAttribute("vo", service.view(no));
		
		//jsp의 정보 리턴
		return MODULE + "/view";
	}
	
	// 3. 이미지 등록 폼
	@GetMapping("/write.do")
	public String writeForm() {
		
		log.info("writeForm() ......");
		
		return MODULE + "/write";
	}
	
	// 3. 이미지 등록 처리
	@PostMapping("/write.do")
	public String write(ImageVO vo, Long perPageNum,  HttpSession session,
			HttpServletRequest request) throws Exception {
		
		log.info("write() .. 이미지 등록에서 새로 고침을 하지 마시고 리스트에서 새로 고침해서 들어오세요.(세션)");
		
		
		// 실제적으로 저장이되는 위치
		String realPath = request.getServletContext().getRealPath(path);
		
		log.info("realPath : " + realPath);
		
		LoginVO loginVO = (LoginVO) session.getAttribute("login");
		
		log.info("loginVO : "+loginVO);
		
		
		// 전달 받지 않는 데이터의 수집
		vo.setId(loginVO.getId());
		String fileName = vo.getMultipartFile().getOriginalFilename();
		File imageFile = FileUtil.noDuplicate(realPath + fileName);
		vo.setFileName(path + imageFile.getName());
		
		log.info("write().vo ......" + vo);
//		log.info("write().vo.getMultipartFile.name : "
//		+ vo.getMultipartFile().getOriginalFilename());
		
		// 전달된 파일을 저장하는 처리
		vo.getMultipartFile().transferTo(imageFile);
		
		// DB에 정보(vo) 저장
		service.write(vo);
		 
		
		return "redirect:list.do?perPageNum=" + perPageNum;
	}
	
	// 이미지 파일 바꾸기 처리
	@PostMapping("/updateFile.do")
	public String updateFile(ImageVO vo, PageObject pageObject, String deleteFile,
			HttpServletRequest request) throws Exception {
		
		String realPath = request.getServletContext().getRealPath(path);
		
		// 넘어오는 정보 확인
		log.info("updateFile(). vo = " + vo);
		log.info("updateFile(). pageObject = " + pageObject);
		log.info("updateFile(). deleteFile = " + deleteFile);
		
		// 원본 파일 지우기
		String deleteFileRealPath = request.getServletContext().getRealPath(deleteFile);
		log.info("updateFile(). deleteFile = " + deleteFileRealPath);
		FileUtil.delete(FileUtil.toFile(deleteFileRealPath));
		
		
		// 전달된 파일을 중복 배제해서 저장한다.
		String fileName = vo.getMultipartFile().getOriginalFilename();
		File imageFile = FileUtil.noDuplicate(realPath + fileName);
		vo.setFileName(path + imageFile.getName());
		
		log.info("write().vo ......" + vo);
		
		// 전달된 파일을 서버에 저장하는 처리
		vo.getMultipartFile().transferTo(imageFile);
		
		// DB에 파일 정보를 수정한다.
		service.updateFile(vo);
		
		
		return "redirect:view.do?no="+ vo.getNo()
		+ "&page=" +pageObject.getPage()
		+ "&perPageNum=" + pageObject.getPerPageNum();
	}
	
	// 4-1. 이미지 정보 바꾸기 폼
	@GetMapping("/update.do")
	public String updateForm(Long no, Model model) throws Exception {
		
		// DB에서 no에 맞는 정보를 가져와서 model에 넣는다.
		model.addAttribute("vo", service.view(no));
		
		return MODULE + "/update";
	}
	
	// 4-2. 이미지 정보 바꾸기 처리
	@PostMapping("/update.do")
	public String update(ImageVO vo, PageObject pageObject) throws Exception {
		
		// DB에 이미지 정ㅈ보 수정(제목,내용) - 글번호에 맞는.
		service.update(vo);
		
		// 정보가 수정이 되면 이미지 보기로 이동을 시킨다.
		return "redirect:view.do?no=" + vo.getNo()
		+ "&page=" + pageObject.getPage() + "&perPageNum=" + pageObject.getPerPageNum();
		
	}
	
	
	
}
