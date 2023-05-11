<%--
******************************************************************************************
*** 파일명 : login.jsp
*** 설명   : 사용자 로그인 화면
***
*** -----------------------------    Modified Log   --------------------------------------
*** 버전        수정일자            수정자                  내용
*** --------------------------------------------------------------------------------------
*** 1.0         2020-09-28          ntarget                 First Coding.
*** 1.0         2021-11-08          LSH                     식별아이디 인증 추가
*** 1.0         2022-01-05          LSH                     시스템분리 (사용자/관리자)
******************************************************************************************
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="tiles"  uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="app"    uri="/WEB-INF/tld/app.tld" %>
<%@ taglib prefix="f"      uri="/WEB-INF/tld/f.tld" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><spring:message code="title.sysname"/></title>
    
    <!-- Tiles RESOURCE -->
    <tiles:insertAttribute name="resource"/>

	<script type="text/javascript" src="<c:url value='/js/com/comm_login.js?version=${ver}'/>"></script>

</head>
<body>
<%-- 사용자 로그인 START ================================================== --%>
    <!-- Tiles HEADER -->
    <tiles:insertAttribute name="header"/>

	<section class="contents">
		<div class="contents-wrap">
			<h3>로그인</h3>
			<div class="login-form">
				<form id="loginForm" name="loginForm" method="post">
					<input type="text"     id="username" name="username" maxlength="50" class="app-w300" placeholder="이메일을 입력해 주세요."/><br>
					<input type="password" id="password" name="password" maxlength="30" class="app-w300" placeholder="비밀번호를 입력해 주세요."/>
					<button type="button"  id="btnLogin">로그인</button>
				</form>
			</div>
			<div class="h20">
				<a href="#void" class="btnOauth" data-code="naver" data-url="${naverAuthUrl}"><img src="<c:url value='/images/oauth/btn_login_naver.png'/>" width="183"/></a>
				<a href="#void" class="btnOauth" data-code="kakao" data-url="${kakaoAuthUrl}"><img src="<c:url value='/images/oauth/btn_login_kakao.png'/>" width="183"/></a>
			</div>
			<div class="login-menu">
				<a href="#void" id="btnFind">아이디/비밀번호 찾기</a>
				<a href="#void" id="btnJoin">회원가입</a>
			</div>
		</div>
	</section>
	
	<!-- Tiles FOOTER -->
	<tiles:insertAttribute name="footer" />
	
<%-- 사용자 로그인 END ==================================================== --%>
</body>
</html>
