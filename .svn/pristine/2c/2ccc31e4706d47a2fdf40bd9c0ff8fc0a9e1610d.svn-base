<%--
*******************************************************************************
***	명칭: main.jsp
***	설명: 사용자시스템 메인 페이지
***
***	-----------------------------    Modified Log   ---------------------------
***	버전     수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***	1.0      2023.03.10      LSH           First Coding.
*******************************************************************************
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

<%-- ############################# 내용 (시작) ############################# --%>

<section class="contents">
	<div class="contents-wrap">

		<table class="sample-table">
			<tr>
				<td>
					<a href="#void" class="btn btn-primary" id="btnJoin">회원가입예제</a><br>
				</td>
			</tr>
			<tr>
				<td>
					<%-- PDF다운로드예제 --%>
					<form id="downForm" name="downForm" method="post" onsubmit="return false;">
						<table>
							<colgroup>
								<col width="20%"/>
								<col width="80%"/>
							</colgroup>
							<tr>
								<th colspan="2"># PDF 다운로드 예제 #</th>
							</tr>
							<tr>
								<td>템플릿</td>
								<td><input type="text" name="templateName" value="Sample"/>
								</td>
							</tr>
							<tr>
								<td>다운로드</td>
								<td><input type="text" name="downloadName" value="PDF샘플"/>
								</td>
							</tr>
						</table>
						<a href="#void" class="btn btn-primary" id="btnDown">PDF샘플</a>
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<%-- 메일보내기예제 --%>
					<form id="mailForm" name="mailForm" method="post" onsubmit="return false;">
						<table>
							<colgroup>
								<col width="20%"/>
								<col width="80%"/>
							</colgroup>
							<tr>
								<th colspan="2"># 메일보내기 예제 #</th>
							</tr>
							<tr>
								<td>보내는사람</td>
								<td><input type="text" name="fromName"    size="30" placeholder="이름을 입력하세요." value=""/>
								    <input type="text" name="fromAddress" size="50" placeholder="이메일을 입력하세요." value=""/>
								</td>
							</tr>
							<tr>
								<td>받는사람</td>
								<td><input type="text" name="toName"     size="30" placeholder="이름을 입력하세요." value=""/>
								    <input type="text" name="toAddress"  size="50" placeholder="이메일을 입력하세요." value=""/>
								</td>
							</tr>
							<tr>
								<td>제목</td>
								<td><input type="text" name="subject" size="100" placeholder="제목을 입력하세요." value=""/>
								</td>
							</tr>
							<tr>
								<td>내용</td>
								<td><textarea name="content" rows="10" cols="50">메일테스트 내용입니다.</textarea><br>
									<label>HTML여부</label><input type="checkbox" name="useHtml" value="1" />
								</td>
							</tr>
							<tr>
								<td>템플릿</td>
								<td><input type="text" name="template" value="template.html"/><br>
									<label>템플릿여부</label><input type="checkbox" name="useTemplate" value="1" />
								</td>
							</tr>
						</table>
						<a href="#void" class="btn btn-primary" id="btnMail">메일보내기</a><br>
					</form>
				</td>
			</tr>
		</table>
	</div>
</section>

<%-- ############################# 내용 (종료) ############################# --%>
