package business.com.user.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvestCodeVO {
	/* 투자분야코드 */
	private String invt_rlm_cd;
	/* 관리연도 */
	private String mng_yr;
	/* 투자분야명 */
	private String invt_rlm_nm;
	/* 투자분야내용 */
	private String invt_rlm_cn;
	/* 사용여부 */
	private String use_yn;
	/* 등록자번호 */
	private String rgtr_no;
	/* 등록일자 */
	private String reg_ymd;
	/* 수정자번호 */
	private String mdfr_no;
	/* 수정일자 */
	private String mdfcn_ymd;
	
}
