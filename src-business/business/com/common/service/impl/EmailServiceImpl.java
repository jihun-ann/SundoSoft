package business.com.common.service.impl;

import java.io.File;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import business.com.CommConst;
import business.com.accesslog.AccessControlService;
import business.com.common.service.EmailService;
import business.com.common.service.EmailVO;
import business.com.file.service.FileVO;
import common.base.BaseService;
import common.util.CommUtils;

/**
 * [서비스클래스] - 이메일 전송 구현 클래스
 *
 * @class   : CommServiceImpl
 * @author  :
 * @since   : 2023.03.08
 * @version : 1.0
 *
 *   수정일            수정자                             수정내용
 *  -------    --------    ---------------------------
 *
 */
@Service("EmailService")
public class EmailServiceImpl extends BaseService implements EmailService {

	@Autowired
	private SpringTemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSenderImpl emailSender;

    @Autowired
    private AccessControlService accessControlService;

	@Override
	public boolean sendEmail(EmailVO emailVO) throws Exception {

	   	MimeMessage       message = emailSender.createMimeMessage();
        MimeMessageHelper helper  = new MimeMessageHelper(message, true, CommConst.SYSTEM_CHARSET);
        //메일 제목 설정 (B는 Base64, Q는 quoted-printable)
        helper.setSubject(
        	MimeUtility.encodeText(
        		emailVO.getSubject(), 
        		CommConst.SYSTEM_CHARSET, 
        		CommConst.EMAIL_BASE64_ENCODE
        	)
        );
        //송신자 설정
        helper.setFrom(
        	new InternetAddress(
        		emailVO.getFromAddress(), 
        		emailVO.getFromName(), 
        		CommConst.SYSTEM_CHARSET
        	)
        );
        //수신자 설정
        helper.setTo(
        	new InternetAddress(
        		emailVO.getToAddress(), 
        		emailVO.getToName(), 
        		CommConst.SYSTEM_CHARSET
        	)
        );
        if (CommUtils.isNotEmpty(emailVO.getCcName())) {
            //참조자 설정
            helper.setCc(
            	new InternetAddress(
            		emailVO.getCcAddress(), 
            		emailVO.getCcName(), 
            		CommConst.SYSTEM_CHARSET
            	)
            );
        }
        // 템플릿 형식의 컨텐츠이면
        if (emailVO.isUseTemplate()) {
	        //템플릿에 전달할 데이터 설정
	        Context ctx = new Context();
	        ctx.setVariables(emailVO.getData());
	        //템플릿 프로세스
	        String html = templateEngine.process(emailVO.getTemplate(), ctx);
	        helper.setText(html, true);
	        //파싱된 내용을 메일객체에 저장한다.
	        emailVO.setContent(html);
	        
	        //템플릿에 들어가는 이미지 cid로 삽입
	        if (!CollectionUtils.isEmpty(emailVO.getImages())) {
	        	for (Map<String,String> image : emailVO.getImages()) {
	                helper.addInline(
	                	(String)image.get("label"), 
	                	new FileSystemResource(
	                    	new File(emailVO.getImageDirectory().getRealName((String)image.get("name"))
	                    )
	                ));
	        	}
	        }
        }
        // 일반 컨텐츠이면
        else {
	        helper.setText(emailVO.getContent(), emailVO.isUseHtml());
        }
        //첨부파일
        if (!CollectionUtils.isEmpty(emailVO.getFiles())) {
        	
            for (FileVO fileVO : emailVO.getFiles()) {
            	
                FileSystemResource fileSystemResource = new FileSystemResource(
                	new File(fileVO.getFullFileNm())
                );
                helper.addAttachment(
                	MimeUtility.encodeText(
               			fileVO.getFileNm(), 
               			CommConst.SYSTEM_CHARSET, 
               			CommConst.EMAIL_BASE64_ENCODE
                	), 
                	fileSystemResource
                );
            }
        }
        //메일 보내기
        emailSender.send(message);
        //발송상태 표시
        emailVO.setSendCd(CommConst.EMAIL_SEND_SUCCESS);
        
        //메일발송 이력 저장
        accessControlService.regiEmailLog(emailVO);

		return false;
	}
}
