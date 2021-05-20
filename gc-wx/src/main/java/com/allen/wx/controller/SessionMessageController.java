package com.allen.wx.controller;

import com.allen.wx.domain.WxCorpConfig;
import com.allen.wx.utils.SessionMessageUtil;

/**
 * @author xuguocai on 2021/5/20 15:13  会话存档简单示例
 */
public class SessionMessageController {

    public static void main(String[] args) {
        SessionMessageController controller = new SessionMessageController();
        controller.getChatData();
    }

    private void getChatData(){
        WxCorpConfig corpModel = new WxCorpConfig();

        String chatData = SessionMessageUtil.getChatData(corpModel, 0, 10);
        System.out.println(chatData);


        // todo  key /value 来源 chatdata 的数组里面的元素
        String s = SessionMessageUtil.decryptData(corpModel, "key", "value");
        System.out.println(s);
    }



}
