<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
##========================================================================================
## 화면 상단 HTML 공통 영역
##
##========================================================================================
 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/app.tld"%>
<%@ taglib prefix="f" uri="/WEB-INF/tld/f.tld"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com"%>

<%@ page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="common.user.UserInfo"%>
<%@ page import="common.util.CommUtils"%>
<%@ page import="common.util.properties.ApplicationProperty"%>
<%
	// UserInfo 세션
ServletContext servletContext = this.getServletContext();
WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
UserInfo userInfo = (UserInfo) wac.getBean("userInfo");

String cookLatestTime      = ApplicationProperty.get("COOK.LATEST.TIME");
String cookExpireTime      = ApplicationProperty.get("COOK.EXPIRE.TIME");
%>
<!-- 2020.10.04 세션시간관리[ntarget] -->
<script type="text/javascript" >
    var isLogin           = <%=userInfo.isLogin()%>;
    var COOK_LATEST_TIME  = '<%=cookLatestTime%>';
    var COOK_EXPIRE_TIME  = '<%=cookExpireTime%>';
</script>
<script type="text/javascript" src="<c:url value='/js/com/comm_session.js?version=${ver}'/>"></script>

<c:set var="userInfo" value="<%=userInfo%>" />

<header>
	<div class="header">
		<h1 class="logo">
			<a href="<c:url value='/usr/main/diagsearch.do'/>" title="HOME"><spring:message code="title.sysname"/></a>
		</h1>
		<aside>
			<ul>
				<c:if test="${userInfo.login}">
					<li><a href="<c:url value='/com/common/logout.do'/>">로그아웃</a></li>
				</c:if>
				<c:if test="${!userInfo.login}">
					<li><a href="<c:url value='/com/common/login.do'/>">로그인</a></li>
					<li><a href="<c:url value='/com/user/openJoin.do'/>">회원가입</a></li>
				</c:if>
			</ul>
		</aside>
	</div>
</header>
