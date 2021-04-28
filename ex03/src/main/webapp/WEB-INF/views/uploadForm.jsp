<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드</title>
</head>
<body>
<h1>파일 업로드</h1>
<form action="uploadFormAction" method="post" enctype="multipart/form-data">
<!-- multiple="multiple" : 여러개 선택. 없으면 한개 선택 -->
첨부파일 <input type="file" name="uploadFile" multiple="multiple"><br>
<button>전송</button>
</form>
</body>
</html>