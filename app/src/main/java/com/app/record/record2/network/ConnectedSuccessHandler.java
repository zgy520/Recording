package com.app.record.record2.network;

import android.util.Log;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * Created by a4423 on 2017/10/15.
 */
@ChannelHandler.Sharable
public class ConnectedSuccessHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private static final String TAG = "ConnectedSuccessHandler";

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        Log.i("zgy","收到的消息为:"+((ByteBuf)msg).toString(CharsetUtil.UTF_8));
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx){
        Log.i("zgy","与服务器连接成功"+ctx.channel().id());
        ctx.writeAndFlush(Unpooled.copiedBuffer("连接成功后发送的消息", CharsetUtil.UTF_8));

    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        Log.i("zgy","出现异常");
        cause.printStackTrace();
        ctx.close();
    }
}
