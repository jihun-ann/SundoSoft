/**
******************************************************************************************
*** 파일명    : comm_popup.js
*** 설명      : 공통 팝업 자바스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2023.03.14              LSH
******************************************************************************************
**/
var popup = {
	// 사업자등록번호 조회팝업
    openBizNo : function ( args ) {
		return $.commModal.loadView(
            '사업자등록번호 조회',
			getUrl('/html/popupBizNo.html'), {
				// 배경클릭시 창닫힘 방지
				closeByBackdrop: false,
				
				func: function(dialog) {
					
					$('#p_bizNo1').val(args.params['bizNo1']);
					$('#p_bizNo2').val(args.params['bizNo2']);
					$('#p_bizNo3').val(args.params['bizNo3']);
					$('#p_bizNo' ).val(args.params['bizNo' ]);
					
					let P_BIZFORM = $('#kodataForm');
					
					// 검증 룰 정의
					P_BIZFORM.validate({
						debug: false,
						onfocusout: false,
						onsubmit: false,
						rules: {
							bizNo1  : {required: true, digits: true, equalLength: 3},
							bizNo2  : {required: true, digits: true, equalLength: 2},
							bizNo3  : {required: true, digits: true, equalLength: 5}
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
							}
						},
						invalidHandler: $.validateUtil.handler,
						errorPlacement: $.validateUtil.placement
					});
					bindOnlyNumber ($("#bizNo1"));
					bindOnlyNumber ($("#bizNo2"));
					bindOnlyNumber ($("#bizNo3"));
					
					let fnSearch = function() {

				        if (P_BIZFORM.validate().settings)
				            P_BIZFORM.validate().settings.ignore = false;
				        if (P_BIZFORM.valid() === false)
				            return false;

						$('#p_bizResult').html('KODATA에서 사업자정보를 조회중입니다..');
						$('#p_btnBizRegist').hide();

						//사업자등록번호 병합
						$.formUtil.mergeData('p_bizNo','bzno', 3);
						
						// 사업자정보 조회
						const result = $.ajaxUtil.ajaxDefault(
							getUrl('/com/common/getKodata.do'),{
								bzno: $('#p_bizNo').val()
						});
						if (result && result.Data) {
							let biz = result.Data;
							let dom = $('<div></div>');
							dom.append('<div id="p_bizRow">기업체명 : '       + biz['enpNm'    ] +'</div>');
							dom.append('<div>사업자등록번호 : ' + biz['bzno'     ] +'</div>');
							dom.append('<div>대표자명 : '       + biz['reperName'] +'</div>');
							dom.append('<div>설립일자 : '       + biz['estbDt'   ] +'</div>');
							dom.append('<div>대표번호 : '       + biz['telNo'    ] +'</div>');
							$('#p_bizResult').html('');
							$('#p_bizResult').append(dom);
							
							$('#p_btnBizRegist').hide();
							$('#p_bizRow').bind('click', function() {
								bizNoCallback(biz);
								dialog.close();
								return false;
							});
						}
						else {
							$('#p_bizResult').html('검색결과가 없습니다.');
							$('#p_btnBizRegist').show();
						}
						return false;
					};
					
					// 검색 클릭
					$('#p_btnBizSearch').bind('click', fnSearch);
					// 신규등록 클릭
					$('#p_btnBizRegist').bind('click', function() {
						bizNoCallback(MODE.INSERT);
						dialog.close();
						return false;
					});
					// 취소 클릭
					$('#p_btnBizClose').bind('click', function() {
						dialog.close();
						return false;
					});
					fnSearch();
				}
			}
		);
    },
    // 2021.12.01 LSH 파일업로드 팝업
	openUpload: function( options ) {
		// options.url : 업로드 URL
		// options.extensions : 허용 확장자 배열
		// options.maxBytes   : 파일 제한 크기
		// options.callback   : 저장후 처리 함수
		// options.params     : 파일관련 변수
		// options.addParams  : 추가 변수
		// options.addContent : 추가 정보표시 함수

		$.commModal.loadView(
			options.title || '파일업로드',
			getUrl('/html/popupFileUpload.html'), {
				cssClass: 'modal-w700 modal-center',
				closable: false,
				func: function(dialog) {
					let popTable = $('#popupContentTable');
					let popForm  = $('form[name="popupUploadForm"]');
					let bodyElm  = dialog.getModalBody();

					// 에러메세지 표시함수
					let _showError = function( msg ) {
						bodyElm.find('p.err').html( msg );
					};
					// 파일검증함수
					let _validate = function( fobj ) {
						// 파일빈값체크
						if ($.commUtil.empty( fobj.val() )) {
							_showError('파일을 선택해 주세요.');
							return false;
						}
						// 확장자 체크
						if (!$.fileUtil.checkExtension(fobj, options.extensions, _showError))
							return false;
						// 용량 체크
						if (!$.fileUtil.checkMaxbytes(fobj, options.maxBytes, _showError))
							return false;
						// 검증 성공
						_showError('');
						return true;
					};

					// 추가표시정보가 있는 경우
					if (options.addContent) {
						// 테이블에 컨텐츠를 추가하는 함수
						options.addContent(popTable);
					}


					if (options.params) {
						popForm.find('[name="fileType"]').val($.commUtil.nvlTrim(options.params['fileType'  ]));
						popForm.find('[name="fileNo"  ]').val($.commUtil.nvlTrim(options.params['fileNo'  ]));
						popForm.find('[name="docuCd"  ]').val($.commUtil.nvlTrim(options.params['docuCd'  ]));
						popForm.find('[name="needYn"  ]').val($.commUtil.nvlTrim(options.params['needYn'  ]));
					}
					if (options.addParams) {
						$.each(options.addParams, function(key, value) {
							popForm.append('<input type="hidden" name="'+key+'" value="'+value+'" />');
						});
					}
					if (popForm.find(".pop-filebox").length) {
						popForm.find(".pop-filebox .upload-hidden").on("change", function(){ //값이 변경되면
							if (window.FileReader){ //modern browser
								var filename = $(this)[0].files[0].name;
							}else { //old IE
								var filename = $(this).val().split("/").pop().split("\\").pop(); //파일명만 추출
							}
							//추출한 파일명 삽입
							$(this).siblings(".upload-name").val(filename);
							//파일검증
							_validate( $(this) );
						});
					};

					// 등록버튼 클릭
					$('#btnUploadSave').on("click", function() {
						// 첨부파일 업로드 VALIDATION
						let fobj = popForm.find('input[name="upfile"]');
						// 파일검증
						if (!_validate(fobj))
							return false;
						$.commMsg.confirm('등록하시겠습니까?', function() {
							// 폼을 AJAX로 저장처리
							popForm.ajaxForm({
								url: options.url,
								enctype : 'multipart/form-data',
								// 에러시 처리로직
								error: $.ajaxUtil.error,
				                // 저장후 처리로직
				                success: function(ret) {
									if (options.success) {
										options.success(ret, dialog);
									}
									else {
										$.commMsg.success(ret, '성공적으로 등록되었습니다.', function() {
											if (options.callback)
												options.callback(ret);
											dialog.close();
										});
									}
								}
							}).submit();
						});
						return false;
					});
				}
			}
		);
	},

    // 팝업창 오픈
    open : function (params) {
        var reqGetParamStr = "";
        var paramDatas     = params.data;

        if (paramDatas) {
            for ( var key in paramDatas) {
                if (reqGetParamStr != "") {
                    reqGetParamStr += "&";
                }
                reqGetParamStr += (key + "=" + paramDatas[key]);
            }
        }
        if (reqGetParamStr != "") {
            params.url = params.url + "?" + reqGetParamStr;
        }

        // 옵션 값 재 설정
        var options = $.extend({}, params);

        // 사용자 정의 함수 수행 (팝업 load가 끝난 이후 수행할 내용임)
        options.func = function(){
            // TODO : 팝업의 WIDTH를 나중에 그릴때 문제가 있어서 확인후 각 페이지에서 하는 것으로 할 예정
            if (params && params.width) {
                $("#popup").each(function(){
                    // $(this).parents(".modal-content").css("width", params.width);
                    $(this).parents(".modal-dialog").css("width", params.width);
                });
            }

            if (params && params.onshownFn && $.type(params.onshownFn) == 'function') {
                params.onshownFn();
            }
        }

        //dialog 출력
        return $.commModal.loadView(options.title, options.url, options);
    },
    // 팝업창 닫기
    close : function () {
        $("#closeBtn").trigger("click");
    }
}

//===========================================================================//
// 2021.10.16 LSH 공통팝업
//===========================================================================//
$.fn.appPopup = function ( args ) {

	var options = $.extend({
		// 팝업타입 (base: 기본타입, pageload: 화면로드타입)
		type: 'base',
		// (선택) 팝업제목
		title: false,
		// (선택) 추가 스타일시트
		cls: false,
		// 팝업내용 (문자열 또는 함수)
		message: false,
		// 레이아웃 스타일시트
		popupCls: 'layerPop type3',
		// 감싸는영역 스타일시트
		wrapCls: 'layerPop-wrap',
		// Inner영역 스타일시트
		innerCls: 'layerPop-inner',
		// 제목영역 스타일시트
		titleCls: 'layerPop-title',
		// 내용영역 스타일시트
		bodyCls: 'layerPop-body',
		// 버튼영역 스타일시트
		buttonCls: 'layerPop-buttons',
		// 버튼 스타일시트
		btnCls: 'btnWrap type1',
		// 버튼 목록 배열 {id,code,text,click}
		buttons: false,
		// 팝업생성시 오픈여부
		autoopen: true,
		// 팝업오픈시 로드여부
		autoload: false,
		// 닫을때 객체삭제
		destroy:  true,
		// 오픈시 BODY에 추가
		appendBody: false,
		// 오픈후 콜백함수
		onshown:  false,
		// 닫기후 콜백함수
		onhidden: false,
		// 페이지로드후 콜백함수
		onloaded: false,

		// 페이지로드시 URL
		url: false,
		// 페이지로드시 조회조건
		params: false,

		innerWidth: false,

	}, args);

	// 페이지로드 타입인 경우 기본설정변경
	if (options.type == 'pageload') {
		$.extend(options, {
			popupCls:  'layerPop form type1',
			buttonCls: 'layerBtnWrap',
			btnCls:    false,
			autoload:  true,
			autoopen:  false,
			destroy:   true
		});
		$.extend(options, args);
	}

	//현재객체
	let thisCmp = this;
	//레이어객체
	let thisElm = $(this);
	let thisDialog = false;

	// 옵션정보 반환
	this.getOptions = function() {
		return options;
	};
	// 다이얼로그객체 반환
	this.getDialog = function() {
		return thisDialog;
	};
	// 팝업 버튼객체 반환
	this.getButton = function( btnId ) {
		return thisElm.find('[id="'+btnId+'"]');
	};
	// 팝업 내용객체 반환
	this.getMessage = function() {
		return options.message;
	};
	// 팝업 조회조건 반환
	this.getParams = function() {
		return options.params;
	};
	// 팝업 조회조건 반환
	this.getParseParams = function() {
		return JSON.parse(options.params);
	};

	// 팝업 내용로드
	this.load = function( opts ) {
		if (opts) {
			if (opts.url)    options.url    = opts.url;
			if (opts.params) options.params = opts.params;
		}
		if (options.url &&
			options.params) {
			$.ajax({
				url: options.url,
				dataType : "html",
				type: 'post',
				async: false,
                contentType: 'application/json',
				data: options.params,
				success: thisCmp.loadHtml,
				error: function(){}
			});
		}
	};

	// 팝업 내용HTML 로딩
	this.loadHtml = function( html ) {

		if (!html)
			return;

		// 내용담기
		options.message = html;
		thisElm.find('.'+options.bodyCls).html('');
		thisElm.find('.'+options.bodyCls).html(html);

		if (options.onloaded)
			options.onloaded(thisCmp);
	};

	// 팝업 내용HTML 리셋
	this.resetHtml = function() {
		thisElm.find('.'+options.bodyCls).html('');
	};

	// 팝업레이아웃 생성
	this.createLayout = function( opts ) {

		if (opts) {
			$.extend(options, opts);
		}
		let inner = $('<div></div>');
		inner.addClass(options.innerCls);
		if (options.innerWidth)
			inner.css('width', options.innerWidth);

		// 제목생성
		if (options.title) {
			let title = $('<h3></h3>');
			title.addClass(options.titleCls);
			title.append(options.title);
			inner.append(title);
		}
		// 내용생성
		inner.append(thisCmp.createBody(options.message));

		// 버튼 생성
		if (options.buttons &&
			options.buttons.length > 0) {
			inner.append(thisCmp.createButtons(options.buttons));
		}
		let wrap = $('<div></div>');
		wrap.addClass(options.wrapCls);
		wrap.append('<button class="close"></button>');
		wrap.append(inner);

		thisElm.append('<div class="cover"></div>');
		thisElm.append(wrap);
		thisElm.addClass(options.popupCls);
		if (options.cls)
			thisElm.addClass(options.cls);

		thisElm.find('button.close').bind('click', thisCmp.bindClose);

		if (options.appendBody)
			thisElm.appendTo('body');
	};

	// 팝업내용 생성
	this.createBody = function( msg ) {

		let body = $('<div></div>');
		body.addClass(options.bodyCls);

		// 내용생성
        if ($.type(msg) == 'function')
            body.append(msg(thisCmp));
		else
			body.append(msg);

		return body;
	};

	// 버튼 생성
	this.createButtons = function( buttons ) {

		let div = $('<div></div>');
		div.addClass(options.buttonCls);

		$.each(buttons, function(i,b) {

			let obj = $('<a href="javascript:void(0);"></a>');
			obj.append(b['text']);
			if (b.cls)
				obj.addClass(b.cls);
			if (b['id'])
				obj.prop('id', b['id']);
			if (b['clickClose']) {
				obj.bind('click', function() {
					thisCmp.close();
				});
			}
			else if (b['click']) {
				obj.bind('click', function() {
					b['click'](thisCmp);
				});
			}
			obj.data('text', b['text']);
			if (b['code'])
				obj.data('code', b['code']);

			if (options.btnCls) {
				let btn = false;
				btn = $('<div></div>');
				btn.addClass(options.btnCls);
				btn.append(obj);
				div.append(btn);
			}
			else {
				div.append(obj);
			}
		});
		return div;
	};
	// 닫기 이벤트 핸들러
	this.bindClose = function() {
		thisElm.removeClass('on');
		if (options.onhidden)
			options.onhidden(thisCmp);
		if (options.destroy) {
			if (options.appendBody)
				thisElm.remove();
			else {
				thisElm.removeClass();
				thisElm.children().remove();
			}
		}
	};

	// 닫기 함수
	this.close = function() {
		thisElm.find('.close').trigger('click');
	};

	// 오픈 함수
	this.open = function( opts ) {
		thisCmp.createLayout( opts );
		// 팝업오픈시 로드
		if (options.autoload)
			thisCmp.load( opts );
		if (options.onshown)
			options.onshown(thisCmp);
		thisElm.addClass('on');
		/*
		if (opts) {
			if (opts.url)    options.url    = opts.url;
			if (opts.params) options.params = opts.params;
		}
		let message = options.message;
		if (options.url &&
			options.params) {
			$.ajax({
				url: options.url,
				dataType : "html",
				type: 'post',
				async: false,
                contentType: 'application/json',
				data: options.params,
				success: function(html) {
					message = $(html);
				},
				error: function(){}
			});
		}
		$.extend(options, {
            draggable: true,
            closable:  true,
			message:   message
		});
		if (options.onloaded) {
			if (options.onshown) {
				options.onshown = function(dialog) {
					options.onloaded(thisCmp);
					options.onshown(dialog);
				};
			}
			else {
				options.onshown = function(dialog) {
					options.onloaded(thisCmp);
				};
			}
		}
        thisDialog = BootstrapDialog.show(options);
		*/
		return thisCmp;
	};

	// 객체생성시 팝업오픈
	if (options.autoopen)
		this.open();

	return this;
};

//===========================================================================//
// 2021.10.27 LSH 공통메세지처리
//===========================================================================//
$.fn.appMessage = function ( args ) {

	var options = $.extend({
		// alert, confirm
		type: 'alert',
		// (선택) 추가 스타일시트
		cls: false,
		// 팝업내용 (문자열 또는 함수)
		message: false,
		// 레이아웃 스타일시트
		popupCls:  'app-message',
		// Inner영역 스타일시트
		innerCls:  'app-message-inner',
		// 내용영역 스타일시트
		wrapCls:   'app-message-wrap',
		// 버튼영역 스타일시트
		buttonCls: 'app-message-btnwrap',
		// 버튼 목록 배열 {id,code,text,click}
		buttons: false,
		// 오픈후 콜백함수
		onshown:  false,
		// 닫기후 콜백함수
		onhidden: false,
		// 확인버튼 클릭시 처리함수
		clickOk: false,
		// 취소버튼 클릭시 처리함수
		clickClose: false

	}, args);

	//현재객체
	let thisCmp = this;
	//레이어객체
	let thisElm = $(this);

	// 옵션정보 반환
	this.getOptions = function() {
		return options;
	};
	// 팝업 버튼객체 반환
	this.getButton = function( btnId ) {
		return thisElm.find('[id="'+btnId+'"]');
	};
	// 팝업 내용객체 반환
	this.getMessage = function() {
		return options.message;
	};

	if (!options.buttons) {
		if (options.type == 'confirm') {
			options.buttons = [{
				id: 'btn_msg_ok',
				text: '확인',
				click: function() {
					thisCmp.close();
					if (options.clickOk)
						options.clickOk(thisCmp);
				}
			},{
				id: 'btn_msg_close',
				text: '취소',
				click: function() {
					thisCmp.close();
					if (options.clickClose)
						options.clickClose(thisCmp);
				}
			}];
		}
		else {
			options.buttons = [{
				id: 'btn_msg_ok',
				text: '확인',
				click: function() {
					thisCmp.close();
					if (options.clickClose)
						options.clickClose(thisCmp);
				}
			}];
		}
	}

	// 팝업레이아웃 생성
	this.create = function() {

		let inner = thisCmp.createInner();
		let wrap  = thisCmp.createWrap();
		wrap.append(inner);
		thisElm.append('<div class="cover"></div>');
		thisElm.append(wrap);

		thisElm.addClass(options.popupCls);
		if (options.cls)
			thisElm.addClass(options.cls);

		thisElm.appendTo('body');
	};

	// 감싸는 영역 생성
	this.createWrap = function() {
		let wrap = $('<div></div>');
		wrap.addClass(options.wrapCls);
		return wrap;
	};

	// 내용 영역 생성
	this.createInner = function() {
		let inner = $('<div></div>');
		inner.addClass(options.innerCls);

		// 내용생성
        if ($.type(options.message) == 'function')
			inner.append(options.message(thisCmp));
		else
			inner.append(options.message);

		// 버튼 생성
		if (options.buttons &&
			options.buttons.length > 0) {
			inner.append(thisCmp.createButtons(options.buttons));
		}
		return inner;
	};

	// 버튼 생성
	this.createButtons = function( buttons ) {

		let div = $('<div></div>');
		div.addClass(options.buttonCls);

		$.each(buttons, function(i,b) {
			let obj = $('<a href="javascript:void(0);"></a>');
			obj.append(b.text);
			obj.data('text', b.text);
			if (b.code)       obj.data('code', b.code);
			if (b.cls)        obj.addClass(b.cls);
			if (b.id )        obj.prop('id', b.id);
			if (b.clickClose) obj.bind('click', function() { thisCmp.close(); return false; });
			else if (b.click) obj.bind('click', function() {b.click(thisCmp); return false; });
			div.append(obj);
		});
		return div;
	};

	// 닫기 함수
	this.close = function() {
		thisElm.removeClass('on');
		if (options.onhidden)
			options.onhidden(thisCmp);
		thisElm.remove();
	};
	// 오픈 함수
	this.open = function() {
		thisCmp.create();
		if (options.onshown)
			options.onshown(thisCmp);
		thisElm.addClass('on');
		$('#btn_msg_ok').focus();
		return thisCmp;
	};
	this.open();

	return this;
};

$.popupMsg = {
	alert: function(msg, clickClose) {
		$('<div></div>').appMessage({
			type: 'alert',
			message: msg,
			clickClose: clickClose
		});
	},
    confirm: function (msg, clickOk, clickClose) {
		$('<div></div>').appMessage({
			type: 'confirm',
			message: msg,
			clickOk: clickOk,
			clickClose: clickClose
		});
    }
};

/**
 * 2021.10.20 LSH comm_utils.js -> comm_element.js 로 이동처리
 *
 * 공통 Alert, Confirm
 *
 * $.commMsg.alert('message');
 */
$.commMsg = {
    alert: function (msg, clickClose) {
		// 2021.10.20 LSH BootstrapDialog 사용제외
		$.popupMsg.alert(msg, clickClose);
        //BootstrapDialog.alert(msg, function(){
        //    if (clickClose)
        //        clickClose();
        //});
    },
    confirm: function (msg, clickOk, clickClose) {

		// 2021.10.20 LSH BootstrapDialog 사용제외
		$.popupMsg.confirm(msg, clickOk, clickClose);
        //BootstrapDialog.confirm(msg, function(result){
        //    if (result) {
        //        if (clickOk)
        //            clickOk();
        //    }
        //    else {
        //        if (clickClose)
        //            clickClose();
        //    }
        //});
    },
    /**
     * AJAX 통신 결과 공통 처리 로직
     * ajax의 success 함수에서 필요시 사용한다.
     * 성공 메세지
     * 2021.10.26 LSH ADD
     */
    success: function(data, msg, callback) {
		// 2022.02.07 결과메세지 공통처리
		$.ajaxUtil.result(data, function() {
			if (msg) {
				$.commMsg.alert(msg, function() {
					if (callback)
		            	callback(data);
				});
			}
			else {
				if (callback)
	            	callback(data);
			}
		})
    },
    /**
     * AJAX 통신 오류 발생시 공통 처리 로직
     * ajax의 error 함수에 맵핑하여 사용한다.
     * 2021.10.26 LSH ADD
     */
    error: function(request, status, error) {
		// 2022.02.07 결과메세지 공통처리
		$.ajaxUtil.error(request, status, error);
    },
    /**
     * 엑셀로드 결과 공통 처리 로직
     * 2022.01.28 LSH ADD
     */
    successLoad: function(data, msg, callback) {
		// 2022.02.07 결과메세지 공통처리
		$.ajaxUtil.result(data, function() {
			$.commMsg.alert(msg, callback);
		})
    },
}

/**
 * 2021.10.20 LSH comm_utils.js -> comm_element.js 로 이동처리
 *
 * 공통 modal (bootstrap dialog) 처리
 */
$.commModal = {
    // remote page
    // $.commModal.loadView(title, url);
    loadView: function (title, url, options) {
		var args = $.extend({
            title: title,
            draggable: true,
            closable: true,
            message: function (dialog) {
                var $message = $('<div class="loadView"></div>');
                var pageToLoad = dialog.getData('pageToLoad');
                $message.load(pageToLoad);

                return $message;
            },
            data: {
                'pageToLoad': url
            },
            onshown: function (dialog) {
                $('.modal-dialog :input[type="text"]:enabled:visible:first').focus();

                // modal width 옵션 : large/small (입력하지 않으면 normal)
                if(options && options.sizeType) {
                    var modalSize = "";
                    if(options.sizeType == "large"){
                        modalSize = 'modal-lg';
                    } else if(options.sizeType == "small"){
                        modalSize = 'modal-sm';
                    }
                    $('.modal-dialog').addClass(modalSize);
                }

                // 'func' 속성의 함수가 존재하면 수행
                if (options && options.func && $.type(options.func) == 'function') {
                    options.func(dialog);
                }
            }
		}, options);
        return BootstrapDialog.show(args);
    },
    // $.commModal.loadFullView(title, url);
    loadFullView: function (title, url) {
        BootstrapDialog.show({
            title: title,
            draggable: true,
            closable: true,
            size: BootstrapDialog.SIZE_WIDE,
            message: function (dialog) {
                var $message = $('<div class="loadFullView"></div>');
                var pageToLoad = dialog.getData('pageToLoad');
                $message.load(pageToLoad);

                return $message;
            },
            data: {
                'pageToLoad': url
            },
            onshown: function (dialog) {
                $('.modal-dialog :input[type="text"]:enabled:visible:first').focus();
            }
        });
    },
    // 컨텐츠 모달창 오픈
    // $.commModal.openView(options);
    openView: function (options) {

		let args = $.extend({
            title: options.title,
            draggable: true,
            closable: true,
            message: options.message
		}, options, true);

        // 'func' 속성의 함수가 존재하면 수행
        if (options &&
            options.func &&
            $.type(options.func) == 'function') {
            args.onshown = options.func;
        }
        return BootstrapDialog.show(args);
    },
    // $.commModal.close();
    close: function () {
        $.each(BootstrapDialog.dialogs, function (id, dialog) {
            dialog.close();
        });
    },
    // $.commModal.idClose(obj);
    idClose: function (obj) {
        $.each(BootstrapDialog.dialogs, function (id, dialog) {
            if (id == obj)
                dialog.close();
        });
    }
}
