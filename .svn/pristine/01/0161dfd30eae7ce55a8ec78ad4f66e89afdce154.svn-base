/**
******************************************************************************************
*** 파일명    : openForm.js
*** 설명      : 회원가입 - 기본정보입력
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2023.03.14              LSH
******************************************************************************************
**/
$(function() {
	
	let P_FORM = $('#joinForm');
	
	// 검증 룰 정의
	P_FORM.validate({
		debug: false,
		onfocusout: false,
		onsubmit: false,
		rules: {
			userNm   : {required: true},
			mobile   : {required: true},
			userId   : {
				required: true, 
				email: true, 
				maxlength: 50, 
				remote: {
				    type: 'post',
					url: getUrl('/com/user/unique.do'),
					data: {
						userId: function() {
							return $('#userId').val();
						}
					}
				}
			},
			pswd: {
				required:  true,
                minlength: 8,
                maxlength: 30,
                regx:      /^(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/
			},
			pswdCnfm : {required: true, equalTo: '#pswd'}
		},
		messages: {
			userNm   : {required: '본인인증을 진행해 주세요.'},
			mobile   : {required: '본인인증을 진행해 주세요.'},
			userId   : {
				required: '이메일을 입력해 주세요.',
				email:    '이메일을 형식에 맞게 입력해 주세요.', 
				maxlength: $.validator.format('이메일은 {0}자를 초과할 수 없습니다.'),
				remote:   '이미 가입된 이메일입니다.'
			},
            pswd      : {
				required: '비밀번호를 입력해 주세요.',
                minlength: $.validator.format('비밀번호를 {0}자 이상 입력해 주세요.'),
                maxlength: $.validator.format('비밀번호를 {0}자 이하 입력해 주세요.'),
                regx:     '비밀번호를 영대소문자 + 숫자 + 특수문자 조합으로 입력해 주세요.'
            },
            pswdCnfm  : {
				required: '비밀번호 확인을 입력해 주세요.',
                equalTo:  '입력한 비밀번호가 일치하지 않습니다.'
			}
		},
		invalidHandler: $.validateUtil.handler01,
		errorPlacement: $.validateUtil.placement01
	});
	
    // 기본정보 저장 (다음단계)
    //--------------------------------------------------------//
    function doSave() {
	
		let userCd = $('#userCd').val();

        // VALIDATION 기능 활성화
        if (P_FORM.validate().settings)
            P_FORM.validate().settings.ignore = false;

        // FORM 검증
        if (P_FORM.valid() === false)
            return false;

        // FORM을 AJAX로 저장처리
        P_FORM.ajaxForm({
            url: getUrl('/com/user/saveForm.do'),
            // 오류시 처리로직
            error: $.ajaxUtil.error,
            // 저장후 처리로직
            success: function(ret) {
				$.commMsg.success(ret, false, function() {
					
					if (userCd == CODE.USER.INDIVIDUAL) {
						$.formUtil.submitForm(getUrl('/com/user/doneJoin.do'), {
							params: {userId: ret.userId}
						});
					}
					else {
						goUrl( getUrl('/com/user/openCorp.do') );
					}
				});
            }
        }).submit();
        return false;
    }
	
	// 단계클릭
	$('.btnStep').bind('click', function() {
		let c = $(this).data('code');
		if (c == 'prev') {
			history.go(-1);
		}
		else if (c == 'next') {
			doSave();
		}
		return false;
	});
	
	// 소셜로그인 회원가입 (팝업)
	$('.btnOauth').bind('click', function() {
		let url = $(this).data('url');
		let opt = "width=480,height=880, scrollbars=yes, resizable=yes";
		let key = "oauth_pop";
		window.open(url, key, opt);
		return false;
	});
	// 휴대폰 본인인증 (팝업)
	$('#btnCertify').bind('click', function() {
		let url = getUrl("/com/common/popupMobilians.do");
		let opt = "width=480,height=880, scrollbars=yes, resizable=yes";
		let key = "mobilians_pop";
		window.open(url, key, opt);
		return false;
	});
	
});

// 휴대폰 본인인증 팝업창에서 호출하는 함수
function certifyCallback( data ) {
	console.log(data['Result'  ]); // 결과 (SUCCESS)
	console.log(data['Name'    ]); // 이름
	console.log(data['No'      ]); // 휴대전화번호
	console.log(data['Socialno']); // 생년월일
	
	let nm 	= data['Name'];
	let no	= data['No'  ];
	
	if (data['Result'] == 'SUCCESS') {
		$.commMsg.alert('성공적으로 휴대폰 본인인증이 완료되었습니다.', function() {
			$('#userNm').val(nm);
			$('#mobile').val(no);
			// 휴대폰인증 완료여부
			$('#certYn').val('Y');
		});
	}
}
