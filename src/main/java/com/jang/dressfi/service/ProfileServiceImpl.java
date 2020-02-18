package com.jang.dressfi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jang.dressfi.mapper.ProfileMapper;
import com.jang.dressfi.model.AttFileVO;
import com.jang.dressfi.model.DesignVO;
import com.jang.dressfi.model.FactoryVO;
import com.jang.dressfi.model.FollowVO;
import com.jang.dressfi.model.KnowHowVO;
import com.jang.dressfi.model.PortFolioVO;
import com.jang.dressfi.model.PsalVO;
import com.jang.dressfi.model.SellerVO;
import com.jang.dressfi.model.UserVO;
import com.jang.dressfi.model.iReplyVO;
import com.jang.dressfi.model.khReplyVO;
import com.jang.dressfi.utils.PageHelper;

@Service(value = "ProfileService")
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileMapper ProfileMapper;

	PageHelper pageHelper = new PageHelper();

	@Override
	public ArrayList<Map<String, Object>> getdmain() {
		// TODO Auto-generated method stub
		return ProfileMapper.getdmain();
	}
	@Override
	public ArrayList<Map<String, Object>> getfmain() {
		// TODO Auto-generated method stub
		return ProfileMapper.getfmain();
	}
	@Override
	public ArrayList<Map<String, Object>> getcmain() {
		// TODO Auto-generated method stub
		return ProfileMapper.getcmain();
	}
	
//	@Override
//	public List<PortFolioVO> getdmain() {
//		// TODO Auto-generated method stub
//		return ProfileMapper.getdmain();
//	}
//
//	@Override
//	public List<PortFolioVO> getfmain() {
//		// TODO Auto-generated method stub
//		return ProfileMapper.getfmain();
//	}
//
//	@Override
//	public List<PortFolioVO> getcmain() {
//		// TODO Auto-generated method stub
//		return ProfileMapper.getcmain();
//	}

	@Override
	public List<KnowHowVO> getkmain() {
		// TODO Auto-generated method stub
		return ProfileMapper.getkmain();
	}

	@Override
	public List<PsalVO> getsend(String userId) {
		// TODO Auto-generated method stub
		return ProfileMapper.getsend(userId);
	}

	@Override
	public List<PsalVO> getrec(String userId) {
		// TODO Auto-generated method stub
		return ProfileMapper.getrec(userId);
	}

	@Override
	public PsalVO getpsal(int prno) {
		// TODO Auto-generated method stub
		return ProfileMapper.getpsal(prno);
	}

	@Override
	public void insertpsal(PsalVO psal) {
		// TODO Auto-generated method stub
		ProfileMapper.insertpsal(psal);
	}

	@Override
	public List<UserVO> pgetuser() {
		// TODO Auto-generated method stub
		return ProfileMapper.pgetuser();
	}

	@Override
	public List<DesignVO> pgetdesign(int ano1) {
		// TODO Auto-generated method stub
		return ProfileMapper.pgetdesign(ano1);
	}

	@Override
	public List<FactoryVO> pgetfactory(int ano1) {
		// TODO Auto-generated method stub
		return ProfileMapper.pgetfactory(ano1);
	}

	@Override
	public List<SellerVO> pgetseller(int ano1) {
		// TODO Auto-generated method stub
		return ProfileMapper.pgetseller(ano1);
	}
	@Override
	public List<FollowVO> pgetfl(String follower) {
		// TODO Auto-generated method stub
		return ProfileMapper.pgetfl(follower);
	}

	@Override
	public void ilikecnt(int ino) {
		// TODO Auto-generated method stub
		ProfileMapper.ilikecnt(ino);
	}

	@Override
	public int iviewcnt(int ino) {
		// TODO Auto-generated method stub
		return ProfileMapper.iviewcnt(ino);
	}

	@Override
	public void idelknow(int ino) {
		// TODO Auto-generated method stub
		ProfileMapper.idelknow(ino);
	}

	@Override
	public void ideletereply(int rino) {
		// TODO Auto-generated method stub
		ProfileMapper.ideletereply(rino);
	}

	@Override
	public void ilikereplycnt(int rino) {
		// TODO Auto-generated method stub
		ProfileMapper.ilikereplycnt(rino);
	}

	@Override
	public int iwritereply(iReplyVO ireply) {
		// TODO Auto-generated method stub
		int ino = ireply.getIno();
		this.ProfileMapper.ireplycnt(ino);
		
		return ProfileMapper.iwritereply(ireply);
	}

	@Override
	public List<iReplyVO> igetreply(int ino) {
		// TODO Auto-generated method stub
		return ProfileMapper.igetreply(ino);
	}

	@Override
	public FollowVO getfl(FollowVO followVO) {
		// TODO Auto-generated method stub
		return ProfileMapper.getfl(followVO);
	}

	@Override
	public void incfl(FollowVO followVO) {
		// TODO Auto-generated method stub
		this.ProfileMapper.incfl(followVO);
	}

	@Override
	public void delfl(FollowVO followVO) {
		// TODO Auto-generated method stub
		this.ProfileMapper.delfl(followVO);
	}

	@Override
	public List<FollowVO> getflerlist(String following) {
		// TODO Auto-generated method stub
		return ProfileMapper.getflerlist(following);
	}

	@Override
	public List<FollowVO> getflinglist(String follower) {
		// TODO Auto-generated method stub
		return ProfileMapper.getflinglist(follower);
	}

	@Override
	public List<khReplyVO> kgetreply(int hno) {
		// TODO Auto-generated method stub
		return ProfileMapper.kgetreply(hno);
	}

	@Override
	public int kwritereply(khReplyVO khreply) {
		// TODO Auto-generated method stub
		int hno = khreply.getHno();
		this.ProfileMapper.kreplycnt(hno);

		return this.ProfileMapper.kwritereply(khreply);
	}

	@Override
	public void likecnt(int hno) {
		// TODO Auto-generated method stub
		this.ProfileMapper.likecnt(hno);
	}

	@Override
	public void likereplycnt(int rhno) {
		// TODO Auto-generated method stub
		this.ProfileMapper.likereplycnt(rhno);
	}

	@Override
	public void delknow(int hno) {
		// TODO Auto-generated method stub
		this.ProfileMapper.delknow(hno);

	}

	@Override
	public void deletereply(int rhno) {
		// TODO Auto-generated method stub
		this.ProfileMapper.deletereply(rhno);
	}

	@Override
	public int viewcnt(int hno) {
		// TODO Auto-generated method stub
		return ProfileMapper.viewcnt(hno);
	}

	@Override
	public int updatePwd(UserVO userVO) {
		// TODO Auto-generated method stub
		return ProfileMapper.updatePwd(userVO);
	}

	@Override
	public int updateMDPwd(DesignVO designVO) {
		// TODO Auto-generated method stub
		return ProfileMapper.updateMDPwd(designVO);
	}

	@Override
	public int updateMFPwd(FactoryVO factoryVO) {
		// TODO Auto-generated method stub
		return ProfileMapper.updateMFPwd(factoryVO);
	}

	@Override
	public int updateMCPwd(SellerVO sellerVO) {
		// TODO Auto-generated method stub
		return ProfileMapper.updateMCPwd(sellerVO);
	}

	@Override
	public int updateProfile(UserVO userVO) {
		// TODO Auto-generated method stub
		return ProfileMapper.updateProfile(userVO);
	}

	@Override
	public int updateMDProfile(DesignVO designVO) {
		// TODO Auto-generated method stub
		return ProfileMapper.updateMDProfile(designVO);
	}

	@Override
	public int updateMFProfile(FactoryVO factoryVO) {
		// TODO Auto-generated method stub
		return ProfileMapper.updateMFProfile(factoryVO);
	}

	@Override
	public int updateMCProfile(SellerVO sellerVO) {
		// TODO Auto-generated method stub
		return ProfileMapper.updateMCProfile(sellerVO);
	}

	@Override
	public UserVO getProfile(String mno) {
		// TODO Auto-generated method stub
		return this.ProfileMapper.getProfile(mno);
	}

	@Override
	public DesignVO getMDProfile(String mdno) {
		// TODO Auto-generated method stub
		return this.ProfileMapper.getMDProfile(mdno);
	}

	@Override
	public FactoryVO getMFProfile(String mfno) {
		// TODO Auto-generated method stub
		return this.ProfileMapper.getMFProfile(mfno);
	}

	@Override
	public SellerVO getMCProfile(String mcno) {
		// TODO Auto-generated method stub
		return this.ProfileMapper.getMCProfile(mcno);
	}

	@Override
	public void deleteProfile(int mno) {
		this.ProfileMapper.deleteProfile(mno);
	}

	@Override
	public List<AttFileVO> getFileList(int bno) {
		return this.ProfileMapper.getFileList(bno);
	}

	@Override
	public AttFileVO getFile(int pno) {
		return this.ProfileMapper.getFile(pno);
	}

	@Override
	public String getFileName(int fno) {
		return this.ProfileMapper.getFileName(fno);
	}

	@Override
	public int insertFile(AttFileVO file) {
		return this.ProfileMapper.insertFile(file);
	}

	@Override
	public void deleteFile(int fno) {
		this.ProfileMapper.deleteFile(fno);
	}

	@Override
	public int insertPortFolio(PortFolioVO portfolioVO) {
		// TODO Auto-generated method stub
		return ProfileMapper.insertPortFolio(portfolioVO);
	}

	@Override
	public ArrayList<PortFolioVO> getPortFolio(PortFolioVO portfolioItem) {
		// TODO Auto-generated method stub
		return ProfileMapper.getPortFolio(portfolioItem);
	}

	@Override
	public PortFolioVO getPortFolioDeteil(PortFolioVO portfolioVO) {
		// TODO Auto-generated method stub
		return ProfileMapper.getPortFolioDeteil(portfolioVO);
	}

	@Override
	public int insertKnowHow(KnowHowVO knowhowVO) {
		// TODO Auto-generated method stub
		return ProfileMapper.insertKnowHow(knowhowVO);
	}

	@Override
	public KnowHowVO getKnowHowDeteil(KnowHowVO knowhowVO) {
		// TODO Auto-generated method stub
		return ProfileMapper.getKnowHowDeteil(knowhowVO);
	}

	@Override
	public ArrayList<KnowHowVO> getKnowHow(KnowHowVO knowhowVO) {
		// TODO Auto-generated method stub
		return ProfileMapper.getKnowHow(knowhowVO);
	}

	@Override
	public ArrayList<Map<String, Object>> selectDesign(Map<String, Object> item) {
		// TODO Auto-generated method stub
		return ProfileMapper.selectDesign(item);
	}

	@Override
	public ArrayList<Map<String, Object>> selectFactory(Map<String, Object> item) {
		// TODO Auto-generated method stub
		return ProfileMapper.selectFactory(item);
	}

	@Override
	public ArrayList<Map<String, Object>> selectSeller(Map<String, Object> item) {
		// TODO Auto-generated method stub
		return ProfileMapper.selectSeller(item);
	}
}
