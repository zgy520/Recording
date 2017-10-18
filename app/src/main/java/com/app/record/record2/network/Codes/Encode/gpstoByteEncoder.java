package com.app.record.record2.network.Codes.Encode;

import android.util.Log;

import com.app.record.record2.model.GPS;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by a4423 on 2017/10/18.
 */

public class gpstoByteEncoder extends MessageToByteEncoder<GPS> {
    private static final String TAG = "gpsToByteEncoder";
    @Override
    protected void encode(ChannelHandlerContext ctx, GPS msg, ByteBuf out) throws Exception {
        Log.i(TAG,"开始编码"+msg.getLongitude());
        try{
            /*out.writeInt(msg.getLocationType());
            out.writeDouble(msg.getLatitude());
            out.writeDouble(msg.getLongitude());
            out.writeFloat(msg.getAccuracy());*/
            out.writeInt(10);
            /*
            out.writeBytes(msg.getAddress().getBytes(Charset.forName("UTF-8")));
            out.writeBytes(msg.getCountry().getBytes(Charset.forName("UTF-8")));
            out.writeBytes(msg.getProvince().getBytes(Charset.forName("UTF-8")));
            out.writeBytes(msg.getCity().getBytes(Charset.forName("UTF-8")));
            out.writeBytes(msg.getDistrict().getBytes(Charset.forName("UTF-8")));
            out.writeBytes(msg.getStreet().getBytes(Charset.forName("UTF-8")));
            out.writeBytes(msg.getStreetNum().getBytes(Charset.forName("UTF-8")));
            out.writeBytes(msg.getAdCode().getBytes(Charset.forName("UTF-8")));
            out.writeBytes(msg.getAoiName().getBytes(Charset.forName("UTF-8")));
            out.writeBytes(msg.getBuildingId().getBytes(Charset.forName("UTF-8")));
            out.writeBytes(msg.getFloor().getBytes(Charset.forName("UTF-8")));
            out.writeInt(msg.getGpsStatus());
            out.writeBytes(msg.getLocationDetail().getBytes(Charset.forName("UTF-8")));

            out.writeBytes(msg.getErrorInfo().getBytes(Charset.forName("UTF-8")));
            out.writeInt(msg.getErrorCode());*/
            Log.i(TAG,"编码完毕");
        }catch (Exception ex){
            Log.e(TAG,ex.getMessage());
            ex.printStackTrace();
        }

    }
}
