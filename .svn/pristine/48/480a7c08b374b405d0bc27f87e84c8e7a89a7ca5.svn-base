package business.com.file.service;

import java.util.List;
import java.util.Map;

import commf.paging.PaginatedArrayList;
import common.file.FileInfo;

/**
 * [서비스인터페이스] - 첨부파일 공통 Service Interface
 *
 * @class   : FileService
 * @author  : LSH
 * @since   : 2021.10.07
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface FileService {

    /**
     * 서류기준 제출파일 목록조회
     */
    public List listFileByPape(FileVO fileVO) throws Exception;
    /**
     * 일련변호 배열 제출파일 목록조회
     */
    public List listFileByArray(FileVO fileVO) throws Exception;
    /**
     * 제출파일 목록조회
     */
    public List listFile(FileVO fileVO) throws Exception;
    /**
     * 제출파일 목록조회 (페이징)
     */
    public PaginatedArrayList listFile(FileVO fileVO, int currPage, int pageSize) throws Exception;
    /**
     * 제출파일 상세조회
     */
    public FileVO viewFile(String sn) throws Exception;
    /**
     * 신청서류 목록조회
     */
    public List listPapeFile(Map paramMap) throws Exception;
    /**
     * 신청서류 그룹목록 조회
     */
    public List listPapeFileGroup(Map paramMap) throws Exception;
    /**
     * 신청서류 양식파일조회
     */
    public FileInfo viewPapeFile(FileVO fileVO) throws Exception;
    /**
     * 제출파일 임시저장처리
     */
    public FileVO saveTempFile(FileInfo fileInfo) throws Exception;
}