package business.com.file.service;

import java.util.List;

import common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class FileVO extends BaseModel {
	
    // 일련번호
    private String sn;
    // 이전일련번호
    private String orgSn;
    // 업무구분코드
    private String dtySeCd;
    // 문서번호
    private String dcmtNo;
    // 세부문서번호
    private String dtlDcmtNo;
    // 파일경로
    private String filePath;
    // 저장파일명
    private String strgNm;
    // 파일명
    private String fileNm;
    // 파일크기
    private String fileSz;
    // 삭제여부
    private String delYn;
    // 등록자번호
    private String rgtrNo;
    // 등록일시
    private String regDttm;
    // 등록일자
    private String regDate;
    // 수정자번호
    private String mdfrNo;
    // 수정일시
    private String mdfDttm;
    // 수정일자
    private String mdfDate;
    // 상위서류코드
    private String upPapeCd;
    private String upPapeNm;
    // 서류코드
    private String papeCd;
    private String papeNm;
    // 처리상태코드
    private String prcsStusCd;
    private String prcsStusNm;

    // 세션사용자번호
    private String gsUserNo;

    private int fileIdx;

    // 임시파일여부
    private String tempYn;
    
    // 물리적 경로포함 파일명
    private String fullFileNm;

    // ,'로 연결한 일련번호 문자열
    private String sns;
    
    // 일련번호 List
    private List<String> snList;
}
