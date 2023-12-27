package com.jang.dressfi.mapper;

import com.jang.dressfi.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginMapper {

    List<UserVO> getUserList();

    List<DesignVO> getMDUserList();

    List<FactoryVO> getMFUserList();

    List<SellerVO> getMCUserList();

    UserVO findUser(UserVO userVO);

    UserVO getssUser(String mno);

    UserVO getAdmin(UserVO userVO);

    UserVO getUser(String userId); // ID �ߺ�Ȯ��

    UserVO getsUser(String username);

    DesignVO getMDUser(String userId);

    FactoryVO getMFUser(String userId);

    SellerVO getMCUser(String userId);

    void updatePass(UserVO userVO);

    int updateUser(UserVO userVO); // ��������

    int insertMFUser(FactoryVO factoryVO);

    int insertMCUser(SellerVO sellerVO);

    int insertMMUser(MaterialVO materialVO);

    int insertMDUser(DesignVO designVO);

    int insertUser(UserVO userVO); // ȸ������

    int deleteUser(UserVO userVO);

    int MDdeleteUser(DesignVO designVO);

    int MFdeleteUser(FactoryVO factoryVO);

    int MCdeleteUser(SellerVO sellerVO);

    UserVO findId(UserVO userVO);

    UserVO findPass(UserVO userVO);
}
