package business.com.user.service;


import common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@ToString
public class IrinfoVO extends BaseModel{

	/* ir번호 */
	private String ir_no;
	/* 업체번호 */
	private String ent_no;
	/* 업체명 */
	private String ent_nm;
	/* 사업자등록번호 */
	private String brno;
	/* 업체형태코드 */
	private String ent_shap_cd;
	/* 업체유형코드 */
	private String ent_ty_cd;
	/* 설립일자 */
	private String fndn_ymd;
	/* 업체규모 */
	private Integer ent_scal;
	/* 업종코드-사업분야 */
	private String induty_cd;
	/* 홈페이지주소 */
	private String hmpg_addr;
	/* 대표자명 */
	private String rprsv_nm;
	/* 이메일주소 */
	private String eml_addr;
	/* 소재지주소 */
	private String ent_plac_addr;
	/* 전화번호 */
	private String telno;
	/* 팩스번호 */
	private String fxno;
	/* 주요사업내용 */
	private String main_biz_cn;
	/* 핵심아이템내용 */
	private String core_itm_cn;
	/* 사업내용 */
	private String biz_cn;
	/* 담당자명 */
	private String pic_nm;
	/* 담당자번호 */
	private String pic_telno;
	/* 공개여부 */
	private String rls_yn;
	/* 홍보영상 */
	private String pr_vido_url;
	/* 조회수 */
	private Integer inq_cnt;
	/* 등록자번호 */
	private String rgtr_no;
	/* 등록일자 */
	private String reg_ymd;
	/* 수정자번호 */
	private String mdfr_no;
	/* 수정일자 */
	private String mdfcn_ymd;
}
