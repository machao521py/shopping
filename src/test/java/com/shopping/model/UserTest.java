package com.shopping.model;

import javax.annotation.Resource;

import org.junit.Test;
import org.yyz.helper.PasswdDigestHelper;
import org.yyz.test.MyTest;

import com.shopping.repository.RoleRepository;
import com.shopping.repository.UserRepository;

public class UserTest extends MyTest{
	@Resource
	UserRepository ur;
	@Resource
	RoleRepository rr;
	
	@Test
	public void addUser(){
		User u = new User();
		
		u.setLoginName("root");
		u.setUsername("用户名");
		u.setPassword(PasswdDigestHelper.digestPwd("123456"));
		ur.save(u);
	}
	@Test
	public void addRole_User(){
		User u = ur.findOne("300c28b9-39f5-4370-919e-b862353da10b");
		Role r = rr.findOne("");
		u.setRole(r);
		ur.save(u);
	}
}
