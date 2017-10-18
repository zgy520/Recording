package com.app.record.record2.network.Handler.gps;

import android.util.Log;

import com.app.record.record2.model.GPS;

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
public class gpsInfoHandler extends ChannelHandlerAdapter {
    private static final String TAG ="gpsInfoHandler";
    private GPS gpsInfo;
    private static ChannelHandlerContext mChannelHandlerContext;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx){
        this.mChannelHandlerContext = ctx;
        Log.i(TAG,"channelHandler加入到了处理事件中");
    }

    public void sendMsg(){
        mChannelHandlerContext.writeAndFlush(gpsInfo).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                Log.i(TAG,"发送成功");
            }
        });
        //Log.i(TAG,"获取到的gps:"+gpsInfo.getLatitude()+","+gpsInfo.getLongitude()+",status="+mChannelHandlerContext.channel().isOpen());
    }


    public void setGpsInfo(GPS gpsInfo) {
        this.gpsInfo = gpsInfo;
    }


}
