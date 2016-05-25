package org.yyz.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.yyz.helper.AjaxError;

import com.shopping.model.User;
import com.shopping.service.CommonService;



public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	CommonService commonService;
	public boolean preHandle(@RequestParam(required=false)HttpServletRequest request,
			@RequestParam(required=false)HttpServletResponse response, Object handler) throws Exception {
		String path = request.getRequestURI();
		String cxt = request.getContextPath();
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		path = path.replaceFirst(cxt, "");
		if (("/admin/login".lastIndexOf(path))==-1&&(u==null||u.getId()==null)) {
			String requestedWith = request.getHeader("x-requested-with");
			// ajax请求
			if (requestedWith != null && "XMLHttpRequest".equals(requestedWith)) {
				AjaxError ae = new AjaxError();
				ae.setMessage("登陆超时");
				ae.setSuccess(false);
				request.setAttribute("Content-Type", "text/plain;charset=UTF-8");
				request.setAttribute("Errir", ae);
				response.setHeader("session-status", "timeout");
				response.getWriter().print(WebConstants.TIME_OUT);

			} else {
				response.sendRedirect(cxt + "/admin/login");
			}
		}
		boolean isflase = super.preHandle(request, response, handler);
		System.out.println(isflase);
		return isflase;
	}
}
