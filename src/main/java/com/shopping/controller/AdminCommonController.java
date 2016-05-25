package com.shopping.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yyz.helper.PasswdDigestHelper;

import com.shopping.model.User;
import com.shopping.service.AdminCommonService;

@Controller
@RequestMapping("/admin")
public class AdminCommonController {
	
	
	private final String URL="admin/";
	
	
	@Resource
	AdminCommonService acs;
	
	@RequestMapping("/welcome")
	public String welcome(){
		return URL+"welcome";
	}
	
	/**
	 * 登陆页面
	 * @param session
	 * @param m
	 * @return
	 */
	@RequestMapping(value = {"/login","/"}, method = RequestMethod.GET)
	public String loginGET(HttpSession session, Model m) {
		return URL+"login";
	}
	/**
	 * 登陆页面
	 * @param session
	 * @param m map型
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPOST(HttpSession session,HttpServletRequest request, Model m ,String loginname,String password) {
		m.addAttribute("active", "index");
		User user = acs.login(loginname, PasswdDigestHelper.digestPwd(password));
		if(user!=null){
			session.setAttribute("user", user);
			session.setAttribute("role", user.getRole());
			return URL+"index";
		}else{
			m.addAttribute("message", "用户名或者密码错误");
			return URL+"login";
		}
	}
	/**
	 * 退出
	 * @param session
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public String loginOut(HttpSession session, Model m) {
		session.invalidate();
		return "{success:'true'}";
	}
}
