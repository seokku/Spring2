package org.zerock.image.mapper;

import java.util.List;

import org.zerock.image.vo.ImageVO;

import com.webjjang.util.PageObject;

public interface ImageMapper {

	// 1. 이미지 겔러리 리스트
	public List<ImageVO> list(PageObject pageObject);
	
	// 1-1. 전체 데이터의 갯수 - 화면 표시용 - 페이지 네이션
	public Long getTotalRow(PageObject pageObject);
	
	// 2. 이미지 보기
	public ImageVO view(Long no);
	
	// 3. 이미지 등록
	public int write(ImageVO vo);
	
	// 4. 이미지 파일 수정
	public int updateFile(ImageVO vo);
	
	// 4-1. 이미지 정보 수정 - 제목 , 내용
	public int update(ImageVO vo);
	
	
}
