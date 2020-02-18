package com.jang.dressfi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jang.dressfi.model.DesignVO;
import com.jang.dressfi.model.FactoryVO;
import com.jang.dressfi.model.FollowVO;
import com.jang.dressfi.model.PsalVO;
import com.jang.dressfi.model.SellerVO;
import com.jang.dressfi.model.UserVO;
import com.jang.dressfi.service.LoginService;
import com.jang.dressfi.service.ProfileService;

@Controller
@RequestMapping("/proposal")
public class ProposalController {

	@Autowired //
	private LoginService loginService;

	@Autowired //
	private ProfileService profileservice;
	@RequestMapping(value = "/proposalwrite.do", method = RequestMethod.GET)
	public String proposalWrite() {

		return "/proposal/proposalwrite";
	}

	@RequestMapping(value = "/proposalwrite.do", method = RequestMethod.POST)
	public String proposalWritedo(PsalVO psal, HttpSession session) {
		System.out.println(psal.toString());
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userName");
		psal.setPr_sendid(userName);
		int target = psal.getPr_target();
		int ano1 = psal.getP_ano1();
		psal.setPr_area(ano1);
		if (target == 0) { // 모든
			List<UserVO> user = profileservice.pgetuser();//모든 회원 네임값 뽑아올것
			System.out.println(user.toString());
			for(UserVO loginuser : user) {
				psal.setPr_targetid(loginuser.getName());
				profileservice.insertpsal(psal);
				
			}
		} else if (target == 1) { // 디자이너
			List<DesignVO> de = profileservice.pgetdesign(ano1);//모든 회원 네임값 뽑아올것
			System.out.println(de.toString());//디자이너 모든 회원 네임값 뽑아올것
			for(DesignVO des : de) {
				psal.setPr_targetid(des.getMd_name());
				profileservice.insertpsal(psal);
				
			}
		} else if (target == 2) { // 공장
			List<FactoryVO> fa= profileservice.pgetfactory(ano1);//모든 회원 네임값 뽑아올것
			System.out.println(fa.toString());//공장 모든 회원 네임값 뽑아올것
			for(FactoryVO fac : fa) {
				psal.setPr_targetid(fac.getMf_name());
				profileservice.insertpsal(psal);
				
			}
		} else if (target == 3) { // 도소매
			List<SellerVO> se = profileservice.pgetseller(ano1);//모든 회원 네임값 뽑아올것
			System.out.println(se.toString());
			for(SellerVO sel : se) {
				psal.setPr_targetid(sel.getMc_name());
				profileservice.insertpsal(psal);
				
			}
		} else if (target == 4) { // 팔로잉
			String follower = userName;
			List<FollowVO> fl = profileservice.pgetfl(follower);//모든 회원 네임값 뽑아올것
			System.out.println(fl.toString());
			for(FollowVO fol : fl) {
				psal.setPr_targetid(fol.getFollowing());
				profileservice.insertpsal(psal);
			}
		}
		return "redirect:proposalsend.do";
	}

	@RequestMapping(value = "/proposalsend.do", method = RequestMethod.GET)
	public String proposalSend(HttpSession session, Model model) {
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userName");
		List<PsalVO> psallist = this.profileservice.getsend(userName);
		model.addAttribute("psallist",psallist);
		return "/proposal/proposalsend";
	}

	@RequestMapping(value = "/proposalrec.do", method = RequestMethod.GET)
	public String proposalReceive(HttpSession session, Model model) {
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userName");
		List<PsalVO> psallist = this.profileservice.getrec(userName);
		model.addAttribute("psallist",psallist);
		return "/proposal/proposalrec";
	}

	@RequestMapping(value = "/proposalview.do", method = RequestMethod.GET)
	public String proposalView(HttpServletRequest request,Model model) {
		int prno = Integer.parseInt(request.getParameter("prno"));
		PsalVO psal = profileservice.getpsal(prno);
		model.addAttribute("psal",psal);
		if(psal.getP_ano1() == 0 ) {
			model.addAttribute("pr_area","전국");
		}
		else if(psal.getP_ano1() == 10 ) {
			model.addAttribute("pr_area","서울");
		}
		else if(psal.getP_ano1() == 20 ) {
			model.addAttribute("pr_area","강원");
		}
		else if(psal.getP_ano1() == 30 ) {
			model.addAttribute("pr_area","대전");
		}
		System.out.println(psal.toString());
		return "/proposal/proposalview";
	}
}
