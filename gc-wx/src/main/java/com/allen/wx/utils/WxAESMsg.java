package com.allen.wx.utils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
public class WxAESMsg {

	private String encrypt;
	private String msgSignature;
	private String timestamp;
	private String nonce;
	
	public String getEncrypt() {
		return encrypt;
	}
	@XmlElement(name = "Encrypt")
	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}
	public String getMsgSignature() {
		return msgSignature;
	}
	@XmlElement(name = "MsgSignature")
	public void setMsgSignature(String msgSignature) {
		this.msgSignature = msgSignature;
	}
	public String getTimestamp() {
		return timestamp;
	}
	@XmlElement(name = "TimeStamp")
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonce() {
		return nonce;
	}
	@XmlElement(name = "Nonce")
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	@Override
	public String toString() {
		return "WxAESMsg [encrypt=" + encrypt + ", msgSignature=" + msgSignature + ", timestamp=" + timestamp
				+ ", nonce=" + nonce + "]";
	}
}
