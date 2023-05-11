package business.com.user.service;

import common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 회원가입 모델 클래스
 *
 * @class   : UserVO
 * @author  : LSH
 * @since   : 2023.03.09
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class UserVO extends BaseModel {
    
	// 세션사용자번호
	private String gsUserNo;
	
	private String userNo;
	private String userId;
	private String userNm;
	private String userCd;
	private String pswd;
	private String mobile;
	private String email;
	private String roleId;
	private String agreYn;
	private String infoYn;
	private String certYn;
	
	public void fromOauth(OAuthVO oauthVO) {
		setUserId    (oauthVO.getEmail ());
		setUserNm    (oauthVO.getUserNm());
		setEmail     (oauthVO.getEmail ());
		setMobile    (oauthVO.getMobile());
	}
}
