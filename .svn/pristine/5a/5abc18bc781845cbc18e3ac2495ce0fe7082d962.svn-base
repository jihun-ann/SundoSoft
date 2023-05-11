package common.user;

import java.io.Serializable;

import business.com.CommConst;

/**
 * Program Name 	: UserInfo
 * Description 		:
 * Programmer Name 	: ntarget
 * Creation Date 	: 2021-02-08
 * Used Table 		:
 */

@SuppressWarnings({"serial"})
public class UserInfo implements Serializable {
	
    // 시스템 공통정보
    private String userId      = null; // 사용자아이디
    private String userNm      = null; // 사용자명
    private String userCd      = null; // 사용자가입유형
    private String pswd        = null; // 비밀번호
    private String roleId      = null; // 권한롤
    private String roleNm      = null; // 권한롤명칭
    private String ipAddr      = null; // IP주소

    private int    diffDays    = 0;    // 비밀번호변경일수
    private int    diffNextDays= 0;    // 비밀번호 다음에변경 일수

    /* 사용자정보 */
    private String userNo      = null; // 사용자번호
    private String useIp       = null; // 사용IP
    private String email       = null; // 이메일
    private String mobile      = null; // 휴대전화번호
    private String joinYmd     = null; // 가입일자
    private String pswdLockYmd = null; // 비밀번호잠금일자
    private String pswdErrCnt  = null; // 비밀번호오류횟수
    private String pswdChgYmd  = null; // 비밀번호변경일자
    private String pswdNextYmd = null; // 비밀번호다음일자
    private String lstLgnDt    = null; // 마지막로그인일시
    private String rgtrNo      = null; // 등록자번호
    private String regDttm     = null; // 등록일시
    private String regDate     = null; // 등록일자
    private String mdfrNo      = null; // 수정자번호
    private String mdfDttm     = null; // 수정일시
    private String mdfDate     = null; // 수정일자
    
    private String gsUserNo    = null; // 로그인사용자번호 2021.09.09 추가
    private String mode        = null; // 처리모드 2021.09.09 추가
    
    private String oathCd      = null; // OAUTH 타입
    private String oathId      = null; // OAUTH ID
    
    // 2021.08.27 LSH 사용자 로그인 여부
    public boolean isLogin() {
    	return CommConst.isLogin(userNo);
    }
    // 2021.08.27 LSH 관리자인지 여부
    public boolean isAdmin() {
    	return CommConst.isAdminRole(roleId);
    }
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleNm() {
		return roleNm;
	}
	public void setRoleNm(String roleNm) {
		this.roleNm = roleNm;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public int getDiffDays() {
		return diffDays;
	}
	public void setDiffDays(int diffDays) {
		this.diffDays = diffDays;
	}
	public int getDiffNextDays() {
		return diffNextDays;
	}
	public void setDiffNextDays(int diffNextDays) {
		this.diffNextDays = diffNextDays;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUseIp() {
		return useIp;
	}
	public void setUseIp(String useIp) {
		this.useIp = useIp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getJoinYmd() {
		return joinYmd;
	}
	public void setJoinYmd(String joinYmd) {
		this.joinYmd = joinYmd;
	}
	public String getPswdLockYmd() {
		return pswdLockYmd;
	}
	public void setPswdLockYmd(String pswdLockYmd) {
		this.pswdLockYmd = pswdLockYmd;
	}
	public String getPswdErrCnt() {
		return pswdErrCnt;
	}
	public void setPswdErrCnt(String pswdErrCnt) {
		this.pswdErrCnt = pswdErrCnt;
	}
	public String getPswdChgYmd() {
		return pswdChgYmd;
	}
	public void setPswdChgYmd(String pswdChgYmd) {
		this.pswdChgYmd = pswdChgYmd;
	}
	public String getPswdNextYmd() {
		return pswdNextYmd;
	}
	public void setPswdNextYmd(String pswdNextYmd) {
		this.pswdNextYmd = pswdNextYmd;
	}
	public String getLstLgnDt() {
		return lstLgnDt;
	}
	public void setLstLgnDt(String lstLgnDt) {
		this.lstLgnDt = lstLgnDt;
	}
	public String getRgtrNo() {
		return rgtrNo;
	}
	public void setRgtrNo(String rgtrNo) {
		this.rgtrNo = rgtrNo;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getMdfrNo() {
		return mdfrNo;
	}
	public void setMdfrNo(String mdfrNo) {
		this.mdfrNo = mdfrNo;
	}
	public String getMdfDttm() {
		return mdfDttm;
	}
	public void setMdfDttm(String mdfDttm) {
		this.mdfDttm = mdfDttm;
	}
	public String getMdfDate() {
		return mdfDate;
	}
	public void setMdfDate(String mdfDate) {
		this.mdfDate = mdfDate;
	}
	public String getGsUserNo() {
		return gsUserNo;
	}
	public void setGsUserNo(String gsUserNo) {
		this.gsUserNo = gsUserNo;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getOathCd() {
		return oathCd;
	}
	public void setOathCd(String oathCd) {
		this.oathCd = oathCd;
	}
	public String getOathId() {
		return oathId;
	}
	public void setOathId(String oathId) {
		this.oathId = oathId;
	}
	public String getUserCd() {
		return userCd;
	}
	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}

}

