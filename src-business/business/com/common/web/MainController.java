package business.com.common.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import business.com.CommConst;
import business.com.common.service.MainVO;
import common.base.BaseController;

/**
 * [컨트롤러클래스] - 메인 컨트롤러
 *
 * @class   : MainController
 * @author  : ntarget
 * @since   : 2023.03.08
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Controller
public class MainController extends BaseController {

	//@Autowired
    //protected MainService mainService;

    @RequestMapping(CommConst.INDEX_URL)
    public String main(HttpServletRequest request, ModelMap model, @ModelAttribute MainVO mainVO) throws Exception {

        //Map paramMap = getParameterMap(request, true);
        
        return CommConst.INDEX_PAGE;
    }
}
