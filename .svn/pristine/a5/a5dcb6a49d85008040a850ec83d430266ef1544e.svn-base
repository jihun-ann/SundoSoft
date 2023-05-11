package business.com.user.service.impl;

import org.springframework.stereotype.Repository;

import business.com.user.service.OAuthVO;
import common.base.BaseDAO;
import common.user.UserInfo;

/**
 * [DAO클래스] - OAUTH DAO
 *
 * @class   : OAuthDAO
 * @author  : LSH
 * @since   : 2023.03.17
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */
@Repository("OAuthDAO")
@SuppressWarnings({"all"})
public class OAuthDAO extends BaseDAO {

    /**
     * OAUTH 정보 상세 조회
     */
    public OAuthVO viewOAuth(OAuthVO oauthVO) {
        return (OAuthVO)view("OAuth.viewOAuth", oauthVO);
    }

    /**
     * OAUTH 정보 등록
     */
    public int regiOAuth(OAuthVO oauthVO) {
        return insert("OAuth.regiOAuth", oauthVO);
    }

    /**
     * OAUTH 정보 수정
     */
    public int updtOAuth(OAuthVO oauthVO) {
        return update("OAuth.updtOAuth", oauthVO);
    }

    /**
     * OAUTH 정보 삭제
     */
    public int deltOAuth(OAuthVO oauthVO) {
        return delete("OAuth.deltOAuth", oauthVO);
    }
    
    /**
     * OAUTH 가입 사용자정보 조회
     */
    public UserInfo getOAuthUserInfo(String oauthId) throws Exception {
        return (UserInfo)view("Login.getOAuthUserInfo", oauthId);
    }
}
