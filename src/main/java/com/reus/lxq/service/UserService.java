package com.reus.lxq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reus.lxq.mapper.UserMapper;
import com.reus.lxq.model.User;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	public User getInfo(){
		return userMapper.getUser("zhangsan");
	}
}
