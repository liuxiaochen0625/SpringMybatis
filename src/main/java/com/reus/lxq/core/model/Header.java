package com.reus.lxq.core.model;

public class Header{
	RetResult ret_result;
	String token_id;

	Header() {
		ret_result = new RetResult();
	}

	public RetResult getRet_result() {
		return ret_result;
	}

	public void setRet_result(RetResult ret_result) {
		this.ret_result = ret_result;
	}

	public String getToken_id() {
		return token_id;
	}

	public void setToken_id(String token_id) {
		this.token_id = token_id;
	}
}
