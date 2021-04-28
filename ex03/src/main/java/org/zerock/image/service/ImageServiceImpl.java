package org.zerock.image.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zerock.image.mapper.ImageMapper;
import org.zerock.image.vo.ImageVO;

import com.webjjang.util.PageObject;

@Service
@Qualifier("isi")
public class ImageServiceImpl implements ImageService{

	@Inject // 자동 DI 적용
	private ImageMapper mapper;
	
	@Override
	public List<ImageVO> list(PageObject pageObject) throws Exception {
		// TODO Auto-generated method stub
		// pageObject에 전체 데이터를 받아서 셋팅 해준다. -> 화면의 페이지 네이션 때문에
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		
		return mapper.list(pageObject);
	}

	@Override
	public ImageVO view(Long no) throws Exception {
		// TODO Auto-generated method stub
		return mapper.view(no);
	}

	@Override
	public int write(ImageVO vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.write(vo);
	}

	@Override
	public int updateFile(ImageVO vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.updateFile(vo);
	}

	@Override
	public int update(ImageVO vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}

}
