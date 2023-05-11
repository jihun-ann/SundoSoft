package business.com.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.common.service.CommService;
import common.base.BaseService;

/**
 * [서비스클래스] - 공통 ServiceImpl
 *
 * @class   : CommServiceImpl
 * @author  :
 * @since   : 2023.03.08
 * @version : 1.0
 *
 *   수정일            수정자                             수정내용
 *  -------    --------    ---------------------------
 *
 */
@Service("CommService")
@SuppressWarnings("rawtypes")
public class CommServiceImpl extends BaseService implements CommService {

    @Resource(name = "CommDAO")
    private CommDAO commDAO;
    
	@Override
	public List<Map<String, Object>> listCode(Map<String, Object> paramMap) throws Exception {
		return commDAO.listCode(paramMap);
	}

    /**
     * 코드상세조회
     */
	@Override
    public Map<String,Object> getCode(String upCdId, String cdId) throws Exception{
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put("upCdId", upCdId);
    	params.put("cdId"  , cdId);
    	return commDAO.viewCode(params);
    }
    
    /**
     * 상위코드ID에 해당하는 코드목록 반환
     */
    @SuppressWarnings("unchecked")
	private List<Map<String, Object>> _getCodeList(String upCdId) throws Exception {
		Map params = new HashMap();
		params.put("upCdId", upCdId);
		return listCode(params);
    }
    
    /**
     * 코드리스트를 맵으로 변환
     * @param list      코드리스트 (List<Map>)
     * @param keyField  맵의 KEY에 해당하는 필드명
     * @param valueField 맵의 VALUE에 해당하는 필드명
     */
    private Map<String,Object> _toMap(List<Map<String, Object>> list, String keyField, String valueField) {
    	Map<String,Object> ret = new HashMap<String,Object>();
    	if (list == null)
    		return ret;
    	for (Map item : list) {
    		ret.put((String)item.get(keyField), item.get(valueField));
    	}
    	return ret;
    }
    
    /**
     * 코드목록을 Name KEY 맵으로 변환
     */
	@Override
	public Map<String,Object> getMapCodeByName(String upCdId) throws Exception{
    	List<Map<String, Object>> list = _getCodeList(upCdId);
    	return _toMap(list, "text", "code");
    }
    
    /**
     * 코드목록을 CODE KEY 맵으로 변환
     */
	@Override
	public Map<String,Object> getMapCode(String upCdId) throws Exception{
    	List<Map<String, Object>> list = _getCodeList(upCdId);
    	return _toMap(list, "code", "text");
    }

	// 서류양식 상위코드 콤보 조회
	@Override
    public List<Map<String,Object>> listUpPape(Map<String,Object> paramMap) throws Exception{
		return commDAO.listUpPape(paramMap);
    }
	// 서류양식 하위코드 콤보 조회
	@Override
    public List<Map<String,Object>> listPape(Map<String,Object> paramMap) throws Exception{
		return commDAO.listPape(paramMap);
    }
}
