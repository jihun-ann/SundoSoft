package business.com.common.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.CommBizUtils;
import business.com.CommConst;
import business.com.authority.AuthorityService;
import business.com.common.service.CommService;
import common.base.BaseController;
import common.util.CommUtils;

/**
 *  [컨트롤러클래스] - 공통 Controller
 *
 * @class   : CommController
 * @author  :
 * @since   : 2023.03.08
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Controller
@SuppressWarnings({"all"})
public class CommController extends BaseController {

    @Resource(name="CommService")
    protected CommService commService;

    @Resource(name="AuthorityService")
    protected AuthorityService authorityService;

    /**
     * Combo Code 조회
     */
    @RequestMapping("/com/common/getComboCode.do")
    @ResponseBody
    public List getComboCode(HttpServletRequest request) throws Exception {
        Map paramMap = getParameterMap(request, true);
        return commService.listCode(paramMap);
    }

    /**
     * 특정 코드 조회
     */
    @RequestMapping("/com/common/getCode.do")
    @ResponseBody
    public Map getCode(HttpServletRequest request) throws Exception {
        Map paramMap = getParameterMap(request, true);
        Map codeMap = commService.getCode(
        		(String)paramMap.get("upCdId"), 
        		(String)paramMap.get("cdId")
        			);
    	return getSuccess(codeMap);
    }

    /**
     * 2021.08.26 LSH
     * 해당 시스템 메뉴 목록 조회 (JSON)
     * 
     * 입력조건에 따라 메뉴가 2가지로 조회되어야 한다.
     * - 관리자시스템 (sysCd = ADM)
     * - 사용자시스템 (sysCd = USR)
     * TREE 형태의 계층구조 목록으로 반환한다.
     */
    @RequestMapping("/com/common/listMenu.do")
    @ResponseBody
    public List listMenu(HttpServletRequest request, 
    		ModelMap model) throws Exception {

        Map paramMap = getParameterMap(request, true);
        
        // 최상위 항목
        String rootId   = (String)paramMap.get("rootId");
        String rootText = (String)paramMap.get("rootText");
        
        // -------------------- Default Setting End -----------------------//
        Map menuMap = new HashMap();
        menuMap.put("roleId",   CommUtils.nvlTrim(userInfo.getRoleId(), CommConst.ROLE_RESTRICTED));
        menuMap.put("sysCd",    paramMap.get("sysCd"));
        menuMap.put("notId",    paramMap.get("notId"));
        menuMap.put("menuLvl",  paramMap.get("menuLvl"));
        menuMap.put("upMenuId", paramMap.get("upMenuId"));
        List<Map> menuList = authorityService.getMenuList(menuMap);

        // 2021.08.26 메뉴를 계층구조로 변경
		// 2021.09.09 계층구조 생성유틸로 변경
        // 2023.01.11 menuLvl 용도변경에 따라 level로 계층레벨 사용변경
		Map keys = new HashMap();
		keys.put("level" , "level");
		keys.put("itemId", "menuId");
		keys.put("itemNm", "menuNm");
		keys.put("parentId", "upMenuId");
		
		List<Map> list = CommBizUtils.buildTree(menuList, keys);
		
        // 최상위 항목이 존재하는 경우
		if (CommUtils.isNotEmpty(rootText)) {
			Map root = new HashMap();
			root.put("id", rootId);
			root.put("text", rootText);
			root.put("children", list);
			List<Map> result = new ArrayList<Map>();
			result.add(root);
			return result;
		}
		return list; 
    }

    /**
     * 서류양식 상위코드 콤보 조회
     */
    @RequestMapping("/com/common/getComboUpPape.do")
    @ResponseBody
    public List getComboUpPape(HttpServletRequest request) throws Exception {
        Map paramMap = getParameterMap(request, true);
        return commService.listUpPape(paramMap);
    }
    /**
     * 서류양식 하위코드 콤보 조회
     */
    @RequestMapping("/com/common/getComboPape.do")
    @ResponseBody
    public List getComboPape(HttpServletRequest request) throws Exception {
        Map paramMap = getParameterMap(request, true);
        return commService.listPape(paramMap);
    }
}
