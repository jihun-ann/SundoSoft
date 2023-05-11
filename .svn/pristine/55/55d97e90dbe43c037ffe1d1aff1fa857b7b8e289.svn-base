<%-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**  @(#)500.jsp
**  @author     : ntarget
**  @version    : 1.0
**  @since      : 2021/02/21
**                2022/01/12 LSH error controller 적용
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ --%>
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

</head>
<body>
<%-- 오류 메시지 START ================================================== --%>
    <!-- Tiles HEADER -->
    <tiles:insertAttribute name="header"/>
	
	<section class="contents">
		<div class="contents-wrap">
		
			<c:if test="${ exceptionMessage == null }">
				<div class="app-error-usr">
					<div class="error-title">
						<h2>${errorTitle}</h2>
					</div>
					
					<div class="error-wrap">
						<p class="exception_message">
							${errorMessage}
						</p>
					</div>
				</div>
			</c:if>
			
			<c:if test="${ exceptionMessage != null }">
				<div class="app-error-usr">
					<div class="error-title">
						<h2>안내 메시지</h2>
					</div>
					
					<div class="error-wrap">
						<p class="exception_message">
							<span class="exception_detail_message">${exceptionMessage}</span>
						</p>
					</div>
				</div>
			</c:if>
			
			<div>
				<a href="javascript:history.back();" class="btn btn-primary" title="이전페이지로 이동">이전페이지로 이동</a>
			</div>
			
		</div>
	</section>

	<!-- Tiles FOOTER -->
	<tiles:insertAttribute name="footer" />
	
<%-- 오류 메시지 END ==================================================== --%>
</body>
</html>

