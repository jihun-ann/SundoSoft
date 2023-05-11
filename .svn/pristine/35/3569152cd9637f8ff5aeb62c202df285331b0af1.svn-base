package business.com.common.service;

import java.util.List;
import java.util.Map;

/**
 * [서비스인터페이스] - 공통 Service IF
 *
 * @class   : CommService
 * @author  :
 * @since   : 2023.03.08
 * @version : 1.0
 *
 *   수정일            수정자                             수정내용
 *  -------    --------    ---------------------------
 *
 */
public interface CommService {

    /**
     * 특정 상위코드에 해당하는 코드 리스트 조회 
     */
    public List<Map<String,Object>> listCode(Map<String,Object> paramMap) throws Exception;

    /**
     * 코드상세조회
     */
    public Map<String,Object> getCode(String upCdId, String cdId) throws Exception;
    
    /**
     * 코드목록을 Name KEY 맵으로 변환
     */
    public Map<String,Object> getMapCodeByName(String upCdId) throws Exception;
    
    /**
     * 코드목록을 Code KEY 맵으로 변환
     */
    public Map<String,Object> getMapCode(String upCdId) throws Exception;

	// 서류양식 상위코드 콤보 조회
    public List<Map<String,Object>> listUpPape(Map<String,Object> paramMap) throws Exception;
	// 서류양식 하위코드 콤보 조회
    public List<Map<String,Object>> listPape(Map<String,Object> paramMap) throws Exception;
}
