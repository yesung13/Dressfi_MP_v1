package com.jang.dressfi;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jang.dressfi.model.KnowHowVO;
import com.jang.dressfi.model.PortFolioVO;
import com.jang.dressfi.service.LoginService;
import com.jang.dressfi.service.ProfileService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired //
	private LoginService loginService; //
	@Autowired
	private ProfileService profileService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		/*		logger.info("Welcome home! The client locale is {}.", locale);
				
				Date date = new Date();
				DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
				
				String formattedDate = dateFormat.format(date);
				
				model.addAttribute("serverTime", formattedDate );*/
//		List<PortFolioVO> dlist = profileService.getdmain();
//		List<PortFolioVO> flist = profileService.getfmain();
//		List<PortFolioVO> clist = profileService.getcmain();
		List<KnowHowVO> klist = profileService.getkmain();
		ArrayList<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		ArrayList<Map<String, Object>> result1 = new ArrayList<Map<String, Object>>();
		ArrayList<Map<String, Object>> result2 = new ArrayList<Map<String, Object>>();
		
		result = profileService.getdmain();
		result1 = profileService.getfmain();
		result2 = profileService.getcmain();
//		model.addAttribute("dlist",dlist);
//		model.addAttribute("flist",flist);
//		model.addAttribute("clist",clist);
		model.addAttribute("dlist",result);
		model.addAttribute("flist",result1);
		model.addAttribute("clist",result2);
		model.addAttribute("klist",klist);
		return "index_container";
	}
	
}
