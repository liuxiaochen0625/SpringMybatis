package com.reus.lxq.model;

public class User {
	private String name;
	private Integer user_id;
	private Integer age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String toString(){
		return "[user_id="+user_id+",name="+name+",age="+age+"]";
	}
}
