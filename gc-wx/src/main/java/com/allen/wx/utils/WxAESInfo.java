package com.allen.wx.utils;

public class WxAESInfo {

	private String appId;
	private String encodingAesKey;
	private String token;
	
	public WxAESInfo(String appId, String encodingAesKey, String token) {
		super();
		this.appId = appId;
		this.encodingAesKey = encodingAesKey;
		this.token = token;
	}
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getEncodingAesKey() {
		return encodingAesKey;
	}
	public void setEncodingAesKey(String encodingAesKey) {
		this.encodingAesKey = encodingAesKey;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
