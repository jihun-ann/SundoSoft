package business.com.file.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.CommConst;
import business.com.file.service.FileService;
import business.com.file.service.FileVO;
import common.base.BaseController;
import common.file.FileDirectory;
import common.file.FileInfo;
import common.file.FileManager;
import common.util.CommUtils;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * [컨트롤러클래스] - 신청첨부파일 Controller
 *
 * @class   : AplyFileController
 * @author  : LSH
 * @since   : 2021.10.07
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class FileController extends BaseController {

    @Resource(name="FileService")
    protected FileService fileService;

    @Resource(name="fileManager")
    FileManager fileManager;

    /**
     * 첨부파일 목록조회
     */
    @RequestMapping("/com/file/getListAplyFileByPape.do")
    @ResponseBody
    public List getListAplyFileByPape(HttpServletRequest request
    		, @ModelAttribute FileVO fileVO) throws Exception {
        Map paramMap = getParameterMap(request, true);
        return fileService.listFileByPape(fileVO);
    }

    /**
     * 공통사용 - 제출서류목록조회 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/com/file/getListAplyFile.do")
    @ResponseBody
    public Map getListAplyFile(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute FileVO fileVO
            , ModelMap model) throws Exception {

        setGlobalSession(fileVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = fileService.listFile(fileVO, page, size);
        }
        else {
        	list = fileService.listFile(fileVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 신청서류 목록조회
     */
    @RequestMapping("/com/file/getListPape.do")
    @ResponseBody
    public List getListPape(HttpServletRequest request) throws Exception {
        Map paramMap = getParameterMap(request, true);
        return fileService.listPapeFile(paramMap);
    }

    /**
     * 신청서류그룹 조회
     */
    @RequestMapping("/com/file/getListPapeGroup.do")
    @ResponseBody
    public List getListPapeGroup(HttpServletRequest request) throws Exception {
        Map paramMap = getParameterMap(request, true);
        return fileService.listPapeFileGroup(paramMap);
    }

    /**
     * 신청서류 다운로드
     */
    @RequestMapping("/com/file/downloadPapeFile.do")
    public void downloadPapeFile(HttpServletRequest request,
    		HttpServletResponse response,
    		@ModelAttribute FileVO fileVO) throws Exception {

        // 다운로드할 파일 정보 조회
    	FileInfo fileInfo = fileService.viewPapeFile(fileVO);
    	fileInfo.setFullPath(FileDirectory.PAPER.getRealPath(fileVO.getFilePath()));

        // 실제 파일 다운로드 처리
        fileManager.procFileDownload(request, response, fileInfo);
    }

    /**
     * 첨부파일 임시 업로드 (업로드 팝업에서 호출)
     */
    @RequestMapping("/com/file/uploadAplyFile.do")
    @ResponseBody
    public Map uploadAplyFile(HttpServletRequest request) throws Exception {

    	Map paramMap = getParameterMap(request, true);

	    // 파일을 임시 경로에 업로드 한다.
	    List<FileInfo> files = fileManager.multiFileUploadByName(request, null);
	    FileVO obj = null;

	    // 단일 파일로 처리
	    if (files != null &&
	    	files.size() == 1) {

	    	FileInfo fileInfo = files.get(0);
	    	fileInfo.setGsUserNo((String)paramMap.get("gsUserNo"));

		    // 업로드한 파일정보를 저장한다.
		    obj = fileService.saveTempFile(files.get(0));
	    }
	    return getSuccess("File", obj);
    }

    /**
     * 첨부파일 다운로드
     */
    @RequestMapping(value="/com/file/downloadAplyFile.do")
    public void downloadAplyFile(HttpServletRequest request,
    		HttpServletResponse response,
    		@RequestParam String sn) throws Exception {

    	Map paramMap = getParameterMap(request, true);

    	// 2021.12.28 LSH BASE64 DECODE 
    	String decSn = EgovFileScrty.decode(sn);

        // 다운로드할 파일 정보 조회
    	FileVO fileVO = _getAplyFile(decSn, paramMap);

        FileInfo fileInfo = FileInfo.builder()
							.saveName(fileVO.getStrgNm())
							.fileName(fileVO.getFileNm())
							.build();
        // 파일문서타입
        FileDirectory d = FileDirectory.PAPER;

        // 임시경로인 경우
        if (CommUtils.isEmpty(fileVO.getDcmtNo())) {
        	fileInfo.setFullPath(d.getTempDir());
        }
        else {
        	fileInfo.setFullPath(d.getRealPath(fileVO.getFilePath()));
        }
        // 실제 파일 다운로드 처리
        fileManager.procFileDownload(request, response, fileInfo);
    }

    /**
     * 문서번호 기준 첨부파일 압축 다운로드
     */
    @RequestMapping(value="/com/file/downloadAplyFileZip.do")
    public void downloadAplyFileZip(HttpServletRequest request,
    		HttpServletResponse response,
    		@ModelAttribute FileVO fileVO) throws Exception {

    	Map paramMap = getParameterMap(request, true);

        String gsRoleId = (String)paramMap.get("gsRoleId");
    	
        // 첨부파일 경로 정보
        FileDirectory fd = FileDirectory.PAPER;
        
        // 다운로드 파일목록
        List<Map> list = null;
        
        // sns (,'로 연결한 일련번호 문자열)값이 있는 경우엔 해당 번호로 처리
        if (CommUtils.isNotEmpty(fileVO.getSns())) {
            // 관리자인 경우 ACCESS OK
            if (!CommConst.isAdminRole(gsRoleId))
                throw new EgovBizException(message.getMessage("error.file.notAccess"));

            // 배열로 변경
            fileVO.setSnList(CommUtils.splitToList(fileVO.getSns(), ","));

            list = fileService.listFileByArray(fileVO);
        }
        else {
        	list = fileService.listFile(fileVO);
        }
        
        if (CommUtils.isEmptyList(list))
        	throw new EgovBizException("다운로드할 첨부파일이 없습니다.");

        List<FileInfo> files = new ArrayList<FileInfo>();

        for (Map vo : list) {
            // 파일 다운로드 공통함수 사용
        	files.add(FileInfo.builder()
        		.fullPath(fd.getRealPath((String)vo.get("filePath")))
				.filePath((String)vo.get("filePath"))
				.saveName((String)vo.get("strgNm"))
				.fileName((String)vo.get("fileNm"))
				.fileType((String)vo.get("fileType")) // 2022.01.10 파일복호화를 위한 타입정의
				.build()
        	);
        }
        // 실제 파일 압축 다운로드 처리
        fileManager.procFileZipDownload(request, response, files, fd.getType());
    }

    /**
     * 문서/미디어 URL링크로 미리보기
     */
    @RequestMapping(value="/com/file/previewAplyFile.do")
    @ResponseBody
    public HttpEntity<byte[]> previewAplyFile(HttpServletRequest request,
    		HttpServletResponse response,
    		@RequestParam String sn) throws Exception {

    	Map paramMap = getParameterMap(request, true);
    	// 파일정보조회
    	FileVO fileVO = _getAplyFile(sn, paramMap);
        // 파일문서타입
        FileDirectory d = FileDirectory.PAPER;
        // 파일경로
        String filePath = null;
        // 임시경로인 경우
        if (CommUtils.isEmpty(fileVO.getDcmtNo()))
        	filePath = d.getTempDir();
        else
        	filePath = d.getRealPath(fileVO.getFilePath());
        // 파일정보
        FileInfo f = FileInfo.builder()
        		.fullPath(filePath)
        		.saveName(fileVO.getStrgNm())
        		.build();
        
        // 미디어 URL 링크용 HttpEntity 반환
    	return fileManager.linkFile(f , request, response);
    }

    /**
     * hwpjs 미리보기용 문서 URL링크보기
     */
    @RequestMapping(value = "/com/file/previewAplyHwp{sn}.do", method= RequestMethod.GET)
    public void previewAplyHwp(HttpServletRequest request,
    		HttpServletResponse response,
    		@PathVariable("sn") String sn
    	) throws Exception {
    	Map paramMap = getParameterMap(request, true);
    	// 파일정보조회
    	FileVO fileVO = _getAplyFile(sn, paramMap);
        // 파일문서타입
        FileDirectory d = FileDirectory.PAPER;
        // 파일경로
        String filePath = null;
        // 임시경로인 경우
        if (CommUtils.isEmpty(fileVO.getDcmtNo()))
        	filePath = d.getTempDir();
        else
        	filePath = d.getRealPath(fileVO.getFilePath());
        // 파일정보
        FileInfo f = FileInfo.builder()
        		.fullPath(filePath)
        		.saveName(fileVO.getStrgNm())
        		.build();
        // 문서 URL 링크 스트리밍
    	fileManager.linkDoc(f, request, response);
    }

    /**
     * 파일 상세조회 및 권한 체크
     */
    private FileVO _getAplyFile(String sn, Map paramMap) throws Exception {

    	// 파일정보조회
    	FileVO fileVO = fileService.viewFile(sn);
    	if (fileVO == null)
    		throw new EgovBizException(message.getMessage("error.file.notExist"));

    	String gsRoleId = (String)paramMap.get("gsRoleId");
    	String gsUserNo = (String)paramMap.get("gsUserNo");
    	String gsUserId = (String)paramMap.get("gsUserId");
    	// 관리자인 경우 ACCESS OK
    	if (CommConst.isAdminRole(gsRoleId)) {
    		return fileVO;
    	}
    	// 회원사용자인 경우 본인인지 체크
    	else if (CommConst.isUserRole(gsRoleId)) {
    		// 파일 생성자이면 ACCESS OK
    		if (CommUtils.isEqual(gsUserNo, fileVO.getRgtrNo()))
    			return fileVO;
    	}
    	// 그외엔 NOT ACCESS
		throw new EgovBizException(message.getMessage("error.file.notAccess"));
    }
}
