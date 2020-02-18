package com.jang.dressfi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jang.dressfi.model.DesignVO;
import com.jang.dressfi.model.FactoryVO;
import com.jang.dressfi.model.MaterialVO;
import com.jang.dressfi.model.SellerVO;
import com.jang.dressfi.model.UserVO;

@Mapper
public interface LoginMapper {

	List<UserVO> getUserList();

	List<DesignVO> getMDUserList();

	List<FactoryVO> getMFUserList();

	List<SellerVO> getMCUserList();

	UserVO findUser(UserVO userVO);

	UserVO getssUser(String mno);
	
	UserVO getAdmin(UserVO userVO);

	UserVO getUser(String userId); // ID 중복확인

	UserVO getsUser(String username);

	DesignVO getMDUser(String userId);

	FactoryVO getMFUser(String userId);

	SellerVO getMCUser(String userId);

	void updatePass(UserVO userVO);

	int updateUser(UserVO userVO); // 정보수정

	int insertMFUser(FactoryVO factoryVO);

	int insertMCUser(SellerVO sellerVO);

	int insertMMUser(MaterialVO materialVO);

	int insertMDUser(DesignVO designVO);

	int insertUser(UserVO userVO); // 회원가입

	int deleteUser(UserVO userVO);

	int MDdeleteUser(DesignVO designVO);

	int MFdeleteUser(FactoryVO factoryVO);

	int MCdeleteUser(SellerVO sellerVO);

	UserVO findId(UserVO userVO);

	UserVO findPass(UserVO userVO);
}
