package org.zerock.image.service;

import java.util.List;

import org.zerock.image.vo.ImageVO;

import com.webjjang.util.PageObject;

public interface ImageService {

	// 1. 이미지 겔러리 리스트
	public List<ImageVO> list(PageObject pageObject) throws Exception;
	
	// 2. 이미지 보기
	public ImageVO view(Long no) throws Exception;
	
	// 3. 이미지 등록
	public int write(ImageVO vo) throws Exception;
	
	// 4. 이미지 파일 수정
	public int updateFile(ImageVO vo) throws Exception;

	// 4-1. 이미지 정보 수정
	public int update(ImageVO vo) throws Exception;
}
