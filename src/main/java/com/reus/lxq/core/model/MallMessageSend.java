package com.reus.lxq.core.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author liyang
 *
 *         网上药房发送消息模版
 *
 */
public class MallMessageSend{
	private String key;
	private Header header;
	private Map<String, Object> body;

	public MallMessageSend() {
		header = new Header();
		body = new HashMap<String, Object>();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Map<String, Object> getBody() {
		return body;
	}

	public void setBody(Map<String, Object> body) {
		this.body = body;
	}

}
