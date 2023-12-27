package com.jang.dressfi.service;

import com.jang.dressfi.model.DesignVO;
import com.jang.dressfi.model.FactoryVO;
import com.jang.dressfi.model.SellerVO;
import com.jang.dressfi.model.UserVO;

import java.util.List;

public interface LoginService {

    List<UserVO> getUserList();

    List<DesignVO> getMDUserList();

    List<FactoryVO> getMFUserList();

    List<SellerVO> getMCUserList();

    UserVO findUser(UserVO userVO);

    UserVO getssUser(String mno);

    UserVO getUser(String userId);

    UserVO getsUser(String username);

    DesignVO getMDUser(String userId);

    FactoryVO getMFUser(String userId);

    SellerVO getMCUser(String userId);

    UserVO getAdmin(UserVO userVO);

    void updatePass(UserVO userVO);

    int insertUser(UserVO userVO);

    int insertMDUser(DesignVO designVO);

    int insertMFUser(FactoryVO factoryVO);

    int insertMCUser(SellerVO sellerVO);

    int updateUser(UserVO userVO);

    int deleteUser(UserVO userVO);

    int MDdeleteUser(DesignVO designVO);

    int MFdeleteUser(FactoryVO factoryVO);

    int MCdeleteUser(SellerVO sellerVO);

    UserVO findId(UserVO userVO);

    UserVO findPass(UserVO userVO);
}
