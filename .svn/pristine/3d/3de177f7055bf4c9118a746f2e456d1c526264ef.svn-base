/**
******************************************************************************************
*** 파일명    : comm_const.js
*** 설명      : 공통상수 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.08.05              LSH
******************************************************************************************
**/

// 숫자 상수
const NUMBER = {
	// 첨부파일 용량제한 BYTES
	// (바이트단위: 500MB), 524288000
	// (바이트단위: 200MB), 209715200
	// (바이트단위: 100MB), 104857600
	// FILE_MAXBYTES: 104857600,
	FILE_MAXBYTES: 524288000,
	// 첨부파일 파일명 최대길이 (50자)
	FILE_MAXLENGTH: 30
};

// 공통 상수
const COMMONS = {
	// 첨부파일 기본허용 확장자 배열
	FILE_EXTENSIONS: [
		"txt" ,"pdf", "hwp", "doc", "docx", "ppt", "pptx", "xls","xlsx",
		"jpg", "jpeg", "png", "gif", "bmp",
		"zip", "alz", "7z"
	],
	// 이미지 첨부파일 허용 확장자 배열
	IMAGE_EXTENSIONS: ["jpg", "jpeg", "png", "gif", "bmp", "pdf"],
	// 문서 첨부파일 허용 확장자 배열
	DOCS_EXTENSIONS: ["txt","pdf","hwp","doc","docx","ppt","pptx","xls","xlsx"]
};

// 편집 모드
const MODE = {
	INSERT:  'I',
	UPDATE:  'U',
	REMOVE:  'D',
	SAVE:    'S',
	LOAD:    'L',
	LIST:    'LIST',
	VIEW:    'VIEW',
	SUBMIT:  'SUBMIT',
	TMPSAVE: 'TMPSAVE',
	MYPAGE:  'MYPAGE',
	ADD:     'ADD',
	LOGIN:   'LOGIN',
	MAIN:    'MAIN',
	SEARCH:  'SEARCH',
	OPENAPI: 'OPENAPI'
};

// 공통코드
const CODE = {
	SYSTEM:   'CT003', // 시스템
	USER: {
		INDIVIDUAL: 'MI', // 일반회원
		CORPORATE : 'MC'  // 기업회원
	}
};

const CODES = {
	// 2022.01.24 피해자사망시 의료비도 신청가능
	APLY_KIND_DPTH: ['RK1','RK3','RK4','RK5'],
	APLY_KIND_LIVE: ['RK1','RK2','RK5'],
	// 2023.01.16 살생물제품 신청구분에 따른 신청종류 코드 정의
	BIO_APLY_KIND_DPTH: ['BK3','BK4','BK5'],
	BIO_APLY_KIND_LIVE: ['BK1','BK2'],
	// 신청,접수,본조사,예비조사,지급
	RELIEF_PRGRE_STUS: ['02','04','05','06','07']
};

const SYSTEM = {
	ADMIN: {code: 'ADM', text: '관리자시스템'},
	USER:  {code: 'USR', text: '사용자시스템'}
};
const ROOT_CODE = {code: 'NONE', text: '최상위코드'};
const ROOT_MENU = {code: 'NONE', text: '최상위메뉴'};
const ROOT_ROLE = {code: 'NONE', text: '최상위역할'};

const COMBO = {
	INIT_ALL    : {code:'', text:':::전체:::'},
	INIT_DIRECT : {code:'', text:'직접입력'},
	INIT_NOT    : {code:'', text:'선택안함'}
};
const RADIO = {
	INIT_ALL: {code:'', text:'전체'}
};

const STORE = {
	USE_STTS: [{code:'1',text:'사용'},{code:'0',text:'중지'}],
	USE_YN  : [{code:'Y', text:'사용'},{code:'N', text:'중지'}],
	ESNTL_YN: [{code:'Y', text:'필수'},{code:'N', text:'아님'}],
    DOWNTRGT_YN  : [{code:'Y', text:'대상'},{code:'N', text:'아님'}],   // 다운로드대상여부
	POP_YN  : [{code:'Y', text:'팝업'},{code:'N', text:'일반'}],
	PERM_YN : [{code:'Y', text:'허용'},{code:'N', text:'불가'}],
	YES_NO  : [{code:'Y', text:'예'},{code:'N', text:'아니오'}],
	PROG_TY : [{code:'url', text:'url'}],
	EML_AT  : [{code:'Y',text:'이메일 정보 수신에 동의합니다.'}],
	MBL_AT  : [{code:'Y',text:'휴대전화 정보 수신에 동의합니다.'}],
	SX_DST  : [{code:'M',text:'남성'},{code:'F',text:'여성'}],
	OX_DST  : [{code:'Y',text:'○'},{code:'N',text:'X'}],
	YN_DST  : [{code:'Y',text:'Y'},{code:'N',text:'N'}],
	SBMT_YN : [{code:'Y',text:'제출'},{code:'N',text:'미제출'}],

	getName: function(group, code) {
		if (STORE[group] &&
			$.type(STORE[group]) == 'array') {
			let s = '';
			$.each(STORE[group], function(i,o) {
				if (o['code'] == code) {
					s = o['text'];
					return false;
				}
			});
			return s;
		}
		return '';
	},
	getAge: function(min, max, sort, formatter) {

		min = min || 10;
		max = max || 100;
		sort = sort || 'asc';
		var rows  = [];
		if (sort == 'asc') {
			for (var i = min; i <= max; i++) {
				var row = {code:i, text:i};
				if (formatter)
					row['text'] = formatter(row['text']);
				rows.push(row);
			}
		}
		else {
			for (var i = max; i >= min; i--) {
				var row = {code:i, text:i};
				if (formatter)
					row['text'] = formatter(row['text']);
				rows.push(row);
			}
		}
		return rows;
	},
	getYears: function(diff, formatter) {
		var rows  = [];
		var year = (new Date()).getFullYear()+(diff || 0);
		for (var i = 0; i < 80; i++) {
			var row = {code:year, text:year};
			if (formatter)
				row['text'] = formatter(row['text']);
			rows.push(row);
			year--;
		}
		return rows;
	},
	getMonths: function(formatter) {
		var rows  = [];
		for (var i = 1; i <= 12; i++) {
			var value = (i < 10 ? '0'+i : i+'');
			var row = {code:value, text:value};
			if (formatter)
				row['text'] = formatter(row['text']);
			rows.push(row);
		}
		return rows;
	},
	getDays: function(formatter) {
		var rows  = [];
		for (var i = 1; i <= 31; i++) {
			var value = (i < 10 ? '0'+i : i+'');
			var row = {code:value, text:value};
			if (formatter)
				row['text'] = formatter(row['text']);
			rows.push(row);
		}
		return rows;
	},
	getHours: function(nameFormatter, valueFormatter) {
		var rows  = [];
		for (var i = 1; i <= 24; i++) {
			var value = (i < 10 ? '0'+i : i+'');
			var row = {code:value, text:value};
			if (valueFormatter)
				row['code'] = valueFormatter(row['code']);
			if (nameFormatter)
				row['text'] = nameFormatter(row['text']);
			rows.push(row);
		}
		return rows;
	}
};

// 공통코드 필터함수
const CODE_FILTER = {
	RELIEF_PRGRE_STUS: function(o) {
		return ($.inArray(o['code'], CODES.RELIEF_PRGRE_STUS) >= 0);
	}
};