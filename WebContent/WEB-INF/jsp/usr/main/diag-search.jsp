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

<section class="contents">
	<div class="search-wrap">
		<div class="form-wrap">
		<span class="search-label">농림수산식품경영체 검색</span>
		<form id="searchForm" action="/usr/main/diaglist.do" method="post">
			<input type="text" value="경영체명, 분야명, 대표자를 검색하세요." name="searchVal" class="searchVal"/>
			<div class="smart-wrap">
				<div class="toggle-wrap">
			<div class="smartsearch-font">
				<span>스마트검색</span>
			</div>
					<div class="font-wrap">
						<span>투자분야</span>
					</div>
					<div class="checkbox-wrap">
						<input type="checkbox" id="invt-0" class ="invt-check" value="0"/><label for="invt-0">전체</label>
						<input type="checkbox" id="invt-1" class ="invt-check" value="1"/><label for="invt-1">수산일반</label>
						<input type="checkbox" id="invt-2" class ="invt-check" value="2"/><label for="invt-2">수산벤처창업</label>
						<input type="checkbox" id="invt-3" class ="invt-check" value="3"/><label for="invt-3">스마트양식</label>
						<input type="checkbox" id="invt-4" class ="invt-check" value="4"/><label for="invt-4">농림축산식품일반</label>
						<input type="checkbox" id="invt-5" class ="invt-check" value="5"/><label for="invt-5">수출</label>
						<input type="checkbox" id="invt-6" class ="invt-check" value="6"/><label for="invt-6">(20~22)영파머스</label>
						<input type="checkbox" id="invt-7" class ="invt-check" value="7"/><label for="invt-7">(23)영파머스</label>
						<input type="checkbox" id="invt-8" class ="invt-check" value="8"/><label for="invt-8">푸드테크</label>
						<input type="checkbox" id="invt-9" class ="invt-check" value="9"/><label for="invt-9">(20~21)농식품벤처</label>
						<input type="checkbox" id="invt-10" class ="invt-check" value="10"/><label for="invt-10">(22~23)농식품벤처</label>
						<input type="checkbox" id="invt-11" class ="invt-check" value="11"/><label for="invt-11">(20~21)마이크로</label>
						<input type="checkbox" id="invt-12" class ="invt-check" value="12"/><label for="invt-12">(22~23)마이크로</label>
						<input type="checkbox" id="invt-13" class ="invt-check" value="13"/><label for="invt-13">(21~22)스마트농업</label>
						<input type="checkbox" id="invt-14" class ="invt-check" value="14"/><label for="invt-14">(23)스마트농업</label>
						<input type="checkbox" id="invt-15" class ="invt-check" value="15"/><label for="invt-15">경북지역특성화</label>
						<input type="checkbox" id="invt-16" class ="invt-check" value="16"/><label for="invt-16">창업보육</label>
						<input type="checkbox" id="invt-17" class ="invt-check" value="17"/><label for="invt-17">(21~22)그린바이오</label>
						<input type="checkbox" id="invt-18" class ="invt-check" value="18"/><label for="invt-18">(23)그린바이오</label>
						<input type="checkbox" id="invt-19" class ="invt-check" value="19"/><label for="invt-19">징검다리</label>
						<input type="checkbox" id="invt-20" class ="invt-check" value="20"/><label for="invt-20">직접투자</label>
						<input type="hidden" name="investList" class="investList" value="0"/>
					</div>
					<div class="font-wrap">
						<span>사업분야</span>
					</div>
					<div class="checkbox-wrap">
						<input type="checkbox" id="ids-0" class ="indust-check" value="0"/><label for="ids-0">전체선택</label>
						<input type="checkbox" id="ids-1" class ="indust-check" value="1"/><label for="ids-1">농업</label>
						<input type="checkbox" id="ids-2" class ="indust-check" value="2"/><label for="ids-2">수산업</label>
						<input type="checkbox" id="ids-3" class ="indust-check" value="3"/><label for="ids-3">식품산업</label>
						<input type="checkbox" id="ids-4" class ="indust-check" value="4"/><label for="ids-4">축산업</label>
						<input type="checkbox" id="ids-5" class ="indust-check" value="5"/><label for="ids-5">소재및 생산설비 산업</label>
						<input type="checkbox" id="ids-6" class ="indust-check" value="6"/><label for="ids-6">기타 농림수산식품 관련 산업</label>
						<input type="hidden" name="industryList" class="industryList" value="0"/>
					</div>
					<div class="font-wrap">
						<span>투자희망금액</span>
						<span style="float:right">정부지원사업 이력</span>
					</div>
					<div style="width:780px; display:flex"> 
						<div class="invt-amount-wrap">
							<input class="amount" type="range" id="amount-left" name="amountMin" min="0" max="9999999" value="0" step="1"/>
							<input class="amount" type="range" id="amount-right" name="amountMax" min="1" max="10000000" value="10000000" step="1"/>
							<div class="fakeamount">
								<div class="range">
									<div id="rangeleft" class="rangeleft rangepoint"><label for="rangeleft" class="leftval fakeval">min</label></div>
									<div id="rangeright" class="rangeright rangepoint"><label for="rangeleft" class="rightval fakeval">max</label></div>
								</div>					
							</div>
						</div>
						<div class="gvmentSpHst-wrap">
							<input id="gvmentSpHstAll" type="checkbox"/><label for="gvmentSpHstAll">전체(미보유포함)</label>
							<input id="gvmentSpHstHas" type="checkbox"/><label for="gvmentSpHstHas">보유기업</label>
						</div>
					</div>
					<hr/>
					<input type="button" class="smartToggleBtn" value="스마트 검색 닫기"/>
				</div>
			</div>
			<div class="searchBtn-wrap">
				<input type="button" id="resetBtn" value="초기화"/>
				<input type="submit" id="formBtn" value="검 색"/>
			</div>
		</form>
		</div>
	</div>
</section>
<script>

$('.searchVal').focusin(function(){
	let val = $('.searchVal').val();
	if(val == "경영체명, 분야명, 대표자를 검색하세요."){
		$('.searchVal').val("");
	}
})
$('.searchVal').focusout(function(){
	let val = $('.searchVal').val();
	if(val == ""){
		$('.searchVal').val("경영체명, 분야명, 대표자를 검색하세요.");
	}
})


$('.smartToggleBtn').click(function(){
	let btnVal = $('.smartToggleBtn').val();
	if(btnVal == "스마트 검색 닫기"){
		$('.smart-wrap').css({'height':'60px'});
		$('.toggle-wrap').css({'top':'-530px'});
		$('.smartToggleBtn').val("스마트 검색 열기");
	}else if(btnVal == "스마트 검색 열기"){
		$('.smart-wrap').css({'height':'600px'});
		$('.toggle-wrap').css({'top':'0px'});
		$('.smartToggleBtn').val("스마트 검색 닫기");
	}
})



$(document).ready(function(){
	$('#invt-0').prop('checked',true);
	$('#ids-0').prop('checked',true);
})

$('.invt-check').click(function(){
	if($(this).val() != 0){
		if($('#invt-0').is(':checked')){
			$('#invt-0').prop('checked',false);
			
			
		}
	}else{
		$('.invt-check').prop('checked',false);
		$(this).prop('checked',true);
		$('.investList').val('0')
		
	}
	
	let invtlst = [];
	$('.invt-check:checked').each(function(){
		invtlst.push($(this).val());
	})
	$('.investList').val(invtlst);
	console.log(invtlst);
	console.log($('.investList').val());
})

$('.indust-check').click(function(){
	if($(this).val() != 0){
		if($('#ids-0').is(':checked')){
			$('#ids-0').prop('checked',false);
		}
	}else{
		$('.indust-check').prop('checked',false);
		$(this).prop('checked',true);
	}
	let indtlst = [];
	$('.indust-check:checked').each(function(){
		indtlst.push($(this).val());
	})
	$('.industryList').val(indtlst);
	console.log(indtlst);
	console.log($('.industryList').val());
})

$('.rangepoint').mousedown(function() {
	
	let currleft = Number($('#amount-left').val())/25000;
	let currright = Number($('#amount-right').val())/25000;
    $('.amount').trigger('mousedown');
    let getLocation = () => {
    	  if($(this).hasClass('rangeleft')){
    		    return 'left';
    		  } else if($(this).hasClass('rangeright')){
    		    return 'right';
    		  }
    		}	
    
    let location = getLocation();
    $(document).mousemove(function(e){
    	let track = $('.fakeamount').offset();
    	let trackLeft = Number(Math.floor(track.left));
    	let xval = Number(e.clientX) - trackLeft;
    	let xval2 = xval * 25000;
    	let xvalt = xval2.toLocaleString('ko-KR');
    	
    	if(location == 'left'){
	    	if(xval < currright && -1<xval){
	    		console.log(xval);
				$('.rangeleft').css({"left":xval+"px"})
    			$('#amount-left').val(xval2);
				$('.leftval').text(xvalt);
				if(xval2 == 0){
					$('.leftval').text("min");	
				}
			}	
    	}
    	else if(location == 'right'){
    		if(currleft<xval && xval<401){
				$('.rangeright').css({"left":xval+"px"})
    			$('#amount-right').val(xval2);
				$('.rightval').text(xvalt);
				if(xval2 == 10000000){
					$('.rightval').text("max");	
				}
			}
    	}
    })
  });


$(document).mouseup(function() {
  $(document).off('mousemove');
});

$('#resetBtn').click(function(){
	location.reload();
})
</script>