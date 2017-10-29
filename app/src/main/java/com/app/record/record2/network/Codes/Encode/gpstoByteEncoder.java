package com.app.record.record2.network.Codes.Encode;

import android.util.Log;

import com.app.record.record2.model.GPS;
import com.zgy.message.request.RequestGPSMessage;
import com.zgy.model.business.gps.Z_GPS;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by a4423 on 2017/10/18.
 */

public class gpstoByteEncoder extends MessageToByteEncoder<RequestGPSMessage> {
    private static final String TAG = "gpsToByteEncoder";
    @Override
    protected void encode(ChannelHandlerContext ctx, RequestGPSMessage msg, ByteBuf out) throws Exception {
        Log.i(TAG,"开始编码");
        try{
            Z_GPS z_GPS = (Z_GPS)msg.getMessageBody();
            out.writeInt(msg.getMessageHeader().getMessageType());

            out.writeInt(msg.getMessageLen());
            out.writeShort(msg.getMessageHeader().getMessageSequence());
            out.writeShort(msg.getMessageHeader().getMessageVersion());
            out.writeDouble(z_GPS.getLatitude());
            out.writeDouble(z_GPS.getLongitude());
            out.writeFloat(z_GPS.getAccury());
            out.writeDouble(z_GPS.getAltitude());
            out.writeFloat(z_GPS.getSpeed());
            out.writeFloat(z_GPS.getBear());
            out.writeInt(z_GPS.getLocationType());
            out.writeByte((int)z_GPS.getErrorCode());
            Log.i(TAG,"编码完毕");
        }catch (Exception ex){
            Log.e(TAG,ex.getMessage());
            ex.printStackTrace();
        }

    }
}
