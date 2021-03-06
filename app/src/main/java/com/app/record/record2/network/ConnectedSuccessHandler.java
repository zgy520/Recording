package com.app.record.record2.network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.app.record.record2.model.GPS;
import com.zgy.message.request.RequestGPSMessage;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * Created by a4423 on 2017/10/15.
 */
@ChannelHandler.Sharable
public class ConnectedSuccessHandler extends ChannelInboundHandlerAdapter {
    private static final String TAG = "ConnectedSuccessHandler";

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Log.i("zgy","收到的消息为:"+((ByteBuf)msg).toString(CharsetUtil.UTF_8));
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx)throws Exception{
       Log.i(TAG,"与服务器之间的通道建立并激活了");
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx)throws Exception{
        Log.i(TAG,"连接断开了,进行重新连接");
        new ClientServer().connect();
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        Log.i(TAG,"出现异常");
        cause.printStackTrace();
        ctx.close();
    }
}
