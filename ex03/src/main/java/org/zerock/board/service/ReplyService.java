package org.zerock.board.service;

import java.util.List;

import org.zerock.board.vo.ReplyVO;

import com.webjjang.util.PageObject;

public interface ReplyService {

	// 1. 댓글 리스트 - 페이지 처리 - Mybatis의 메서드들은 파라메터의 갯수를 한개만 받도록 작성되어 있다.
	public List<ReplyVO> list(PageObject pageObject, Long no) throws Exception; 
	
	// 2. 댓글보기 생략 - 리스트에 다 표시가 되므로
	
	// 3. 댓글쓰기
	public int write(ReplyVO vo) throws Exception;
	
	// 4. 댓글 수정
	public int update(ReplyVO vo) throws Exception;
	
	// 5. 댓글 삭제
	public int delete(ReplyVO vo) throws Exception;
	
}
