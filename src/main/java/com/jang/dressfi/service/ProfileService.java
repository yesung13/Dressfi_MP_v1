package com.jang.dressfi.service;

import com.jang.dressfi.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ProfileService {

    List<UserVO> pgetuser();

    List<DesignVO> pgetdesign(int ano1);

    List<FactoryVO> pgetfactory(int ano1);

    List<SellerVO> pgetseller(int ano1);

    List<FollowVO> pgetfl(String follower);

    //	List<PortFolioVO> getdmain();
//
//	List<PortFolioVO> getfmain();
//
//	List<PortFolioVO> getcmain();
    ArrayList<Map<String, Object>> getdmain();

    ArrayList<Map<String, Object>> getfmain();

    ArrayList<Map<String, Object>> getcmain();

    List<KnowHowVO> getkmain();

    void insertpsal(PsalVO psal);

    List<PsalVO> getsend(String userId);

    List<PsalVO> getrec(String userId);

    PsalVO getpsal(int prno);

    UserVO getProfile(String mno);

    DesignVO getMDProfile(String mdno);

    FactoryVO getMFProfile(String mfno);

    SellerVO getMCProfile(String mcno);

    void ilikecnt(int ino); // �Խñ� ���ƿ�

    int iviewcnt(int ino); // �Խñ� ��ȸ�� ����

    void idelknow(int ino); // �Խñ� ����

    void ideletereply(int rino); // ��� ����

    void ilikereplycnt(int rino); // ��� ���ƿ� ����

    int iwritereply(iReplyVO ireply); // ��� �ۼ�

    List<iReplyVO> igetreply(int ino); // ��� ����

    void incfl(FollowVO followVO);// �ȷο� �߰�

    void delfl(FollowVO followVO);// �ȷο� ����

    List<FollowVO> getflerlist(String following);// �� �ȷο� ����

    List<FollowVO> getflinglist(String follower);// �� �ȷ��� ����

    FollowVO getfl(FollowVO followVO); // �ȷο� ���� üũ

    // ���Ͽ�Խ��� ���ƿ�, ����
    void likecnt(int hno);

    void likereplycnt(int rhno);

    void delknow(int hno);

    int updateProfile(UserVO userVO);

    int updateMDProfile(DesignVO designVO);

    int updateMFProfile(FactoryVO factoryVO);

    int updateMCProfile(SellerVO sellerVO);

    int updatePwd(UserVO userVO);

    int updateMDPwd(DesignVO designVO);

    int updateMFPwd(FactoryVO factoryVO);

    int updateMCPwd(SellerVO sellerVO);

    void deleteProfile(int mno); // �ۻ���

    void deletereply(int rhno);

    int kwritereply(khReplyVO khreply);

    List<khReplyVO> kgetreply(int hno);

    int viewcnt(int hno);

    List<AttFileVO> getFileList(int bno); // ÷������ ��� ��ȸ

    AttFileVO getFile(int pno);

    String getFileName(int fno); // ÷������ �̿� ��ȸ

    int insertFile(AttFileVO file); // ÷������ ����

    void deleteFile(int fno); // ÷������ ����

    int insertPortFolio(PortFolioVO portfolioVO);

    ArrayList<PortFolioVO> getPortFolio(PortFolioVO portfolioItem);

    PortFolioVO getPortFolioDeteil(PortFolioVO portfolioVO);

    int insertKnowHow(KnowHowVO knowhowVO);

    KnowHowVO getKnowHowDeteil(KnowHowVO knowhowVO);

    ArrayList<KnowHowVO> getKnowHow(KnowHowVO knowhowVO);

    ArrayList<Map<String, Object>> selectDesign(Map<String, Object> item);

    ArrayList<Map<String, Object>> selectFactory(Map<String, Object> item);

    ArrayList<Map<String, Object>> selectSeller(Map<String, Object> item);

}
