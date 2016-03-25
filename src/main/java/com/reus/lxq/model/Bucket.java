package com.reus.lxq.model;

public class Bucket {
	private String bucket;
	private String hash;
	private Long time;
	public String getBucket() {
		return bucket;
	}
	public void setBucket(String bucket) {
		this.bucket = bucket;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public String toString(){
		return "bucket=\""+bucket+"\"&&hash=\""+hash+"\"&&time="+time;
	}
}
