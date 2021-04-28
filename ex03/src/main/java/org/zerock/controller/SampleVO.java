package org.zerock.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// 생성자를 이용한 데이터 채우기 -> SampleVO(Integer mno, String firstName, String lastName)
@AllArgsConstructor
// 기본생성자 생성 -> SampleVO()
@NoArgsConstructor
public class SampleVO {

	private Integer mno;
	private String firstName;
	private String lastName;
	
}
