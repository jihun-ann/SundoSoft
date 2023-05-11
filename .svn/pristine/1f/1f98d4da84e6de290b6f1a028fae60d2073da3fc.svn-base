
package business.com.user.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.scribejava.core.model.OAuth2AccessToken;

import business.com.CommConst;
import business.com.accesslog.AccessControlService;
import business.com.user.UserError;
import business.com.user.oauth.KakaoLoginBO;
import business.com.user.oauth.NaverLoginBO;
import business.com.user.oauth.OAuthLoginBO;
import business.com.user.service.OAuthService;
import business.com.user.service.OAuthVO;
import business.com.user.service.UserService;
import business.com.user.service.UserVO;
import common.base.BaseController;
import common.user.UserInfo;
import common.util.CommUtils;
import egovframework.com.utl.slm.EgovHttpSessionBindingListener;

/**
 * [컨트롤러클래스] - 로그인(관리자)
 *      1. 로그인/로그아웃
 *      2. 중복로그인 방지
 *      3. 5회 비밀번호 실패시 잠김
 *      4. 분기별로 비밀번호 변경안내 및 변경
 *      5. 로그인 이력 남기기
 *
 * @class   : LoginController
 * @author  : ntarget
 * @since   : 2023.03.08
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Controller
@SuppressWarnings({"unchecked","rawtypes"})
public class LoginController extends BaseController {

    protected static final String FORM_USERNAME         = "username";
    protected static final String FORM_PASSWORD         = "password";

    @Autowired
    private AccessControlService accessControlService;

    @Autowired
    private UserService userService;
	
	@Autowired
	private OAuthService oauthService;

	@Autowired
	private KakaoLoginBO kakaoLoginBO;
	
	@Autowired
	private NaverLoginBO naverLoginBO;

    /**
     * 로그인화면 이동
     */
    @RequestMapping(CommConst.LOGIN_URL)
    public String login(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
    	
		// 처리타입을 세션에 담는다. (OAUTH에서 사용됨)
		session.setAttribute(CommConst.SESS_USER_ACTION, CommConst.ACT_LOGIN);
    	
    	model.addAttribute("mode", CommConst.SYSTEM_CODE);
    	model.addAttribute("act" , CommConst.ACT_LOGIN);
		// 네이버로그인 URL
    	model.addAttribute("naverAuthUrl", naverLoginBO.getAuthorizationUrl(session));
    	// 카카오로그인 URL
    	model.addAttribute("kakaoAuthUrl", kakaoLoginBO.getAuthorizationUrl(session));
    	
        logger.info("로그인 화면 이동");

        // 로그인화면 SecurityInterceptor에서 제외되어 있으므로 
        // 다음의 항목을 Attribute로 정의해야 화면에서 사용이 가능하다.
        request.setAttribute("contextPath", request.getContextPath());
        request.setAttribute("sysCd"      , CommConst.SYSTEM_CODE);
        request.setAttribute("ver"        , CommUtils.getCurrDateTime2());

        return CommConst.LOGIN_PAGE;
    }

    /**
     * 로그인 성공시 이동화면
     */
    @RequestMapping(CommConst.LOGIN_SUCCESS_URL)
    public String loginSucc(HttpServletRequest request, ModelMap model) throws Exception {

        logger.info("로그인 성공화면 이동");

        return "redirect:"+CommConst.INDEX_URL;
    }

    /**
     * 로그인 인증 처리
     */
    @RequestMapping(CommConst.LOGIN_CHECK_URL)
    @ResponseBody
    public Map loginCheck(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws Exception {

        String userId       = CommUtils.nvlTrim(request.getParameter(FORM_USERNAME));
        String password     = CommUtils.nvlTrim(request.getParameter(FORM_PASSWORD));
        UserInfo user       = (UserInfo)userService.getUserInfo(userId);

        UserError result = UserError.SUCCESS;
        
        // 로그인 검증.
        if (user == null) {
        	// 사용자정보가 없음.
            result = UserError.NOTFOUND;
        } else {
            // TODO. 비밀번호 암호화 필요
        	boolean equalPassword = userService.existUserPswd(userId, password);

            if (!CommUtils.isEmpty(user.getUseIp()) &&
                 CommUtils.nvlTrim(user.getUseIp()).indexOf(request.getRemoteAddr()) < 0 ) {
            	// 접속불가능 IP
            	result = UserError.REJECT;
            }
            else if (Integer.valueOf(CommUtils.nvlTrim(user.getPswdErrCnt(), "0")) >= 5 ) {
            	// 로그인 5회이상 실패
            	result = UserError.EXPIRED;
            }
            else if (!equalPassword) {
            	// 패스워드 틀림 (일반)
                result = UserError.NOTPASSWD;
                // 패스워드 오류 카운터
                userService.updtPswdErrCnt(
               		user.getUserId(), 
               		CommUtils.strToInt(user.getPswdErrCnt(), 0) + 1
                );
            }
        }
        // 로그인처리 수행
        _loginProcess(request, user, result);

        Map returnMap = new HashMap();
        returnMap.put("failFlag",   result.getFlag());
        returnMap.put("message" ,   result.getMessage());
        
        // 로그인성공시에만 사용자정보가 담기도록
        if (result.isSuccess()) {
        	Map userRet = new HashMap();
        	userRet.put("roleId"      , user.getRoleId());
        	userRet.put("diffDays"    , user.getDiffDays());
        	userRet.put("diffNextDays", user.getDiffNextDays());
            returnMap.put("user",  userRet);
        }
        return returnMap;
    }
    
    // 로그인 처리수행
    private void _loginProcess(HttpServletRequest request, UserInfo user, UserError result) throws Exception {
    	
    	String userNo = null;
    	if (user != null)
    		userNo = user.getUserNo();
    	else
    		result = UserError.NOTFOUND;
    	
        // 로그인처리 이력 저장
        accessControlService.regiLoginLog(request, userNo, result.getFlag());

        // 로그인 성공
        if (result.isSuccess()) {
            // 중복로그인처리(기존로그인사용자 해제)
            loginExpired(request, user.getUserId());
            // 이전세션 제거
            clearSessionInformation(request);
            
            // 회원정보 세션생성
            BeanUtils.copyProperties(user, userInfo);

            userInfo.setIpAddr(request.getRemoteAddr());

            // 로그인 시간 저장, ERR CNT 0
            userService.updtLoginTime(user.getUserId());
        }
    }

    /**
     * 로그아웃 처리.
     */
    @RequestMapping(CommConst.LOGOUT_URL)
    public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
    	
        clearSessionInformation(request);
        request.getSession().invalidate();

        logger.info("로그아웃 화면 이동");
        
        // 관리자시스템인 경우 로그인페이지로 이동
        return "redirect:"+CommConst.LOGIN_URL;
    }
    
	/**
	 * 2022.10.04 ntarget 세션타임아웃 시간을 연장한다. (Egov) 
	 * Cookie에 COOK.LATEST.TIME, COOK.EXPIRE.TIME 기록하도록 한다.
	 * @return result - String
	 * @exception Exception
	 */
    @RequestMapping("/com/common/refreshSessionTimeout.do")
    @ResponseBody
    public Map refreshSessionTimeout() throws Exception {
    	logger.info("세션타임아웃 시간 연장");
        return getSuccess();
    }

	// 네이버 로그인 성공시 callback호출 메소드
	@RequestMapping("/com/common/naverLoginCheck.do")
	public String naverLoginCheck(ModelMap model, 
			@RequestParam String code, 
			@RequestParam String state,
			HttpServletRequest request, 
			HttpSession session) throws Exception {
		
		logger.debug("NAVER 로그인 성공");

		// 접근토큰 발급
		OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state);

		// 로그인 사용자 정보를 읽어온다.
		String apiResult = naverLoginBO.getUserProfile(oauthToken);

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj    = (JSONObject) jsonParser.parse(apiResult);
		JSONObject respObj    = (JSONObject) jsonObj.get("response");

		return _loginOauthCheck(request, session, model, naverLoginBO, oauthToken, 
				OAuthVO.builder()
				.oathCd (CommConst.OAUTH_NAVER)
				.oathId ((String)respObj.get("id"    )) // // 고유식별정보
				.userNm ((String)respObj.get("name"  ))
				.email  ((String)respObj.get("email" ))
				.mobile ((String)respObj.get("mobile"))
				.token  (oauthToken.getAccessToken())
				.build     ()
		);
	}

	// 카카오 로그인 성공시 callback
	@RequestMapping("/com/common/kakaoLoginCheck.do")
	public String kakaoLoginCheck(ModelMap model, 
			@RequestParam String code, 
			@RequestParam String state, 
			HttpServletRequest request, 
			HttpSession session) throws Exception {

		logger.debug("KAKAO 로그인 성공");

		// 접근토큰 발급
		OAuth2AccessToken oauthToken = kakaoLoginBO.getAccessToken(session, code, state);

		// 로그인 사용자 정보를 읽어온다.
		String apiResult = kakaoLoginBO.getUserProfile(oauthToken);

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj    = (JSONObject) jsonParser.parse(apiResult);
		JSONObject respObj    = (JSONObject) jsonObj.get("kakao_account");
		JSONObject profObj    = (JSONObject) respObj.get("profile");
		
		return _loginOauthCheck(request, session, model, kakaoLoginBO, oauthToken,
			OAuthVO.builder()
			.oathCd (CommConst.OAUTH_KAKAO)
			.oathId (String.valueOf((Long)jsonObj.get("id"))) // 고유식별정보
			.userNm ((String)profObj.get("nickname"        )) // profile.name으로 바꿔야함
			.email  ((String)respObj.get("email"           )) // profile.account_email로 가져올것
			.mobile ((String)profObj.get("phone_number"    ))
			.token  (oauthToken.getAccessToken())
			.build     ()
		);
	}

	
	private String _loginOauthCheck(
			HttpServletRequest request, 
			HttpSession session,
			ModelMap model,
			OAuthLoginBO oauthBO, 
			OAuth2AccessToken oauthToken, 
			OAuthVO oauthVO) throws Exception {
		
		logger.debug(oauthVO.getOathCd() + " API PROFILE "
				+ "[ID     = "+oauthVO.getOathId()
				+ ",name   = "+oauthVO.getUserNm()
				+ ",email  = "+oauthVO.getEmail()
				+ ",mobile = "+oauthVO.getMobile()+"]");

		String act  = (String)session.getAttribute(CommConst.SESS_USER_ACTION); // 처리타입
		String code = (String)session.getAttribute(CommConst.SESS_USER_CODE);   // 가입유형
		
		// 해당하는 사용자정보 조회
		UserInfo oauthUser = userService.getUserInfoByOauth(oauthVO);

		
		// 1. 로그인인 경우
		// ---------------------------------------------------------------------
		if (CommConst.ACT_LOGIN.equals(act)) {
			
			// OAUTH 인증 사용자 로그인 처리
		    _loginProcess(request, oauthUser, UserError.SUCCESS);
		    
		    // 사용자정보가 없는 경우
		    if (oauthUser == null) {
		    	// 연동해제 처리
				oauthBO.unlink(oauthToken);
				// 오류 반환 (사용자 정보가 없습니다)
				return _getOauthFailure(model, act, UserError.NOTFOUND);
		    }
			// 메인페이지로 이동
		    return _getOauthSuccess(model, CommConst.ACT_MAIN);
		}
		// 2. 회원가입인 경우
		// ---------------------------------------------------------------------
		else if (CommConst.ACT_JOIN.equals(act)) {

			// 이미 가입된 회원인 경우
			if (oauthUser != null) {
				// 오류 메세지 반환 (이미 가입된 회원입니다. 로그인하세요.)
				return _getOauthFailure(model, CommConst.ACT_LOGIN, UserError.EXIST_USER);
			}
			// 이미 해당 이메일로 가입된 경우
			if (userService.existUser(oauthVO.getEmail())) {
		    	// 현재 연결된 OAUTH 연동해제 처리
				oauthBO.unlink(oauthToken);
				// 오류 메세지 반환 (이미 해당 이메일로 가입된 회원이 있습니다. 로그인하세요.)
				return _getOauthFailure(model, CommConst.ACT_LOGIN, UserError.EXIST_EMAIL);
			}

			// 가입유형이 개인회원인 경우
			if (CommConst.USER_INDIVIDUAL.equals(code)) {
				
				UserVO userVO = UserVO.builder()
						.roleId(CommConst.USER_INDIVIDUAL_ROLE)
						.userCd(code)
						.build();
				userVO.fromOauth(oauthVO);

				// 회원정보 저장 처리
				if (userService.saveJoin(userVO) > 0) {
					oauthVO.setUserNo(userVO.getUserNo());
					// OAUTH 정보 저장 처리
					oauthService.regiOAuth(oauthVO);
				}
				
				// 가입완료 페이지로 이동
				return _getOauthSuccess(model, CommConst.ACT_DONE);
			}
			// 가입유형이 기업회원인 경우
			else if (CommConst.USER_CORPORATE.equals(code)) {
				
				// OAUTH 정보를 임시 세션에 담는다.
				session.setAttribute(CommConst.SESS_USER_INFO, oauthVO);
				
				// 기업정보입력 페이지로 이동
			    return _getOauthSuccess(model, CommConst.ACT_JOIN);
			}
		}
		// ---------------------------------------------------------------------
		// 팝업페이지이므로 하위 페이지를 이동시켜야 함.
	    return _getOauthFailure(model, CommConst.ACT_ERROR, UserError.UNKNOWN);
	}
	
	private String _getOauthFailure(ModelMap model, String act, UserError err) {
		model.addAttribute("act"     , act);
		model.addAttribute("success" , CommConst.NO);
		// 오류 메세지 반환 (이미 개인회원으로 가입된 회원입니다. 로그인하세요.)
		model.addAttribute("message" , err.getMessage());
		model.addAttribute("failFlag", err.getFlag());
		return "com/common/loginCheck";
	}
	
	private String _getOauthSuccess(ModelMap model, String act) {
		model.addAttribute("act"     , act);
		model.addAttribute("success" , CommConst.YES);
		return "com/common/loginCheck";
	}
	
    /**
     * 중복로그인 처리
     */
    private void loginExpired(HttpServletRequest request, String userId) throws Exception {
        EgovHttpSessionBindingListener listener = new EgovHttpSessionBindingListener();
        request.getSession().setAttribute(userId, listener);

        // 세션중복로그인 설정.
        //request.getSession().setAttribute("SESS.EXPIRED",   "1");
    }

    // Session Remove
    private void clearSessionInformation(HttpServletRequest request) {
        request.getSession().removeAttribute(CommConst.SESS_PAGE_INFO);
        request.getSession().removeAttribute(CommConst.SESS_MENU_INFO);
        request.getSession().removeAttribute(CommConst.SESS_USER_ACTION);
        request.getSession().removeAttribute(CommConst.SESS_USER_INFO);
        request.getSession().removeAttribute(CommConst.SESS_USER_CODE);
    }
}
