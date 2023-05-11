/**
******************************************************************************************
*** 파일명    : openCorp.js
*** 설명      : 회원가입 - 기업정보입력
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2023.03.20              LSH
******************************************************************************************
**/
$(function() {
	
    //========================================================//
    // 화면 스크립트 내에 사용할 객체,상수 정의
    //--------------------------------------------------------//
	let P_FORM = $('#joinForm');
	
	// 검증 룰 정의
	P_FORM.validate({
		debug: false,
		onfocusout: false,
		onsubmit: false,
		rules: {
			bizNo1  : {required: true, digits: true, equalLength: 3},
			bizNo2  : {required: true, digits: true, equalLength: 2},
			bizNo3  : {required: true, digits: true, equalLength: 5},
			bizNo   : {required: true},
			bizNm   : {required: true},
			bizOwnr : {required: true},
			bizDate : {required: true, dateHyphen: true},
			bizPhone: {required: true, phone: true},
			bizType : {required: true}
		},
		messages: {
			bizNo1  : {
				required: '사업자등록번호 첫번째 항목을 입력해 주세요.',
				digits:   '사업자등록번호 첫번째 항목을 숫자로만 입력해 주세요.',
				equalLength: $.validator.format('사업자등록번호 첫번째 항목을 {0}자리 숫자로 입력해 주세요.')
			},
			bizNo2  : {
				required: '사업자등록번호 두번째 항목을 입력해 주세요.',
				digits:   '사업자등록번호 두번째 항목을 숫자로만 입력해 주세요.',
				equalLength: $.validator.format('사업자등록번호 두번째 항목을 {0}자리 숫자로 입력해 주세요.')
			},
			bizNo3  : {
				required: '사업자등록번호 세번째 항목을 입력해 주세요.',
				digits:   '사업자등록번호 세번째 항목을 숫자로만 입력해 주세요.',
				equalLength: $.validator.format('사업자등록번호 세번째 항목을 {0}자리 숫자로 입력해 주세요.')
			},
			bizNo   : {required: '사업자 조회를 실행해 주세요.'},
			bizNm   : {required: '회사명을 입력해 주세요.'},
			bizOwnr : {required: '대표자를 입력해 주세요.'},
			bizDate : {
				required:   '설립일을 입력해 주세요.', 
				dateHyphen: '설립일을 형식에 맞게 입력해 주세요. (예.2023-01-01)'
			},
			bizPhone: {
				required: '대표번호를 입력해 주세요.', 
				phone:    '대표번호를 형식에 맞게 입력해 주세요. (예.02-0000-0000)'
			},
			bizType : {required: '기업유형을 선택해 주세요.'}
		},
		invalidHandler: $.validateUtil.handler01,
		errorPlacement: $.validateUtil.placement01
	});

	// 기업유형 콤보박스 (TODO. 나중에 공통코드로 처리할것)
	$('#bizType').appComboBox({
		type: 'static',
		rows: [
			{code:'A', text: '농림수산식품 경영체'},
			{code:'A', text: '투자자'},
			{code:'A', text: '유관기관'}
		]
	});
	
	bindOnlyNumber ($("#bizNo1"));
	bindOnlyNumber ($("#bizNo2"));
	bindOnlyNumber ($("#bizNo3"));
	bindDateHyphen ($("#bizDate"));
	bindPhoneHyphen($("#bizPhone"));
	// 달력 처리
	//$( "#bizDate" ).datepicker();
	
    // 기업정보 저장 (다음단계)
    //--------------------------------------------------------//
    function doSave() {

        // VALIDATION 기능 활성화
        if (P_FORM.validate().settings)
            P_FORM.validate().settings.ignore = false;

        // FORM 검증
        if (P_FORM.valid() === false)
            return false;

		// 파일 검증확인
		let check = true;
		// 첨부파일갯수만큼 LOOP
		P_FORM.find('input[name="upfile"]').each(function(i) {
			// 파일객체의 선택값
			let nfile = $(this).val();
			// 이전파일의 저장값
			let ofile = P_FORM.find('input[name="fileName"]').eq(i).val();
			// 파읠의 필수여부
			let need  = P_FORM.find('input[name="needYn"  ]').eq(i).val();
			
			// 파일업로드여부 설정
			if (nfile === '')
				P_FORM.find('input[name="fileYn"]').eq(i).val('N');
			else
				P_FORM.find('input[name="fileYn"]').eq(i).val('Y');

			//이미 false 체크된 경우 SKIP
			if (check == false)
				return false;
			// 파일정보가 없는 경우
			if (nfile == '' && ofile == '') {
				//필수 체크가 아닌 경우 SKIP
				if (need != 'Y')
					return true;
				$.commMsg.alert('파일을 선택해 주세요.');
				check = false;
				return false;
			}
		});
		// 파일검증 실패시
		if (check == false)
			return false;
		
    	$.commMsg.confirm("저장하시겠습니까?", function() {
            // 등록폼을 AJAX로 저장처리
            P_FORM.ajaxForm({
                url: getUrl('/com/user/saveCorp.do'),
				// 파일업로드 타입
                enctype : 'multipart/form-data',
                // 오류시 처리로직
                error: $.ajaxUtil.error,
                // 저장후 처리로직
                success: function(ret) {
					$.commMsg.success(ret, false, function() {
						$.formUtil.submitForm(getUrl('/com/user/doneJoin.do'), {
							params: {userId: ret.userId}
						});
					});
                }
            }).submit();
    	});
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
	
	// 사업자등록번호 조회 (팝업)
	$('#btnBizNo').bind('click', function() {
		
		var validator = P_FORM.validate();
		if (!validator.element($('#bizNo1'))) {
			$('#bizNo1').focus();
			return false;
		}
		if (!validator.element($('#bizNo2'))) {
			$('#bizNo2').focus();
			return false;
		}
		if (!validator.element($('#bizNo3'))) {
			$('#bizNo3').focus();
			return false;
		}
		//사업자등록번호 병합
		$.formUtil.mergeData('bizNo','bzno', 3);
		popup.openBizNo({
			params: {
				bizNo : $('#bizNo' ).val(),
				bizNo1: $('#bizNo1').val(),
				bizNo2: $('#bizNo2').val(),
				bizNo3: $('#bizNo3').val()
			}
		});
		return false;
	});
	// 첨부파일 파일선택 변경시 텍스트박스에 파일명을 표시해주는 이벤트
	$('.file_wrap').on('change','.input_file', function() {
		// 파일명만 추출한다.
		var fname = $(this).val().split("\\").pop();
		// 텍스트박스에 셋팅한다.
		$(this).closest('.file_wrap').find('.input_text').val(fname);
	});
	
});

// 사업자등록번호 조회 팝업창에서 호출하는 함수
function bizNoCallback( data ) {
	
	if (!data)
		return;
	
	// 신규등록인 경우
	if (data == MODE.INSERT) {
		// 빈값 셋팅
		$.formUtil.toForm({
			bizNm   : '',
			bizOwnr : '',
			bizDate : '',
			bizPhone: ''
		}, $('#joinForm'));
		// 읽기전용 해제
		$.formUtil.setReadonly($('#joinForm'), false, [
			'bizNm', 
			'bizOwnr', 
			'bizDate', 
			'bizPhone'
		]);
	}
	else {
		console.log(data['bzno'     ]); // 사업자등록번호
		console.log(data['enpNm'    ]); // 기업체명
		console.log(data['reperName']); // 대표자명
		console.log(data['estbDt'   ]); // 설립일자
		console.log(data['telNo'    ]); // 대표번호
		// 빈값 셋팅
		$.formUtil.toForm({
			bizNm   : data['enpNm'    ],
			bizOwnr : data['reperName'],
			bizDate : $.formatUtil.toDashDate(data['estbDt']),
			bizPhone: $.formatUtil.toPhone   (data['telNo' ])
		}, $('#joinForm'));
		// 읽기전용 활성
		$.formUtil.setReadonly($('#joinForm'), true, [
			'bizNm', 
			'bizOwnr', 
			'bizDate', 
			'bizPhone'
		]);
	}
}
