package com.allen.wx.utils;

import com.alibaba.fastjson.JSONObject;
import com.allen.wx.domain.WxCorpConfig;
import com.tencent.wework.Finance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xuguocai on 2021/5/14 14:24  会话存档接口
 */
public class SessionMessageUtil {

    private final static Logger logger = LoggerFactory.getLogger(SessionMessageUtil.class);
    private static long sdk = 0;
    private static long timeout = 10 * 60 * 1000;

    private static void initSDK (WxCorpConfig corpModel) {
        if (sdk == 0) {
            sdk = Finance.NewSdk();
            Finance.Init(sdk,corpModel.getCorpId(),corpModel.getSessionSecret());
        }
    }

    /**
     * 获取会话记录数据
     * @param corpModel  会话存档相关秘钥信息
     * @param seq  序号
     * @param limit  限制返回数
     * @return
     */
    public static String getChatData(WxCorpConfig corpModel,long seq, long limit) {
        initSDK(corpModel);
        long seqNum = -1;

        long slice = Finance.NewSlice();
        long ret = Finance.GetChatData(sdk, seq, limit, "", "", timeout, slice);
        if (ret != 0) {
            logger.error("getchatdata ret :{}",ret);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("errcode",-1);
            jsonObject.put("errmsg","getchatdata ret :"+ret);
            jsonObject.put("seqNum",seqNum);

            return jsonObject.toJSONString();
        }

        String msg = Finance.GetContentFromSlice(slice);
        logger.info("获取会话记录数据 getchatdata :" + msg);
        Finance.FreeSlice(slice);

        return msg;
    }

    /**
     * 获取媒体数据
     * @param corpModel
     * @param indexbuf
     * @param sdkField
     * @return
     */
    public static boolean getMediaData(WxCorpConfig corpModel, String indexbuf, String sdkField) {
        initSDK(corpModel);
        long slice = Finance.NewMediaData();
        long ret = Finance.GetMediaData(sdk, indexbuf, sdkField, "", "",timeout,slice);
        if (ret != 0) {
            logger.warn("获取媒体数据 " + ret);
        } else {
            logger.info("upload media  "+sdkField);
            byte[] b = Finance.GetData(slice);
            boolean isFinish = Finance.IsMediaDataFinish(slice) > 0;
            String outIndex = Finance.GetOutIndexBuf(slice);

            b = null;
            Finance.FreeMediaData(slice);
            if (isFinish) {
                logger.info("upload media finish "+sdkField);

            } else {
                getMediaData(corpModel,outIndex, sdkField);
            }
            return true;
        }
        return false;
    }

    /**
     * 解密操作
     * @param corpModel
     * @param encrypt_random_key
     * @param encrypt_msg
     * @return
     */
    public static String decryptData(WxCorpConfig corpModel,String encrypt_random_key, String encrypt_msg){
        initSDK(corpModel);

        String encrypt_key = "";
        try {
            encrypt_key = RSAUtil.decryptByPriKey(encrypt_random_key,corpModel.getPrivateKey());
        }catch (Exception e){
            logger.error("解密失败："+e.getMessage(),e);
            return encrypt_key;
        }

        long message = Finance.NewSlice();
        int ret = Finance.DecryptData(sdk,encrypt_key, encrypt_msg, message);
        if (ret != 0) {
            System.out.println("解密失败:" + ret);
            return "";
        }

        String text =  Finance.GetContentFromSlice(message);
        Finance.FreeSlice(message);
        return text;
    }
}
