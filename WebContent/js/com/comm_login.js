/**
******************************************************************************************
*** 파일명    : comm_login.js
*** 설명      : 공통 로그인 기능 자바스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2023.03.20              LSH
******************************************************************************************
**/
$(document).ready(function () {
	// 회원 로그인
	Login.doInit();
});

var Login = {
	doInit: function() {
		// Validation Rule 정의
		$('#loginForm').validate({
			debug: false,
			onfocusout: false,
			onsubmit: false,
			rules: {
				username: { required: true},
				password: { required: true}
			},
			messages: {
				username: { required: '이메일을 입력해 주세요.'},
				password: { required: '비밀번호를 입력해 주세요.'}
			},
			invalidHandler: validateHandler,
			errorPlacement: validatePlacement
		});
	    // 로그인 클릭
	    $("#btnLogin").bind("click", Login.doLogin);
	    // 회원가입 클릭
	    $("#btnJoin" ).bind("click", Login.doJoin);
	    // 소셜로그인 클릭
	    $(".btnOauth").bind("click", Login.doOauth);

	    // 아이디/비밀번호찾기 클릭
	    $("#btnFind" ).bind("click", Login.doFind);

		bindEnter($('#username'), $("#btnLogin"));
		bindEnter($('#password'), $("#btnLogin"));
		$('#username').focus();
	},
	//회원 로그인
	//--------------------------------------------------------//
	doLogin: function() {

		let loginForm = $('#loginForm');
		if (loginForm.validate().settings)
			loginForm.validate().settings.ignore = false;
		if (loginForm.valid() === false)
			return false;
	    let param   = loginForm.serializeObject();
	    let result  = $.ajaxUtil.ajaxDefault(getUrl('/com/common/loginCheck.do'), param, false);
	    if (result && result != 'syserr') {
			let failFlag = result.failFlag;
			let message  = result.message;
			// 성공이면
			if (failFlag == 'OK') {
				goUrl(getUrl("/com/common/loginSucc.do"));
			}
	        // 실패
			else {
				$('#password').val('');
	            // 에러 메시지
	            $.commMsg.alert(message);
			}
	    }
	},
	// 회원 가입 페이지로 이동
	//--------------------------------------------------------//
	doJoin: function() {
		goUrl(getUrl("/com/user/openJoin.do"));
		return false;
	},
	// 소셜 로그인 (네이버/카카오)
	//--------------------------------------------------------//
	doOauth: function() {
		let url = $(this).data('url');
		let opt = "width=480,height=880, scrollbars=yes, resizable=yes";
		let key = "oauth_pop";
		window.open(url, key, opt);
		return false;
	},

	// TODO 아이디/비밀번호 찾기 팝업
	//--------------------------------------------------------//
	doFind: function() {
		return false;
	}

};
