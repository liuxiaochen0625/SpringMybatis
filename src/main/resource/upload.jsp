<%@page import="com.reus.lxq.model.Bucket"%>
<%@page import="java.util.UUID"%>
<%@page import="flexjson.JSONSerializer"%>
<%@page import="flexjson.JSONDeserializer"%>
<%@page import="com.reus.lxq.core.model.MallMessageRecv"%>
<%@page import="com.reus.lxq.core.model.MallMessageSend"%>
<%@page import="com.reus.lxq.util.HttpUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String url = "http://localhost:8080/token";
		Bucket bucket = new Bucket();
		bucket.setBucket("liuxiaoqiang");
		bucket.setHash("wqweqewqwewerwr");
		bucket.setTime(1000000L);
		String result = HttpUtils.sendPost(url, bucket.toString());
		String key = UUID.randomUUID().toString().replaceAll("-", "")+".png";
		out.println(result);
		out.println(key);
	%>
	<form action="http://upload.qiniu.com/" enctype="multipart/form-data" method="POST">
		<input name="key" type="hidden" value=<%=key%>>
  		<input name="token" type="hidden" value=<%=result%>>
		<input name="file" type="file" />
		<button name="submit" type="submit"></button>
	</form>
</body>
</html>