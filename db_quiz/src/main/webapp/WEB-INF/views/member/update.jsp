<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update</title>
<script src="resources/check.js"></script>
</head>
<body>
	<c:import url="/header"/>
	<div align="center">
		<h1>회원 수정</h1>
		<table>
		<tr><td>
			<form action="update" method="post" id="f">
				<input type="text" name="id" id="id" value="${sessionScope.id }" readonly="readonly"><br>
				<input type="password" name="pw" placeholder="비밀번호" id="pw"><br>
				<input type="password" name="confirm" placeholder="비밀번호 확인 " id="confirm" onchange="pwCheck()">
				<label id="label">(*필수 체크)</label><br>
				<input type="text" name="username" id="username" value="${sessionScope.username }" ><br>
				<input type="text" name="address" value="${sessionScope.address }" ><br>
				<input type="text" name="mobile" value="${sessionScope.mobile }" ><br>
				<input type="button" value="회원수정" onclick="allCheck()"><br>
			</form>
		</td></tr>
		</table>
	</div>
	<c:import url="/footer"/>
</body>
</html>

