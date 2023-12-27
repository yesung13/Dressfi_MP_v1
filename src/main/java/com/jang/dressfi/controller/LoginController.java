package com.jang.dressfi.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jang.dressfi.model.DesignVO;
import com.jang.dressfi.model.FactoryVO;
import com.jang.dressfi.model.SellerVO;
import com.jang.dressfi.model.UserVO;
import com.jang.dressfi.service.LoginService;
import com.jang.dressfi.utils.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
@RequestMapping("/member")
public class LoginController {

    @Autowired //
    private LoginService loginService; //

    private String uploadPath = "C:\\Users\\berno\\eclipse-workspace\\Dressfi_MP_v1\\src\\main\\webapp\\resources\\img\\";

    //DressFi ?????????
    @RequestMapping(value = "/intro.do", method = RequestMethod.GET)
    public String DressFiIntroduce() {
        /*model.addAttribute("userVO", new UserVO());*/
        return "/community/intro";
    }

    @RequestMapping(value = "/adminlogin.do", method = RequestMethod.GET)
    public String adminlogin() {

        return "/member/admin_login";
    }

    @RequestMapping(value = "/adminlogin.do", method = RequestMethod.POST)
    public String adminlogin(@Valid UserVO userVO, BindingResult result, Model model, HttpSession session) {

        if (result.hasFieldErrors("userId") || result.hasFieldErrors("passwd")) {
            model.addAllAttributes(result.getModel());
            return "/member/admin_login";
        }

        UserVO loginUser = this.loginService.getAdmin(userVO);
        String str = loginUser.getMno();
        str = str.substring(0, 1);
        System.out.print(str);

		/*		if (loginUser == null) {
					model.addAttribute("userId", "");
					model.addAttribute("errCode", 1); // ?????????? ?????
					return "/member/admin_login";
				}*/

        if (str.equals("A")) {
            if (BCrypt.checkpw(userVO.getPasswd(), loginUser.getPasswd())) { // ??
                session.setAttribute("userId", loginUser.getUserId()); // ????? ???????
                session.setAttribute("userName", loginUser.getName()); // ????? ???????
                session.setAttribute("p_img", loginUser.getProimg());
                model.addAttribute("loginUser", loginUser);
                return "redirect:/member/adminlist.do";

            } else {
                model.addAttribute("userId", "");
                model.addAttribute("errCode", 4);// ??????? ?????
                return "/member/admin_login";
            }
        } else {
            return "/member/admin_login";
        }
    }

    @RequestMapping(value = "/adminlist.do", method = RequestMethod.GET)
    public String list(Model model) throws Exception {
        List<UserVO> list = this.loginService.getUserList();
        model.addAttribute("list", list);
        System.out.print(list.toString() + "\n");
        return "/member/admin_Memberlist";
    }

    @RequestMapping(value = "/adminMDlist.do", method = RequestMethod.GET)
    public String dlist(Model model) throws Exception {
        List<DesignVO> Dlist = this.loginService.getMDUserList();
        model.addAttribute("dlist", Dlist);
        System.out.print(Dlist.toString() + "\n");
        return "/member/admin_Designlist";
    }

    @RequestMapping(value = "/adminMFlist.do", method = RequestMethod.GET)
    public String flist(Model model) throws Exception {
        List<FactoryVO> Flist = this.loginService.getMFUserList();
        model.addAttribute("flist", Flist);
        System.out.print(Flist.toString() + "\n");
        return "/member/admin_Factorylist";
    }

    @RequestMapping(value = "/adminMClist.do", method = RequestMethod.GET)
    public String clist(Model model) throws Exception {
        List<SellerVO> Slist = this.loginService.getMCUserList();
        model.addAttribute("slist", Slist);
        System.out.print(Slist.toString() + "\n");
        return "/member/admin_Sellerlist";
    }

    @RequestMapping(value = "/deletec.do", method = RequestMethod.POST)
    public String delc(@RequestParam(value = "mno") String mno, RedirectAttributes rea, UserVO userVO) throws Exception {

        this.loginService.deleteUser(userVO);
        return "redirect:/member/adminlist.do";
    }

    @RequestMapping(value = "/MDdeletec.do", method = RequestMethod.POST)
    public String delc1(@RequestParam(value = "mdno") String mdno, RedirectAttributes rea, DesignVO designVO) throws Exception {

        this.loginService.MDdeleteUser(designVO);
        return "redirect:/member/adminMDlist.do";
    }

    @RequestMapping(value = "/MFdeletec.do", method = RequestMethod.POST)
    public String delc2(@RequestParam(value = "mfno") String mfno, RedirectAttributes rea, FactoryVO factoryVO) throws Exception {

        this.loginService.MFdeleteUser(factoryVO);
        return "redirect:/member/adminMFlist.do";
    }

    @RequestMapping(value = "/MSdeletec.do", method = RequestMethod.POST)
    public String delc3(@RequestParam(value = "mcno") String mcno, RedirectAttributes rea, SellerVO sellerVO) throws Exception {

        this.loginService.MCdeleteUser(sellerVO);
        return "redirect:/member/adminMClist.do";
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String login() {

        return "/member/login";
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String loginProc(@Valid UserVO userVO, BindingResult result, Model model, HttpSession session) {

        if (result.hasFieldErrors("userId") || result.hasFieldErrors("passwd")) {
            model.addAllAttributes(result.getModel());
            return "/member/login";
        }
        UserVO loginUser = this.loginService.getUser(userVO.getUserId());
        if (loginUser == null) {
            model.addAttribute("userId", "");
            model.addAttribute("errCode", 1); // ?????????? ?????
            return "/member/login";
        } else if (BCrypt.checkpw(userVO.getPasswd(), loginUser.getPasswd())) { // ??
            model.addAttribute("loginUser", loginUser);
            session.setAttribute("userId", loginUser.getUserId()); // ????? ???????
            session.setAttribute("userName", loginUser.getName()); // ????? ???????
            if (loginUser.getProimg() != null) {
                session.setAttribute("p_img", loginUser.getProimg());
            } else {
                session.setAttribute("p_img", "noimg.png");
            }
            return "redirect:/";
        } else {
            model.addAttribute("userId", "");
            model.addAttribute("errCode", 4);// ??????? ?????
            return "/member/login";
        }
    }

    @RequestMapping("/logout.do")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect: /dressfi"; /* /member/login.do */
    }

    //??????
    //	@RequestMapping("/chat.do")
    //	public String chat() {
    //		return "Chat";
    //	}

    @RequestMapping("/ajaxlogin.do")
    public String ajlogin() {
        return "/member/ajaxlogin";
    }

    @RequestMapping(value = "/ajaxlogin.do", method = RequestMethod.POST)
    public @ResponseBody String AjaxView(@Valid UserVO userVO, BindingResult bindingResult, HttpSession session) {

        Gson gson = new Gson();
        JsonObject object = new JsonObject();

        object.addProperty("msg", "Fail");
        if (bindingResult.hasFieldErrors("userId")) {
            object.addProperty("err", "empty_id");
            return gson.toJson(object).toString();
        } else if (bindingResult.hasFieldErrors("passwd")) {
            object.addProperty("err", "empty_pass");
            return gson.toJson(object).toString();
        }
        UserVO loginUser = this.loginService.findUser(userVO);
        if (loginUser == null) {
            object.addProperty("id", "Null");
            object.addProperty("err", "noUser");
            return gson.toJson(object).toString();
        } else {
            session.setAttribute("userId", loginUser.getUserId()); // ????? ???????
            session.setAttribute("userName", loginUser.getName()); // ????? ???????
            // Json Map-->{msg=Success, id=jjin}
            object.addProperty("id", loginUser.getUserId());
            object.addProperty("msg", "Success");
            object.addProperty("err", "");
            return gson.toJson(object).toString();
        }
    }

    // ??????? ????(???,??????)
    @RequestMapping(value = "/joinSelect.do", method = RequestMethod.GET)
    public String joinSelect() {
        return "/member/joinSelect";
    }

    // ??????
    @RequestMapping(value = "/join.do", method = RequestMethod.GET)
    public String toUserEntryView(Model model) {
        model.addAttribute("userVO", new UserVO());
        return "/member/join";
    }

    @RequestMapping(value = "/checkid.do", method = RequestMethod.GET)
    public String dupCheckId(@RequestParam("userId") String userId, Model model) { // ??? -join d

        String message = "";
        int reDiv = 0;
        System.out.println(userId);
        UserVO loginuser = this.loginService.getUser(userId);
        System.out.println(loginuser.getUserId());
        // try {
        // message = "??? ???? ????? ????";
        // userId = "";
        // } catch (EmptyResultDataAccessException e) {
        // message = "??? ?????? ????? ????";
        // }
        if (loginuser.getUserId().equals(userId)) {
            message = "??? ???? ????? ????";
            userId = "";
            reDiv = 1;
        } else {
            reDiv = 1;
            message = "??? ?????? ????? ????";
        }
        model.addAttribute("message", message);
        model.addAttribute("reDiv", reDiv);
        model.addAttribute("userId", userId);
        return "/member/joinSuccess";
    }

    @RequestMapping(value = "/join.do", method = RequestMethod.POST)
    public String joinSubmit(@Valid UserVO userVO, BindingResult result, Model model) throws Exception {
        if (result.hasErrors()) {
            model.addAllAttributes(result.getModel());
            return "/member/join";
        } // passwd ????
        String hashPass = BCrypt.hashpw(userVO.getPasswd(), BCrypt.gensalt(12));
        userVO.setPasswd(hashPass); // ?????? passwd ?? ????
        System.out.println(userVO.toString());
        if (this.loginService.insertUser(userVO) != 0) {
            model.addAttribute("user", userVO);
            model.addAttribute("errCode", 3);// ??????
            return "/member/login";
        } else {
            model.addAttribute("errCode", 5);// ??????
            return "/member/join";
        }
    }

    // ?????????????
    @RequestMapping(value = "/joinPro.do", method = RequestMethod.GET)
    public String joinProDesign(Model model) {
        model.addAttribute("designVO", new DesignVO());
        return "/member/joinPro";
    }

    @RequestMapping(value = "/joinPro.do", method = RequestMethod.POST)
    public String joinProDesignSubmit(DesignVO designVO, BindingResult result, Model model, MultipartHttpServletRequest request) throws Exception {

        String uploadPath = "C:\\Users\\berno\\eclipse-workspace\\Dressfi_MP_v1\\src\\main\\webapp\\resources\\img\\"; // C:\\Users\\Admin\\Desktop\\Dressfi_MP\\src\\main\\webapp\\resources\\img\\
        MultipartFile mf = request.getFile("file");

        String originFileName = mf.getOriginalFilename(); // ????

        String safeFile = uploadPath + originFileName;
        try {
            mf.transferTo(new File(safeFile));
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        designVO.setMd_proimg(originFileName);

        // passwd ????
        String hashPass = BCrypt.hashpw(designVO.getMd_passwd(), BCrypt.gensalt(12));
        designVO.setMd_passwd(hashPass); // ?????? passwd ?? ????
        System.out.println(designVO.toString());

        if (this.loginService.insertMDUser(designVO) != 0) {
            model.addAttribute("user", designVO);
            model.addAttribute("errCode", 3);// ??????
            return "/member/login";
        } else {
            model.addAttribute("errCode", 5);// ??????
            return "/member/joinPro";
        }
    }

    @RequestMapping(value = "/joinPro2.do", method = RequestMethod.GET)
    public String joinProFactory(Model model) {
        return "/member/joinPro2";
    }

    // Factory
    @RequestMapping(value = "/joinPro2.do", method = RequestMethod.POST)
    public String joinProFacatorySubmit(FactoryVO factoryVO, BindingResult result, Model model, MultipartHttpServletRequest request) throws Exception {

        String uploadPath = "C:\\Users\\berno\\eclipse-workspace\\Dressfi_MP_v1\\src\\main\\webapp\\resources\\img\\";
        MultipartFile mf = request.getFile("file");

        String originFileName = mf.getOriginalFilename(); // ????

        String safeFile = uploadPath + originFileName;
        try {
            mf.transferTo(new File(safeFile));
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        factoryVO.setMf_proimg(originFileName);

        // passwd ????
        String hashPass = BCrypt.hashpw(factoryVO.getMf_passwd(), BCrypt.gensalt(12));
        factoryVO.setMf_passwd(hashPass); // ?????? passwd ?? ????
        System.out.println(factoryVO.toString());

        if (this.loginService.insertMFUser(factoryVO) != 0) {
            model.addAttribute("user", factoryVO);
            model.addAttribute("errCode", 3);// ??????
            return "/member/login";
        } else {
            model.addAttribute("errCode", 5);// ??????
            return "/member/joinPro";
        }
    }

    @RequestMapping(value = "/joinPro3.do", method = RequestMethod.GET)
    public String joinProSeller(Model model) {
        return "/member/joinPro3";
    }

    // Seller
    @RequestMapping(value = "/joinPro3.do", method = RequestMethod.POST)
    public String joinProSellerSubmit(SellerVO sellerVO, BindingResult result, Model model, MultipartHttpServletRequest request) throws Exception {

        String uploadPath = "C:\\Users\\berno\\eclipse-workspace\\Dressfi_MP_v1\\src\\main\\webapp\\resources\\img\\";
        MultipartFile mf = request.getFile("file");

        String originFileName = mf.getOriginalFilename(); // ????

        String safeFile = uploadPath + originFileName;
        try {
            mf.transferTo(new File(safeFile));
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        sellerVO.setMc_proimg(originFileName);

        // passwd ????
        String hashPass = BCrypt.hashpw(sellerVO.getMc_passwd(), BCrypt.gensalt(12));
        sellerVO.setMc_passwd(hashPass); // ?????? passwd ?? ????
        System.out.println(sellerVO.toString());

        if (this.loginService.insertMCUser(sellerVO) != 0) {
            model.addAttribute("user", sellerVO);
            model.addAttribute("errCode", 3);// ??????
            return "/member/login";
        } else {
            model.addAttribute("errCode", 5);// ??????
            return "/member/joinPro";
        }
    }

    @RequestMapping(value = "/findId.do", method = RequestMethod.GET)
    public String findId() {
        return "/member/findId";
    }

    @RequestMapping(value = "/findId.do", method = RequestMethod.POST)
    public String findIdProc(@Valid UserVO userVO, BindingResult result, Model model) throws Exception {
        if (result.hasFieldErrors("email") || result.hasFieldErrors("name")) {// result.hasFieldErrors("jumin")
            model.addAllAttributes(result.getModel());
            return "/member/findId";
        }

        UserVO loginuser = this.loginService.findId(userVO);
        if (loginuser == null) {
            model.addAttribute("errCode", 1);
            return "/member/findId";
        } else {
            model.addAttribute("userId", loginuser.getUserId());
            model.addAttribute("userName", loginuser.getName());
            model.addAttribute("errCode", 4);
            return "/member/findIdSuccess";
        }
    }

    @RequestMapping(value = "/edituser", method = RequestMethod.GET)
    public String toUserEditView(HttpSession session, Model model)
            throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        String userId = session.getAttribute("userId").toString();
        UserVO loginUser = this.loginService.getUser(userId);

        if (loginUser == null) {
            model.addAttribute("userId", "");
            model.addAttribute("errCode", 1);// ?????????? ?????
            return "/member/login";
        }
        model.addAttribute("userVO", loginUser);
        System.out.println(loginUser.toString());
        return "/member/editForm";

        // }
    }

    @RequestMapping(value = "/edituser", method = RequestMethod.POST)
    public String onEditSave(@Valid UserVO userVO, BindingResult result, Model model) throws Exception {
        if (result.hasErrors()) {
            model.addAllAttributes(result.getModel());
            return "/member/editForm";
        }
        System.out.println(userVO.getPasswd());
        userVO.setPasswd(BCrypt.hashpw(userVO.getPasswd(), BCrypt.gensalt(12)));
        System.out.println(userVO.getName() + userVO.getUserId());
        System.out.println(userVO.getPasswd());

        try {
            System.out.println(userVO.toString());
            loginService.updateUser(userVO);
            model.addAttribute("userVO", userVO);
            System.out.println("????!try");
            return "redirect:/member/logout.do";
        } catch (DataAccessException e) {
            System.out.println("????!");
            result.reject("error.duplicate.user");
            model.addAllAttributes(result.getModel());
            return "/member/editForm";

        }
    }

    @RequestMapping(value = "/findPass.do", method = RequestMethod.GET)
    public String findPass(Model model) {
        model.addAttribute("userVO", new UserVO());
        return "/member/findPass";
    }

    @RequestMapping(value = "/findPass.do", method = RequestMethod.POST)
    public String findPassProc(@Valid UserVO userVO, BindingResult result, Model model) throws Exception {
        if (result.hasFieldErrors("userId") || result.hasFieldErrors("name") || result.hasFieldErrors("email")) {
            model.addAllAttributes(result.getModel());
            return "/member/findPass";
        }

        UserVO loginuser = this.loginService.findPass(userVO);
        if (loginuser == null) {
            model.addAttribute("errCode", 1);
            return "/member/findPass";
        } else {
            model.addAttribute("user", loginuser);
            System.out.println(loginuser.toString());
            return "/member/editpass";
        }
    }

    @RequestMapping(value = "/editpass.do", method = RequestMethod.POST)
    public String onEditpassSave(@Valid UserVO userVO, BindingResult result, Model model) throws Exception {
        if (result.hasFieldErrors("userId") || result.hasFieldErrors("passwd")) {
            model.addAllAttributes(result.getModel());
            System.out.println("result ????!");
            return "/member/editPass";
        }
        userVO.setPasswd(BCrypt.hashpw(userVO.getPasswd(), BCrypt.gensalt(12)));
        try {
            loginService.updatePass(userVO);
            System.out.println(userVO.toString());
            return "redirect:/member/login.do";
        } catch (DataAccessException e) {
            result.reject("error.duplicate.user");
            model.addAllAttributes(result.getModel());
            return "/member/editPass";
        }
    }
}
