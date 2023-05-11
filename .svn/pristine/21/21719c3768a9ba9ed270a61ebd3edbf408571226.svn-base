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
	<form id="joinForm" name="joinForm" method="post" enctype="multipart/form-data" onsubmit="return false;">
		<input type="hidden" id="userCd" name="userCd" value="${model.userCd}" />
		<div class="contents-wrap">
			<h3>회원가입 - 기업정보입력</h3>
			<div class="h50"></div>
			<div>
				<h4>기업정보입력</h4>
				<div>
					<table>
						<colgroup>
							<col style="width: 17%;">
							<col style="width: 83%;">
						</colgroup>
						<tr>
							<td class="must"><span>사업자번호</span></td>
							<td><input type="text" id="bizNo1" name="bizNo1" maxlength="3" style="width:60px">
							    <input type="text" id="bizNo2" name="bizNo2" maxlength="2" style="width:40px">
							    <input type="text" id="bizNo3" name="bizNo3" maxlength="5" style="width:80px">
							    <input type="hidden" id="bizNo" name="bizNo">
								<button class="app-small-btn" id="btnBizNo">조회</button>
								<span class="app-error"></span>
							</td>
						</tr>
						<tr>
							<td class="must"><span>회사명</span></td>
							<td><input type="text" id="bizNm" name="bizNm" class="w300" maxlength="30" placeholder="회사명" readonly="readonly"><span class="app-error"></span></td>
						</tr>
						<tr>
							<td class="must"><span>대표자</span></td>
							<td><input type="text" id="bizOwnr" name="bizOwnr" class="w300" maxlength="20" placeholder="대표자" readonly="readonly"><span class="app-error"></span></td>
						</tr>
						<tr>
							<td class="must"><span>설립일</span></td>
							<td><input type="text" id="bizDate" name="bizDate" class="w300" maxlength="10" placeholder="설립일" readonly="readonly"><span class="app-error"></span></td>
						</tr>
						<tr>
							<td class="must"><span>대표번호</span></td>
							<td><input type="text" id="bizPhone" name="bizPhone" class="w300" maxlength="13" placeholder="대표번호" readonly="readonly"><span class="app-error"></span></td>
						</tr>
						<tr>
							<td class="must"><span>기업유형</span></td>
							<td><select id="bizType" name="bizType" class="w300"></select><span class="app-error"></span></td>
						</tr>
						<tr>
							<td class="must"><span>사업자등록증</span></td>
							<td><div class="file_wrap app-small">
									<input type="hidden" name="docuNo"   value="SAMPLE" /><%-- 연결문서번호 --%>
									<input type="hidden" name="fileType" value="SAMPLE" /><%-- 파일타입 --%>
									<input type="hidden" name="filePath" value=""       /><%-- 기저장된 파일경로 --%>
									<input type="hidden" name="fileNo"   value=""       /><%-- 기저장된 파일번호(sn) --%>
									<input type="hidden" name="needYn"   value="Y"      /><%-- 파일의 필수체크여부 --%>
									<input type="hidden" name="fileYn"   value="N"      /><%-- 파일의 업로드여부 --%>
									<input type="text"   name="fileName" value="" class="input_text" title="filebox" readonly /><%-- 파일명 표시박스 --%>
									<div class="box_wrap">
										<input type="file" name="upfile" class="input_file">
										<button class="btn_file"></button>
									</div>
								</div>
							    ※ 100MB 이내 PDF파일 업로드 가능
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div>
				<h4>기업상세정보입력 + </h4>
			</div>
			<div>
				<a href="#void" class="btnStep" data-code="prev">이전단계</a>
				<a href="#void" class="btnStep" data-code="next">다음단계</a>
			</div>
		</div>
	</form>
</section>

<%-- ############################# 내용 (종료) ############################# --%>
