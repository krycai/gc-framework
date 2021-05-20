package com.allen.wx.utils;

import com.allen.wx.aes.WXBizMsgCrypt;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

/**
 * @author xuguocai on 2021/5/20 11:20  微信消息体加解密工具
 */
public class WxAESUtil {

	private static String encryptNonce = "4eqdgb7O";
	
	public static String encryptMsg(WxAESInfo info, String replyMsg) throws Exception{
		WXBizMsgCrypt pc = new WXBizMsgCrypt(info.getToken(), info.getEncodingAesKey(), info.getAppId());
		String miwen = pc.encryptMsg(replyMsg, "", encryptNonce);
		return miwen;
	}
	
	public static WxAESMsg encryptMsgToBean(WxAESInfo info, String replyMsg) throws Exception{
		String miwen = encryptMsg(info, replyMsg);
		//将xml转化为对象
		JAXBContext context = JAXBContext.newInstance(WxAESMsg.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		WxAESMsg msg = (WxAESMsg) unmarshaller.unmarshal(new StringReader(miwen));
		return msg;
	}
	
	public static String decryptMsg(WxAESInfo info, String encrypt, String msgSignature, String timestamp, String nonce) throws Exception{
		String format = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
		String fromXML = String.format(format, encrypt);
		WXBizMsgCrypt pc = new WXBizMsgCrypt(info.getToken(), info.getEncodingAesKey(), info.getAppId());
		String mingwen = pc.decryptMsg(msgSignature, timestamp, nonce, fromXML);
		return mingwen;
	}
	
	public static void main(String[] args) {
		WxAESInfo info = new WxAESInfo("wx81dd66c5ec721949", "4eqdgb7Or0saM1fDMYmslqrRLSCddsYLUkxWwenbAVB", "bluemoon2018");
		try {
			WxAESMsg vo = WxAESUtil.encryptMsgToBean(info, "<xml><AppId> </AppId><CreateTime>1413192605 </CreateTime><InfoType> </InfoType><ComponentVerifyTicket> </ComponentVerifyTicket></xml>");
			System.out.println(vo.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
