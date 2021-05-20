//package com.allen.wx.controller;
//
//
//
//import com.thoughtworks.xstream.XStream;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
///**
// * @author xuguocai on 2021/5/20 11:20
// */
//@CrossOrigin
//@RestController
//@RequestMapping("/weixinopen/qyevent")
//public class QyExternalContactsController {
//
//    private static Logger log = LogManager.getLogger("ASYNFILE");
//
//    @Autowired
//    private ToolsKafkaProducer toolsKafkaProducer;
//
//    @Value("${qy.externalUser.token}")
//    private String qyToken;
//
//    @Value("${qy.externalUser.encodingAesKey}")
//    private String qyEncodingAesKey;
//
//    /**
//     * 基于 xstream ，效果最优
//     * @param request
//     * @param response
//     * @param corpId
//     * @param postData
//     * @return
//     */
//    @PostMapping(value = "/callback/external-user/{corpId}")
//    public String callbackExternalUserMsgXStream(HttpServletRequest request, HttpServletResponse response,@PathVariable String corpId, @RequestBody String postData){
//        String encodingAesKey = qyEncodingAesKey;
//        String token = qyToken;
//        String nonce = request.getParameter("nonce");
//        String timestamp = request.getParameter("timestamp");
//        String msgSignature = request.getParameter("msg_signature");
//        log.info("外部联系人事件消息通知，参数:corpId:{},nonce:{},timestamp:{},msgSignature:{},postData:\n{}",corpId,nonce,timestamp,msgSignature,postData);
//
//        try {
//            XStream xstream = XStreamUtils.create(QyCustVo.class);
//            QyCustVo msgVo = (QyCustVo) xstream.fromXML(postData);
//            msgVo.setXmlStr(postData);
//
//            if (StringUtil.isNotEmpty(msgVo.getEncrypt())) {
//                WxAESInfo wxcpt = new WxAESInfo(corpId, encodingAesKey, token);
//                String result = WxAESUtil.decryptMsg(wxcpt, msgVo.getEncrypt(), msgSignature, timestamp, nonce);
//                msgVo.setXmlStr(result);
//            }
//            if (StringUtil.isNotBlank(msgVo.getXmlStr())){
//                String topic = "qywxopen-externalUserMsg";
//                toolsKafkaProducer.send(topic, msgVo.getXmlStr());
//            }
//            return "success";
//        }catch (Exception e){
//            log.error("外部联系人变更回调失败"+e.getMessage(),e);
//            return "error";
//        }
//    }
//
//    /**
//     * 外部联系人URL路径校验
//     * @param request
//     * @param response
//     * @throws IOException
//     */
//    @RequestMapping(value = "/callback/external-user/{corpId}", method = RequestMethod.GET)
//    public void callbackExternalUser(HttpServletRequest request, HttpServletResponse response,@PathVariable String corpId) throws IOException {
//        String encodingAesKey =qyEncodingAesKey.trim();
//        String token = qyToken.trim();
//
//        String nonce = request.getParameter("nonce").trim();
//        String timestamp = request.getParameter("timestamp").trim();
//        String msgSignature = request.getParameter("msg_signature").trim();
//        String echostr = request.getParameter("echostr").trim();
//        echostr = echostr.replace(" ", "+");
//        log.info("企业微信外部联系人回调路径验证，参数corpId：{},nonce:{},timestamp:{},msgSignature:{},echostr:{}",corpId,nonce,timestamp,msgSignature,echostr);
//
//        String result = null;
//        PrintWriter printWriter = response.getWriter();
//        try {
//            WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(token,encodingAesKey,corpId);
//            result = wxBizMsgCrypt.verifyUrl(msgSignature,timestamp,nonce,echostr);
//        }catch (Exception e){
//            log.error("外部联系人回调验证地址失败"+e.getMessage(),e);
//        }
//
//        if (StringUtil.isEmpty(result)){
//            result = "success";
//        }
//        printWriter.print(result);
//        printWriter.close();
//    }
//
//}
