package business.com.user.service;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseIrinfoVO {

	public List<Result> result;
	public String resultCode;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@ToString
	public static class Result {
		public String company_type;
		public String biz_shape;
		public String rprsv_nm;
		public String biz_rlm;
		public String hmpg_url;
		public String company_nm;
		public String est_date;
		public String tel;
		public String biz_scale;
		public String addr;
		public String fax;
		public String eml_addr;
		public String brno;
		}
}
