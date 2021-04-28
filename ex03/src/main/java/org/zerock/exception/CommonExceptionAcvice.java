package org.zerock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

// Spring 예외 처리 객체
// servlet-context.xml component-scan에 패키지가 포함되어 있어야만다.
@ControllerAdvice
@Log4j
public class CommonExceptionAcvice {

	// 어떤 예외를 잡을 것인지 선언해 놓는다. - Exception : 500 전체
	@ExceptionHandler(Exception.class)
	public String except(Exception ex, Model model) {
		
		log.error("Exception..." + ex.getMessage());
		model.addAttribute("exception", ex);
		log.error(model);
		
		return "error_page";
	}
	
	// Exception 외의 예외 
	@ExceptionHandler(NoHandlerFoundException.class)
	// 404 오류
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex) {
		
		log.error("Exception..." + ex.getMessage());
		
		return "custom404";
	}
	
}
