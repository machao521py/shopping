package com.shopping.service;

import org.springframework.transaction.annotation.Transactional;

import com.shopping.model.User;
@Transactional
public interface AdminCommonService {

	/**
	 * 登陆
	 * @param loginname
	 * @param password
	 * @return
	 */
	User login(String loginname, String password);

}
