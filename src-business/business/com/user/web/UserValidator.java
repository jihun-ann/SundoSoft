package business.com.user.web;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import business.com.user.service.UserVO;
import common.util.CommUtils;

/**
 * [검증클래스] - 회원가입 Server Side의 데이터 검증을 위한 Validator
 *
 * @class   : UserValidator
 * @author  : LSH
 * @since   : 2023.03.20
 * @version : 1.0
 *
 *   수정일     수정자              수정내용
 *  -------    --------    ---------------------------
 *
 * Errors 에 대한 사용가능 메서드 
 *     
 *     reject(String errorCode) 
 *     : 전 객체에 대한 글로벌 에러 코드를 추가
 *     
 *     reject(String errorCode, String defaultMessage) 
 *     : 전 객체에 대한 글로벌 에러코드를 추가하고, 
 *       에러코드에 대한 메세지가 존재하지 않을 경우 defaultMessage를 사용
 *     
 *     reject(String errorCode, Object[] errorArgs, String defaultMessage) 
 *     : 전 객체에 대한 글로벌 에러코드를 추가, 
 *       메세지 인자로 errorArgs를 전달, 
 *       에러코드에 대한 메세지가 존재하지 않을 경우 defaultMessage를 사용
 *     
 *     rejectValue(String field, String errorCode) 
 *     : 필드에 대한 에러코드를 추가
 *     
 *     rejectValue(String field, String errorCode, String defaultMessage) 
 *     : 필드에 대한 에러코드를 추가 
 *       에러코드에 대한 메세지가 존재하지 않을 경우 defaultMessage를 사용
 *     
 *     rejectValue(String field, String errorCode, Object[] errorArgs, String defaultMessage) 
 *     : 필드에 대한 에러코드를 추가, 
 *       메세지 인자로 errorArgs를 전달, 
 *       에러코드에 대한 메세지가 존재하지 않을 경우 defaultMessage사용
 */
@Service
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return UserVO.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	
        UserVO data = (UserVO) o;
        
        if (!"Y".equals(data.getCertYn())) {
            errors.reject("certYn", "본인인증이 완료되지 않았습니다.");
        }
        if (CommUtils.nvlTrim(data.getUserNm()).isEmpty()) {
            errors.reject("userNm", "성명은 필수입력사항입니다.");
        }
        if (CommUtils.nvlTrim(data.getUserNm()).isEmpty()) {
            errors.reject("mobile", "핸드폰 번호는 필수입력사항입니다.");
        }
        if (CommUtils.nvlTrim(data.getUserId()).isEmpty()) {
            errors.reject("userId", "아이디는 필수입력사항입니다.");
        }
        if (CommUtils.nvlTrim(data.getPswd()).isEmpty()) {
            errors.reject("pswd", "비밀번호는 필수입력사항입니다.");
        }
    }
}
