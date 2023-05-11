 package business.com.file.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.com.file.service.FileVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;
import common.file.FileInfo;

/**
 * [DAO클래스] - 신청첨부파일을 관리하는 DAO 클래스
 *
 * 사용 가능한  DAO Statement Method
 * 1. list          : 리스트 조회시 사용함.
 * 2. pageListOra   : 페이징처리용 리스트조회시 사용함. for Oracle, Tibero
 * 3. view          : 단건조회, 상세조회시 사용함.
 * 4. save          : INSERT, UPDATE, DELETE 모두 사용가능. (Return Type : Integer)
 * 5. insert        : INSERT (Return String : Key 채번 사용함.)
 * 6. update        : UPDATE (Return Type : Integer)
 * 7. delete        : DELETE (Return Type : Integer)
 *
 *
 * @class   : FileDAO
 * @author  : LSH
 * @since   : 2021.10.07
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("FileDAO")
@SuppressWarnings({"all"})
public class FileDAO extends BaseDAO {

    /**
     * 서류기준 제출파일 목록조회
     */
    public List listFileByPape(FileVO fileVO) {
        return list("File.listFileByPape", fileVO);    
    }
    /**
     * 일련변호 배열 제출파일 목록조회
     */
    public List listFileByArray(FileVO fileVO) {
        return list("File.listFileByArray", fileVO);    
    }
    /**
     * 제출파일 목록조회
     */
    public List listFile(FileVO fileVO) {
    	return list("File.listFile", fileVO);
    }
    /**
     * 제출파일 목록조회 (페이징)
     */
    public PaginatedArrayList listFile(FileVO fileVO, int currPage, int pageSize) {
    	return pageList("File.listFile", fileVO, currPage, pageSize);
    }
    /**
     * 제출파일 상세조회
     */
    public FileVO viewFile(String sn) {
    	return (FileVO)view("File.viewFile", sn);
    }
    /**
     * 신청서류 목록조회
     */
	public List listPapeFile(Map paramMap) {
    	return list("File.listPapeFile", paramMap);
	}
    /**
     * 신청서류 그룹목록 조회
     */
	public List listPapeFileGroup(Map paramMap) {
    	return list("File.listPapeFileGroup", paramMap);
	}
    /**
     * 신청서류 양식파일조회
     */
	public FileInfo viewPapeFile(FileVO fileVO) {
    	return (FileInfo)view("File.viewPapeFile", fileVO);
	}
    /**
     * 제출파일 등록저장처리
     */
    public int regiFile(FileVO fileVO) {
        return insert("File.regiFile", fileVO);
    }
}