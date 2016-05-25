package com.shopping.model;

import javax.annotation.Resource;

import org.junit.Test;
import org.yyz.test.MyTest;

import com.shopping.repository.RoleRepository;

public class RoleTest extends MyTest{
	@Resource
	RoleRepository rr;
	@Test
	public void addRole(){
		Role r = new Role();
		r.setName("超级管理员");
		r.setNamekey("admin");
		rr.save(r);
	}
}
