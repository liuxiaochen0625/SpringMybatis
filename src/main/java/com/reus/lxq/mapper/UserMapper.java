package com.reus.lxq.mapper;

import org.apache.ibatis.annotations.Select;

import com.reus.lxq.model.User;

public interface UserMapper {
	@Select("select * from user where name = #{name}")
	public User getUser(String name);
}
