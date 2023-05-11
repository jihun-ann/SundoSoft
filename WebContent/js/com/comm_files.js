/**
******************************************************************************************
*** 파일명    : comm_file.js
*** 설명      : 사용자 정의 파일처리 컴포넌트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2023.03.21              LSH
******************************************************************************************
**/

//===========================================================================//
// 게시판첨부파일 컨트롤 (수정해야함)
//===========================================================================//
$.fn.appBbsFile = function ( args ) {

	var options = $.extend({

		// 생성시 기본 데이터
		initData: {},
		// 컨트롤 전체를 감싸는 영역
		controlWrap: 'file_control_wrap',
		// 업로드 버튼을 감싸는 영역
		buttonWrap: 'file_button_wrap',
		// 파일박스 전체를 감싸는 영역
		fileWrap: 'file_wrap',
		// 파일선택박스를 감싸는 영역
		boxWrap: 'box_wrap',
		// 버튼을 감싸는 영역
		btnWrap: 'file_btn_wrap',
		// 파일선택박스 객체
		fileBox: 'input_file',
		// 파일텍스트박스 객체
		textBox: 'input_text',
		// 파일선택 버튼
		fileBtn: 'btn_file',
		// 파일추가 버튼
		addBtn: 'btn_add',
		// 파일삭제 버튼
		delBtn: 'btn_del',
		// 첨부파일 용량제한 (바이트단위: 200MB), 209715200
		maxBytes: NUMBER.FILE_MAXBYTES,
		// 2022.01.21 첨부파일 파일명 글자수 최대길이 (50자)
		maxLengthName: NUMBER.FILE_MAXLENGTH,
		// 첨부파일 확장자 제한 (허용하는 확장자 목록)
		extensions: COMMONS.FILE_EXTENSIONS,
		//[	"txt" ,"pdf", "hwp", "doc", "docx", "ppt", "pptx", "xls","xlsx",
		//	"jpg", "jpeg", "png", "gif", "bmp",
		//	"zip", "alz", "7z"
		//],
		// 초기 표시 갯수
		initCount: 1,
		// 추가 최대 갯수
		maxCount: 5,
		// 추가,삭제 가능 여부
		multiple: true,
		// 메세지 alert 여부
		isAlert: false,
		// 확장자 체크 여부
		isExt:   false,
		// 용량 제한 체크 여부
		isLimit: false,

		// 파일 타입
		fileType: false,

		// [업로드모달창] 업로드창 제목
		title:    '첨부파일 업로드',
		// [업로드모달창] 업로드폼 명칭
		formId:   'uploadForm',
		// [업로드모달창] 파일영역 레이어 명칭
		attachId: 'file-attach',
		// [업로드모달창] 업로드창 추가 EL
		appendEl: false,
		
		// 업로드 URL
		uploadUrl  : getUrl('/usr/bbs/uploadBbsFile.do'),
		// 파일 목록 조회 URL
		loadUrl    : getUrl('/usr/bbs//searchBbsFile.do'),
		// 파일 단일 삭제 URL
		removeUrl  : getUrl('/usr/bbs/deleteBbsFile.do'),
		// 파일 다운로드 URL
		downloadUrl: getUrl('/usr/bbs/downBbsFile.do'),
		// 압축파일 다운로드 URL
		downloadZipUrl: getUrl('/usr/bbs/downloadZipBbsFile.do')

	}, args);

	if (options.fileType)
		options.initData['fileType'] = options.fileType;

	//현재객체
	var thisCmp = this;

	//레이어객체
	var thisElm = $(this);

	// 첨부파일박스 생성
	this.init = function( data, elm ) {

		options.initData = (data || options.initData);

		if (elm)
			thisElm = elm;

		// 이전데이터 제거
		this.destroy();

		if (thisElm.find('.'+options.controlWrap).length == 0)
			thisElm.append('<div class="'+options.controlWrap+'"></div>');
		if (thisElm.find('.'+options.buttonWrap).length == 0)
			thisElm.append('<div class="'+options.buttonWrap +'"></div>');

		// 첨부파일 선택박스 생성
		this.initBox();
		//this.createBox(thisElm.find('.'+options.controlWrap), options.initData);

		return this;
	};
	
	this.initBox = function() {
		for (let i = 0; i <options.initCount; i++) {
			// 첨부파일 선택박스 생성
			this.createBox(thisElm.find('.'+options.controlWrap), options.initData);
		}		
	};
	
	// 이전 데이터 제거
	this.destroy = function() {
		thisElm.find("."+options.fileWrap).each(function() {
			$(this).remove();
		});
		thisElm.find("."+options.btnWrap).each(function() {
			$(this).remove();
		});
	};

	// 파일박스 리셋
	this.reset = function() {
		this.init();
	};

	// 파일선택박스 이벤트 바인딩
	this.bindBox = function(elm) {

		var wrp  = '.'+ options.fileWrap;
		var fadd = '.'+ options.addBtn;
		var fdel = '.'+ options.delBtn;
		var fbox = '.'+ options.fileBox;
		var tbox = '.'+ options.textBox;

		// 첨부파일 파일선택 변경시
		// 텍스트박스에 파일명을 표시해주는 이벤트
		elm.on('change', fbox, function() {
			// 파일명만 추출한다.
			var fname = $(this).val().split("\\").pop();
			// 텍스트박스에 셋팅한다.
			$(this).closest(wrp).find(tbox).val(fname);
		});
		// 파일선택 추가 이벤트
		elm.on("click", fadd, function() {
			var oform = $(this).closest(wrp);
			// 최대갯수 체크
			if (thisElm.find(fbox).length >= options.maxCount) {
				$.commMsg.alert('추가할 최대 갯수는 '+options.maxCount+'개 입니다.');
				return false;
			}
			// 객체복사
			var cform = oform.clone(true).hide();
			cform.find('input[name="fileNo"]'  ).val("");
			cform.find('input[name="fileName"]').val("");
			cform.end().find(fbox).off("change");
			cform.insertAfter(oform);
			cform.fadeIn(200);
			return false;
		});
		// 파일선택 삭제 이벤트
		elm.on("click", fdel, function() {
			var oform = $(this).closest(wrp);
			var title = oform.find(tbox).attr("title");
			var len   = oform.parent().find(tbox+"[title='" + title + "']").length;
			var obox  = oform.find(tbox);
			if (len <= 1) {
				$.commMsg.alert('파일은 최소 1개 이상 등록되어야 합니다.');
				return false;
			}
			// 화면에서 파일항목을 삭제한다.
			var fnDelete = function() {
				oform.fadeOut(200, function() { oform.remove(); });
				return false;
			};
			if (obox.val() === "") {
				fnDelete();
				return false;
			}
			if (confirm('파일을 삭제하시겠습니까?')) {
				fnDelete();
				return false;
			}
			return false;
		});
	};

	// 파일선택박스 생성
	this.createBox = function(elm, data) {
		let dom = $('<div class="'+options.fileWrap+'"></div>');
		dom.append('<input type="hidden" name="docuNo"   value="'+$.commUtil.nvlTrim(data['dcmtNo'  ])+'"/>');
		dom.append('<input type="hidden" name="fileType" value="'+$.commUtil.nvlTrim(data['fileType'])+'"/>');
		dom.append('<input type="hidden" name="filePath" value="'+$.commUtil.nvlTrim(data['filePath'])+'"/>');
		dom.append('<input type="hidden" name="fileNo"   value="'+$.commUtil.nvlTrim(data['sn'      ])+'"/>');
		dom.append('<input type="hidden" name="needYn"   value="'+$.commUtil.nvlTrim(data['needYn'  ])+'"/>');
		dom.append('<input type="hidden" name="fileYn"   value="N"/>');
		dom.append('<input type="text"   name="fileName" value="'+$.commUtil.nvlTrim(data['fileName'])+'" class="'+options.textBox+'" title="filebox" readonly="readonly"/>');
		dom.append('<div class="'+options.boxWrap+'"></div>');
		dom.find("."+options.boxWrap).append('<input type="file" name="upfile" class="'+options.fileBox+'">');
		dom.find("."+options.boxWrap).append('<button type="button" class="btn btn-default '+options.fileBtn+'"></button>');

		// 다중갯수 허용이면
		if (options.multiple) {
			dom.append('<button type="button" class="btn btn-default '+options.addBtn+'"><span class="fa fa-plus"  aria-hidden="true"></span></button>');
			dom.append('<button type="button" class="btn btn-default '+options.delBtn+'"><span class="fa fa-minus" aria-hidden="true"></span></button>');
		}
		thisCmp.bindBox(dom);

		elm.append(dom);
	};

	// 첨부파일 데이터 로드 (수정시에 호출)
	this.loadBox = function( url, params ) {
		// 첨부파일 목록 초기화
		this.destroy();
		$.ajaxUtil.ajaxLoad(url, params, function(result) {
			var rows = result.rows;
			if (rows &&
				rows.length &&
				rows.length > 0) {
				// 행만큼 선택박스 생성
				$.each(rows, function(i, row) {
					thisCmp.createBox(thisElm.find('.'+options.controlWrap), row);
				});
			}
			else {
				// 기본선택박스 생성
				thisCmp.createBox(thisElm.find('.'+options.controlWrap), params);
			}
		});
	};

	// 첨부파일 목록조회 (상세조회시에 호출)
	// dom: 첨부파일목록이 표시될 레이어 객체
	// args.isEasyUI: EasyUI 그리드 사용여부
	// args.loadUrl: 첨부파일 목록조회 URL
	// args.params: 첨부파일 목록조회 조건
	// args.downloadUrl: 첨부파일 다운로드 URL
	// args.removeUrl: 첨부파일 단일삭제 URL
	// args.removeCallback: 첨부파일 단일삭제후 실행할 함수
	this.select = function(args) {

		var params         = args.params;
		var loadUrl        = args.loadUrl || options.loadUrl;
		//var removeUrl      = args.removeUrl || options.removeUrl;
		var downloadUrl    = args.downloadUrl || options.downloadUrl;
		//var removeCallback = args.removeCallback;
		
		$.ajaxUtil.ajaxLoad(loadUrl, params, function(result) {
			var rows = result.rows;
			if (rows &&
				rows.length &&
				rows.length > 0) {

				var table = $('<table class="file-table"></table>');
				table.append('<tbody></tbody>');

				$.each(rows, function(i,row) {
					var keys = ' data-type="'+row['fileType']+'"'
							 + ' data-no="'  +row['dcmtNo']+'"'
							 + ' data-seq="' +row['sn']+'"';

					var tr = $('<tr></tr>');
					tr.append('<td></td>');
					//tr.append('<td></td>');
					tr.find('td:first').append('<a href="javascript:void(0);"'+keys+' class="icon-anchor btnFileDown">'+row['fileName']+'</a>');
					// 2021.11.23 LSH 첨부파일 상세에서 삭제하는 로직 제외
					//tr.find('td:last').append('<button '+keys+' class="icon-anchor btnFileDelt"><i class="fa fa-remove" aria-hidden="true"></i></button>');
					table.find('tbody').append(tr);
				});
				thisElm.html('');
				thisElm.append(table);
				thisElm.find(".btnFileDown").on('click', function() {
					thisCmp.download(downloadUrl, {
						fileType: $(this).data('type'),
						dcmtNo:   $(this).data('no'),
						sn:       $(this).data('seq')
					});
				});
				/* 2021.11.23 LSH 첨부파일 상세에서 삭제하는 로직 제외
				thisElm.find(".btnFileDelt").on('click', function() {
					thisCmp.remove(removeUrl, {
						fileType: $(this).data('type'),
						dcmtNo:   $(this).data('no'),
						sn:       $(this).data('seq')
					}, removeCallback);
				});
				*/
			}
		});
	};

	// 첨부파일 업로드 VALIDATION
	// args.isAlert : 메세지 alert 표시 여부
	// args.isExt   : 확장자 체크 여부
	// args.extensions : 허용가능 확장자 배열 (확장자 체크시에만 필수)
	// args.isLimit : 용량 체크 여부
	// args.maxBytes : 허용가능 바이트단위 용량크기 (용량 체크시에만 필수)
	this.validate = function(args) {
		let check = true;
		let extensions = (args.extensions || options.extensions);
		let maxBytes   = (args.maxBytes   || options.maxBytes);
		let isAlert    = (args.isAlert    || options.isAlert);
		let isExt      = (args.isExt      || options.isExt);
		let isLimit    = (args.isLimit    || options.isLimit);

		thisElm.find('input[name="upfile"]').each(function(i) {
			let fobj  = $(this);
			let nfile = fobj.val();
			let ofile = thisElm.find('input[name="fileName"]').eq(i).val();
			let need  = thisElm.find('input[name="needYn"]'  ).eq(i).val();

			if (nfile === '')
				thisElm.find('input[name="fileYn"]').eq(i).val('N');
			else
				thisElm.find('input[name="fileYn"]').eq(i).val('Y');

			//이미 false 체크된 경우 SKIP
			if (check == false)
				return;

			if (nfile == '' && ofile == '') {
				//필수 체크가 아닌 경우 SKIP
				if (need != 'Y')
					return;
				if (isAlert)
					$.commMsg.alert('파일을 선택해 주세요.');
				check = false;
				return;
			}
			// 파일이 있는 경우
			if (!$.commUtil.empty(nfile)) {
				// 파일명 길이체크
				if ($.fileUtil.checkMaxLength(ofile, options.maxLengthName, true) == false) {
					check = false;
					return false;
				}
				// 확장자 체크
				if (isExt) {
					if ($.fileUtil.checkExtension(fobj, extensions, isAlert) == false) {
						check = false;
						return;
					}
				}
				// 용량 체크
				if (isLimit) {
					if ($.fileUtil.checkMaxbytes(fobj, maxBytes, isAlert) == false) {
						check = false;
						return;
					}
				}
			}
		});
		return check;
	};

	// 첨부파일 삭제
	// url : 첨부파일 삭제 URL
	// seq : 첨부파일 고유번호
	this.remove = function( url, params, callback ) {
		
		url = (url || options.removeUrl);
		
		$.commMsg.confirm('정말 삭제하시겠습니까?', function() {
	   		$.ajaxUtil.ajaxLoad(url, params, callback);
		});
		return false;
	};

	// 첨부파일 다운로드 (링크 클릭시 처리)
	this.download = function( url, params ) {
		url = (url || options.downloadUrl);
		// 파일 다운로드 실행
		$.formUtil.submitForm(url, {params: params});
		return false;
	};

	// 2021.08.03 첨부파일 압축 다운로드 (링크 클릭시 처리)
	this.downloadZip = function( url, params ) {
		url = (url || options.downloadZipUrl);
		// 파일 압축 다운로드 실행
		$.formUtil.submitForm(url, {params: params});
		return false;
	};

	return this;
};

