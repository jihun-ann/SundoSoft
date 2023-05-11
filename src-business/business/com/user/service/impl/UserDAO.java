package business.com.user.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.com.user.service.OAuthVO;
import business.com.user.service.UserVO;
import common.base.BaseDAO;
import common.user.UserInfo;

/**
 * [DAO클래스] - 회원가입 관리 DAO 클래스
 * 
 * 사용 가능한  DAO Statement Method
 * 1. list          : 리스트 조회시 사용함.
 * 2. pageListOra   : 페이징처리용 리스트조회시 사용함. for Oracle, Tibero
 * 3. view          : 단건조회, 상세조회시 사용함.
 * 4. save          : INSERT, UPDATE, DELETE 모두 사용가능. (Return Type : Integer)
 * 5. insert        : INSERT (Return String : Key 채번 사용함.)
 * 6. update        : UPDATE (Return Type : Integer)
 * 7. delete        : DELETE (Return Type : Integer)
 * 
 * @class   : UserDAO
 * @author  : LSH
 * @since   : 2023.03.09
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Repository("UserDAO")
public class UserDAO extends BaseDAO {
	
	// 회원가입 - 회원정보 저장처리
	public int regiUser(UserVO userVO) {
		return insert("User.regiUser", userVO);
	}
	
	// 회원가입 - 회원ROLE 저장처리
	public int regiRole(UserVO userVO) {
		return insert("User.regiRole", userVO);
	}
	
	// 회원가입 - 회원ROLE 등록되어 있는지 확인
	public boolean existRole(UserVO userVO) {
		return (Boolean)view("User.existRole", userVO);
	}

	// 회원가입 - 이미 가입된 회원인지 확인
	public boolean existUser(String userId) {
		return (Boolean)view("User.existUser", userId);
	}

    /**
     *  사용자정보 조회
     */
    public UserInfo getUserInfo(String userId) throws Exception {
        return (UserInfo)view("User.getUserInfo", userId);
    }
    /**
     *  OAUTH 로그인시 사용자정보 조회
     */
    public UserInfo getUserInfoByOauth(OAuthVO oauthVO) throws Exception {
        return (UserInfo)view("User.getUserInfoByOauth", oauthVO);
    }

    /**
     * 패스워드 실패시 카운터 업데이트 및 잠금시간 등록
     */
    public int updtPswdErrCnt(Map<String,Object> userMap) throws Exception {
        return update("User.updtPswdErrCnt", userMap);
    }

    /**
     * 로그인시간 등록
     */
    public int updtLoginTime(String userId) throws Exception {
        return update("User.updtLoginTime", userId);
    }

    /**
     * 사용자아이디,비밀번호 일치여부 확인
     */
	public boolean existUserPswd(String userId, String password) {
    	Map<String,String> params = new HashMap<String,String>();
    	params.put("userId"  , userId);
    	params.put("password", password);
    	return (Integer)view("User.existUserPswd", params) > 0;
    }
}