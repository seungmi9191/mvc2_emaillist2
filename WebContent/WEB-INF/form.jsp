<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>메일 리스트 가입</h1>
	<p>
		메일 리스트에 가입하려면,<br> 아래 항목을 기입하고 submit 버튼을 클릭하세요.
	</p>
	<!-- 컨트롤러에게 요청해야하기 때문에 주소 형식을 바꿔야한다. 뒤아 &값들은 밑에 form애들이 자동으로 달려감-->
	<form action="/emaillist2/el" method="get">
		<input type="hidden" name="a" value="insert"><br> <!-- 주소 구분을 위해 히든 사용 -->
		Last name(성): <input type="text" name="ln" value=""><br>
		First name(이름): <input type="text" name="fn" value=""><br>
		Email address: <input type="text" name="email" value=""><br>
		<input type="submit" value="등록">
	</form>
	<br>
	<p>
		<a href="/emaillist2/el">리스트 바로가기</a>
	</p>
</body>
</html>