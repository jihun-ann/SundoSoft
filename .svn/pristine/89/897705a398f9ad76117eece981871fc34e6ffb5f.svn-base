package business.com.user.web;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.stringtemplate.v4.compiler.CodeGenerator.list_return;

import com.fasterxml.jackson.databind.ObjectMapper;

import business.com.CommConst;
import business.com.user.oauth.KakaoLoginBO;
import business.com.user.oauth.NaverLoginBO;
import business.com.user.service.IndustryCodeVO;
import business.com.user.service.InvestCodeVO;
import business.com.user.service.InvestInfoVO;
import business.com.user.service.IrinfoService;
import business.com.user.service.IrinfoVO;
import business.com.user.service.ResponseIrinfoVO;
import business.com.user.service.SearchValueVO;
import business.com.user.service.UserService;
import business.com.user.service.UserVO;
import common.base.BaseController;

/**
 * [컨트롤러클래스] - 회원컨트롤러
 *
 * @class   : UserController
 * @author  : LSH
 * @since   : 2023.03.09
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
public class UserController extends BaseController {

	/* KakaoLogin */
	@Autowired
	private KakaoLoginBO kakaoLoginBO;
	
	/* NaverLogin */
	@Autowired
	private NaverLoginBO naverLoginBO;

	@Autowired
	private UserService userService;

	@Autowired 
    private UserValidator userValidator;
	
	@Autowired
	private IrinfoService irinfoService;

	/**
	 * 회원가입 - 유형선택 화면 오픈
	 */
	@RequestMapping("/com/user/openJoin.do")
	public String openJoin(ModelMap model) throws Exception {

		model.addAttribute("userCdI", CommConst.USER_INDIVIDUAL);
		model.addAttribute("userCdC", CommConst.USER_CORPORATE );
		
		return "com/user/openJoin";
	}

	/**
	 * 회원가입 - 이용약관 동의 화면 오픈
	 */
	@RequestMapping("/com/user/openAgree.do")
	public String openAgree(@ModelAttribute UserVO userVO, 
			HttpSession session,
			ModelMap model) throws Exception {

		// 선택한 가입유형을 세션에 담는다. (OAUTH에서 사용됨)
		session.setAttribute(CommConst.SESS_USER_CODE  , userVO.getUserCd());
		session.setAttribute(CommConst.SESS_USER_ACTION, CommConst.ACT_JOIN);
		
		model.addAttribute("model", userVO);
		return "com/user/openAgree";
	}

	/**
	 * 회원가입 - 기본정보 입력 화면 오픈
	 */
	@RequestMapping("/com/user/openForm.do")
	public String openForm(@ModelAttribute UserVO userVO, 
			HttpSession session,
			ModelMap model) throws Exception {
		
		model.addAttribute("model", userVO);
		// 네이버로그인 URL
    	model.addAttribute("naverAuthUrl", naverLoginBO.getAuthorizationUrl(session));
    	// 카카오로그인 URL
    	model.addAttribute("kakaoAuthUrl", kakaoLoginBO.getAuthorizationUrl(session));
		
		return "com/user/openForm";
	}

	/**
	 * 회원가입 - 기업정보 입력 화면 오픈
	 */
	@RequestMapping("/com/user/openCorp.do")
	public String openCorp(@ModelAttribute UserVO userVO, 
			HttpSession session,
			ModelMap model) throws Exception {
		model.addAttribute("model", userVO);
		
		return "com/user/openCorp";
	}

	/**
	 * 회원가입 - 가입완료 화면 오픈
	 */
	@RequestMapping("/com/user/doneJoin.do")
	public String doneJoin(@ModelAttribute UserVO userVO, 
			ModelMap model) throws Exception {
		model.addAttribute("model", userVO);
		return "com/user/doneJoin";
	}

	/**
	 * [AJAX] 회원가입 - 기본정보 저장처리
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/com/user/saveForm.do")
	@ResponseBody
	public Map saveJoin(HttpSession session,
			@ModelAttribute UserVO userVO, 
			BindingResult bindingResult) throws Exception {
		// 세션사용자정보를 정의
		setGlobalSession(userVO);
		
		// 세션 사용자정보의 사용자번호를 모델객체에 맵핑
		userVO.setGsUserNo(userVO.getUserInfo().getUserNo());

        // 입력값 검증
    	userValidator.validate(userVO, bindingResult);
    	
    	// 입력값 검증시 오류가 있을 경우
        if (bindingResult.hasErrors()) {
        	logger.info("User Validate Error...", bindingResult.getAllErrors());
        	return getFailure(bindingResult);
        }
		
		String userCd = userVO.getUserCd();
		
		// 일반회원인 경우 회원가입 처리
		if (CommConst.USER_INDIVIDUAL.equals(userCd)) {
			
			// 사용자의 기본권한을 ROLE_AUTH_USR(회원사용자)로 정의
			userVO.setRoleId(CommConst.USER_INDIVIDUAL_ROLE);
			// 회원가입처리
			if (userService.saveJoin(userVO) > 0)
				return getSuccess("userId", userVO.getUserId());
			// 실패결과 반환
			return getFailure();
		}
		// 기업회원인 경우 입력정보를 세션에 저장처리
		else if (CommConst.USER_CORPORATE.equals(userCd)) {
			// 회원정보를 임시 세션에 담는다.
			session.setAttribute(CommConst.SESS_USER_INFO, userVO);
			return getSuccess();
		}
		return getFailure("가입유형을 확인할 수 없습니다.");
	}

	/**
	 * 회원가입 - 이메일(아이디) 중복체크
	 */
	@RequestMapping("/com/user/unique.do")
	@ResponseBody
	public boolean unique(@RequestParam("userId") String userId) throws Exception {
		return !userService.existUser(userId);
	}
	
	
	@RequestMapping("/usr/main/diagsearch.do")
	public String diagSearch() {
		return "usr/main/diag-search";
	}
	
	
	
	@PostMapping("/usr/main/diaglist.do")
	public String diagList(HttpServletRequest request,SearchValueVO valVo,Model mo,HttpSession session) throws Exception {
		
		if(valVo == null) {
			return "usr/main/diag-search"; 
		}
		
		if(valVo.getSearchVal().equals("경영체명, 분야명, 대표자를 검색하세요.")) {
			valVo.setSearchVal("all");
		}
		
		session.setAttribute("searchVal", valVo);
		
		List<IrinfoVO> ilst = irinfoService.searchirLst(valVo);
		List<InvestInfoVO> ivlst = irinfoService.findInvestInfoAll();
		List<IndustryCodeVO> indstlst = irinfoService.findIndstCodeAll();
		List<InvestCodeVO> invtlst = irinfoService.findInvtCodeAll();
		
		
		request.setAttribute("lstSize",ilst.size());
		mo.addAttribute("lstSize",ilst.size());
		if (ilst.size()>=6) {
			List<IrinfoVO> ilstCut = ilst.subList(0, 6);
			mo.addAttribute("irinfoLst",ilstCut);
		}else {
			mo.addAttribute("irinfoLst",ilst);
		}
		mo.addAttribute("invtinfoLst",ivlst);
		mo.addAttribute("indstlst", indstlst);
		mo.addAttribute("invtlst", invtlst);
		return "usr/main/diag-list";
	}
	
	
	
	@GetMapping("/usr/main/diaglist.do")
	public String diagListPaging(int page,HttpServletRequest request,Model mo,HttpSession session) throws Exception {
		
		SearchValueVO valVO = null;
		if(session.getAttribute("searchVal") != null) {
			valVO = (SearchValueVO)session.getAttribute("searchVal");
		}else {
			return "usr/main/diag-search";
		}
		
		request.setAttribute("cPage", page);
		
		
		List<IrinfoVO> ilst = irinfoService.searchirLst(valVO);
		List<InvestInfoVO> ivlst = irinfoService.findInvestInfoAll();
		List<IndustryCodeVO> indstlst = irinfoService.findIndstCodeAll();
		List<InvestCodeVO> invtlst = irinfoService.findInvtCodeAll();
		
		request.setAttribute("lstSize",ilst.size());
		mo.addAttribute("lstSize",ilst.size());
		
		int startitem = (page-1)*6;
		int enditem = (page)*6;
		
		
		if (ilst.size()>=enditem) {
			List<IrinfoVO> ilstCut = ilst.subList(startitem, enditem);
			mo.addAttribute("irinfoLst",ilstCut);
		}else {
			List<IrinfoVO> ilstCut = ilst.subList(startitem, ilst.size());
			mo.addAttribute("irinfoLst",ilstCut);
		}
		mo.addAttribute("invtinfoLst",ivlst);
		mo.addAttribute("indstlst", indstlst);
		mo.addAttribute("invtlst", invtlst);
		
		return "usr/main/diag-list";
	}
	
	
	
	//etc/openRegiEtcDisDiag.do
		@GetMapping("/usr/main/irinfo.do")
		public String irInfo(String entNum,Model mo, HttpServletRequest request) throws Exception {
			if(entNum == null) {
				return "usr/main/diag-search";
			}
			
			//HttpHeaders header = new HttpHeaders();
			//header.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			 
			MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
			body.add("id", "testapfs");
			body.add("password", "testapfs11!!");
			
			RestTemplate rt = new RestTemplate();
	        String response = rt.postForObject(
	                "http://210.113.102.196/api/testApfs.do", body, String.class
	        );

	        ObjectMapper obmapper = new ObjectMapper();
	        ResponseIrinfoVO resIrinfo;
	        resIrinfo = obmapper.readValue(response, ResponseIrinfoVO.class);
	        
	        System.out.println("%%%%%%%%%%%%%%%%%%%"+resIrinfo.getResult().get(0));
			
			mo.addAttribute("testApi",resIrinfo.getResult().get(0));
	        
			IrinfoVO vo = irinfoService.findOneIrinfo(entNum);
			request.setAttribute("irinfo",vo);
			
			List<InvestInfoVO> invtVo = irinfoService.findOneInvtinfo(entNum);
			List<IndustryCodeVO> indstlst = irinfoService.findIndstCodeAll();
			List<InvestCodeVO> invtlst = irinfoService.findInvtCodeAll();
			mo.addAttribute("invtinfoLst",invtVo);
			mo.addAttribute("invtlst", invtlst);
			mo.addAttribute("indstlst", indstlst);
			
			return "usr/main/ir-info";
		}
		
		
		@PostMapping("/usr/main/irinfoUpdate.do")
		@ResponseBody
		public boolean irinfoUpdate(@RequestParam("data")String data,@RequestParam("entNum")String entNum) throws Exception {
			System.out.println(data.substring(0, 5));
			System.out.println(data.substring(5));
			
			if(data == null || entNum == null) {
				return false;
			}else{
				HashMap<String, String> datamap = new HashMap<String, String>();
				datamap.put("type", data.substring(0, 5));
				datamap.put("data", data.substring(5));
				datamap.put("entNum", entNum);
				irinfoService.updateirinfo(datamap);
				
				return true;
			}
		}
		
		@GetMapping("/api/testApfs.do")
		public void testApi() {
			
		}
}
