<%@page import="java.util.ArrayList"%>
<%@page import="business.com.user.service.SearchValueVO"%>
<%@page import="org.springframework.ui.Model"%>
<%@page import="com.fasterxml.jackson.annotation.JacksonInject.Value"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="business.com.user.service.IrinfoService"%>
<%@page import="business.com.user.service.InvestCodeVO"%>
<%@page import="business.com.user.service.IndustryCodeVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="tiles"  uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="app"    uri="/WEB-INF/tld/app.tld" %>
<%@ taglib prefix="f"      uri="/WEB-INF/tld/f.tld" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>

<%
	Object lstSize = request.getAttribute("lstSize");
	int totalCount = 0;
	int pageSize = 0;
	if(lstSize != null){
		totalCount = (int)lstSize;
	}
	
	if(totalCount%6 ==0){
		pageSize = totalCount/6;
	}else{
		pageSize = totalCount/6;
		pageSize++;			
	}
	
	
	Object cPage = request.getAttribute("cPage");
	int currentPage = 1;
	if(cPage == null){
		currentPage = 1;
	}else{
		if((int)cPage<0){
			currentPage = 1;
		}else{
			currentPage = (int)cPage;
		}
	}
	
	
	int startPage = currentPage-((currentPage%10)-1);
	int endPage = pageSize > startPage*20 ? startPage*20: pageSize;
	
	List<Integer> pNumlst = new ArrayList();
	for(int i=startPage; i<=endPage; i++){
		pNumlst.add(i);
	}
	
	request.setAttribute("pNumlst", pNumlst);
	
	SearchValueVO valVO = (SearchValueVO)session.getAttribute("searchVal");
	session.setAttribute("searchVal", valVO);
%>

<c:set var="pNumlst" value="<%=pNumlst %>" />
<c:set var="cPage" value="<%=currentPage %>" />
<c:set var="tPage" value="<%=totalCount %>" />
<c:set var="startPage" value="<%=startPage %>" />
<c:set var="endPage" value="<%=endPage %>" />
<c:set var="pageSize" value="<%=pageSize %>" />

<section class="contents">
	<div class="entire-irlist-wrap">
		<div class="list-wrap">
				<div class="font-wrap">
					<span class="listCount">${lstSize}</span><span>개의 농림수산식품경영체가 있습니다.</span>
				</div>
			<div class="type-wrap">
				<input type="button" class="type-card" value="카드형"/>
				<input type="button" class="type-list" value="리스트형"/>
				<select>
					<option value="name">가나다순</option>
					<option value="amount">투자희망금액순</option>
					<option value="amount">투자희망금액순</option>
				</select>
			</div>
			<div class="irList">
				<input type="hidden" id="type" name="type" value="card"/>
				<c:forEach var="ent" items="${irinfoLst}">
					<div id="irinfo${ent.ent_no}" class="ir-simple ">
						<div class="ir-simple-img godetail"><i class="fa fa-bookmark-o simpleBookmarkBtn" value="off"></i>
						 <span>경영체 등록 사진<br/>(기업CI/회사사진/대표상품사진</span>
						</div>
						<c:choose>
							<c:when test="${not empty ent.ent_nm}">
								<span class="ir-name godetail" >
									<c:out value="${ent.ent_nm}"></c:out>
								</span>
							</c:when>
							<c:otherwise>
								<span class="ir-name">
									<c:out value="경영체명"></c:out>
								</span>
							</c:otherwise>
						</c:choose> 
						<div>
							<c:choose>
								<c:when test="${not empty ent.induty_cd}">
									<span class="ir-industry ir-cate" name="${ent.induty_cd}">
										<c:choose>
											<c:when test="${ent.induty_cd == '1'}">
												<c:out value="농업"></c:out>
											</c:when>
											<c:when test="${ent.induty_cd == '2'}">
												<c:out value="수산업"></c:out>
											</c:when>
											<c:when test="${ent.induty_cd == '3'}">
												<c:out value="식품산업"></c:out>
											</c:when>
											<c:when test="${ent.induty_cd == '4'}">
												<c:out value="축산업"></c:out>
											</c:when>
											<c:when test="${ent.induty_cd == '5'}">
												<c:out value="소재,설비"></c:out>
											</c:when>
											<c:when test="${ent.induty_cd == '6'}">
												<c:out value="기타"></c:out>
											</c:when>
										</c:choose>
									</span>
								</c:when>
								<c:otherwise>
									
								</c:otherwise>
							</c:choose> 
							<c:forEach var="invtinfo" items="${invtinfoLst}">
									<c:if test="${ent.ent_no == invtinfo.ent_no}">
										<c:forEach var="invtcode" items="${invtlst}">
												<c:if test="${invtinfo.invt_rlm_cd == invtcode.invt_rlm_cd}">
													<span class="ir-invest ir-cate" name="${invtcode.invt_rlm_cd}">
														<c:out value="${invtcode.invt_rlm_nm }"></c:out>
													</span>
												</c:if>
										</c:forEach>
										<div>
											<c:if test="${not empty invtinfo.invt_amount}">
												<p class="invt-amount">
													투자 희망 금액 
													<c:if test="${fn:length((invtinfo.invt_amount).toString()) < 6}">
														<c:out value="${invtinfo.invt_amount / 1000 }"></c:out>백만
													</c:if>
													<c:if test="${fn:length((invtinfo.invt_amount).toString()) >= 6}">
														<c:out value="${invtinfo.invt_amount / 100000 }"></c:out>억
													</c:if>
												</p>
											</c:if>
										</div>
									</c:if>
							</c:forEach>
						</div>
					</div>
				</c:forEach>
			</div>
			<div id="paging-wrap">
				<c:if test="${startPage>10}">
					<a href="/usr/main/diaglist.do?page=${startPage-10}"><c:out value="이전"></c:out></a>
				</c:if>
				<c:forEach var="pNum"  items="${pNumlst}">
					<c:if test="${cPage != pNum}" >
						<a href="/usr/main/diaglist.do?page=${pNum}"><c:out value="${pNum}"></c:out></a>
					</c:if>
					<c:if test="${cPage == pNum}" >
						<span><c:out value="${pNum}"></c:out></span>
					</c:if>
				</c:forEach>
				<c:if test="${pageSize>endpage}">
					<a href="/usr/main/diaglist.do?page=${startPage+10}"><c:out value="다음"></c:out></a>
				</c:if>
			</div>
		</div>
	</div>
</section>

<script>
$(document).ready(function(){
	
	console.log("startPage:"+${startPage});
	console.log("endPage:"+${endPage});
	
	let type = $('#type').val();
	if(type == 'card'){
		$('.type-card').css({'background':'#2E68FF','color':'white'});
		$('.type-list').css({'background':'none','color':'none'});
		
	}else if(type == 'list'){
		$('.type-list').css({'background':'#2E68FF','color':'white'});
		$('.type-card').css({'background':'none','color':'none'});
	}
})

$('.simpleBookmarkBtn').click(function(e){
	if($(e.target).attr('value')=="off"){
		$(e.target).attr('value','on');
		$(e.target).removeClass('fa-bookmark-o').addClass('fa-bookmark');
		$(e.target).css({"color":"#2E68FF"})
	}else if($(e.target).attr('value')=="on"){
		$(e.target).attr('value','off');
		$(e.target).removeClass('fa-bookmark').addClass('fa-bookmark-o');
		$(e.target).css({"color":"grey"})
	}
})

$('.godetail').click(function(){
	let entNum = $(this).parent().attr('id');
		entNum = entNum.substr(6);
	location.href = "/usr/main/irinfo.do?entNum="+entNum
})
</script>