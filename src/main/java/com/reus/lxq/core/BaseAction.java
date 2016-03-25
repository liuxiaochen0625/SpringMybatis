/**
 *
 * @author : liuxiaoqiang
 * @date   :Feb 24, 2016 9:51:39 AM 
 * @version 1.0 
 *
 */
package com.reus.lxq.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reus.lxq.core.model.MallMessageRecv;
import com.reus.lxq.core.model.MallMessageSend;
public abstract class BaseAction implements Runnable{
	private HttpServletRequest request;
	private HttpServletResponse response;
	protected MallMessageRecv mmr;
	protected MallMessageSend mms;
	protected Integer code;
	protected String retMsg;
	
	public abstract void exec();
	
	public void run() {
		exec();
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public MallMessageRecv getMmr() {
		return mmr;
	}
	public void setMmr(MallMessageRecv mmr) {
		this.mmr = mmr;
	}
	public MallMessageSend getMms() {
		return mms;
	}
	public void setMms(MallMessageSend mms) {
		this.mms = mms;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	
}
