package business.com.user.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.exception.PopupBusinessException;
import business.com.user.service.OAuthVO;
import business.com.user.service.UserService;
import business.com.user.service.UserVO;
import common.base.BaseService;
import common.user.UserInfo;

/**
 * [서비스클래스] - 회원관리 Service 구현 클래스
 * 
 * @class   : UserServiceImpl
 * @author  : LSH
 * @since   : 2023.03.09
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("UserService")
@SuppressWarnings({"all"})
public class UserServiceImpl extends BaseService implements UserService {

    @Resource(name = "UserDAO")
    private UserDAO userDAO;

    // 회원가입 저장처리
	@Override
	public int saveJoin(UserVO userVO) throws Exception {
		
		// 이미 가입한 회원인지 확인
		if (existUser(userVO.getUserId())) {
			throw new PopupBusinessException("이미 가입한 회원입니다.");
		}
		// 사용자정보 저장처리
		if (userDAO.regiUser(userVO) > 0) {
			// TODO. 권한정보가 규정되어야 함
			
			if (!userDAO.existRole(userVO)) {
				// 권한 저장처리
				userDAO.regiRole(userVO);
			}
			return 1;
		}
		return 0;
	}

    // 이미 가입된 회원인지 확인
	@Override
	public boolean existUser(String userId) throws Exception {
		return userDAO.existUser(userId);
	}

	// 로그인 사용자정보 조회
	@Override
	public UserInfo getUserInfo(String userId) throws Exception {
        return userDAO.getUserInfo(userId);
	}

    // OAUTH 로그인시 사용자정보 조회
	@Override
    public UserInfo getUserInfoByOauth(OAuthVO oauthVO) throws Exception {
        return userDAO.getUserInfoByOauth(oauthVO);
	}

	// 패스워드 실패시 카운터 업데이트 및 잠금시간 등록
	@Override
	public int updtPswdErrCnt(String userId, int pswdErrCnt) throws Exception {
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("userId"    , userId    );
		params.put("pswdErrCnt", pswdErrCnt);
        return userDAO.updtPswdErrCnt(params);
	}

    // 로그인시간 등록
	@Override
	public int updtLoginTime(String userId) throws Exception {
        return userDAO.updtLoginTime(userId);
	}

    // 사용자아이디,비밀번호 일치여부 확인
	@Override
	public boolean existUserPswd(String userId, String password) throws Exception {
		return userDAO.existUserPswd(userId, password);
	}
}
