package com.app.record.record2.network.Handler.gps;

import android.util.Log;

import com.app.record.record2.model.GPS;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Created by a4423 on 2017/10/16.
 */
@ChannelHandler.Sharable
public class gpsInfoHandler extends ChannelInboundHandlerAdapter {
    private GPS gpsInfo;
    public static ChannelHandlerContext mChannelHandlerContext;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx){
        mChannelHandlerContext = ctx;
        Log.i("zgy","mChannelHandlerContext.id="+mChannelHandlerContext.channel().id());
    }

    public void channelActive(){

        mChannelHandlerContext.writeAndFlush(Unpooled.copiedBuffer("dd",CharsetUtil.UTF_8))
                        .addListener(ChannelFutureListener.CLOSE);
    }

    public void setGpsInfo(GPS gpsInfo) {
        this.gpsInfo = gpsInfo;
    }

}
