package business.com.user.service;

import common.user.UserInfo;

/**
 * [서비스인터페이스] - OAUTH Service IF
 *
 * @class   : OAuthService
 * @author  : LSH
 * @since   : 2023.03.17
 * @version : 1.0
 *
 *   수정일            수정자                             수정내용
 *  -------    --------    ---------------------------
 *
 */
public interface OAuthService {

    /**
     * OAUTH 정보 상세 조회
     */
    public OAuthVO viewOAuth(OAuthVO oauthVO) throws Exception;

    /**
     * OAUTH 정보 등록
     */
    public int regiOAuth(OAuthVO oauthVO) throws Exception;

    /**
     * OAUTH 정보 수정
     */
    public int updtOAuth(OAuthVO oauthVO) throws Exception;

    /**
     * OAUTH 정보 삭제
     */
    public int deltOAuth(OAuthVO oauthVO) throws Exception;
    
    /**
     * OAUTH 가입 사용자정보 조회
     */
    public UserInfo getOAuthUserInfo(String oauthId) throws Exception;

}
