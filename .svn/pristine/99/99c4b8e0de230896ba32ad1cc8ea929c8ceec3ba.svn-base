package business.com;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import common.util.CommUtils;
import common.util.properties.ApplicationProperty;

/**
 * [상수클래스] - 공통 상수
 *
 * @class   : CommConst
 * @author  : ntarget
 * @since   : 2021.02.08
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
public class CommConst {

	public final static String INSERT  = "I";        // 등록
    public final static String UPDATE  = "U";        // 수정
    public final static String DELETE  = "D";        // 삭제
    public final static String SAVE    = "S";        // 저장
    public final static String LOAD    = "L";        // 로드
    public final static String LIST    = "LIST";     // 목록
    public final static String VIEW    = "VIEW";     // 상세조회
    public final static String OPEN    = "OPEN";     // 오픈
    public final static String SUBMIT  = "SUBMIT";   // 제출하기
    public final static String TMPSAVE = "TMPSAVE";  // 임시저장
    public final static String RESULT  = "RESULT";   // 결과
    
    public final static String YES = "Y";
    public final static String NO  = "N";
    
    public static final String ACT_LOGIN = "LOGIN"; // 로그인 ACTION
    public static final String ACT_JOIN  = "JOIN" ; // 회원가입 ACTION
    public static final String ACT_MAIN  = "MAIN" ; // 메인 ACTION
    public static final String ACT_DONE  = "DONE" ; // 완료 ACTION
    public static final String ACT_ERROR = "ERROR"; // 오류 ACTION
    
    public static final String SYSTEM_CODE               = ApplicationProperty.get("system.code");               // 시스템코드
    public static final String IS_AUTH_CHECK             = ApplicationProperty.get("auth.check");                // 권한체크 사용여부
    public static final String EXCLUDE_ACC_PROG          = ApplicationProperty.get("exclude.acc.prog");          // 페이지세션에 저장할 페이지 제외 프로그램.
    public static final String SESS_PAGE_INFO            = ApplicationProperty.get("SESS.PAGEINFO");             // 페이지세션명
    public static final String SESS_MENU_INFO            = ApplicationProperty.get("SESS.MENUINFO");             // 메뉴세션명
    public static final String SESS_ACCESS_URL           = ApplicationProperty.get("SESS.ACCESSURL");            // 접근URL세션명
    public static final String SESS_SYSTEM_CODE          = ApplicationProperty.get("SESS.SYSTEMCODE");           // 시스템코드세션명
    public static final String SESS_MENU_LIST            = ApplicationProperty.get("SESS.MENULIST");             // 메뉴목록세션명

    public static final String SERVER_NAME               = ApplicationProperty.get("system.servername");         // 서버명
    public static final String ROLE_RESTRICTED           = ApplicationProperty.get("system.role.restricted");    // 제한된사용자 롤

    public static final String REMOVE_PATH               = ApplicationProperty.get("upload.remove.dir");         // 삭제된 파일의 ROOT 디렉토리
    public static final String EXCEL_TEMPLATE_PATH       = ApplicationProperty.get("excel.templete.dir");        // 엑셀다운로드용 템플릿 경로
    
    public static final String SYSTEM_CHARSET            = "UTF-8";
    public static final String EMAIL_BASE64_ENCODE       = "B";
    public static final int    EMAIL_TYPE_CONTENT        = 1; // 이메일 내용타입 : HTML 컨텐츠
    public static final int    EMAIL_TYPE_TEMPLATE       = 2; // 이메일 내용타입 : Thymeleaf를 이용한 템플릿 컨텐츠
    
    public static final String EMAIL_SEND_SUCCESS        = "00";
    public static final String EMAIL_SEND_FAILURE        = "99";
    
    /*
     * SecurityInterceptor 에서 사용하는 상수
     **/
    public static final String COMMON_URL_PATTERN        = "/com/";
    public static final String COMMON_SYSTEM_CODE        = "COM";
    
    public static final String ERROR_URL_PATTERN         = "/com/error/";
    public static final String URL_POSTFIX               = ".do";

    public static final String LOGIN_PAGE                = "com/common/login";
    public static final String LOGIN_CHECK_PAGE          = "com/common/loginCheck";
    public static final String LOGIN_SUCCESS_PAGE        = "com/common/loginSucc";
    public static final String LOGOUT_PAGE               = "com/common/logout";
    
    public static final String LOGIN_URL                 = "/" + LOGIN_PAGE         + URL_POSTFIX;
    public static final String LOGIN_CHECK_URL           = "/" + LOGIN_CHECK_PAGE   + URL_POSTFIX;
    public static final String LOGIN_SUCCESS_URL         = "/" + LOGIN_SUCCESS_PAGE + URL_POSTFIX;
    public static final String LOGOUT_URL                = "/" + LOGOUT_PAGE        + URL_POSTFIX;
    // TODO. 관리자/사용자에 따라 다르게 INDEX URL을 설정해야함
    public static final String INDEX_PAGE                = "usr/main/main";
    public static final String INDEX_URL                 = "/" + INDEX_PAGE + URL_POSTFIX;
    
    // ACCESS LOG 기록여부
    public static final boolean IS_ACCESS_LOG = false;
    
    /*
     * 네이버 로그인 설정값
     **/
    public static final String OAUTH_NAVER               = ApplicationProperty.get("OAUTH.NAVER");
    public static final String OAUTH_NAVER_CLIENT_ID     = ApplicationProperty.get("OAUTH.NAVER.CLIENT_ID");
    public static final String OAUTH_NAVER_CLIENT_SECRET = ApplicationProperty.get("OAUTH.NAVER.CLIENT_SECRET");
    public static final String OAUTH_NAVER_SESSION_STATE = ApplicationProperty.get("OAUTH.NAVER.SESSION_STATE");
    public static final String OAUTH_NAVER_CALLBACK_URL  = ApplicationProperty.get("OAUTH.NAVER.CALLBACK_URL");
    public static final String OAUTH_NAVER_AUTHORIZE_URL = ApplicationProperty.get("OAUTH.NAVER.AUTHORIZE_URL");
    public static final String OAUTH_NAVER_TOKEN_URL     = ApplicationProperty.get("OAUTH.NAVER.TOKEN_URL");
    public static final String OAUTH_NAVER_PROFILE_URL   = ApplicationProperty.get("OAUTH.NAVER.PROFILE_URL");
    public static final String OAUTH_NAVER_UNLINK_URL    = ApplicationProperty.get("OAUTH.NAVER.UNLINK_URL");

    /*
     * 카카오 로그인 설정값
     **/
    public static final String OAUTH_KAKAO               = ApplicationProperty.get("OAUTH.KAKAO");
    public static final String OAUTH_KAKAO_CLIENT_ID     = ApplicationProperty.get("OAUTH.KAKAO.CLIENT_ID");
    public static final String OAUTH_KAKAO_CLIENT_SECRET = ApplicationProperty.get("OAUTH.KAKAO.CLIENT_SECRET");
    public static final String OAUTH_KAKAO_SESSION_STATE = ApplicationProperty.get("OAUTH.KAKAO.SESSION_STATE");
    public static final String OAUTH_KAKAO_CALLBACK_URL  = ApplicationProperty.get("OAUTH.KAKAO.CALLBACK_URL");
    public static final String OAUTH_KAKAO_AUTHORIZE_URL = ApplicationProperty.get("OAUTH.KAKAO.AUTHORIZE_URL");
    public static final String OAUTH_KAKAO_TOKEN_URL     = ApplicationProperty.get("OAUTH.KAKAO.TOKEN_URL");
    public static final String OAUTH_KAKAO_PROFILE_URL   = ApplicationProperty.get("OAUTH.KAKAO.PROFILE_URL");
    public static final String OAUTH_KAKAO_UNLINK_URL    = ApplicationProperty.get("OAUTH.KAKAO.UNLINK_URL");

    // 개인회원
    public static final String USER_INDIVIDUAL = "UI";
    // 기업회원
    public static final String USER_CORPORATE  = "UC";
    // 회원가입/로그인시 OAUTH에서 사용되는 임시세션 - 가입유형
    public static final String SESS_USER_CODE   = "SS_USER_CODE";
    // 회원가입/로그인시 OAUTH에서 사용되는 임시세션 - 회원가입/로그인
    public static final String SESS_USER_ACTION = "SS_USER_ACTION";
    // 회원가입/로그인시 OAUTH에서 사용되는 임시세션 - 회원정보
    public static final String SESS_USER_INFO   = "SS_USER_INFO";
    
    // 일반회원 사용자 ROLE
    public static final String USER_INDIVIDUAL_ROLE = "ROLE_AUTH_USR";
    // 기업회원 사용자 ROLE
    public static final String USER_CORPORATE_ROLE = "ROLE_AUTH_COP";

    /*
     * KG 모빌리언스 휴대폰 본인인증 관련 설정값
     */
    // 휴대폰 본인인증 - 처리구분 ( 00 : 테스트결제, 10 : 실거래결제 ) - "10"
    public static final String MOBILIANS_PAYMODE  = ApplicationProperty.get("MOBILIANS.PAYMODE");
    // 휴대폰 본인인증 - SMS발송 (61:SMS발송  67:SMS미발송) - "61"
    public static final String MOBILIANS_CIMODE   = ApplicationProperty.get("MOBILIANS.CIMODE");
    // 휴대폰 본인인증 - 서비스 아이디
    public static final String MOBILIANS_SVCID    = ApplicationProperty.get("MOBILIANS.SVCID");
    // 휴대폰 본인인증 - 가맹점 도메인
    public static final String MOBILIANS_SITEURL  = ApplicationProperty.get("MOBILIANS.SITEURL");
    // 휴대폰 본인인증 - OK URL (처리결과수신 페이지)
    public static final String MOBILIANS_OKURL    = ApplicationProperty.get("MOBILIANS.OKURL");
    // 휴대폰 본인인증 - NOTI URL (가맹점결과전송 페이지)
    public static final String MOBILIANS_NOTIURL  = ApplicationProperty.get("MOBILIANS.NOTIURL");
    // 휴대폰 본인인증 - 암호화 사용 여부 - "Y"
    public static final String MOBILIANS_CRYPTYN  = ApplicationProperty.get("MOBILIANS.CRYPTYN");
    // 휴대폰 본인인증 - 암호화 Key 구분 (1 or 2 : 가맹점 관리자 등록 후 사용) - "1"
    public static final String MOBILIANS_KEYGB    = ApplicationProperty.get("MOBILIANS.CRYPTGB");
    // 휴대폰 본인인증 - 암호화 Key
    public static final String MOBILIANS_PASSWORD = ApplicationProperty.get("MOBILIANS.PASSOWRD");
    
    /*
     * KODATA API 설정값
     */
    public static final String KODATA_API_URL      = ApplicationProperty.get("KODATA.API.URL");
    public static final String KODATA_API_PATH     = ApplicationProperty.get("KODATA.API.PATH");
    public static final String KODATA_API_USERID   = ApplicationProperty.get("KODATA.API.USERID");
    public static final String KODATA_API_PROCESS  = "S";
    public static final String KODATA_API_PIDAGRYN = "Y";
    public static final String KODATA_API_FORMAT   = "JSON";

    // 관리자 URL 패턴
    public final static String ADMIN_URL_PATTERN = "/adm/";
    // 비회원 임시 ID
    public final static String USER_GUEST_ID = "guest";
    // 최상위 CODE
    public final static String ROOT_CODE = "NONE";
    // 최상위 ROLE
    public final static String ROOT_ROLE = "NONE";
    // 최상위 메뉴
    public final static String ROOT_MENU = "NONE";
    // 관리자 ROLE 배열
    public final static String[] ADMIN_ROLES = {"ROLE_AUTH_ADM", "ROLE_AUTH_SYS"};
    // 관리자 기본명칭
    public final static String ADMIN_NAME = "관리자";
    
    // 로그인 여부를 확인하는 함수
    public static boolean isLogin(String userId) {
    	if (CommUtils.isNotEmpty(userId) && 
        	CommConst.USER_GUEST_ID.equals(userId) == false)
        	return true;
        return false;
    }
    // 관리자인지 확인하는 함수
    public static boolean isAdminRole(String roleId) {
    	return Arrays.asList(ADMIN_ROLES).contains(roleId);
    }
    // 회원사용자인지 확인하는 함수
    public static boolean isUserRole(String roleId) {
    	return USER_INDIVIDUAL_ROLE.equals(roleId);
    }
    public static boolean isAuthCheck() {
    	return IS_AUTH_CHECK.equalsIgnoreCase("true");
    }
    // WEB ROOT 경로
    public static String getWebRootDir(HttpServletRequest request) {
    	return request.getServletContext().getRealPath("");
    }
    
    // 처리상태 - 제출
    public static final String PROCESS_SUBMIT    = "01";
    // 처리상태 - 미제출
    public static final String PROCESS_NOTSUBMIT = "02";
    
}
