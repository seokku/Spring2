<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>

<!-- bootstrap 라이브러리 등록 - CDN 방식 : sitemesh에서 decorator.jsp에서 한꺼번에 해결-->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script type="text/javascript">
$(function(){
	$(".cancelBackBtn").click(function(){
		history.back();
	});
});
</script>

</head>
<body>
<div class="container">
<h1>게시판 글쓰기 폼</h1>
  <form action="write.do" method="post">
  	<input name="perPageNum" type="hidden" value="${param.perPageNum }">
    <div class="form-group">
      <label for="title">제목:</label>
      <!-- required : 필수 입력, placeholder : 입력의 초기 안내,
      	   pattern : 정규표현식으로 유효성 검사, title : 패턴이 맞이 않을 때 메시지 -->
      <input name="title" type="text" class="form-control" id="title"
      pattern=".{4,100}" maxlength="100" required="required" 
      title="4자이상 100자 이하 입력" placeholder="4자이상 100자 이하 입력">
    </div>
	<div class="form-group">
	  <label for="content">내용:</label>
	  <textarea name="content" class="form-control" rows="5"
	  placeholder="내용은 4자이상 700자 이하 입력" 
	  id="content"></textarea>
	</div>
    <div class="form-group">
      <label for="writer">작성자:</label>
      <input name="writer" type="text" class="form-control" id="writer"
      required="required" pattern="[A-Za-z가-힣][0-9A-Za-z가-힣]{1,9}"
      placeholder="이름은 2자부터 10자까지"
      title="이름은 2자부터 10자까지 첫글자를 숫자가 올 수 없다." >
    </div>
    <div class="form-group">
      <label for="pw">비밀번호:</label>
      <input name="pw" type="text" class="form-control" id="pw" 
      pattern="[^가-힣ㄱ-ㅎ]{4,20}" required="required"
      title="4-20자. 한글은 입력할 수 없습니다.">
    </div>
    <button>등록</button>
    <button type="reset">새로입력</button>
    <button type="button" class="cancelBackBtn">취소</button>
  </form>

</div>
</body>
</html>