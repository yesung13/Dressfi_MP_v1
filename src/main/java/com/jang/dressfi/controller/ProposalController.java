package com.jang.dressfi.controller;

import com.jang.dressfi.model.*;
import com.jang.dressfi.service.LoginService;
import com.jang.dressfi.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
        if (target == 0) { // ���
            List<UserVO> user = profileservice.pgetuser();//��� ȸ�� ���Ӱ� �̾ƿð�
            System.out.println(user.toString());
            for (UserVO loginuser : user) {
                psal.setPr_targetid(loginuser.getName());
                profileservice.insertpsal(psal);

            }
        } else if (target == 1) { // �����̳�
            List<DesignVO> de = profileservice.pgetdesign(ano1);//��� ȸ�� ���Ӱ� �̾ƿð�
            System.out.println(de.toString());//�����̳� ��� ȸ�� ���Ӱ� �̾ƿð�
            for (DesignVO des : de) {
                psal.setPr_targetid(des.getMd_name());
                profileservice.insertpsal(psal);

            }
        } else if (target == 2) { // ����
            List<FactoryVO> fa = profileservice.pgetfactory(ano1);//��� ȸ�� ���Ӱ� �̾ƿð�
            System.out.println(fa.toString());//���� ��� ȸ�� ���Ӱ� �̾ƿð�
            for (FactoryVO fac : fa) {
                psal.setPr_targetid(fac.getMf_name());
                profileservice.insertpsal(psal);

            }
        } else if (target == 3) { // ���Ҹ�
            List<SellerVO> se = profileservice.pgetseller(ano1);//��� ȸ�� ���Ӱ� �̾ƿð�
            System.out.println(se.toString());
            for (SellerVO sel : se) {
                psal.setPr_targetid(sel.getMc_name());
                profileservice.insertpsal(psal);

            }
        } else if (target == 4) { // �ȷ���
            String follower = userName;
            List<FollowVO> fl = profileservice.pgetfl(follower);//��� ȸ�� ���Ӱ� �̾ƿð�
            System.out.println(fl.toString());
            for (FollowVO fol : fl) {
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
        model.addAttribute("psallist", psallist);
        return "/proposal/proposalsend";
    }

    @RequestMapping(value = "/proposalrec.do", method = RequestMethod.GET)
    public String proposalReceive(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        String userName = (String) session.getAttribute("userName");
        List<PsalVO> psallist = this.profileservice.getrec(userName);
        model.addAttribute("psallist", psallist);
        return "/proposal/proposalrec";
    }

    @RequestMapping(value = "/proposalview.do", method = RequestMethod.GET)
    public String proposalView(HttpServletRequest request, Model model) {
        int prno = Integer.parseInt(request.getParameter("prno"));
        PsalVO psal = profileservice.getpsal(prno);
        model.addAttribute("psal", psal);
        if (psal.getP_ano1() == 0) {
            model.addAttribute("pr_area", "����");
        } else if (psal.getP_ano1() == 10) {
            model.addAttribute("pr_area", "����");
        } else if (psal.getP_ano1() == 20) {
            model.addAttribute("pr_area", "����");
        } else if (psal.getP_ano1() == 30) {
            model.addAttribute("pr_area", "����");
        }
        System.out.println(psal.toString());
        return "/proposal/proposalview";
    }
}
