package business.com.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@SuppressWarnings("all")
public interface IrinfoService {
	public List<IrinfoVO> findIrinfoLst() throws Exception;
	
	public List<IrinfoVO> searchirLst(SearchValueVO VO) throws Exception;
	
	public List<InvestInfoVO> findInvestInfoAll()throws Exception;
	
	public List<InvestCodeVO> findInvtCodeAll()throws Exception;
	
	public List<IndustryCodeVO> findIndstCodeAll()throws Exception;
	
	public IrinfoVO findOneIrinfo(String entNum) throws Exception;
	
	public List<InvestInfoVO> findOneInvtinfo(String entNum) throws Exception;
	
	public int updateirinfo(HashMap<String, String> datamap) throws Exception; 
}
