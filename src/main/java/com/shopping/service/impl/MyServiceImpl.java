package com.shopping.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.shopping.repository.UserRepository;
import com.shopping.service.MyService;
@Component
public class MyServiceImpl implements MyService{
	@Resource
	protected UserRepository ur;
}
