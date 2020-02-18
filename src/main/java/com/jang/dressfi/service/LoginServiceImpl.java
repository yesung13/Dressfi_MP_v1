package com.jang.dressfi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jang.dressfi.mapper.LoginMapper;
import com.jang.dressfi.model.DesignVO;
import com.jang.dressfi.model.FactoryVO;
import com.jang.dressfi.model.SellerVO;
import com.jang.dressfi.model.UserVO;

@Service(value = "loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginMapper loginMapper;

	
	@Override
	public UserVO getssUser(String mno) {
		// TODO Auto-generated method stub
		return loginMapper.getssUser(mno);
	}

	@Override
	public int MDdeleteUser(DesignVO designVO) {
		// TODO Auto-generated method stub
		return loginMapper.MDdeleteUser(designVO);
	}

	@Override
	public int MFdeleteUser(FactoryVO factoryVO) {
		// TODO Auto-generated method stub
		return loginMapper.MFdeleteUser(factoryVO);
	}

	@Override
	public int MCdeleteUser(SellerVO sellerVO) {
		// TODO Auto-generated method stub
		return loginMapper.MCdeleteUser(sellerVO);
	}

	@Override
	public UserVO findUser(UserVO userVO) {
		// TODO Auto-generated method stub
		return loginMapper.findUser(userVO);
	}

	@Override
	public List<UserVO> getUserList() {
		return this.loginMapper.getUserList();
	}

	@Override
	public List<DesignVO> getMDUserList() {
		// TODO Auto-generated method stub
		return this.loginMapper.getMDUserList();
	}

	@Override
	public List<FactoryVO> getMFUserList() {
		// TODO Auto-generated method stub
		return this.loginMapper.getMFUserList();
	}

	@Override
	public List<SellerVO> getMCUserList() {
		// TODO Auto-generated method stub
		return this.loginMapper.getMCUserList();
	}

	@Override
	public UserVO getUser(String userId) {
		// TODO Auto-generated method stub
		return loginMapper.getUser(userId);
	}

	@Override
	public UserVO getsUser(String username) {
		// TODO Auto-generated method stub
		return loginMapper.getsUser(username);
	}

	@Override
	public FactoryVO getMFUser(String userId) {
		// TODO Auto-generated method stub
		return loginMapper.getMFUser(userId);
	}

	@Override
	public UserVO getAdmin(UserVO userVO) {
		// TODO Auto-generated method stub
		return loginMapper.getAdmin(userVO);
	}

	@Override
	public DesignVO getMDUser(String userId) {
		// TODO Auto-generated method stub
		return loginMapper.getMDUser(userId);
	}

	@Override
	public SellerVO getMCUser(String userId) {
		// TODO Auto-generated method stub
		return loginMapper.getMCUser(userId);
	}

	@Override
	public int insertUser(UserVO userVO) {
		// TODO Auto-generated method stub
		return loginMapper.insertUser(userVO);
	}

	@Override
	public int insertMDUser(DesignVO designVO) {
		// TODO Auto-generated method stub
		return loginMapper.insertMDUser(designVO);
	}

	@Override
	public int insertMCUser(SellerVO sellerVO) {
		// TODO Auto-generated method stub
		return loginMapper.insertMCUser(sellerVO);
	}

	@Override
	public int insertMFUser(FactoryVO factoryVO) {
		// TODO Auto-generated method stub
		return loginMapper.insertMFUser(factoryVO);
	}

	@Override
	public int updateUser(UserVO userVO) {
		// TODO Auto-generated method stub
		return loginMapper.updateUser(userVO);
	}

	@Override
	public int deleteUser(UserVO userVO) {
		// TODO Auto-generated method stub
		return loginMapper.deleteUser(userVO);
	}

	@Override
	public UserVO findId(UserVO userVO) {
		// TODO Auto-generated method stub
		return loginMapper.findId(userVO);
	}

	@Override
	public UserVO findPass(UserVO userVO) {
		// TODO Auto-generated method stub
		return loginMapper.findPass(userVO);
	}

	@Override
	public void updatePass(UserVO userVO) {
		// TODO Auto-generated method stub
		loginMapper.updatePass(userVO);
	}

}
