package com.shopping.service.impl;

import org.springframework.stereotype.Component;

import com.shopping.model.User;
import com.shopping.service.AdminCommonService;
@Component
public class AdminCommonServiceImpl extends MyServiceImpl implements AdminCommonService {
	@Override
	public User login(String loginName, String password) {
		// TODO 登陆接口实现
		return ur.login(loginName,password);
	}
}
