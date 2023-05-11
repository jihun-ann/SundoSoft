<%--
*******************************************************************************
***    명칭: openJoin.jsp
***    설명: 회원가입 - 이용약관동의
***
*** -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2023.03.14    LSH        First Coding.
*******************************************************************************
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/app.tld"%>
<%@ taglib prefix="f" uri="/WEB-INF/tld/f.tld"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%-- ############################# 내용 (시작) ############################# --%>

<section class="contents">
	<form id="joinForm" name="joinForm" method="post">
		<input type="hidden" id="userCd" name="userCd" value="${model.userCd}" />
		<div class="contents-wrap">
			<h3>회원가입 - 약관동의</h3>
			<div class="h50"></div>
			<div class="join-wrap">
				<p>이용약관</p>
				<div class="h10"></div>
				<div class="inputWrap">
					<input type="radio" id="agreYn1" name="agreYn" value="Y" />
					<label for="agreYn1">동의합니다</label>
					<input type="radio" id="agreYn2" name="agreYn" value="N" />
					<label for="agreYn2">동의하지 않습니다</label>
				</div>

				<div class="h50"></div>
				<p>개인정보 수집 및 이용 동의</p>
				<div class="h10"></div>
				<div class="inputWrap">
					<input type="radio" id="infoYn1" name="infoYn" value="Y" />
					<label for="infoYn1">동의합니다</label>
					<input type="radio" id="infoYn2" name="infoYn" value="N" />
					<label for="infoYn2">동의하지 않습니다</label>
				</div>
			</div>
			<div>
				<a href="#void" class="btnStep" data-code="prev">이전단계</a>
				<a href="#void" class="btnStep" data-code="next">다음단계</a>
			</div>
		</div>
	</form>
</section>

<%-- ############################# 내용 (종료) ############################# --%>
