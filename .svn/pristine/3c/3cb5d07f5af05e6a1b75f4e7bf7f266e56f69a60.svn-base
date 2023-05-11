package business.com.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import common.base.BaseDAO;

/**
 * [DAO클래스] - 공통 DAO
 *
 * @class   : CommDAO
 * @author  :
 * @since   : 2023.03.08
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */
@Repository("CommDAO")
@SuppressWarnings({"all"})
public class CommDAO extends BaseDAO {
	
	// 코드리스트 조회
    public List<Map<String,Object>> listCode(Map paramMap) throws Exception {
        return list("Comm.listCode", paramMap);
    }

    // 코드상세조회
    public Map<String,Object> viewCode(Map paramMap) {
        return (Map)view("Comm.viewCode", paramMap);
    }
    
	// 서류양식 상위코드 콤보 조회
    public List<Map<String,Object>> listUpPape(Map paramMap) throws Exception {
        return list("Comm.listUpPape", paramMap);
    }
	// 서류양식 하위코드 콤보 조회
    public List<Map<String,Object>> listPape(Map paramMap) throws Exception {
        return list("Comm.listPape", paramMap);
    }
}
