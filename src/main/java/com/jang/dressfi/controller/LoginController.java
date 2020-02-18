package com.jang.dressfi.controller;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jang.dressfi.model.DesignVO;
import com.jang.dressfi.model.FactoryVO;
import com.jang.dressfi.model.SellerVO;
import com.jang.dressfi.model.UserVO;
import com.jang.dressfi.service.LoginService;
import com.jang.dressfi.utils.BCrypt;

@Controller
@RequestMapping("/member")
public class LoginController {

	@Autowired //
	private LoginService loginService; //

	private String uploadPath = "C:\\Users\\berno\\eclipse-workspace\\Dressfi_MP_v1\\src\\main\\webapp\\resources\\img\\";

	//DressFi 소개페이지
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
					model.addAttribute("errCode", 1); // 등록되지않은 아이디
					return "/member/admin_login";
				}*/

		if (str.equals("A")) {
			if (BCrypt.checkpw(userVO.getPasswd(), loginUser.getPasswd())) { // 패
				session.setAttribute("userId", loginUser.getUserId()); // 세션에 변수등록
				session.setAttribute("userName", loginUser.getName()); // 세션에 변수등록
				session.setAttribute("p_img", loginUser.getProimg());
				model.addAttribute("loginUser", loginUser);
				return "redirect:/member/adminlist.do";

			} else {
				model.addAttribute("userId", "");
				model.addAttribute("errCode", 4);// 패스워드 불일치
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
			model.addAttribute("errCode", 1); // 등록되지않은 아이디
			return "/member/login";
		} else if (BCrypt.checkpw(userVO.getPasswd(), loginUser.getPasswd())) { // 패
			model.addAttribute("loginUser", loginUser);
			session.setAttribute("userId", loginUser.getUserId()); // 세션에 변수등록
			session.setAttribute("userName", loginUser.getName()); // 세션에 변수등록
			if (loginUser.getProimg() != null) {
				session.setAttribute("p_img", loginUser.getProimg());
			} else {
				session.setAttribute("p_img", "noimg.png");
			}
			return "redirect:/";
		} else {
			model.addAttribute("userId", "");
			model.addAttribute("errCode", 4);// 패스워드 불일치
			return "/member/login";
		}
	}

	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect: /dressfi"; /* /member/login.do */
	}

	//웹소켓
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
			session.setAttribute("userId", loginUser.getUserId()); // 세션에 변수등록
			session.setAttribute("userName", loginUser.getName()); // 세션에 변수등록
			// Json Map-->{msg=Success, id=jjin}
			object.addProperty("id", loginUser.getUserId());
			object.addProperty("msg", "Success");
			object.addProperty("err", "");
			return gson.toJson(object).toString();
		}
	}

	// 회원가입 선택(일반,전문가)
	@RequestMapping(value = "/joinSelect.do", method = RequestMethod.GET)
	public String joinSelect() {
		return "/member/joinSelect";
	}

	// 일반회원
	@RequestMapping(value = "/join.do", method = RequestMethod.GET)
	public String toUserEntryView(Model model) {
		model.addAttribute("userVO", new UserVO());
		return "/member/join";
	}

	@RequestMapping(value = "/checkid.do", method = RequestMethod.GET)
	public String dupCheckId(@RequestParam("userId") String userId, Model model) { // 출력 -join d

		String message = "";
		int reDiv = 0;
		System.out.println(userId);
		UserVO loginuser = this.loginService.getUser(userId);
		System.out.println(loginuser.getUserId());
		// try {
		// message = "이미 사용된 아이디 입니다";
		// userId = "";
		// } catch (EmptyResultDataAccessException e) {
		// message = "사용 가능한 아이디 입니다";
		// }
		if (loginuser.getUserId().equals(userId)) {
			message = "이미 사용된 아이디 입니다";
			userId = "";
			reDiv = 1;
		} else {
			reDiv = 1;
			message = "사용 가능한 아이디 입니다";
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
		} // passwd 암호화
		String hashPass = BCrypt.hashpw(userVO.getPasswd(), BCrypt.gensalt(12));
		userVO.setPasswd(hashPass); // 암호화된 passwd 로 변경
		System.out.println(userVO.toString());
		if (this.loginService.insertUser(userVO) != 0) {
			model.addAttribute("user", userVO);
			model.addAttribute("errCode", 3);// 등록성공
			return "/member/login";
		} else {
			model.addAttribute("errCode", 5);// 등록실패
			return "/member/join";
		}
	}

	// 전문가회원가입
	@RequestMapping(value = "/joinPro.do", method = RequestMethod.GET)
	public String joinProDesign(Model model) {
		model.addAttribute("designVO", new DesignVO());
		return "/member/joinPro";
	}

	@RequestMapping(value = "/joinPro.do", method = RequestMethod.POST)
	public String joinProDesignSubmit(DesignVO designVO, BindingResult result, Model model, MultipartHttpServletRequest request) throws Exception {

		String uploadPath = "C:\\Users\\berno\\eclipse-workspace\\Dressfi_MP_v1\\src\\main\\webapp\\resources\\img\\"; // C:\\Users\\Admin\\Desktop\\Dressfi_MP\\src\\main\\webapp\\resources\\img\\
		MultipartFile mf = request.getFile("file");

		String originFileName = mf.getOriginalFilename(); // 원본

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

		// passwd 암호화
		String hashPass = BCrypt.hashpw(designVO.getMd_passwd(), BCrypt.gensalt(12));
		designVO.setMd_passwd(hashPass); // 암호화된 passwd 로 변경
		System.out.println(designVO.toString());

		if (this.loginService.insertMDUser(designVO) != 0) {
			model.addAttribute("user", designVO);
			model.addAttribute("errCode", 3);// 등록성공
			return "/member/login";
		} else {
			model.addAttribute("errCode", 5);// 등록실패
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

		String originFileName = mf.getOriginalFilename(); // 원본

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

		// passwd 암호화
		String hashPass = BCrypt.hashpw(factoryVO.getMf_passwd(), BCrypt.gensalt(12));
		factoryVO.setMf_passwd(hashPass); // 암호화된 passwd 로 변경
		System.out.println(factoryVO.toString());

		if (this.loginService.insertMFUser(factoryVO) != 0) {
			model.addAttribute("user", factoryVO);
			model.addAttribute("errCode", 3);// 등록성공
			return "/member/login";
		} else {
			model.addAttribute("errCode", 5);// 등록실패
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

		String originFileName = mf.getOriginalFilename(); // 원본

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

		// passwd 암호화
		String hashPass = BCrypt.hashpw(sellerVO.getMc_passwd(), BCrypt.gensalt(12));
		sellerVO.setMc_passwd(hashPass); // 암호화된 passwd 로 변경
		System.out.println(sellerVO.toString());

		if (this.loginService.insertMCUser(sellerVO) != 0) {
			model.addAttribute("user", sellerVO);
			model.addAttribute("errCode", 3);// 등록성공
			return "/member/login";
		} else {
			model.addAttribute("errCode", 5);// 등록실패
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
			model.addAttribute("errCode", 1);// 등록되지않은 아이디
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
			System.out.println("여기!try");
			return "redirect:/member/logout.do";
		}

		catch (DataAccessException e) {
			System.out.println("여기!");
			result.reject("error.duplicate.user");
			model.addAllAttributes(result.getModel());
			return "/member/editForm";

		}
	}

	//비밀번호 찾기 - 수정부분
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
			System.out.println("result 오류!");
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
