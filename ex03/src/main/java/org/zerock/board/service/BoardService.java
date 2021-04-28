package org.zerock.board.service;

import java.util.List;

import org.zerock.board.vo.BoardVO;

import com.webjjang.util.PageObject;

public interface BoardService {

	//1. 게시판 리스트
	//   [전체데이터 갯수 +] 리스트 데이터
	public List<BoardVO> list(PageObject pageObject) throws Exception;
	
	//2. 게시판 글보기
	//   조회수 1증가(리스트->글보기) + 글보기 데이터
	public BoardVO view(Long no, int inc) throws Exception;
	
	//3. 게시판 글쓰기
	public int write(BoardVO vo) throws Exception;
	
	//4. 게시판 글수정
	public int update(BoardVO vo) throws Exception;
	
	//5. 게시판 글삭제
	public int delete(BoardVO vo) throws Exception;
	
	
}
