package business.com.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.user.service.OAuthService;
import business.com.user.service.OAuthVO;
import common.base.BaseService;
import common.user.UserInfo;

/**
 * [서비스클래스] - OAUTH ServiceImpl
 *
 * @class   : OAuthServiceImpl
 * @author  : LSH
 * @since   : 2023.03.17
 * @version : 1.0
 *
 *   수정일            수정자                             수정내용
 *  -------    --------    ---------------------------
 *
 */
@Service("OAuthService")
public class OAuthServiceImpl extends BaseService implements OAuthService {

    @Resource(name = "OAuthDAO")
    private OAuthDAO oauthDAO;

	@Override
	public OAuthVO viewOAuth(OAuthVO oauthVO) throws Exception {
		return oauthDAO.viewOAuth(oauthVO);
	}

	@Override
	public int regiOAuth(OAuthVO oauthVO) throws Exception {
		return oauthDAO.regiOAuth(oauthVO);
	}

	@Override
	public int updtOAuth(OAuthVO oauthVO) throws Exception {
		return oauthDAO.updtOAuth(oauthVO);
	}

	@Override
	public int deltOAuth(OAuthVO oauthVO) throws Exception {
		return oauthDAO.deltOAuth(oauthVO);
	}

	// OAUTH 가입 사용자정보 조회
	@Override
	public UserInfo getOAuthUserInfo(String oauthId) throws Exception {
        return oauthDAO.getOAuthUserInfo(oauthId);
	}
}
