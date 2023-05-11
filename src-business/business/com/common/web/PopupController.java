package business.com.common.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import business.com.common.service.CommService;
import common.base.BaseController;

/**
 *  [컨트롤러클래스] - 공통 팝업 Controller
 *
 * @class   : PopupController
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
public class PopupController extends BaseController {

    @Resource(name="CommService")
    protected CommService commService;

}
