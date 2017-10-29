package com.app.record.record2.network.Handler.gps;

import android.util.Log;

import com.app.record.record2.model.GPS;
import com.zgy.message.request.RequestGPSMessage;
import com.zgy.model.business.gps.Z_GPS;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Created by a4423 on 2017/10/16.
 */
@ChannelHandler.Sharable
public class gpsInfoHandler extends ChannelInboundHandlerAdapter {
    private static final String TAG ="gpsInfoHandler";
    private Z_GPS gpsInfo;
    private static ChannelHandlerContext mChannelHandlerContext;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx){
        this.mChannelHandlerContext = ctx;
        Log.i(TAG,"channelHandler加入到了处理事件中");
    }

    public void sendMsg() throws Exception{
        Log.i(TAG,"开始发送"+gpsInfo.getLatitude());
        RequestGPSMessage requestGPSMessage = new RequestGPSMessage();
        requestGPSMessage.constructMessageBody(gpsInfo.getLatitude(),gpsInfo.getLongitude(),gpsInfo.getAccury(),
                                                gpsInfo.getAltitude(),gpsInfo.getSpeed(),gpsInfo.getBear(),gpsInfo.getLocationType(),(byte)gpsInfo.getErrorCode());
        mChannelHandlerContext.writeAndFlush(requestGPSMessage);
    }

    public void setGpsInfo(Z_GPS gpsInfo) {
        this.gpsInfo = gpsInfo;
    }

}
