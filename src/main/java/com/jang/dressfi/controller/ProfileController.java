package com.jang.dressfi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jang.dressfi.model.DesignVO;
import com.jang.dressfi.model.FactoryVO;
import com.jang.dressfi.model.FollowVO;
import com.jang.dressfi.model.KnowHowVO;
import com.jang.dressfi.model.PortFolioVO;
import com.jang.dressfi.model.SellerVO;
import com.jang.dressfi.model.UserVO;
import com.jang.dressfi.model.iReplyVO;
import com.jang.dressfi.model.khReplyVO;
import com.jang.dressfi.service.LoginService;
import com.jang.dressfi.service.ProfileService;
import com.jang.dressfi.utils.BCrypt;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	@Autowired //
	private LoginService loginService; //
	@Autowired
	private ProfileService profileService;
	private String uploadPath = "C:\\Users\\berno\\eclipse-workspace\\Dressfi_MP_v1\\src\\main\\webapp\\resources\\img\\";

	// mypage profile 부분
	@RequestMapping(value = "/myhome.do", method = RequestMethod.GET)
	public String profile(Model model, UserVO userVO, DesignVO designVO, FactoryVO factoryVO, SellerVO sellerVO,
			HttpSession session) throws Exception {
		String userId = (String) session.getAttribute("userId");
		UserVO loginuser = this.loginService.getUser(userId);
		String mno = loginuser.getMno(); // 뷰 테이블 mno 변수 받기
		String mmno = mno.substring(0, 1);
		int mnum = Integer.parseInt(mno.substring(1).replaceAll(" ", ""));
		PortFolioVO portfolioItem = new PortFolioVO();
		KnowHowVO knowhowVO = new KnowHowVO();
		portfolioItem.setI_mdiv(mmno);
		portfolioItem.setI_mnum(mnum);
		knowhowVO.setH_mdiv(mmno);
		knowhowVO.setH_mnum(mnum);
		ArrayList<PortFolioVO> portfolio = profileService.getPortFolio(portfolioItem);
		KnowHowVO knowhow = profileService.getKnowHowDeteil(knowhowVO);
		ArrayList<KnowHowVO> knowhowList = profileService.getKnowHow(knowhowVO);

		if (mmno.equals("D")) {
			DesignVO design = this.profileService.getMDProfile(mno); // MNO변수에 따른 프로필 받아오기
			System.out.print(design.toString() + "\n");
			if (design.getMd_dept().equals("1")) {
				design.setMd_dept("패션디자이너");

			} else if (design.getMd_dept().equals("2")) {
				design.setMd_dept("렉스타일디자이너");
				System.out.print("여기2");
			} else if (design.getMd_dept().equals("3")) {
				design.setMd_dept("의상디자이너");
				System.out.print("여기3");
			} else if (design.getMd_dept().equals("4")) {
				design.setMd_dept("패션MD");
				System.out.print("여기4");
			}

			if (design.getMd_career().equals("1")) {
				design.setMd_career("5년미만");

			} else if (design.getMd_career().equals("2")) {
				design.setMd_career("5년~10년");

			} else if (design.getMd_career().equals("3")) {
				design.setMd_career("10년~15년");

			} else if (design.getMd_career().equals("4")) {
				design.setMd_career("15년~20년");

			} else if (design.getMd_career().equals("5")) {
				design.setMd_career("20년이상");
			}

			if (design.getMd_business().equals("1")) {
				design.setMd_business("프리랜서");
			} else if (design.getMd_business().equals("2")) {
				design.setMd_business("개인사업자");
			} else if (design.getMd_business().equals("3")) {
				design.setMd_business("법인사업자");
			} else if (design.getMd_business().equals("4")) {
				design.setMd_business("브랜드대리점");
			}

			if (design.getMd_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (design.getMd_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (design.getMd_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (design.getMd_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (design.getMd_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (design.getMd_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (design.getMd_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (design.getMd_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (design.getMd_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", design);
			model.addAttribute("portfolio", portfolio);
			model.addAttribute("size", portfolio.size());
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("knowhowList", knowhowList);
			model.addAttribute("knowhow", knowhow);
			model.addAttribute("knowhowSize", knowhowList.size());
			return "/profile/myhome_design";

		} else if (mmno.equals("F")) {
			FactoryVO factory = this.profileService.getMFProfile(mno); // MNO변수에 따른 프로필 받아오기
			if (factory.getMf_dept().equals("1")) {
				factory.setMf_dept("도로시공장");

			} else if (factory.getMf_dept().equals("2")) {
				factory.setMf_dept("다이마루공장");
				System.out.print("여기2");
			} else if (factory.getMf_dept().equals("3")) {
				factory.setMf_dept("직기공장");
				System.out.print("여기3");
			} else if (factory.getMf_dept().equals("4")) {
				factory.setMf_dept("특수공장");
				System.out.print("여기4");
			}

			if (factory.getMf_business().equals("1")) {
				factory.setMf_business("프리랜서");
			} else if (factory.getMf_business().equals("2")) {
				factory.setMf_business("개인사업자");
			} else if (factory.getMf_business().equals("3")) {
				factory.setMf_business("법인사업자");
			} else if (factory.getMf_business().equals("4")) {
				factory.setMf_business("브랜드대리점");
			}

			if (factory.getMf_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (factory.getMf_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (factory.getMf_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (factory.getMf_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (factory.getMf_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (factory.getMf_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (factory.getMf_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (factory.getMf_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (factory.getMf_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", factory);
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("portfolio", portfolio);
			model.addAttribute("size", portfolio.size());
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("knowhowList", knowhowList);
			model.addAttribute("knowhow", knowhow);
			model.addAttribute("knowhowSize", knowhowList.size());
			return "/profile/myhome_factory";
		} else if (mmno.equals("S")) {
			SellerVO seller = this.profileService.getMCProfile(mno); // MNO변수에 따른 프로필 받아오기
			if (seller.getMc_div().equals("1")) {
				seller.setMc_div("도매");

			} else if (seller.getMc_div().equals("2")) {
				seller.setMc_div("소매");
			}

			if (seller.getMc_business().equals("1")) {
				seller.setMc_business("프리랜서");
			} else if (seller.getMc_business().equals("2")) {
				seller.setMc_business("개인사업자");
			} else if (seller.getMc_business().equals("3")) {
				seller.setMc_business("법인사업자");
			} else if (seller.getMc_business().equals("4")) {
				seller.setMc_business("브랜드대리점");
			}

			if (seller.getMc_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (seller.getMc_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (seller.getMc_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (seller.getMc_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (seller.getMc_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (seller.getMc_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (seller.getMc_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (seller.getMc_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (seller.getMc_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", seller);
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("portfolio", portfolio);
			model.addAttribute("size", portfolio.size());
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("knowhowList", knowhowList);
			model.addAttribute("knowhow", knowhow);
			model.addAttribute("knowhowSize", knowhowList.size());
			return "/profile/myhome_seller";
		} else if (mmno.equals("M")) {
			UserVO user = this.profileService.getProfile(mno); // MNO변수에 따른 프로필 받아오기
			model.addAttribute("profile", user);
			model.addAttribute("loginUser", loginuser);
			return "/profile/myhome";
		} else {
			model.addAttribute("error", "잘못된 경로입니다");
			return "index_container";
		}
	}

	// 상대 사용자 시점 프로필
	@RequestMapping(value = "/userhome.do", method = RequestMethod.GET)
	public String userProfile(Model model, UserVO userVO, DesignVO designVO, FactoryVO factoryVO, SellerVO sellerVO,
			HttpSession session, PortFolioVO portfolioVO) throws Exception {
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userName");
		UserVO loginuser = this.loginService.getUser(userId);
		String mno = loginuser.getMno(); // 뷰 테이블 mno 변수 받기
		String mmno = mno.substring(0, 1);
		String i_mdiv = portfolioVO.getI_mdiv();
		int i_mnum = portfolioVO.getI_mnum();
		mmno = i_mdiv;
		mno = i_mdiv + i_mnum;
		int mnum = Integer.parseInt(mno.substring(1).replaceAll(" ", ""));
		PortFolioVO portfolioItem = new PortFolioVO();
		KnowHowVO knowhowVO = new KnowHowVO();
		portfolioItem.setI_mdiv(mmno);
		portfolioItem.setI_mnum(mnum);
		knowhowVO.setH_mdiv(mmno);
		knowhowVO.setH_mnum(mnum);
		ArrayList<PortFolioVO> portfolio = profileService.getPortFolio(portfolioItem);
		KnowHowVO knowhow = profileService.getKnowHowDeteil(knowhowVO);
		ArrayList<KnowHowVO> knowhowList = profileService.getKnowHow(knowhowVO);
		model.addAttribute("i_mnum", i_mnum);
		model.addAttribute("i_mdiv", i_mdiv);

		if (mmno.equals("D")) {
			DesignVO design = this.profileService.getMDProfile(mno); // MNO변수에 따른 프로필 받아오기
			FollowVO followVO = new FollowVO();
			followVO.setFollower(userName);
			followVO.setFollowing(design.getMd_name());
			FollowVO fl = this.profileService.getfl(followVO);
			if (fl != null) { // 이미 팔로우 했을경우
				model.addAttribute("folyn", "y");
			} else {
				model.addAttribute("folyn", "n");
			}
			System.out.print(design.toString() + "\n");
			if (design.getMd_dept().equals("1")) {
				design.setMd_dept("패션디자이너");

			} else if (design.getMd_dept().equals("2")) {
				design.setMd_dept("렉스타일디자이너");
				System.out.print("여기2");
			} else if (design.getMd_dept().equals("3")) {
				design.setMd_dept("의상디자이너");
				System.out.print("여기3");
			} else if (design.getMd_dept().equals("4")) {
				design.setMd_dept("패션MD");
				System.out.print("여기4");
			}

			if (design.getMd_career().equals("1")) {
				design.setMd_career("5년미만");

			} else if (design.getMd_career().equals("2")) {
				design.setMd_career("5년~10년");

			} else if (design.getMd_career().equals("3")) {
				design.setMd_career("10년~15년");

			} else if (design.getMd_career().equals("4")) {
				design.setMd_career("15년~20년");

			} else if (design.getMd_career().equals("5")) {
				design.setMd_career("20년이상");
			}

			if (design.getMd_business().equals("1")) {
				design.setMd_business("프리랜서");
			} else if (design.getMd_business().equals("2")) {
				design.setMd_business("개인사업자");
			} else if (design.getMd_business().equals("3")) {
				design.setMd_business("법인사업자");
			} else if (design.getMd_business().equals("4")) {
				design.setMd_business("브랜드대리점");
			}

			if (design.getMd_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (design.getMd_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (design.getMd_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (design.getMd_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (design.getMd_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (design.getMd_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (design.getMd_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (design.getMd_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (design.getMd_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", design);
			model.addAttribute("portfolio", portfolio);
			model.addAttribute("size", portfolio.size());
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("knowhowList", knowhowList);
			model.addAttribute("knowhow", knowhow);
			model.addAttribute("knowhowSize", knowhowList.size());
			return "/profile/userhome_design";

		} else if (mmno.equals("F")) {
			FactoryVO factory = this.profileService.getMFProfile(mno); // MNO변수에 따른 프로필 받아오기
			FollowVO followVO = new FollowVO();
			followVO.setFollower(userName);
			followVO.setFollowing(factory.getMf_name());
			if (this.profileService.getfl(followVO) != null) { // 이미 팔로우 했을경우
				model.addAttribute("folyn", "y");
			} else {
				model.addAttribute("folyn", "n");
			}
			if (factory.getMf_dept().equals("1")) {
				factory.setMf_dept("도로시공장");

			} else if (factory.getMf_dept().equals("2")) {
				factory.setMf_dept("다이마루공장");
				System.out.print("여기2");
			} else if (factory.getMf_dept().equals("3")) {
				factory.setMf_dept("직기공장");
				System.out.print("여기3");
			} else if (factory.getMf_dept().equals("4")) {
				factory.setMf_dept("특수공장");
				System.out.print("여기4");
			}

			if (factory.getMf_business().equals("1")) {
				factory.setMf_business("프리랜서");
			} else if (factory.getMf_business().equals("2")) {
				factory.setMf_business("개인사업자");
			} else if (factory.getMf_business().equals("3")) {
				factory.setMf_business("법인사업자");
			} else if (factory.getMf_business().equals("4")) {
				factory.setMf_business("브랜드대리점");
			}

			if (factory.getMf_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (factory.getMf_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (factory.getMf_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (factory.getMf_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (factory.getMf_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (factory.getMf_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (factory.getMf_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (factory.getMf_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (factory.getMf_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", factory);
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("portfolio", portfolio);
			model.addAttribute("size", portfolio.size());
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("knowhowList", knowhowList);
			model.addAttribute("knowhow", knowhow);
			model.addAttribute("knowhowSize", knowhowList.size());
			return "/profile/userhome_factory";

		} else if (mmno.equals("S")) {
			SellerVO seller = this.profileService.getMCProfile(mno); // MNO변수에 따른 프로필 받아오기
			FollowVO followVO = new FollowVO();
			followVO.setFollower(userName);
			followVO.setFollowing(seller.getMc_name());
			if (this.profileService.getfl(followVO) != null) { // 이미 팔로우 했을경우
				model.addAttribute("folyn", "y");
			} else {
				model.addAttribute("folyn", "n");
			}
			if (seller.getMc_div().equals("1")) {
				seller.setMc_div("도매");

			} else if (seller.getMc_div().equals("2")) {
				seller.setMc_div("소매");
			}

			if (seller.getMc_business().equals("1")) {
				seller.setMc_business("프리랜서");
			} else if (seller.getMc_business().equals("2")) {
				seller.setMc_business("개인사업자");
			} else if (seller.getMc_business().equals("3")) {
				seller.setMc_business("법인사업자");
			} else if (seller.getMc_business().equals("4")) {
				seller.setMc_business("브랜드대리점");
			}

			if (seller.getMc_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (seller.getMc_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (seller.getMc_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (seller.getMc_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (seller.getMc_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (seller.getMc_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (seller.getMc_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (seller.getMc_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (seller.getMc_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", seller);
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("portfolio", portfolio);
			model.addAttribute("size", portfolio.size());
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("knowhowList", knowhowList);
			model.addAttribute("knowhow", knowhow);
			model.addAttribute("knowhowSize", knowhowList.size());
			return "/profile/userhome_seller";

		} else if (mmno.equals("M")) {
			UserVO user = this.profileService.getProfile(mno); // MNO변수에 따른 프로필 받아오기
			model.addAttribute("profile", user);
			model.addAttribute("loginUser", loginuser);
			return "/profile/userhome";
		} else {
			model.addAttribute("error", "잘못된 경로입니다");
			return "index_container";
		}
	}

	// 팔로우 테스트 매핑
	@RequestMapping(value = "/followerView.do", method = RequestMethod.GET)
	public String followerView(Model model,HttpSession session,HttpServletRequest request) throws Exception {
		String userId = (String) session.getAttribute("userId");
		String userName = request.getParameter("userName");
		UserVO user = loginService.getsUser(userName);
		
		List<FollowVO> fl = profileService.getflerlist(userName);
		model.addAttribute("fllist",fl);
		model.addAttribute("user", user);
		return "/profile/followview_follower";
	}

	@RequestMapping(value = "/followingView.do", method = RequestMethod.GET)
	public String followingView(Model model, HttpSession session,HttpServletRequest request) throws Exception {
		String userId = (String) session.getAttribute("userId");
		String userName = request.getParameter("userName");
		UserVO user = loginService.getsUser(userName);
		
		List<FollowVO> fl = profileService.getflinglist(userName);
		model.addAttribute("user",user);
		model.addAttribute("fllist",fl);
		return "/profile/followview_following";
		
	}

	// 프로필 수정
	@RequestMapping(value = "/pmodify.do", method = RequestMethod.GET)
	public String pmodify(Model model, UserVO userVO, DesignVO designVO, FactoryVO factoryVO, SellerVO sellerVO,
			HttpSession session) throws Exception {
		String userId = (String) session.getAttribute("userId");
		UserVO loginuser = this.loginService.getUser(userId);
		String mno = loginuser.getMno(); // 뷰 테이블 mno 변수 받기
		String mmno = mno.substring(0, 1);

		if (mmno.equals("D")) {
			DesignVO design = this.profileService.getMDProfile(mno); // MNO변수에 따른 프로필 받아오기
			System.out.print(design.toString() + "\n");
			if (design.getMd_dept().equals("1")) {
				design.setMd_dept("패션디자이너");

			} else if (design.getMd_dept().equals("2")) {
				design.setMd_dept("렉스타일디자이너");
				System.out.print("여기2");
			} else if (design.getMd_dept().equals("3")) {
				design.setMd_dept("의상디자이너");
				System.out.print("여기3");
			} else if (design.getMd_dept().equals("4")) {
				design.setMd_dept("패션MD");
				System.out.print("여기4");
			}

			if (design.getMd_career().equals("1")) {
				design.setMd_career("5년미만");

			} else if (design.getMd_career().equals("2")) {
				design.setMd_career("5년~10년");

			} else if (design.getMd_career().equals("3")) {
				design.setMd_career("10년~15년");

			} else if (design.getMd_career().equals("4")) {
				design.setMd_career("15년~20년");

			} else if (design.getMd_career().equals("5")) {
				design.setMd_career("20년이상");
			}

			if (design.getMd_business().equals("1")) {
				design.setMd_business("프리랜서");
			} else if (design.getMd_business().equals("2")) {
				design.setMd_business("개인사업자");
			} else if (design.getMd_business().equals("3")) {
				design.setMd_business("법인사업자");
			} else if (design.getMd_business().equals("4")) {
				design.setMd_business("브랜드대리점");
			}

			if (design.getMd_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (design.getMd_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (design.getMd_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (design.getMd_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (design.getMd_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (design.getMd_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (design.getMd_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (design.getMd_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (design.getMd_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", design);

			model.addAttribute("loginUser", loginuser);
			return "/profile/pmodify_design";

		} else if (mmno.equals("F")) {
			FactoryVO factory = this.profileService.getMFProfile(mno); // MNO변수에 따른 프로필 받아오기
			if (factory.getMf_dept().equals("1")) {
				factory.setMf_dept("도로시공장");

			} else if (factory.getMf_dept().equals("2")) {
				factory.setMf_dept("다이마루공장");
				System.out.print("여기2");
			} else if (factory.getMf_dept().equals("3")) {
				factory.setMf_dept("직기공장");
				System.out.print("여기3");
			} else if (factory.getMf_dept().equals("4")) {
				factory.setMf_dept("특수공장");
				System.out.print("여기4");
			}

			if (factory.getMf_business().equals("1")) {
				factory.setMf_business("프리랜서");
			} else if (factory.getMf_business().equals("2")) {
				factory.setMf_business("개인사업자");
			} else if (factory.getMf_business().equals("3")) {
				factory.setMf_business("법인사업자");
			} else if (factory.getMf_business().equals("4")) {
				factory.setMf_business("브랜드대리점");
			}

			if (factory.getMf_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (factory.getMf_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (factory.getMf_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (factory.getMf_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (factory.getMf_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (factory.getMf_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (factory.getMf_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (factory.getMf_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (factory.getMf_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", factory);
			model.addAttribute("loginUser", loginuser);
			return "/profile/pmodify_factory";
		} else if (mmno.equals("S")) {
			SellerVO seller = this.profileService.getMCProfile(mno); // MNO변수에 따른 프로필 받아오기
			if (seller.getMc_div().equals("1")) {
				seller.setMc_div("도매");

			} else if (seller.getMc_div().equals("2")) {
				seller.setMc_div("소매");
			}

			if (seller.getMc_business().equals("1")) {
				seller.setMc_business("프리랜서");
			} else if (seller.getMc_business().equals("2")) {
				seller.setMc_business("개인사업자");
			} else if (seller.getMc_business().equals("3")) {
				seller.setMc_business("법인사업자");
			} else if (seller.getMc_business().equals("4")) {
				seller.setMc_business("브랜드대리점");
			}

			if (seller.getMc_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (seller.getMc_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (seller.getMc_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (seller.getMc_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (seller.getMc_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (seller.getMc_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (seller.getMc_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (seller.getMc_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (seller.getMc_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", seller);
			model.addAttribute("loginUser", loginuser);
			return "/profile/pmodify_seller";
		} else if (mmno.equals("M")) {
			UserVO user = this.profileService.getProfile(mno); // MNO변수에 따른 프로필 받아오기
			System.out.print(mno.toString());
			System.out.print(user.toString());
			model.addAttribute("profile", user);
			model.addAttribute("loginUser", loginuser);
			System.out.print("여기!멤버");
			return "/profile/pmodify";
		} else {
			model.addAttribute("error", "잘못된 경로입니다");
			return "redirect:/myhome.do";
		}

	}

	@RequestMapping(value = "/pmodify.do", method = RequestMethod.POST)
	public String upProfile(UserVO userVO, Model model, RedirectAttributes flush, HttpSession session)
			throws Exception {
		if (this.profileService.updateProfile(userVO) != 0) {
			// model.addAttribute(userId);
			// sysout //
			String userId = (String) session.getAttribute("userId");
			flush.addFlashAttribute("userId", userId);
			return "redirect:/profile/myhome.do";
		} else {
			model.addAttribute("error", "수정에 실패하였습니다");
			return "/profile/pmodify";
		}
	}

	@RequestMapping(value = "/dpmodify.do", method = RequestMethod.POST)
	public String updProfile(DesignVO designVO, Model model, RedirectAttributes flush, HttpSession session)
			throws Exception {
		if (this.profileService.updateMDProfile(designVO) != 0) {
			// model.addAttribute(userId);
			// sysout //
			String userId = (String) session.getAttribute("userId");
			flush.addFlashAttribute("userId", userId);
			return "redirect:/profile/myhome.do";
		} else {
			model.addAttribute("error", "수정에 실패하였습니다");
			return "/profile/pmodify";
		}
	}

	@RequestMapping(value = "/fpmodify.do", method = RequestMethod.POST)
	public String upfProfile(FactoryVO factoryVO, Model model, RedirectAttributes flush, HttpSession session)
			throws Exception {
		if (this.profileService.updateMFProfile(factoryVO) != 0) {
			// model.addAttribute(userId);
			// sysout //
			String userId = (String) session.getAttribute("userId");
			flush.addFlashAttribute("userId", userId);
			return "redirect:/profile/myhome.do";
		} else {
			model.addAttribute("error", "수정에 실패하였습니다");
			return "/profile/pmodify";
		}
	}

	@RequestMapping(value = "/cpmodify.do", method = RequestMethod.POST)
	public String upcProfile(SellerVO sellerVO, Model model, RedirectAttributes flush, HttpSession session)
			throws Exception {
		if (this.profileService.updateMCProfile(sellerVO) != 0) {
			// model.addAttribute(userId);
			// sysout //
			String userId = (String) session.getAttribute("userId");
			flush.addFlashAttribute("userId", userId);
			return "redirect:/profile/myhome.do";
		} else {
			model.addAttribute("error", "수정에 실패하였습니다");
			return "/profile/pmodify";
		}
	}

	// 패스워드 수정
	@RequestMapping(value = "/peditpasswd.do", method = RequestMethod.GET)
	public String profileeditpasswd(HttpSession session, Model model) throws Exception {
		String userId = (String) session.getAttribute("userId");
		UserVO loginuser = this.loginService.getUser(userId);
		model.addAttribute("loginUser", loginuser);
		return "/profile/peditpasswd";
	}

	// @RequestMapping(value = "/peditpasswd.do", method = RequestMethod.POST)
	// public String PEditpassSave(UserVO userVO, HttpSession session, Model model)
	// throws Exception {
	// String userId = (String) session.getAttribute("userId");
	// userVO.setUserId(userId);
	// System.out.println(userVO.getPasswd());
	// String hashpass = BCrypt.hashpw(userVO.getPasswd(), BCrypt.gensalt(12));
	// userVO.setPasswd(hashpass);
	// if(this.profileService.updatePwd(userVO) != 0) {
	// System.out.println(userVO.toString());
	// return "redirect:/member/logout.do";
	// }
	// else {
	// return "/profile/peditpasswd";
	// }
	// }
	@RequestMapping(value = "/peditpasswd.do", method = RequestMethod.POST)
	public String PEditpassSave(UserVO userVO, DesignVO designVO, FactoryVO factoryVO, SellerVO sellerVO,
			HttpSession session, Model model) throws Exception {
		String userId = (String) session.getAttribute("userId");
		UserVO loginuser = this.loginService.getUser(userId);
		String mno = loginuser.getMno(); // 뷰 테이블 mno 변수 받기
		String mmno = mno.substring(0, 1); // D,F,S,M
		if (mmno.equals("M")) {
			userVO.setUserId(userId);
			String hashpass = BCrypt.hashpw(userVO.getPasswd(), BCrypt.gensalt(12));
			userVO.setPasswd(hashpass);
			if (this.profileService.updatePwd(userVO) != 0) {
				System.out.println(userVO.toString());
				return "redirect:/member/logout.do";
			} else {
				return "/profile/peditpasswd";
			}
		} else if (mmno.equals("D")) {
			designVO.setMd_userId(userId);
			String hashpass = BCrypt.hashpw(userVO.getPasswd(), BCrypt.gensalt(12));
			designVO.setMd_passwd(hashpass);
			if (this.profileService.updateMDPwd(designVO) != 0) {
				return "redirect:/member/logout.do";
			} else {
				return "/profile/peditpasswd";
			}

		} else if (mmno.equals("F")) {
			factoryVO.setMf_userId(userId);
			String hashpass = BCrypt.hashpw(userVO.getPasswd(), BCrypt.gensalt(12));
			factoryVO.setMf_passwd(hashpass);
			if (this.profileService.updateMFPwd(factoryVO) != 0) {
				return "redirect:/member/logout.do";
			} else {
				return "/profile/peditpasswd";
			}
		} else if (mmno.equals("S")) {
			sellerVO.setMc_userId(userId);
			System.out.println("여기!" + userVO.getPasswd());
			String hashpass = BCrypt.hashpw(userVO.getPasswd(), BCrypt.gensalt(12));
			sellerVO.setMc_passwd(hashpass);
			if (this.profileService.updateMCPwd(sellerVO) != 0) {
				return "redirect:/member/logout.do";
			} else {
				return "/profile/peditpasswd";
			}
		} else {
			return "/profile/peditpasswd";
		}
	}

	// 전문가공간
	/*
	 * @RequestMapping(value = "/pphotoview.do", method = RequestMethod.GET) public
	 * String profilePhotoView(@RequestParam("userId") String userId, Model model)
	 * throws Exception { UserVO loginuser = this.loginService.getUser(userId);
	 * model.addAttribute("loginUser", loginuser); return "/profile/pphotoview"; }
	 */

	// 디자이너회원 공간
	@RequestMapping(value = "/design.do", method = RequestMethod.GET)
	public String design(Model model, HttpSession session) throws Exception {
		// model.addAttribute("user", new User());
		String userId = (String) session.getAttribute("userId");
		if (userId != null) {
			UserVO loginuser = this.loginService.getUser(userId);
			String mno = loginuser.getMno(); // 뷰 테이블 mno 변수 받기
			String mmno = mno.substring(0, 1);

			model.addAttribute("userId", loginuser.getUserId());
		} else {
			return "redirect:/member/login.do";
		}
		return "/community/design";
	}

	@RequestMapping(value = "/getDesign.do", method = RequestMethod.POST)
	public @ResponseBody ArrayList<Map<String, Object>> getDesign(@Valid UserVO userVO, BindingResult bindingResult,
			HttpSession session, @RequestParam Map<String, Object> item) {

		ArrayList<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		result = profileService.selectDesign(item);
		return result;
	}

	// 공장회원 공간
	@RequestMapping(value = "/factory.do", method = RequestMethod.GET)
	public String factory(Model model, HttpSession session) throws Exception {
		// model.addAttribute("user", new User());
		String userId = (String) session.getAttribute("userId");
		if (userId != null) {
			UserVO loginuser = this.loginService.getUser(userId);
			String mno = loginuser.getMno(); // 뷰 테이블 mno 변수 받기
			String mmno = mno.substring(0, 1);

			model.addAttribute("userId", loginuser.getUserId());
		} else {
			return "redirect:/member/login.do";
		}
		return "/community/factory";
	}

	@RequestMapping(value = "/getFactory.do", method = RequestMethod.POST)
	public @ResponseBody ArrayList<Map<String, Object>> getFactory(@Valid UserVO userVO, BindingResult bindingResult,
			HttpSession session, @RequestParam Map<String, Object> item) {

		ArrayList<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		result = profileService.selectFactory(item);
		return result;
	}

	// 도소매회원 공간
	@RequestMapping(value = "/seller.do", method = RequestMethod.GET)
	public String seller(Model model, HttpSession session) throws Exception {
		// model.addAttribute("user", new User());
		String userId = (String) session.getAttribute("userId");
		if (userId != null) {
			UserVO loginuser = this.loginService.getUser(userId);
			String mno = loginuser.getMno(); // 뷰 테이블 mno 변수 받기
			String mmno = mno.substring(0, 1);

			model.addAttribute("userId", loginuser.getUserId());
		} else {
			return "redirect:/member/login.do";
		}
		return "/community/seller";
	}

	@RequestMapping(value = "/getSeller.do", method = RequestMethod.POST)
	public @ResponseBody ArrayList<Map<String, Object>> getSeller(@Valid UserVO userVO, BindingResult bindingResult,
			HttpSession session, @RequestParam Map<String, Object> item) {

		ArrayList<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		result = profileService.selectSeller(item);
		return result;
	}

	// user portfolio 글쓰기 부분
	@RequestMapping(value = "/portfoliowrite.do", method = RequestMethod.GET)
	public String portfoliowrite(Model model, UserVO userVO, DesignVO designVO, FactoryVO factoryVO, SellerVO sellerVO,
			HttpSession session) throws Exception {
		String userId = (String) session.getAttribute("userId");
		UserVO loginuser = this.loginService.getUser(userId);
		String mno = loginuser.getMno(); // 뷰 테이블 mno 변수 받기
		String mmno = mno.substring(0, 1);

		model.addAttribute("userId", loginuser.getUserId());
		return "/profile/portfoliowrite";
	}

	// user 노하우 글쓰기 부분
	@RequestMapping(value = "/knowhowwrite.do", method = RequestMethod.GET)
	public String knowhowwrite(Model model, UserVO userVO, DesignVO designVO, FactoryVO factoryVO, SellerVO sellerVO,
			HttpSession session) throws Exception {
		String userId = (String) session.getAttribute("userId");
		UserVO loginuser = this.loginService.getUser(userId);
		String mno = loginuser.getMno(); // 뷰 테이블 mno 변수 받기
		String mmno = mno.substring(0, 1);

		model.addAttribute("userId", loginuser.getUserId());
		return "/profile/knowhowwrite";
	}

	// user portfolio 글쓰기 올리기 부분
	@RequestMapping(value = "/portfoliowrite_insert.do")
	public String portfoliowrite_insert(Model model, PortFolioVO portfolioVO, HttpSession session) throws Exception {
		String userId = (String) session.getAttribute("userId");
		UserVO loginuser = this.loginService.getUser(userId);
		String mno = loginuser.getMno(); // 뷰 테이블 mno 변수 받기
		String mmno = mno.substring(0, 1);
		int mnum = Integer.parseInt(mno.substring(1).replaceAll(" ", ""));
		portfolioVO.setI_mdiv(mmno);
		portfolioVO.setI_mnum(mnum);
		int result = profileService.insertPortFolio(portfolioVO);

		if (mmno.equals("D")) {
			DesignVO design = this.profileService.getMDProfile(mno); // MNO변수에 따른 프로필 받아오기
			System.out.print(design.toString() + "\n");
			if (design.getMd_dept().equals("1")) {
				design.setMd_dept("패션디자이너");

			} else if (design.getMd_dept().equals("2")) {
				design.setMd_dept("렉스타일디자이너");
				System.out.print("여기2");
			} else if (design.getMd_dept().equals("3")) {
				design.setMd_dept("의상디자이너");
				System.out.print("여기3");
			} else if (design.getMd_dept().equals("4")) {
				design.setMd_dept("패션MD");
				System.out.print("여기4");
			}

			if (design.getMd_career().equals("1")) {
				design.setMd_career("5년미만");

			} else if (design.getMd_career().equals("2")) {
				design.setMd_career("5년~10년");

			} else if (design.getMd_career().equals("3")) {
				design.setMd_career("10년~15년");

			} else if (design.getMd_career().equals("4")) {
				design.setMd_career("15년~20년");

			} else if (design.getMd_career().equals("5")) {
				design.setMd_career("20년이상");
			}

			if (design.getMd_business().equals("1")) {
				design.setMd_business("프리랜서");
			} else if (design.getMd_business().equals("2")) {
				design.setMd_business("개인사업자");
			} else if (design.getMd_business().equals("3")) {
				design.setMd_business("법인사업자");
			} else if (design.getMd_business().equals("4")) {
				design.setMd_business("브랜드대리점");
			}

			if (design.getMd_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (design.getMd_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (design.getMd_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (design.getMd_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (design.getMd_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (design.getMd_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (design.getMd_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (design.getMd_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (design.getMd_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", design);
			model.addAttribute("loginUser", loginuser);
			return "redirect:/profile/myhome.do";

		} else if (mmno.equals("F")) {
			FactoryVO factory = this.profileService.getMFProfile(mno); // MNO변수에 따른 프로필 받아오기
			if (factory.getMf_dept().equals("1")) {
				factory.setMf_dept("도로시공장");

			} else if (factory.getMf_dept().equals("2")) {
				factory.setMf_dept("다이마루공장");
				System.out.print("여기2");
			} else if (factory.getMf_dept().equals("3")) {
				factory.setMf_dept("직기공장");
				System.out.print("여기3");
			} else if (factory.getMf_dept().equals("4")) {
				factory.setMf_dept("특수공장");
				System.out.print("여기4");
			}

			if (factory.getMf_business().equals("1")) {
				factory.setMf_business("프리랜서");
			} else if (factory.getMf_business().equals("2")) {
				factory.setMf_business("개인사업자");
			} else if (factory.getMf_business().equals("3")) {
				factory.setMf_business("법인사업자");
			} else if (factory.getMf_business().equals("4")) {
				factory.setMf_business("브랜드대리점");
			}

			if (factory.getMf_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (factory.getMf_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (factory.getMf_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (factory.getMf_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (factory.getMf_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (factory.getMf_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (factory.getMf_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (factory.getMf_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (factory.getMf_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", factory);
			model.addAttribute("loginUser", loginuser);
			return "redirect:/profile/myhome.do";

		} else if (mmno.equals("S")) {
			SellerVO seller = this.profileService.getMCProfile(mno); // MNO변수에 따른 프로필 받아오기
			if (seller.getMc_div().equals("1")) {
				seller.setMc_div("도매");

			} else if (seller.getMc_div().equals("2")) {
				seller.setMc_div("소매");
			}

			if (seller.getMc_business().equals("1")) {
				seller.setMc_business("프리랜서");
			} else if (seller.getMc_business().equals("2")) {
				seller.setMc_business("개인사업자");
			} else if (seller.getMc_business().equals("3")) {
				seller.setMc_business("법인사업자");
			} else if (seller.getMc_business().equals("4")) {
				seller.setMc_business("브랜드대리점");
			}

			if (seller.getMc_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (seller.getMc_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (seller.getMc_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (seller.getMc_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (seller.getMc_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (seller.getMc_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (seller.getMc_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (seller.getMc_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (seller.getMc_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", seller);
			model.addAttribute("loginUser", loginuser);
			return "redirect:/profile/myhome.do";
		} else if (mmno.equals("M")) {
			UserVO user = this.profileService.getProfile(mno); // MNO변수에 따른 프로필 받아오기
			model.addAttribute("profile", user);
			model.addAttribute("loginUser", loginuser);
			return "redirect:/profile/myhome.do";
		} else {
			model.addAttribute("error", "잘못된 경로입니다");
			return "index_container";
		}
	}

	// user 노하우 글쓰기 올리기 부분
	@RequestMapping(value = "/knowhowwrite_insert.do")
	public String knowhowwrite_insert(Model model, KnowHowVO knowhowVO, HttpSession session) throws Exception {
		String userId = (String) session.getAttribute("userId");
		UserVO loginuser = this.loginService.getUser(userId);
		String mno = loginuser.getMno(); // 뷰 테이블 mno 변수 받기
		String mmno = mno.substring(0, 1);
		int mnum = Integer.parseInt(mno.substring(1).replaceAll(" ", ""));
		knowhowVO.setH_mdiv(mmno);
		knowhowVO.setH_mnum(mnum);
		int result = profileService.insertKnowHow(knowhowVO);

		if (mmno.equals("D")) {
			DesignVO design = this.profileService.getMDProfile(mno); // MNO변수에 따른 프로필 받아오기
			System.out.print(design.toString() + "\n");
			if (design.getMd_dept().equals("1")) {
				design.setMd_dept("패션디자이너");

			} else if (design.getMd_dept().equals("2")) {
				design.setMd_dept("렉스타일디자이너");
				System.out.print("여기2");
			} else if (design.getMd_dept().equals("3")) {
				design.setMd_dept("의상디자이너");
				System.out.print("여기3");
			} else if (design.getMd_dept().equals("4")) {
				design.setMd_dept("패션MD");
				System.out.print("여기4");
			}

			if (design.getMd_career().equals("1")) {
				design.setMd_career("5년미만");

			} else if (design.getMd_career().equals("2")) {
				design.setMd_career("5년~10년");

			} else if (design.getMd_career().equals("3")) {
				design.setMd_career("10년~15년");

			} else if (design.getMd_career().equals("4")) {
				design.setMd_career("15년~20년");

			} else if (design.getMd_career().equals("5")) {
				design.setMd_career("20년이상");
			}

			if (design.getMd_business().equals("1")) {
				design.setMd_business("프리랜서");
			} else if (design.getMd_business().equals("2")) {
				design.setMd_business("개인사업자");
			} else if (design.getMd_business().equals("3")) {
				design.setMd_business("법인사업자");
			} else if (design.getMd_business().equals("4")) {
				design.setMd_business("브랜드대리점");
			}

			if (design.getMd_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (design.getMd_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (design.getMd_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (design.getMd_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (design.getMd_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (design.getMd_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (design.getMd_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (design.getMd_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (design.getMd_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", design);
			model.addAttribute("loginUser", loginuser);
			return "redirect:/profile/myhome.do";
		} else if (mmno.equals("F")) {
			FactoryVO factory = this.profileService.getMFProfile(mno); // MNO변수에 따른 프로필 받아오기
			if (factory.getMf_dept().equals("1")) {
				factory.setMf_dept("도로시공장");

			} else if (factory.getMf_dept().equals("2")) {
				factory.setMf_dept("다이마루공장");
				System.out.print("여기2");
			} else if (factory.getMf_dept().equals("3")) {
				factory.setMf_dept("직기공장");
				System.out.print("여기3");
			} else if (factory.getMf_dept().equals("4")) {
				factory.setMf_dept("특수공장");
				System.out.print("여기4");
			}

			if (factory.getMf_business().equals("1")) {
				factory.setMf_business("프리랜서");
			} else if (factory.getMf_business().equals("2")) {
				factory.setMf_business("개인사업자");
			} else if (factory.getMf_business().equals("3")) {
				factory.setMf_business("법인사업자");
			} else if (factory.getMf_business().equals("4")) {
				factory.setMf_business("브랜드대리점");
			}

			if (factory.getMf_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (factory.getMf_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (factory.getMf_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (factory.getMf_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (factory.getMf_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (factory.getMf_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (factory.getMf_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (factory.getMf_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (factory.getMf_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", factory);
			model.addAttribute("loginUser", loginuser);
			return "redirect:/profile/myhome.do";
		} else if (mmno.equals("S")) {
			SellerVO seller = this.profileService.getMCProfile(mno); // MNO변수에 따른 프로필 받아오기
			if (seller.getMc_div().equals("1")) {
				seller.setMc_div("도매");

			} else if (seller.getMc_div().equals("2")) {
				seller.setMc_div("소매");
			}

			if (seller.getMc_business().equals("1")) {
				seller.setMc_business("프리랜서");
			} else if (seller.getMc_business().equals("2")) {
				seller.setMc_business("개인사업자");
			} else if (seller.getMc_business().equals("3")) {
				seller.setMc_business("법인사업자");
			} else if (seller.getMc_business().equals("4")) {
				seller.setMc_business("브랜드대리점");
			}

			if (seller.getMc_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (seller.getMc_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (seller.getMc_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (seller.getMc_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (seller.getMc_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (seller.getMc_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (seller.getMc_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (seller.getMc_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (seller.getMc_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", seller);
			model.addAttribute("loginUser", loginuser);
			return "redirect:/profile/myhome.do";
		} else if (mmno.equals("M")) {
			UserVO user = this.profileService.getProfile(mno); // MNO변수에 따른 프로필 받아오기
			model.addAttribute("profile", user);
			model.addAttribute("loginUser", loginuser);
			return "redirect:/profile/myhome.do";
		} else {
			model.addAttribute("error", "잘못된 경로입니다");
			return "index_container";
		}
	}

	// myportfolio 사진 세부보기 부분
	@RequestMapping(value = "/pphotoview.do", method = RequestMethod.GET)
	public String pphotoview(Model model, PortFolioVO portfolioVO, HttpSession session) throws Exception {
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userName");
		UserVO loginuser = this.loginService.getUser(userId);
		String mno = loginuser.getMno(); // 뷰 테이블 mno 변수 받기
		String mmno = mno.substring(0, 1);
		int mnum = Integer.parseInt(mno.substring(1).replaceAll(" ", ""));
		portfolioVO.setI_mdiv(mmno);
		portfolioVO.setI_mnum(mnum);
		PortFolioVO portfolio = profileService.getPortFolioDeteil(portfolioVO);
		ArrayList<PortFolioVO> portfolioList = profileService.getPortFolio(portfolioVO);

		if (mmno.equals("D")) {
			DesignVO design = this.profileService.getMDProfile(mno); // MNO변수에 따른 프로필 받아오기

			System.out.print(design.toString() + "\n");
			if (design.getMd_dept().equals("1")) {
				design.setMd_dept("패션디자이너");

			} else if (design.getMd_dept().equals("2")) {
				design.setMd_dept("렉스타일디자이너");
				System.out.print("여기2");
			} else if (design.getMd_dept().equals("3")) {
				design.setMd_dept("의상디자이너");
				System.out.print("여기3");
			} else if (design.getMd_dept().equals("4")) {
				design.setMd_dept("패션MD");
				System.out.print("여기4");
			}

			if (design.getMd_career().equals("1")) {
				design.setMd_career("5년미만");

			} else if (design.getMd_career().equals("2")) {
				design.setMd_career("5년~10년");

			} else if (design.getMd_career().equals("3")) {
				design.setMd_career("10년~15년");

			} else if (design.getMd_career().equals("4")) {
				design.setMd_career("15년~20년");

			} else if (design.getMd_career().equals("5")) {
				design.setMd_career("20년이상");
			}

			if (design.getMd_business().equals("1")) {
				design.setMd_business("프리랜서");
			} else if (design.getMd_business().equals("2")) {
				design.setMd_business("개인사업자");
			} else if (design.getMd_business().equals("3")) {
				design.setMd_business("법인사업자");
			} else if (design.getMd_business().equals("4")) {
				design.setMd_business("브랜드대리점");
			}

			if (design.getMd_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (design.getMd_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (design.getMd_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (design.getMd_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (design.getMd_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (design.getMd_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (design.getMd_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (design.getMd_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (design.getMd_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", design);
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("portfolioList", portfolioList);
			model.addAttribute("portfolio", portfolio);
			return "/profile/pphotoview";
		} else if (mmno.equals("F")) {
			FactoryVO factory = this.profileService.getMFProfile(mno); // MNO변수에 따른 프로필 받아오기
			if (factory.getMf_dept().equals("1")) {
				factory.setMf_dept("도로시공장");

			} else if (factory.getMf_dept().equals("2")) {
				factory.setMf_dept("다이마루공장");
				System.out.print("여기2");
			} else if (factory.getMf_dept().equals("3")) {
				factory.setMf_dept("직기공장");
				System.out.print("여기3");
			} else if (factory.getMf_dept().equals("4")) {
				factory.setMf_dept("특수공장");
				System.out.print("여기4");
			}

			if (factory.getMf_business().equals("1")) {
				factory.setMf_business("프리랜서");
			} else if (factory.getMf_business().equals("2")) {
				factory.setMf_business("개인사업자");
			} else if (factory.getMf_business().equals("3")) {
				factory.setMf_business("법인사업자");
			} else if (factory.getMf_business().equals("4")) {
				factory.setMf_business("브랜드대리점");
			}

			if (factory.getMf_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (factory.getMf_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (factory.getMf_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (factory.getMf_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (factory.getMf_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (factory.getMf_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (factory.getMf_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (factory.getMf_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (factory.getMf_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", factory);
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("portfolioList", portfolioList);
			model.addAttribute("portfolio", portfolio);
			return "/profile/pphotoview";
		} else if (mmno.equals("S")) {
			SellerVO seller = this.profileService.getMCProfile(mno); // MNO변수에 따른 프로필 받아오기
			if (seller.getMc_div().equals("1")) {
				seller.setMc_div("도매");

			} else if (seller.getMc_div().equals("2")) {
				seller.setMc_div("소매");
			}

			if (seller.getMc_business().equals("1")) {
				seller.setMc_business("프리랜서");
			} else if (seller.getMc_business().equals("2")) {
				seller.setMc_business("개인사업자");
			} else if (seller.getMc_business().equals("3")) {
				seller.setMc_business("법인사업자");
			} else if (seller.getMc_business().equals("4")) {
				seller.setMc_business("브랜드대리점");
			}

			if (seller.getMc_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (seller.getMc_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (seller.getMc_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (seller.getMc_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (seller.getMc_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (seller.getMc_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (seller.getMc_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (seller.getMc_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (seller.getMc_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", seller);
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("portfolioList", portfolioList);
			model.addAttribute("portfolio", portfolio);
			return "/profile/pphotoview";
		} else if (mmno.equals("M")) {
			UserVO user = this.profileService.getProfile(mno); // MNO변수에 따른 프로필 받아오기
			model.addAttribute("profile", user);
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("portfolioList", portfolioList);
			model.addAttribute("portfolio", portfolio);
			return "/profile/pphotoview";
		} else {
			model.addAttribute("error", "잘못된 경로입니다");
			return "index_container";
		}
	}

	// user portfolio 사진 세부보기 부분
	@RequestMapping(value = "/userpphotoview.do", method = RequestMethod.GET)
	public String userpphotoview(Model model, PortFolioVO portfolioVO, HttpSession session) throws Exception {

		String mno = "";
		String mmno = portfolioVO.getI_mdiv(); // D
		int mnum = portfolioVO.getI_mnum();
		mno = mmno + mnum;
		portfolioVO.setI_mdiv(mmno);
		portfolioVO.setI_mnum(mnum);

		UserVO user = this.loginService.getssUser(mno);

		PortFolioVO portfolio = profileService.getPortFolioDeteil(portfolioVO);
		ArrayList<PortFolioVO> portfolioList = profileService.getPortFolio(portfolioVO);
		List<iReplyVO> replyList = this.profileService.igetreply(portfolio.getIno());
		model.addAttribute("mnum", mnum);
		model.addAttribute("mmno", mmno);

		this.profileService.iviewcnt(portfolio.getIno()); // 게시글 조회수 증가

		model.addAttribute("replyList", replyList);
		model.addAttribute("user", user);
		model.addAttribute("portfolioList", portfolioList);
		model.addAttribute("portfolio", portfolio);
		return "/profile/userpphotoview";
	}

	// my portfolio 포트폴리오 부분
	@RequestMapping(value = "/myportfoliolist.do", method = RequestMethod.GET)
	public String userPortfoliolist(Model model, PortFolioVO portfolioVO, HttpSession session, UserVO userVO,
			DesignVO designVO, FactoryVO factoryVO, SellerVO sellerVO) throws Exception {
		String userId = (String) session.getAttribute("userId");
		UserVO loginuser = this.loginService.getUser(userId);
		String mno = loginuser.getMno(); // 뷰 테이블 mno 변수 받기
		String mmno = mno.substring(0, 1);
		int mnum = Integer.parseInt(mno.substring(1).replaceAll(" ", ""));
		portfolioVO.setI_mdiv(mmno);
		portfolioVO.setI_mnum(mnum);
		PortFolioVO portfolio = profileService.getPortFolioDeteil(portfolioVO);
		ArrayList<PortFolioVO> portfolioList = profileService.getPortFolio(portfolioVO);

		if (mmno.equals("D")) {
			DesignVO design = this.profileService.getMDProfile(mno); // MNO변수에 따른 프로필 받아오기
			System.out.print(design.toString() + "\n");
			if (design.getMd_dept().equals("1")) {
				design.setMd_dept("패션디자이너");

			} else if (design.getMd_dept().equals("2")) {
				design.setMd_dept("렉스타일디자이너");
				System.out.print("여기2");
			} else if (design.getMd_dept().equals("3")) {
				design.setMd_dept("의상디자이너");
				System.out.print("여기3");
			} else if (design.getMd_dept().equals("4")) {
				design.setMd_dept("패션MD");
				System.out.print("여기4");
			}

			if (design.getMd_career().equals("1")) {
				design.setMd_career("5년미만");

			} else if (design.getMd_career().equals("2")) {
				design.setMd_career("5년~10년");

			} else if (design.getMd_career().equals("3")) {
				design.setMd_career("10년~15년");

			} else if (design.getMd_career().equals("4")) {
				design.setMd_career("15년~20년");

			} else if (design.getMd_career().equals("5")) {
				design.setMd_career("20년이상");
			}

			if (design.getMd_business().equals("1")) {
				design.setMd_business("프리랜서");
			} else if (design.getMd_business().equals("2")) {
				design.setMd_business("개인사업자");
			} else if (design.getMd_business().equals("3")) {
				design.setMd_business("법인사업자");
			} else if (design.getMd_business().equals("4")) {
				design.setMd_business("브랜드대리점");
			}

			if (design.getMd_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (design.getMd_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (design.getMd_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (design.getMd_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (design.getMd_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (design.getMd_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (design.getMd_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (design.getMd_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (design.getMd_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", design);
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("portfolioList", portfolioList);
			model.addAttribute("portfolio", portfolio);
			return "/profile/myportfoliolist_design";
		} else if (mmno.equals("F")) {
			FactoryVO factory = this.profileService.getMFProfile(mno); // MNO변수에 따른 프로필 받아오기
			if (factory.getMf_dept().equals("1")) {
				factory.setMf_dept("도로시공장");

			} else if (factory.getMf_dept().equals("2")) {
				factory.setMf_dept("다이마루공장");
				System.out.print("여기2");
			} else if (factory.getMf_dept().equals("3")) {
				factory.setMf_dept("직기공장");
				System.out.print("여기3");
			} else if (factory.getMf_dept().equals("4")) {
				factory.setMf_dept("특수공장");
				System.out.print("여기4");
			}

			if (factory.getMf_business().equals("1")) {
				factory.setMf_business("프리랜서");
			} else if (factory.getMf_business().equals("2")) {
				factory.setMf_business("개인사업자");
			} else if (factory.getMf_business().equals("3")) {
				factory.setMf_business("법인사업자");
			} else if (factory.getMf_business().equals("4")) {
				factory.setMf_business("브랜드대리점");
			}

			if (factory.getMf_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (factory.getMf_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (factory.getMf_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (factory.getMf_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (factory.getMf_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (factory.getMf_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (factory.getMf_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (factory.getMf_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (factory.getMf_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", factory);
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("portfolioList", portfolioList);
			model.addAttribute("portfolio", portfolio);
			return "/profile/myportfoliolist_factory";
		} else if (mmno.equals("S")) {
			SellerVO seller = this.profileService.getMCProfile(mno); // MNO변수에 따른 프로필 받아오기
			if (seller.getMc_div().equals("1")) {
				seller.setMc_div("도매");

			} else if (seller.getMc_div().equals("2")) {
				seller.setMc_div("소매");
			}

			if (seller.getMc_business().equals("1")) {
				seller.setMc_business("프리랜서");
			} else if (seller.getMc_business().equals("2")) {
				seller.setMc_business("개인사업자");
			} else if (seller.getMc_business().equals("3")) {
				seller.setMc_business("법인사업자");
			} else if (seller.getMc_business().equals("4")) {
				seller.setMc_business("브랜드대리점");
			}

			if (seller.getMc_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (seller.getMc_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (seller.getMc_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (seller.getMc_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (seller.getMc_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (seller.getMc_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (seller.getMc_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (seller.getMc_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (seller.getMc_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", seller);
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("portfolioList", portfolioList);
			model.addAttribute("portfolio", portfolio);
			return "/profile/myportfoliolist_seller";
		} else if (mmno.equals("M")) {
			UserVO user = this.profileService.getProfile(mno); // MNO변수에 따른 프로필 받아오기
			model.addAttribute("profile", user);
			model.addAttribute("loginUser", loginuser);
			return "/profile/myhome";
		} else {
			model.addAttribute("error", "잘못된 경로입니다");
			return "index_container";
		}
	}

	// my 노하우 부분
	@RequestMapping(value = "/myknowhow.do", method = RequestMethod.GET)
	public String myKnowhow(Model model, KnowHowVO knowhowVO, HttpSession session, UserVO userVO, DesignVO designVO,
			FactoryVO factoryVO, SellerVO sellerVO) throws Exception {
		String userId = (String) session.getAttribute("userId");
		UserVO loginuser = this.loginService.getUser(userId);
		String mno = loginuser.getMno(); // 뷰 테이블 mno 변수 받기
		String mmno = mno.substring(0, 1);
		int mnum = Integer.parseInt(mno.substring(1).replaceAll(" ", ""));
		knowhowVO.setH_mdiv(mmno);
		knowhowVO.setH_mnum(mnum);
		KnowHowVO knowhow = profileService.getKnowHowDeteil(knowhowVO);
		ArrayList<KnowHowVO> knowhowList = profileService.getKnowHow(knowhowVO);

		if (mmno.equals("D")) {
			DesignVO design = this.profileService.getMDProfile(mno); // MNO변수에 따른 프로필 받아오기
			System.out.print(design.toString() + "\n");
			if (design.getMd_dept().equals("1")) {
				design.setMd_dept("패션디자이너");

			} else if (design.getMd_dept().equals("2")) {
				design.setMd_dept("렉스타일디자이너");
				System.out.print("여기2");
			} else if (design.getMd_dept().equals("3")) {
				design.setMd_dept("의상디자이너");
				System.out.print("여기3");
			} else if (design.getMd_dept().equals("4")) {
				design.setMd_dept("패션MD");
				System.out.print("여기4");
			}

			if (design.getMd_career().equals("1")) {
				design.setMd_career("5년미만");

			} else if (design.getMd_career().equals("2")) {
				design.setMd_career("5년~10년");

			} else if (design.getMd_career().equals("3")) {
				design.setMd_career("10년~15년");

			} else if (design.getMd_career().equals("4")) {
				design.setMd_career("15년~20년");

			} else if (design.getMd_career().equals("5")) {
				design.setMd_career("20년이상");
			}

			if (design.getMd_business().equals("1")) {
				design.setMd_business("프리랜서");
			} else if (design.getMd_business().equals("2")) {
				design.setMd_business("개인사업자");
			} else if (design.getMd_business().equals("3")) {
				design.setMd_business("법인사업자");
			} else if (design.getMd_business().equals("4")) {
				design.setMd_business("브랜드대리점");
			}

			if (design.getMd_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (design.getMd_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (design.getMd_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (design.getMd_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (design.getMd_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (design.getMd_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (design.getMd_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (design.getMd_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (design.getMd_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", design);
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("knowhowList", knowhowList);
			model.addAttribute("knowhow", knowhow);
			return "/profile/myknowhow_design";
		} else if (mmno.equals("F")) {
			FactoryVO factory = this.profileService.getMFProfile(mno); // MNO변수에 따른 프로필 받아오기
			if (factory.getMf_dept().equals("1")) {
				factory.setMf_dept("도로시공장");

			} else if (factory.getMf_dept().equals("2")) {
				factory.setMf_dept("다이마루공장");
				System.out.print("여기2");
			} else if (factory.getMf_dept().equals("3")) {
				factory.setMf_dept("직기공장");
				System.out.print("여기3");
			} else if (factory.getMf_dept().equals("4")) {
				factory.setMf_dept("특수공장");
				System.out.print("여기4");
			}

			if (factory.getMf_business().equals("1")) {
				factory.setMf_business("프리랜서");
			} else if (factory.getMf_business().equals("2")) {
				factory.setMf_business("개인사업자");
			} else if (factory.getMf_business().equals("3")) {
				factory.setMf_business("법인사업자");
			} else if (factory.getMf_business().equals("4")) {
				factory.setMf_business("브랜드대리점");
			}

			if (factory.getMf_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (factory.getMf_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (factory.getMf_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (factory.getMf_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (factory.getMf_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (factory.getMf_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (factory.getMf_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (factory.getMf_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (factory.getMf_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", factory);
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("knowhowList", knowhowList);
			model.addAttribute("knowhow", knowhow);
			return "/profile/myknowhow_factory";
		} else if (mmno.equals("S")) {
			SellerVO seller = this.profileService.getMCProfile(mno); // MNO변수에 따른 프로필 받아오기
			if (seller.getMc_div().equals("1")) {
				seller.setMc_div("도매");

			} else if (seller.getMc_div().equals("2")) {
				seller.setMc_div("소매");
			}

			if (seller.getMc_business().equals("1")) {
				seller.setMc_business("프리랜서");
			} else if (seller.getMc_business().equals("2")) {
				seller.setMc_business("개인사업자");
			} else if (seller.getMc_business().equals("3")) {
				seller.setMc_business("법인사업자");
			} else if (seller.getMc_business().equals("4")) {
				seller.setMc_business("브랜드대리점");
			}

			if (seller.getMc_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (seller.getMc_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (seller.getMc_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (seller.getMc_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (seller.getMc_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (seller.getMc_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (seller.getMc_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (seller.getMc_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (seller.getMc_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", seller);
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("knowhowList", knowhowList);
			model.addAttribute("knowhow", knowhow);
			return "/profile/myknowhow_seller";
		} else if (mmno.equals("M")) {
			UserVO user = this.profileService.getProfile(mno); // MNO변수에 따른 프로필 받아오기
			model.addAttribute("profile", user);
			model.addAttribute("loginUser", loginuser);
			return "/profile/myhome";
		} else {
			model.addAttribute("error", "잘못된 경로입니다");
			return "index_container";
		}
	}

	// user portfolio 포트폴리오 부분
	@RequestMapping(value = "/portfoliolist_design.do", method = RequestMethod.GET)
	public String portfoliolist_design(Model model, PortFolioVO portfolioVO, HttpSession session, UserVO userVO,
			DesignVO designVO, FactoryVO factoryVO, SellerVO sellerVO) throws Exception {
		String userId = userVO.getUserId();
		UserVO loginuser = this.loginService.getUser(userId);
		String userName = (String) session.getAttribute("userName");
		String mno = loginuser.getMno(); // 뷰 테이블 mno 변수 받기
		String mmno = mno.substring(0, 1);
		int mnum = Integer.parseInt(mno.substring(1).replaceAll(" ", ""));
		portfolioVO.setI_mdiv(mmno);
		portfolioVO.setI_mnum(mnum);
		PortFolioVO portfolio = profileService.getPortFolioDeteil(portfolioVO);
		ArrayList<PortFolioVO> portfolioList = profileService.getPortFolio(portfolioVO);
		model.addAttribute("i_mnum", mnum);
		model.addAttribute("i_mdiv", mmno);

		if (mmno.equals("D")) {
			DesignVO design = this.profileService.getMDProfile(mno); // MNO변수에 따른 프로필 받아오기
			FollowVO followVO = new FollowVO();
			followVO.setFollower(userName);
			followVO.setFollowing(design.getMd_name());
			if (this.profileService.getfl(followVO) != null) { // 이미 팔로우 했을경우
				model.addAttribute("folyn", "y");
			} else {
				model.addAttribute("folyn", "n");
			}
			System.out.print(design.toString() + "\n");
			if (design.getMd_dept().equals("1")) {
				design.setMd_dept("패션디자이너");

			} else if (design.getMd_dept().equals("2")) {
				design.setMd_dept("렉스타일디자이너");
				System.out.print("여기2");
			} else if (design.getMd_dept().equals("3")) {
				design.setMd_dept("의상디자이너");
				System.out.print("여기3");
			} else if (design.getMd_dept().equals("4")) {
				design.setMd_dept("패션MD");
				System.out.print("여기4");
			}

			if (design.getMd_career().equals("1")) {
				design.setMd_career("5년미만");

			} else if (design.getMd_career().equals("2")) {
				design.setMd_career("5년~10년");

			} else if (design.getMd_career().equals("3")) {
				design.setMd_career("10년~15년");

			} else if (design.getMd_career().equals("4")) {
				design.setMd_career("15년~20년");

			} else if (design.getMd_career().equals("5")) {
				design.setMd_career("20년이상");
			}

			if (design.getMd_business().equals("1")) {
				design.setMd_business("프리랜서");
			} else if (design.getMd_business().equals("2")) {
				design.setMd_business("개인사업자");
			} else if (design.getMd_business().equals("3")) {
				design.setMd_business("법인사업자");
			} else if (design.getMd_business().equals("4")) {
				design.setMd_business("브랜드대리점");
			}

			if (design.getMd_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (design.getMd_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (design.getMd_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (design.getMd_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (design.getMd_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (design.getMd_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (design.getMd_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (design.getMd_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (design.getMd_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", design);
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("portfolioList", portfolioList);
			model.addAttribute("portfolio", portfolio);
			return "/profile/portfoliolist_design";
		} else if (mmno.equals("F")) {
			FactoryVO factory = this.profileService.getMFProfile(mno); // MNO변수에 따른 프로필 받아오기
			FollowVO followVO = new FollowVO();
			followVO.setFollower(userName);
			followVO.setFollowing(factory.getMf_name());
			if (this.profileService.getfl(followVO) != null) { // 이미 팔로우 했을경우
				model.addAttribute("folyn", "y");
			} else {
				model.addAttribute("folyn", "n");
			}
			System.out.print(factory.toString() + "\n");
			if (factory.getMf_dept().equals("1")) {
				factory.setMf_dept("도로시공장");

			} else if (factory.getMf_dept().equals("2")) {
				factory.setMf_dept("다이마루공장");
				System.out.print("여기2");
			} else if (factory.getMf_dept().equals("3")) {
				factory.setMf_dept("직기공장");
				System.out.print("여기3");
			} else if (factory.getMf_dept().equals("4")) {
				factory.setMf_dept("특수공장");
				System.out.print("여기4");
			}

			if (factory.getMf_business().equals("1")) {
				factory.setMf_business("프리랜서");
			} else if (factory.getMf_business().equals("2")) {
				factory.setMf_business("개인사업자");
			} else if (factory.getMf_business().equals("3")) {
				factory.setMf_business("법인사업자");
			} else if (factory.getMf_business().equals("4")) {
				factory.setMf_business("브랜드대리점");
			}

			if (factory.getMf_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (factory.getMf_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (factory.getMf_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (factory.getMf_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (factory.getMf_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (factory.getMf_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (factory.getMf_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (factory.getMf_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (factory.getMf_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", factory);
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("portfolioList", portfolioList);
			model.addAttribute("portfolio", portfolio);
			return "/profile/portfoliolist_factory";
		} else if (mmno.equals("S")) {
			SellerVO seller = this.profileService.getMCProfile(mno); // MNO변수에 따른 프로필 받아오기
			FollowVO followVO = new FollowVO();
			followVO.setFollower(userName);
			followVO.setFollowing(seller.getMc_name());
			if (this.profileService.getfl(followVO) != null) { // 이미 팔로우 했을경우
				model.addAttribute("folyn", "y");
			} else {
				model.addAttribute("folyn", "n");
			}
			System.out.print(seller.toString() + "\n");
			if (seller.getMc_div().equals("1")) {
				seller.setMc_div("도매");

			} else if (seller.getMc_div().equals("2")) {
				seller.setMc_div("소매");
			}

			if (seller.getMc_business().equals("1")) {
				seller.setMc_business("프리랜서");
			} else if (seller.getMc_business().equals("2")) {
				seller.setMc_business("개인사업자");
			} else if (seller.getMc_business().equals("3")) {
				seller.setMc_business("법인사업자");
			} else if (seller.getMc_business().equals("4")) {
				seller.setMc_business("브랜드대리점");
			}

			if (seller.getMc_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (seller.getMc_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (seller.getMc_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (seller.getMc_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (seller.getMc_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (seller.getMc_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (seller.getMc_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (seller.getMc_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (seller.getMc_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", seller);
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("portfolioList", portfolioList);
			model.addAttribute("portfolio", portfolio);
			return "/profile/portfoliolist_seller";
		} else if (mmno.equals("M")) {
			UserVO user = this.profileService.getProfile(mno); // MNO변수에 따른 프로필 받아오기
			FollowVO followVO = new FollowVO();
			followVO.setFollower(userName);
			followVO.setFollowing(user.getName());
			if (this.profileService.getfl(followVO) != null) { // 이미 팔로우 했을경우
				model.addAttribute("folyn", "y");
			} else {
				model.addAttribute("folyn", "n");
			}
			System.out.print(user.toString() + "\n");
			model.addAttribute("profile", user);
			model.addAttribute("loginUser", loginuser);
			return "/profile/myhome";
		} else {
			model.addAttribute("error", "잘못된 경로입니다");
			return "index_container";
		}
	}

	// user 노하우부분
	@RequestMapping(value = "/knowhow_design.do", method = RequestMethod.GET)
	public String knowhow_design(Model model, KnowHowVO knowhowVO, HttpSession session, UserVO userVO,
			DesignVO designVO, FactoryVO factoryVO, SellerVO sellerVO) throws Exception {
		String userId = userVO.getUserId();
		UserVO loginuser = this.loginService.getUser(userId);
		String userName = (String) session.getAttribute("userName");
		String mno = loginuser.getMno(); // 뷰 테이블 mno 변수 받기
		String mmno = mno.substring(0, 1);
		int mnum = Integer.parseInt(mno.substring(1).replaceAll(" ", ""));
		knowhowVO.setH_mdiv(mmno);
		knowhowVO.setH_mnum(mnum);
		KnowHowVO knowhow = profileService.getKnowHowDeteil(knowhowVO);
		ArrayList<KnowHowVO> knowhowList = profileService.getKnowHow(knowhowVO);
		model.addAttribute("i_mnum", mnum);
		model.addAttribute("i_mdiv", mmno);

		if (mmno.equals("D")) {
			DesignVO design = this.profileService.getMDProfile(mno); // MNO변수에 따른 프로필 받아오기
			FollowVO followVO = new FollowVO();
			followVO.setFollower(userName);
			followVO.setFollowing(design.getMd_name());
			if (this.profileService.getfl(followVO) != null) { // 이미 팔로우 했을경우
				model.addAttribute("folyn", "y");
			} else {
				model.addAttribute("folyn", "n");
			}
			System.out.print(design.toString() + "\n");
			if (design.getMd_dept().equals("1")) {
				design.setMd_dept("패션디자이너");

			} else if (design.getMd_dept().equals("2")) {
				design.setMd_dept("렉스타일디자이너");
				System.out.print("여기2");
			} else if (design.getMd_dept().equals("3")) {
				design.setMd_dept("의상디자이너");
				System.out.print("여기3");
			} else if (design.getMd_dept().equals("4")) {
				design.setMd_dept("패션MD");
				System.out.print("여기4");
			}

			if (design.getMd_career().equals("1")) {
				design.setMd_career("5년미만");

			} else if (design.getMd_career().equals("2")) {
				design.setMd_career("5년~10년");

			} else if (design.getMd_career().equals("3")) {
				design.setMd_career("10년~15년");

			} else if (design.getMd_career().equals("4")) {
				design.setMd_career("15년~20년");

			} else if (design.getMd_career().equals("5")) {
				design.setMd_career("20년이상");
			}

			if (design.getMd_business().equals("1")) {
				design.setMd_business("프리랜서");
			} else if (design.getMd_business().equals("2")) {
				design.setMd_business("개인사업자");
			} else if (design.getMd_business().equals("3")) {
				design.setMd_business("법인사업자");
			} else if (design.getMd_business().equals("4")) {
				design.setMd_business("브랜드대리점");
			}

			if (design.getMd_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (design.getMd_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (design.getMd_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (design.getMd_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (design.getMd_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (design.getMd_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (design.getMd_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (design.getMd_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (design.getMd_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", design);
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("knowhowList", knowhowList);
			model.addAttribute("knowhow", knowhow);
			return "/profile/knowhow_design";
		} else if (mmno.equals("F")) {
			FactoryVO factory = this.profileService.getMFProfile(mno); // MNO변수에 따른 프로필 받아오기
			FollowVO followVO = new FollowVO();
			followVO.setFollower(userName);
			followVO.setFollowing(factory.getMf_name());
			if (this.profileService.getfl(followVO) != null) { // 이미 팔로우 했을경우
				model.addAttribute("folyn", "y");
			} else {
				model.addAttribute("folyn", "n");
			}
			if (factory.getMf_dept().equals("1")) {
				factory.setMf_dept("도로시공장");

			} else if (factory.getMf_dept().equals("2")) {
				factory.setMf_dept("다이마루공장");
				System.out.print("여기2");
			} else if (factory.getMf_dept().equals("3")) {
				factory.setMf_dept("직기공장");
				System.out.print("여기3");
			} else if (factory.getMf_dept().equals("4")) {
				factory.setMf_dept("특수공장");
				System.out.print("여기4");
			}

			if (factory.getMf_business().equals("1")) {
				factory.setMf_business("프리랜서");
			} else if (factory.getMf_business().equals("2")) {
				factory.setMf_business("개인사업자");
			} else if (factory.getMf_business().equals("3")) {
				factory.setMf_business("법인사업자");
			} else if (factory.getMf_business().equals("4")) {
				factory.setMf_business("브랜드대리점");
			}

			if (factory.getMf_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (factory.getMf_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (factory.getMf_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (factory.getMf_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (factory.getMf_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (factory.getMf_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (factory.getMf_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (factory.getMf_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (factory.getMf_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", factory);
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("knowhowList", knowhowList);
			model.addAttribute("knowhow", knowhow);
			return "/profile/knowhow_factory";
		} else if (mmno.equals("S")) {
			SellerVO seller = this.profileService.getMCProfile(mno); // MNO변수에 따른 프로필 받아오기
			FollowVO followVO = new FollowVO();
			followVO.setFollower(userName);
			followVO.setFollowing(seller.getMc_name());
			if (this.profileService.getfl(followVO) != null) { // 이미 팔로우 했을경우
				model.addAttribute("folyn", "y");
			} else {
				model.addAttribute("folyn", "n");
			}
			if (seller.getMc_div().equals("1")) {
				seller.setMc_div("도매");

			} else if (seller.getMc_div().equals("2")) {
				seller.setMc_div("소매");
			}

			if (seller.getMc_business().equals("1")) {
				seller.setMc_business("프리랜서");
			} else if (seller.getMc_business().equals("2")) {
				seller.setMc_business("개인사업자");
			} else if (seller.getMc_business().equals("3")) {
				seller.setMc_business("법인사업자");
			} else if (seller.getMc_business().equals("4")) {
				seller.setMc_business("브랜드대리점");
			}

			if (seller.getMc_ano1() == 10) {
				model.addAttribute("ano1", "서울");
			} else if (seller.getMc_ano1() == 20) {
				model.addAttribute("ano1", "강원");
			} else if (seller.getMc_ano1() == 30) {
				model.addAttribute("ano1", "대전");
			}

			if (seller.getMc_ano2() == 10) {
				model.addAttribute("ano2", "서울");
			} else if (seller.getMc_ano2() == 20) {
				model.addAttribute("ano2", "강원");
			} else if (seller.getMc_ano2() == 30) {
				model.addAttribute("ano2", "대전");
			}

			if (seller.getMc_ano3() == 10) {
				model.addAttribute("ano3", "서울");
			} else if (seller.getMc_ano3() == 20) {
				model.addAttribute("ano3", "강원");
			} else if (seller.getMc_ano3() == 30) {
				model.addAttribute("ano3", "대전");
			}

			model.addAttribute("profile", seller);
			model.addAttribute("loginUser", loginuser);
			model.addAttribute("knowhowList", knowhowList);
			model.addAttribute("knowhow", knowhow);
			return "/profile/knowhow_seller";
		} else if (mmno.equals("M")) {
			UserVO user = this.profileService.getProfile(mno); // MNO변수에 따른 프로필 받아오기
			FollowVO followVO = new FollowVO();
			followVO.setFollower(userName);
			followVO.setFollowing(user.getName());
			if (this.profileService.getfl(followVO) != null) { // 이미 팔로우 했을경우
				model.addAttribute("folyn", "y");
			} else {
				model.addAttribute("folyn", "n");
			}
			model.addAttribute("profile", user);
			model.addAttribute("loginUser", loginuser);
			return "/profile/myhome";
		} else {
			model.addAttribute("error", "잘못된 경로입니다");
			return "index_container";
		}
	}

	// user 노하우게시판 세부화면 부분
	@RequestMapping(value = "/knowhowview.do", method = RequestMethod.GET)
	public String knowhowview(@RequestParam(value = "hno") int hno, Model model, KnowHowVO knowhowVO,
			HttpSession session, UserVO userVO, DesignVO designVO, FactoryVO factoryVO, SellerVO sellerVO)
			throws Exception {
		// String userId = (String) session.getAttribute("userId");
		// UserVO loginuser = this.loginService.getUser(userId);
		// String mno = loginuser.getMno(); // 뷰 테이블 mno 변수 받기
		// String mmno = mno.substring(0, 1);
		// int mnum = Integer.parseInt(mno.substring(1).replaceAll(" ", ""));
		// knowhowVO.setH_mdiv(mmno);
		// knowhowVO.setH_mnum(mnum);
		KnowHowVO knowhow = profileService.getKnowHowDeteil(knowhowVO);
		ArrayList<KnowHowVO> knowhowList = profileService.getKnowHow(knowhow);
		List<khReplyVO> replyList = profileService.kgetreply(hno);
		this.profileService.viewcnt(hno);
		String h_name = knowhow.getH_name();
		String username = h_name;
		System.out.println(username);
		UserVO user = this.loginService.getsUser(username);

		model.addAttribute("user", user);
		model.addAttribute("knowhowList", knowhowList);
		model.addAttribute("replyList", replyList);
		model.addAttribute("knowhow", knowhow);
		return "/profile/knowhowview";
	}

	@RequestMapping(value = "/kwritereply") // 댓글작성
	public String replykWrite(@ModelAttribute("khreply") khReplyVO khreply, RedirectAttributes rea,
			HttpSession session) {
		String content = khreply.getRh_text().replaceAll("<", "&lt;");
		content = khreply.getRh_text().replaceAll(">", "&gt;");
		content = khreply.getRh_text().replaceAll("&", "&amp;");
		content = khreply.getRh_text().replaceAll("\"", "&quot;");
		content = khreply.getRh_text().replaceAll("\r\n", "<br />");
		khreply.setRh_text(content);

		String userId = (String) session.getAttribute("userId");
		UserVO loginuser = this.loginService.getUser(userId);
		String mno = loginuser.getMno(); // 뷰 테이블 mno 변수 받기
		String mmno = mno.substring(0, 1);
		int mnum = Integer.parseInt(mno.substring(1).replaceAll(" ", ""));
		khreply.setRh_mdiv(mmno);
		khreply.setRh_mnum(mnum);

		this.profileService.kwritereply(khreply);
		rea.addAttribute("hno", khreply.getHno());
		return "redirect:knowhowview.do";
	}

	@RequestMapping(value = "/likeknow") // 노하우 좋아요
	public String likeknow(HttpServletRequest request, RedirectAttributes rea) {
		System.out.println("여기실행");
		int hno = Integer.parseInt(request.getParameter("hno"));
		profileService.likecnt(hno);
		rea.addAttribute("hno", hno);
		return "redirect:knowhowview.do";
	}

	@RequestMapping(value = "/likeknowreply") // 노하우 댓글 좋아요
	public String likeknowreply(HttpServletRequest request, RedirectAttributes rea) {
		int rhno = Integer.parseInt(request.getParameter("rhno"));
		int hno = Integer.parseInt(request.getParameter("hno"));
		profileService.likereplycnt(rhno);
		rea.addAttribute("hno", hno);
		return "redirect:knowhowview.do";
	}

	@RequestMapping(value = "/deletereply") // 노하우 댓글 삭제
	public String delreply(HttpServletRequest request, RedirectAttributes rea) {
		int rhno = Integer.parseInt(request.getParameter("rhno"));
		int hno = Integer.parseInt(request.getParameter("hno"));
		profileService.deletereply(rhno);
		rea.addAttribute("hno", hno);
		return "redirect:knowhowview.do";
	}

	@RequestMapping(value = "/delknow") // 노하우 글 삭제
	public String delknow(HttpServletRequest request) {
		int hno = Integer.parseInt(request.getParameter("hno"));
		profileService.delknow(hno);
		return "redirect:/board/knowhow.do";
	}

	@RequestMapping(value = "/delfl") // 팔로우 삭제
	public String delfl(HttpServletRequest request, HttpSession session, RedirectAttributes rea) {
		String userName = (String) session.getAttribute("userName");
		String name = request.getParameter("name");
		String i_mdiv = request.getParameter("i_mdiv");
		int i_mnum = Integer.parseInt(request.getParameter("i_mnum"));
		FollowVO followVO = new FollowVO();
		followVO.setFollower(userName);
		followVO.setFollowing(name);
		this.profileService.delfl(followVO);
		rea.addAttribute("i_mnum", i_mnum);
		rea.addAttribute("i_mdiv", i_mdiv);
		return "redirect:userhome.do";
	}

	@RequestMapping(value = "/incfl") // 팔로우 추가
	public String incfl(HttpServletRequest request, HttpSession session, RedirectAttributes rea) {
		String userName = (String) session.getAttribute("userName"); // 팔로우 하는사람
		String name = request.getParameter("name"); // 팔로우 당하는사람
		UserVO fw = loginService.getsUser(userName);
		UserVO fl = loginService.getsUser(name);
		
		FollowVO followVO = new FollowVO();
		followVO.setFollower(userName);
		followVO.setFollowing(name);
		followVO.setFwimg(fw.getProimg());
		followVO.setFlimg(fl.getProimg());
		String i_mdiv = request.getParameter("i_mdiv");
//		String i_mdiv = request.getParameter("i_mdiv");
		int i_mnum = Integer.parseInt(request.getParameter("i_mnum"));
		this.profileService.incfl(followVO);
		rea.addAttribute("i_mnum", i_mnum);
		rea.addAttribute("i_mdiv", i_mdiv);
		return "redirect:userhome.do";
	}

	@RequestMapping(value = "/iwritereply") // 댓글작성
	public String ireplykWrite(@ModelAttribute("ireply") iReplyVO ireply, RedirectAttributes rea, HttpSession session,
			HttpServletRequest request) {
		String content = ireply.getRi_text().replaceAll("<", "&lt;");
		content = ireply.getRi_text().replaceAll(">", "&gt;");
		content = ireply.getRi_text().replaceAll("&", "&amp;");
		content = ireply.getRi_text().replaceAll("\"", "&quot;");
		content = ireply.getRi_text().replaceAll("\r\n", "<br />");
		ireply.setRi_text(content);

		String userId = (String) session.getAttribute("userId");
		UserVO loginuser = this.loginService.getUser(userId);

		String i_mnum = request.getParameter("i_mnum");
		String i_mdiv = request.getParameter("i_mdiv");

		// String mno = loginuser.getMno(); // 뷰 테이블 mno 변수 받기
		// String mmno = mno.substring(0, 1);
		// int mnum = Integer.parseInt(mno.substring(1).replaceAll(" ", ""));
		// ireply.setRh_mdiv(mmno);
		// ireply.setRh_mnum(mnum);
		this.profileService.iwritereply(ireply);
		rea.addAttribute("ino", ireply.getIno());
		rea.addAttribute("i_mnum", i_mnum);
		rea.addAttribute("i_mdiv", i_mdiv);
		return "redirect:userpphotoview.do";
	}

	@RequestMapping(value = "/ilikeknow") // 노하우 좋아요
	public String ilikeknow(HttpServletRequest request, RedirectAttributes rea) {
		System.out.println("여기실행");
		int ino = Integer.parseInt(request.getParameter("ino"));
		profileService.ilikecnt(ino);
		String i_mnum = request.getParameter("i_mnum");
		String i_mdiv = request.getParameter("i_mdiv");
		rea.addAttribute("ino", ino);
		rea.addAttribute("i_mnum", i_mnum);
		rea.addAttribute("i_mdiv", i_mdiv);
		return "redirect:userpphotoview.do";
	}

	@RequestMapping(value = "/ilikeknowreply") // 노하우 댓글 좋아요
	public String ilikeknowreply(HttpServletRequest request, RedirectAttributes rea) {
		int rino = Integer.parseInt(request.getParameter("rino"));
		int ino = Integer.parseInt(request.getParameter("ino"));
		profileService.ilikereplycnt(rino);
		String i_mnum = request.getParameter("i_mnum");
		String i_mdiv = request.getParameter("i_mdiv");
		rea.addAttribute("ino", ino);
		rea.addAttribute("i_mnum", i_mnum);
		rea.addAttribute("i_mdiv", i_mdiv);
		return "redirect:userpphotoview.do";
	}

	@RequestMapping(value = "/ideletereply") // 노하우 댓글 삭제
	public String idelreply(HttpServletRequest request, RedirectAttributes rea) {
		int rino = Integer.parseInt(request.getParameter("rino"));
		int ino = Integer.parseInt(request.getParameter("ino"));
		profileService.ideletereply(rino);
		String i_mnum = request.getParameter("i_mnum");
		String i_mdiv = request.getParameter("i_mdiv");
		rea.addAttribute("ino", ino);
		rea.addAttribute("i_mnum", i_mnum);
		rea.addAttribute("i_mdiv", i_mdiv);
		return "redirect:userpphotoview.do";
	}

	@RequestMapping(value = "/idelknow") // 노하우 글 삭제
	public String idelknow(HttpServletRequest request) {
		int ino = Integer.parseInt(request.getParameter("ino"));
		profileService.delknow(ino);
		return "redirect:/profile/design.do";
	}

}
