package business.com.user.service;

import common.user.UserInfo;

/**
 * [서비스인터페이스] - 회원관리 Service Interface
 * 
 * @class   : UserService
 * @author  : LSH
 * @since   : 2023.03.09
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
public interface UserService {
	
	// 회원가입 저장처리
	public int saveJoin(UserVO userVO)throws Exception;

	// 이미 가입된 회원인지 확인
	public boolean existUser(String userId) throws Exception;

	// 로그인 사용자정보 조회
    public UserInfo getUserInfo(String userId) throws Exception;
    
    // OAUTH 로그인시 사용자정보 조회
    public UserInfo getUserInfoByOauth(OAuthVO oauthVO) throws Exception;
    
    // 패스워드 실패시 카운터 업데이트 및 잠금시간 등록
    public int updtPswdErrCnt(String userId, int pswdErrCnt) throws Exception;

    // 로그인시간 등록
    public int updtLoginTime(String userId) throws Exception;

    // 사용자아이디,비밀번호 일치여부 확인
    public boolean existUserPswd(String userId, String password) throws Exception;
}