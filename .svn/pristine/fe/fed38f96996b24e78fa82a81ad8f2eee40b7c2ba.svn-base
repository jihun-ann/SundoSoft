package business.com.file.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.file.service.FileService;
import business.com.file.service.FileVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.file.FileDirectory;
import common.file.FileInfo;

/**
 * [서비스클래스] - 신청첨부파일 Service 구현 클래스
 *
 * @class   : AplyFileServiceImpl
 * @author  : LSH
 * @since   : 2021.10.07
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("FileService")
@SuppressWarnings({"all"})
public class FileServiceImpl extends BaseService implements FileService {

    @Resource(name = "FileDAO")
    private FileDAO fileDAO;

    /**
     * 서류기준 제출파일 목록조회
     */
    @Override
    public List listFileByPape(FileVO fileVO) throws Exception {
    	return fileDAO.listFileByPape(fileVO);
    }
    /**
     * 일련변호 배열 제출파일 목록조회
     */
    @Override
    public List listFileByArray(FileVO fileVO) throws Exception {
    	return fileDAO.listFileByPape(fileVO);
    }
    /**
     * 제출파일 목록조회
     */
    @Override
    public List listFile(FileVO fileVO) throws Exception {
    	return fileDAO.listFile(fileVO);
    }
    /**
     * 제출파일 목록조회 (페이징)
     */
    @Override
    public PaginatedArrayList listFile(FileVO fileVO, int currPage, int pageSize) throws Exception {
    	return fileDAO.listFile(fileVO, currPage, pageSize);
    }
    /**
     * 제출파일 상세조회
     */
    @Override
    public FileVO viewFile(String sn) throws Exception {
    	return fileDAO.viewFile(sn);
    }
    /**
     * 신청서류 목록조회
     */
    @Override
    public List listPapeFile(Map paramMap) throws Exception {
    	return fileDAO.listPapeFile(paramMap);
    }
    /**
     * 신청서류 그룹목록 조회
     */
    @Override
    public List listPapeFileGroup(Map paramMap) throws Exception {
    	return fileDAO.listPapeFileGroup(paramMap);
    }
    /**
     * 신청서류 양식파일조회
     */
    @Override
    public FileInfo viewPapeFile(FileVO fileVO) throws Exception {
    	return fileDAO.viewPapeFile(fileVO);
    }
    /**
     * 제출파일 임시저장처리
     */
    @Override
    public FileVO saveTempFile(FileInfo fileInfo) throws Exception {

    	if (fileInfo == null)
    		return null;

        // 파일이 첨부된 경우
        if (!"Y".equals(fileInfo.getFileYn()))
        	return null;

        // 첨부파일 경로 정보
        FileDirectory d = FileDirectory.PAPER;

        FileVO vo = FileVO.builder()
                .dtySeCd    (fileInfo.getFileType())
        		.papeCd     (fileInfo.getDocuCd  ())
        		.dcmtNo     (fileInfo.getDocuNo  ())
        		.dtlDcmtNo  (fileInfo.getDocuSeq ())
                .fileNm     (fileInfo.getFileName())
                .strgNm     (fileInfo.getSaveName())
                .fileSz     (fileInfo.getFileSize()+"")
                .fileIdx    (fileInfo.getFileIdx ())
                .gsUserNo   (fileInfo.getGsUserNo())
                .delYn      (CommConst.NO)
                .tempYn     (CommConst.YES)
                .prcsStusCd (CommConst.PROCESS_NOTSUBMIT) // 미제출
                .orgSn      (fileInfo.getFileNo  ())  // 이전번호
                .build();

        // 파일정보 등록
        if (fileDAO.regiFile(vo) > 0)
            return vo;

        return null;
    }

}