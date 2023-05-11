/**
******************************************************************************************
*** 파일명    : message.js
*** 설명      : 메세지 JavaScript
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021-02-10              ntarget
******************************************************************************************
**/

//##################################
// COMMON 부분 메시지
//##################################
var MSG_TITLE_SUCC= "성공";
var MSG_SUCCESS   = "성공적으로 처리 되었습니다.";
var MSG_ERR_FAIL  = "처리중 실패하였습니다.";
var MSG_SUCC_SAVE = "저장이 완료되었습니다.";
var MSG_SUCC_DELT = "삭제가 완료되었습니다.";
var MSG_SUCC_REGI = "등록이 완료되었습니다.";
var MSG_SUCC_UPDT = "수정이 완료되었습니다.";
var MSG_SUCC_UPLD = "업로드가 완료되었습니다.";

// common
var MSG_COMM_1001 = "Name is required.";
var MSG_COMM_1002 = "시스템 오류가 발생하였습니다.";
var MSG_COMM_1003 = "저장할 내역이 없습니다.";
var MSG_COMM_1004 = "오류가 발생하였습니다.\n문제가 계속되는 경우 관리자에게 문의하여 주십시오.";
var MSG_COMM_1005 = "사용 가능합니다.";

var MSG_COMM_U001 = "사용자ID를 입력하세요.";
var MSG_COMM_U002 = "비밀번호를 입력하세요.";
var MSG_COMM_U003 = "정상적으로 로그아웃 되었습니다.";
var MSG_COMM_U004 = "중복 로그인으로 세션 종료되었습니다.";
var MSG_COMM_U005 = "정상적으로 비밀번호가 변경되었습니다.";
var MSG_COMM_U006 = "파일을 선택하세요";


var MSG_COMM_E001 = "사용자정보가 올바르지 않습니다.";//"해당 아이디는 등록된 사용자가 아닙니다.";
var MSG_COMM_E002 = "사용자정보가 올바르지 않습니다.";//"패스워드가 틀립니다. 정확한 정보를 입력하십시요.";
var MSG_COMM_E003 = "사용하지 않는 사용자입니다. 관리자에게 문의 바랍니다.";
var MSG_COMM_E004 = "승인되지 않은 사용자입니다.";
var MSG_COMM_E005 = "접속 불가능한 IP 입니다. 관라자에게 문의 바랍니다.";
var MSG_COMM_E006 = "현재 비밀번호가 틀립니다. 정확한 정보를 입력하십시요";  // MSG_COMM_E002 대체
var MSG_COMM_E007 = "해당 사용자는 로그인 5회이상 실패로 로그인을 할 수 없습니다.";
var MSG_COMM_E008 = "비밀번호 형식에 맞지 않습니다.";
var MSG_COMM_E009 = "새 비밀번호가 일치하지 않습니다.";
var MSG_COMM_E010 = "현재 비밀번호가 틀립니다. 정확한 정보를 입력하십시요";
var MSG_COMM_E011 = "로그인 후 비밀번호 변경이 가능합니다.";
var MSG_COMM_E012 = "";
var MSG_COMM_E013 = "해당 사용자는 로그인 5회이상 실패로 로그인을 할 수 없습니다.\n5분 후에 다시 시도하시기 바랍니다.";
var MSG_COMM_E014 = "세션만료 되었습니다. 다시 로그인 하셔서 사용하시기 바랍니다.";
var MSG_COMM_E015 = "로그인을 하셔야 사용이 가능합니다.";
var MSG_COMM_E016 = "권한이 없거나 세션이 만료되었습니다.";
var MSG_COMM_E017 = "정상적인 접근이 아닙니다.";
var MSG_COMM_E018 = "해당 페이지가 존재하지 않습니다.";
var MSG_COMM_E019 = "서버연결에 문제가 발생했습니다.";
var MSG_COMM_E020 = "중복 로그인으로 세션 종료되었습니다.";


var MSG_COMM_C001 = "저장하시겠습니까?";
var MSG_COMM_C002 = "삭제하시겠습니까?";
var MSG_COMM_C003 = "등록하시겠습니까?";
var MSG_COMM_C004 = "수정하시겠습니까?";
var MSG_COMM_C005 = "파일을 삭제하겠습니까?";
var MSG_COMM_C006 = "엑셀을 다운로드하시겠습니까?";
var MSG_COMM_C007 = "파일을 삭제하시겠습니까?";
var MSG_COMM_C008 = "파일을 업로드하시겠습니까?";
var MSG_COMM_C009 = "엑셀을 업로드하시겠습니까?";


//*********************** Custom Message  ***************************
//-- USM --
var MSG_USM_O001="기존 데이터는 삭제 하실 수 없습니다.";
var MSG_USM_U001="ID 중복확인을 하셔야 합니다.";
var MSG_USM_U002="수정하시려면 사용자를 선택 하셔야 합니다.";
var MSG_USM_U003="이미 등록된 아이디 입니다.";
var MSG_USM_U004="사용 가능한 아이디 입니다.";

var MSG_USM_E001="이메일 형식에 맞지 않습니다.";


//temp의 UI 메시지
var MSG_TENP_U001="ID 항목을 입력하세요";
var MSG_TENP_U002="NAME 항목을 입력하세요";
var MSG_TENP_U003="TITLE 항목을 입력하세요";

// temp의 일반 메시지
var MSG_TENP_0001="";

// temp의 error 메시지
var MSG_TEMP_E001="";


