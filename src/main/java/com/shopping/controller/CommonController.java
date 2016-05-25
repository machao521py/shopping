package com.shopping.controller;



import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shopping.service.CommonService;


@Controller
public class CommonController {
	@Resource
	CommonService cs;
	
	/**
	 * 首页
	 * @param session
	 * @param m
	 * @return
	 */
	@RequestMapping(value = {"/index","/"}, method = RequestMethod.GET)
	public String index(HttpSession session, Model m) {
		return "index";
	}
	@RequestMapping(value = {"/login","/"}, method = RequestMethod.GET)
	public String loginGET(HttpSession session, Model m) {
		return "login";
	}
}
