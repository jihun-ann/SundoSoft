/**
******************************************************************************************
*** 파일명    : openAgree.js
*** 설명      : 회원가입 - 이용약관동의
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2023.03.14              LSH
******************************************************************************************
**/
$(function() {
	
	let P_FORM = $('#joinForm');
	
	// 단계클릭
	$('.btnStep').bind('click', function() {
		let c = $(this).data('code');
		if (c == 'prev') {
			goUrl( getUrl('/com/user/openJoin.do') );
		}
		else if (c == 'next') {
			if ($.formUtil.getValue(P_FORM, 'agreYn') != 'Y') {
				$.commMsg.alert('이용약관은 반드시 동의하셔야 합니다.');
				return false;
			}
			if ($.formUtil.getValue(P_FORM, 'infoYn') != 'Y') {
				$.commMsg.alert('개인정보 수집 및 이용 동의는 반드시 동의하셔야 합니다.');
				return false;
			}
	        $.formUtil.submitForm(getUrl('/com/user/openForm.do'),{
				formId : 'joinForm'
			});
		}
		return false;
	});
});
