package business.com.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.user.service.IndustryCodeVO;
import business.com.user.service.InvestCodeVO;
import business.com.user.service.InvestInfoVO;
import business.com.user.service.IrinfoService;
import business.com.user.service.IrinfoVO;
import business.com.user.service.SearchValueVO;
import common.base.BaseService;


@Service("IrinfoService")
@SuppressWarnings("all")
public class IrinfoServiceImpl extends BaseService implements IrinfoService{

	@Resource(name="IrinfoDAO")
	private IrinfoDAO irinfoDAO;
	
	@Override
	public List<IrinfoVO> findIrinfoLst() throws Exception {
		return irinfoDAO.findIrinfoLst() ;
	}

	@Override
	public List<IrinfoVO> searchirLst(SearchValueVO VO) {
		return irinfoDAO.searchirLst(VO);
	}

	@Override
	public List<InvestInfoVO> findInvestInfoAll() {
		return irinfoDAO.findInvestInfoAll();
	}

	@Override
	public List<InvestCodeVO> findInvtCodeAll() throws Exception {
		return irinfoDAO.findInvtCodeAll();
	}

	@Override
	public List<IndustryCodeVO> findIndstCodeAll() throws Exception {
		return irinfoDAO.findIndstCodeAll();
	}

	@Override
	public IrinfoVO findOneIrinfo(String entNum) throws Exception {
		return irinfoDAO.findOneIrinfo(entNum);
	}

	@Override
	public List<InvestInfoVO> findOneInvtinfo(String entNum) throws Exception {
		return irinfoDAO.findOneInvtinfo(entNum);
	}

	@Override
	public int updateirinfo(HashMap<String, String> datamap) throws Exception {
		return irinfoDAO.updateirinfo(datamap);
	}

}
