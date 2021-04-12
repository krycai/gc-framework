package com.allen.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by xuguocai on 2021/4/12 16:30 客户端处理器
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<String> {

//    @Override
    public void channelRead(ChannelHandlerContext ctx, String msg) throws Exception {
        super.channelRead(ctx, msg);
        System.out.println("收到服务端消息: " + msg);
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("---------");
    }
}
