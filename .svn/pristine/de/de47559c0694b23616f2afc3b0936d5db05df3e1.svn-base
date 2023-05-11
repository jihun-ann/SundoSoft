/**
******************************************************************************************
*** 파일명    : main.js
*** 설명      : 사용자 메인 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2023.03.14              LSH
******************************************************************************************
**/
$(function() {

	// PDF샘플 다운로드
	$('#btnDown').bind('click', function() {
        $.formUtil.submitForm(
             getUrl('/usr/sample/downSamplePDF.do'),
             {formId : "downForm"}
         );
         return false;
	});
	// 회원가입 이동
	$('#btnJoin').bind('click', function() {
		goUrl( getUrl('/com/user/openJoin.do') );
         return false;
	});
	// 메일보내기
	$('#btnMail').bind('click', function() {
        $.ajaxUtil.ajaxLoad(
            getUrl('/com/common/sendEmail.do'), 
            $('#mailForm').serializeObject(), 
            function() {
				$.commMsg.alert('성공적으로 전송되었습니다.');
            }
        );
         return false;
	});
});

