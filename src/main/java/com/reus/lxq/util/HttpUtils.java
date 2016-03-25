package com.reus.lxq.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import flexjson.JSONDeserializer;

public class HttpUtils {
	/**
	* 描述：sendUrl 发送URL的post请求
	* @param urlStr
	* @param params
	* @return 
	*/
	public static String sendPost(String urlStr, String params) {
		OutputStreamWriter out = null;
		BufferedReader in = null;
		StringBuffer result = new StringBuffer();
		try {
			URL realUrl = new URL(urlStr);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			conn.setConnectTimeout(60 * 1000);
			conn.setReadTimeout(600 * 1000);
			// 设置通用的请求属性
			// conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			// conn.setRequestProperty("accept", "*/*");
			// conn.setRequestProperty("connection", "Keep-Alive");
			// conn.setRequestProperty("user-agent",
			// "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			// 发送请求参数
			out.write(params);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));
			String line = "";
			while ((line = in.readLine()) != null) {
				result.append(line);
			}	
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("网络通讯失败，url=" + urlStr + "\n参数=" + params);
		} finally {
			try {
				if (out != null) {
					out.close();
					out = null;
				}
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result.toString();
	}
	
	/**
	* 描述：saveAs	Save Internet resource to local, accept FTP(anonymous)/HTTP/HTTPS protocol.
	* @param sUrl
	* @param sPath
	* @return 0		success
	* @return -1	fail
	* Example:
	* HttpUtils.saveAs("https://.../image/banner.jpg","/Users/.../Desktop/");
	*/
	public static int saveAs(String sUrl,String sPath) {
		try {
			URL url;
			url = new URL(sUrl);
			int lastChar;
			for(lastChar=sUrl.length()-1;lastChar>=0;lastChar--)
			{
				if(sUrl.charAt(lastChar) == '\\' || sUrl.charAt(lastChar) == '/')
					break;
			}
			String fileName= sUrl.substring(lastChar+1);
			File outFile = new File(sPath + fileName);
			OutputStream os = new FileOutputStream(outFile);
			InputStream is = url.openStream();
			byte[] buff = new byte[1024];
			while (true) {
				int readed = is.read(buff);
				if (readed == -1) {
					break;
				}
				byte[] temp = new byte[readed];
				System.arraycopy(buff, 0, temp, 0, readed);
				os.write(temp);
			}
			is.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
	public static String reqShortUrl(String url) {
		return url;
		//return reqShortUrlFromSina(url);
	}
	public static String reqShortUrlFromGoogle(String url) {
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuffer result = new StringBuffer();
		try {
			URL realUrl = new URL("https://www.googleapis.com/urlshortener/v1/url");
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setRequestProperty("Content-Type","application/json");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new PrintWriter(conn.getOutputStream());
			out.print("{\"longUrl\":\"" + url + "\"}");
			out.flush();
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line = "";
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
					out = null;
				}
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		Map<String, String> map = null;
		JSONDeserializer<Map<String, String>> des = new JSONDeserializer<Map<String,String>>();
		map = (Map<String, String>) des.deserialize(result.toString());
		return map.get("id");
	}
	public static String reqShortUrlFromSina(String urlStr){
		try{
		    URL url = new URL("http://api.t.sina.com.cn/short_url/shorten.json?source=209678993&url_long="+urlStr);
		    InputStream in = url.openStream();
		    BufferedReader bin = new BufferedReader(new InputStreamReader(in, "utf-8"));
		    String s = null;
		    String result = null;
		    while((s=bin.readLine())!=null){
		    	result+=s;
		    }
		    bin.close();
		    if(result.startsWith("null")) result = result.substring(4);
		    List<Map<String, String>> lm = null;
			JSONDeserializer<List<Map<String, String>>> des = new JSONDeserializer<List<Map<String, String>>>();
			lm = (List<Map<String, String>>) des.deserialize(result.toString());
			return lm.get(0).get("url_short");
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		return urlStr;
	}
	public static String sendGet(String urlStr){
		try{
		    URL url = new URL(urlStr);
		    InputStream in = url.openStream();
		    BufferedReader bin = new BufferedReader(new InputStreamReader(in, "utf-8"));
		    String s = null;
		    String result = null;
		    while((s=bin.readLine())!=null){
		    	result+=s;
		    }
		    bin.close();
		    if(result.startsWith("null")) result = result.substring(4);
			return result;
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		return urlStr;
	}
	/**
	* 描述：sendGetWithTimeout 发送URL的get请求并携带超时限制
	* @param urlStr
	* @param timeout
	* @return 
	*/
	public static String sendGetWithTimeout(String urlStr, Integer timeout) {
		BufferedReader in = null;
		StringBuffer result = new StringBuffer();
		try {
			URL realUrl = new URL(urlStr);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			((HttpURLConnection) conn).setRequestMethod("GET");
			conn.setReadTimeout(timeout.intValue());
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));
			String line = "";
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
		} finally {
			try {
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result.toString();
	}
	
	public static String uploadFile(String url, String fileName){
		return uploadFile(url, fileName, null,null);
	}
	

	public static String uploadFile(String url, String fileName,String userId,String pathValue) {
		try {
			// 换行符
			final String newLine = "\r\n";
			final String boundaryPrefix = "--";
			// 定义数据分隔线
			String BOUNDARY = "========7d4a6d158c9";
			// 服务器的域名
			URL surl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) surl.openConnection();
			// 设置为POST情
			conn.setRequestMethod("POST");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求头参数
			if(userId != null)
				conn.setRequestProperty("userId", userId);
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Charsert", "UTF-8");
			conn.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + BOUNDARY);
			OutputStream out = new DataOutputStream(conn.getOutputStream());
			
			//上传参数
			if(pathValue != null){
				StringBuilder sub = new StringBuilder();
				String path = "path"; 
				sub.append("\r\n").append(boundaryPrefix).append(BOUNDARY).append(
						"\r\n");
				sub.append("Content-Disposition: form-data; name=\""+path
						+ "\"\r\n\r\n");
				sub.append(pathValue);
				out.write(sub.toString().getBytes());
			}
			
			// 上传文件
			File file = new File(fileName);
			StringBuilder sb = new StringBuilder();
			sb.append(newLine);
			sb.append(boundaryPrefix);
			sb.append(BOUNDARY);
			sb.append(newLine);
			// 文件参数,photo参数名可以随意修改
			sb.append("Content-Disposition: form-data;name=\"photo\";filename=\""
					+ file.getName() + "\"" + newLine);
			sb.append("Content-Type:application/octet-stream");
			// 参数头设置完以后需要两个换行，然后才是参数内容
			sb.append(newLine);
			sb.append(newLine);
			// 将参数头的数据写入到输出流中
			out.write(sb.toString().getBytes());
			// 数据输入流,用于读取文件数据
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			byte[] bufferOut = new byte[1024];
			int bytes = 0;
			// 每次读1KB数据,并且将文件数据写入到输出流中
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			// 最后添加换行
			out.write(newLine.getBytes());
			in.close();
			// 定义最后数据分隔线，即--加上BOUNDARY再加上--。
			byte[] end_data = (newLine + boundaryPrefix + BOUNDARY
					+ boundaryPrefix + newLine).getBytes();
			// 写上结尾标识
			out.write(end_data);
			out.flush();
			out.close();
			// 定义BufferedReader输入流来读取URL的响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			StringBuilder rets = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				rets.append(line);
			}
			return rets.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
