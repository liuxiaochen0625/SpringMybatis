package com.reus.lxq.core.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author liyang
 *
 *         网上药房接收消息模版
 *
 */
public class MallMessageRecv{
	private String key;
	private Map<String, String> header;
	private Map<String, Object> body;

	public MallMessageRecv() {
		header = new HashMap<String, String>();
		header.put("dev_model", "");
		header.put("dev_no", "");
		header.put("dev_plat", "");
		header.put("dev_ver", "");
		header.put("ip_addr", "");
		header.put("push_id", "");
		header.put("soft_ver", "");
		header.put("token_id", "");
		header.put("user_id", "");
		body = new HashMap<String, Object>();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Map<String, String> getHeader() {
		return header;
	}

	public void setHeader(Map<String, String> header) {
		this.header = header;
	}

	public Map<String, Object> getBody() {
		return body;
	}

	public void setBody(Map<String, Object> body) {
		this.body = body;
	}

}
