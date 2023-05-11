package business.com.kodata.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.CommConst;
import business.com.kodata.service.KodataAPI;
import business.com.kodata.service.KodataBizVO;
import business.com.kodata.service.KodataEntity;
import commf.exception.BusinessException;
import common.base.BaseController;

/**
 *  [컨트롤러클래스] - 한국평가데이터 KODATA 연계 Controller
 *
 * @class   : KodataController
 * @author  : LSH
 * @since   : 2023.03.08
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Controller
@SuppressWarnings({"all"})
public class KodataController extends BaseController {
    
    /**
     * KODATA OPEN API 실행 (JSON반환)
     */
    @RequestMapping("/com/common/getKodata.do")
    @ResponseBody
    public Map getKodata(HttpServletRequest request
    		, @ModelAttribute KodataBizVO kodataBizVO
    		, ModelMap model) throws Exception {
    	
    	KodataEntity entity = KodataEntity.builder()
    			.url      (CommConst.KODATA_API_URL)
    			.path     (CommConst.KODATA_API_PATH)
    			.format   (CommConst.KODATA_API_FORMAT)
    			.process  (CommConst.KODATA_API_PROCESS)
    			.pidagryn (CommConst.KODATA_API_PIDAGRYN)
    			.userid   (CommConst.KODATA_API_USERID)
    			.bzno     (kodataBizVO.getBzno())
    			.jmno     ("KED001")
    			.build();
    	
    	try {
        	KodataAPI api = new KodataAPI(entity);
        	api.execute(kodataBizVO);
            return getSuccess(kodataBizVO);
    	}
    	catch (BusinessException be) {
    		return getFailure(be.getMessage());
    	}
    }
}
