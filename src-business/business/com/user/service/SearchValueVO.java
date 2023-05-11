package business.com.user.service;

import java.util.List;

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
public class SearchValueVO {
	/* 검색어 */
	private String searchVal;
	/* 투자분야 */
	private List<String> investList;
	/* 사업분야 */
	private List<String> industryList;
	/* 투자금액 최소 */
	private Long amountMin;
	/* 투자금액 최대 */
	private Long amountMax;
	
}
