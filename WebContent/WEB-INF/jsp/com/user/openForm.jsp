<%--
*******************************************************************************
***    명칭: openForm.jsp
***    설명: 회원가입 - 정보입력
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
	<form id="joinForm" name="joinForm" method="post" onsubmit="return false;">
		<input type="hidden" id="userCd" name="userCd" value="${model.userCd}" />
		<div class="contents-wrap">
			<h3>회원가입 - 기본정보입력</h3>
			<div class="h50"></div>
			<div>
				<h4>간편회원가입</h4>
				<div class="h20">
					<a href="#void" class="btnOauth" data-code="naver" data-url="${naverAuthUrl}"><img src="<c:url value='/images/oauth/btn_login_naver.png'/>" width="183"/></a>
					<a href="#void" class="btnOauth" data-code="kakao" data-url="${kakaoAuthUrl}"><img src="<c:url value='/images/oauth/btn_login_kakao.png'/>" width="183"/></a>
				</div>
				<h4>기본정보입력</h4>
				<div>
					<table>
						<colgroup>
							<col style="width: 17%;">
							<col style="width: 83%;">
						</colgroup>
						<tr>
							<td class="must"><span>성명</span></td>
							<td><input type="text" id="userNm" name="userNm" placeholder="본인인증을 진행해 주세요." maxlength="10">
								본인인증여부: <input type="text" id="certYn" name="certYn" value="N"><%-- 본인인증 완료여부 --%>
								<button class="app-small-btn" id="btnCertify">본인인증</button>
								<span class="app-error"></span>
							</td>
						</tr>
						<tr>
							<td class="must"><span>휴대폰번호</span></td>
							<td><input type="text" id="mobile" name="mobile" placeholder="본인인증을 진행해 주세요." maxlength="15"/><span class="app-error"></span></td>
						</tr>
						<tr>
							<td class="must"><span>아이디</span></td>
							<td><input type="text" id="userId" name="userId" placeholder="이메일을 입력해 주세요." maxlength="50"><span class="app-error"></span></td>
						</tr>
						<tr>
							<td class="must"><span>비밀번호</span></td>
							<td><input type="password" id="pswd" name="pswd" placeholder="비밀번호를 입력해 주세요." maxlength="30"><span class="app-error"></span>
								<div> ※ 8자 이상 영대소문자 + 숫자 + 특수문자 조합</div>
							</td>
						</tr>
						<tr>
							<td class="must"><span>비밀번호 확인</span></td>
							<td><input type="password" id="pswdCnfm" name="pswdCnfm" placeholder="비밀번호를 다시 한번 입력해 주세요." maxlength="30"><span class="app-error"></span></td>
						</tr>
					</table>
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
