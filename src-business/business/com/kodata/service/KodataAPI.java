package business.com.kodata.service;

import java.net.URI;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import commf.exception.BusinessException;

/**
 *  [컨트롤러클래스] - 한국평가데이터 KODATA 연계 API 모듈
 *
 * @class   : KodataAPI
 * @author  : LSH
 * @since   : 2023.03.08
 * @version : 1.0
 * 
 * -----------------------------------------------------------------------------
 * 서비스별 요청 예제
 * #enpinfo 서비스 (테스트서버)
 * https://testkedex.cretop.com:포트/enpinfo?userid={기관ID}&process=S&bzno=1111111111&pidagryn
 * =Y&jmno={기관서비스전문상세코드}&format=XML
 * 
 * 서비스 응답 전문 (JSON 구성도)
 * {"KED":{"HEADER":{... 응답 헤더 구성 ... },
 *         "CONTENTS":{... 전문 내용 ... }
 *        }
 * }
 * 
 * 요청 파라메터
 * 파라미터명 파라미터 한글명 필수구분 설명 형식
 * userid 아이디 필수 요청자 ID VARCHAR(20)
 * process 처리구분 필수 조회(S) VARCHAR(1)
 * bzno 요청사업자번호 필수(택1) bzno/cono/pid 중 반드시 선택1 VARCHAR(10)
 * cono 요청법인번호 (택1) VARCHAR(13)
 * pid 요청주민번호 (택1) (기업정보 조회시 사용안함) VARCHAR(13)
 * pidagryn 개인정보조회동의여부 필수 Y or N VARCHAR(1)
 * jmno 전문상세코드 필수 요청 서비스 전문상세코드 VARCHAR(20)
 * format 데이터 제공방식 필수 XML or JSON VARCHAR(10)
 * 
 * 응답 헤더(HEADER) 구성
 * # enpinfo 서비스
 * 파라미터명 파라미터 한글명 필수구분 설명 형식
 * userid 아이디 요청자 ID VARCHAR(20)
 * process 처리구분 조회(S) VARCHAR(1)
 * bzno 요청사업자번호 bzno/cono/pid 중 반드시 선택1 VARCHAR(10)
 * cono 요청법인번호 VARCHAR(13)
 * pid 요청주민번호 VARCHAR(13)
 * pidagryn 개인정보조회동의여부 Y or N VARCHAR(1)
 * jmno 전문상세코드 요청 서비스 전문상세코드 VARCHAR(20)
 * format 데이터 제공방식 XML or JSON VARCHAR(10)
 * resptm 응답시간 YYYYMMDDHH24MISS VARCHAR(14)
 * errcd 에러코드 에러공통코드 VARCHAR(2)
 * errctt 에러내용 에러공통코드 VARCHAR(100
 * 
 * 통신결과 에러코드
 * 구분 코드 코드내용 비고
 * 정상 00 OK 정상처리
 * 에러 01 Parameters are required 필수항목 값이 누락된 경우, 요청사업자/법인/주민번호 모두 없는 경우
 * 에러 02 No results found 조회 결과 없음 / KED코드가 없는 경우
 * 에러 03 Invalid credentials 권한을 부여받지 않은 전문상세코드를 요청한 경우
 * 에러 04 Invalid Agreement 주민번호 요청은 했으나 동의 여부값이 'N'인 경우
 * 에러 05 Duplication 요청한 번호에 대해 매칭 기관이 2개 이상인 경우
 * 에러 99 Failure 기타오류
 */
public class KodataAPI {

	protected static Log log = LogFactory.getLog(KodataAPI.class);
	
	private static String title = "KODATA API";
	
	private KodataEntity entity;
	
	public KodataAPI(KodataEntity entity) {
		this.entity = entity;
	}
    
	/**
     * KODATA 기본 API 실행
     */
    private JSONObject _openAPI() throws Exception {

		URI uri = UriComponentsBuilder
				.fromUriString(this.entity.getUrl ())
				.path         (this.entity.getPath())
		        .queryParam   ("userid"   , this.entity.getUserid   ())
		        .queryParam   ("process"  , this.entity.getProcess  ())
		        .queryParam   ("bzno"     , this.entity.getBzno     ())
		        .queryParam   ("cono"     , this.entity.getCono     ())
		        .queryParam   ("pid"      , this.entity.getPid      ())
		        .queryParam   ("pidagryn" , this.entity.getPidagryn ())
		        .queryParam   ("jmno"     , this.entity.getJmno     ())
		        .queryParam   ("format"   , this.entity.getFormat   ())
				.build()
				.toUri();

		log.debug(title+" URI : "+ uri.toString());
        
        RestTemplate rt = new RestTemplate();
		// API 호출
		ResponseEntity<String> res = rt.getForEntity(uri, String.class);
		
		// API 응답결과 받기
		if (res.getStatusCode() == HttpStatus.OK) {
			JSONParser parser = new JSONParser();
	        return (JSONObject) parser.parse(res.getBody());
		}
		return null;
    }
    
    // TODO API 연계전까지 테스트를 위해 정의한 샘플
    @SuppressWarnings("unchecked")
	private JSONObject _getSample() throws ParseException {

    	String header  = "{\"userid\": \"test\", \"process\": \"S\", \"bzno\":\"1198125774\", \"cono\":\"\", \"pid\":\"\", \"pidagryn\":\"Y\", \"jmno\":\"KED001\",\"format\":\"JSON\", \"resptm\":\"20230322173700\", \"errcd\":\"00\", \"errctt\":\"OK\"}";
    	String enpinfo = "{\"kedcd\" : \"39939\", \"reper_name\" : \"채명훈\", \"enp_nm\" : \"전성\", \"enp_nm_trd\" : \"전성\", \"enp_nm_eng\" : \"JEONSUNG CO.,LTD.\", \"enp_typ\" : \"1\", \"enp_sze\" : \"2\", \"grdt_pln_dt\" : \"\", \"enp_fcd\" : \"1\", \"estb_fcd\" : \"2\", \"enp_scd\" : \"1\", \"enp_scd_chg_dt\" : \"19970621\", \"pubi_fcd\" : \"0\", \"venp_yn\" : \"N\", \"enp_form_fr\" : \"1\", \"bzc_cd\" : \"C30399\", \"fs_bzc_cd\" : \"1\", \"grp_cd\" : \"\", \"grp_nm\" : \"\", \"cono_pid\" : \"1101111429558\", \"estb_dt\" : \"19970621\", \"ipo_cd\" : \"4\", \"trdbz_rpt_no\" : \"\", \"list_dt\" : \"\", \"dlist_dt\" : \"\", \"mtx_bnk_cd\" : \"30009\", \"mtx_bnk_nm\" : \"기업은행 본부총괄\", \"ovd_tx_bnk_cd\" : \"34513\", \"ovd_tx_bnk_nm\" : \"기업은행 서시화\", \"acct_eddt\" : \"1231\", \"hpage_url\" : \"www.jeonsung.co.kr\", \"email\" : \"webmail@jeonsung.co.kr\", \"std_dt\" : \"20220504\", \"bzno\" : \"1198125774\", \"loc_zip\" : \"27466\", \"loc_addra\" : \"충북 충주시 대소원면\", \"loc_addrb\" : \"본리 629번지\", \"tel_no\" : \"043 845 8901\", \"fax_no\" : \"043 845 8905\", \"laborer_sum\" : \"115\", \"pd_nm\" : \"자동차 부품\", \"ksic9_bzc_cd\" : \"C30399\", \"rel_kedcd\" : \"225768\", \"rel_estb_dt\" : \"19870810\", \"loc_rdnm_zip\" : \"27466\", \"loc_rdnm_addra\" : \"충북 충주시 대소원면 첨단산업1로\", \"loc_rdnm_addrb\" : \"146 (본리)\", \"loc_rdnm_addrb_conf_yn\" : \"\", \"loc_addrb_conf_yn\" : \"\"}";
    	String mntinfo = "{\"bzno\": \"1198125774\", \"base_dt\" : \"20220504\", \"lqdt_cls\": \"03\", \"lqdt_dt\" : \"\", \"lqdt_rsn\": \"부가가치세 일반과세자입니다.\"}";

		JSONParser parser = new JSONParser();
		JSONObject hedobj = (JSONObject)parser.parse( header  );
		JSONObject enpobj = (JSONObject)parser.parse( enpinfo );
		JSONObject mntobj = (JSONObject)parser.parse( mntinfo );
		
		JSONObject conobj = new JSONObject();
		conobj.put("ENPlntr0000", enpobj);
		conobj.put("MNTlqdt0000", mntobj);
		
		JSONObject kedobj = new JSONObject();
		kedobj.put("HEADER"  , hedobj);
		kedobj.put("CONTENTS", conobj);
		
		JSONObject data = new JSONObject();
		data.put("KED", kedobj);
		
		return data;
    }
    
    public void execute(KodataVO kodataVO) {
    	try {
        	// TODO API 연계시 해당 주석 제거할것
    		//JSONObject result = _openAPI();
    		JSONObject result = _getSample();
        	
	        if (result != null) {
		        JSONObject jsonKED = (JSONObject)result.get("KED");
		        if (jsonKED != null) {
		        	
		        	JSONObject jsonHEAD = (JSONObject)jsonKED.get("HEADER");
		        	this.entity.setErrcd ((String)jsonHEAD.get("errcd" ));
		        	this.entity.setErrtt ((String)jsonHEAD.get("errtt" ));
		        	this.entity.setResptm((String)jsonHEAD.get("resptm"));
		        	// 성공인 경우
		        	if ("00".equals(this.entity.getErrcd())) {
			        	JSONObject jsonCONT = (JSONObject)jsonKED.get("CONTENTS");
			        	// Object 변환
			        	kodataVO.fromJSON(jsonCONT);
		        	}
		        	else
		        		throw new BusinessException(title+" ["+this.entity.getErrcd()+"] "+this.entity.getErrtt());
		        }
	        }
    	}
    	catch(BusinessException be) {
    		throw new BusinessException(be);
    	}
    	catch(Exception e) {
    		throw new BusinessException(title+"KODATA 통신오류가 발생하였습니다.", e);
    	}
    }
}
