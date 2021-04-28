package org.zerock.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zerock.board.mapper.BoardMapper;
import org.zerock.board.vo.BoardVO;

import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

// 자동 생성
@Service
// 타입이 같은 객체들이 많은 경우 선택할 수 있도록 지정
@Qualifier("bsi")
@Log4j
public class BoardServiceImpl implements BoardService {

	// mapper를 이용해서 DB처리해야한다.
	// 자동 DI 적용 - @Setter : lombok, @Autowired : spring, @Inject : java
	@Inject
	private BoardMapper mapper;
	
	@Override
	public List<BoardVO> list(PageObject pageObject) throws Exception {
		// TODO Auto-generated method stub
		// 페이지 처리를 위한 전체 데이터 가져오기 - 추후(개발 완료)
		// startRow와 endRow가 계산이 된다.
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		log.info("pageObject : " + pageObject);
		return mapper.list(pageObject);
	}

	@Override
	public BoardVO view(Long no, int inc) throws Exception {
		// TODO Auto-generated method stub
		// list -> view : inc = 1 - 조회수 1증가
		if(inc == 1)
			mapper.increase(no);
		return mapper.view(no);
	}

	@Override
	public int write(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.write(vo);
	}

	@Override
	public int update(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}

	@Override
	public int delete(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.delete(vo);
	}

}
