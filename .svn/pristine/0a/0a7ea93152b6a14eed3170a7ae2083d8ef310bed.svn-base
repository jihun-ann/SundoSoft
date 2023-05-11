package business.com.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import business.com.user.service.IndustryCodeVO;
import business.com.user.service.InvestCodeVO;
import business.com.user.service.InvestInfoVO;
import business.com.user.service.IrinfoVO;
import business.com.user.service.SearchValueVO;
import common.base.BaseDAO;

@Repository("IrinfoDAO")
@SuppressWarnings({"all"})
public class IrinfoDAO extends BaseDAO{
	
	public List<IrinfoVO> findIrinfoLst() {
		List<IrinfoVO> lst = selectList("Main.findIrAll");
		return lst;
	}
	
	public List<IrinfoVO> searchirLst(SearchValueVO VO){
		return selectList("Main.searchirL",VO);
	}
	
	public List<InvestInfoVO> findInvestInfoAll(){
		return selectList("Main.findInvestInfoAll");
	}
	
	public List<InvestCodeVO> findInvtCodeAll(){
		return selectList("Main.findInvestCodeAll");
	}
	
	public List<IndustryCodeVO> findIndstCodeAll(){
		return selectList("Main.findIndustryCodeAll");
	}
	public IrinfoVO findOneIrinfo(String entNum) {
		return selectOne("Main.findOneIrinfo",entNum);
	}
	public List<InvestInfoVO> findOneInvtinfo(String entNum) {
		return selectList("Main.findOneInvtinfo",entNum);
	}
	
	public int updateirinfo(HashMap<String, String> datamap) {
		return update("Main.updateirinfo", datamap);
	}
}
