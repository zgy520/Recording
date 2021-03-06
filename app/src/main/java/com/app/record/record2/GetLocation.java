package com.app.record.record2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.app.record.record2.model.GPS;
import com.app.record.record2.network.ClientServer;
import com.app.record.record2.network.ConnectedSuccessHandler;
import com.app.record.record2.network.Handler.gps.gpsInfoHandler;
import com.zgy.model.business.gps.Z_GPS;

import java.util.Date;

import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * Created by a4423 on 2017/10/9.
 * 用于实时的获取当前所在位置
 */

public class GetLocation{
    private static final String TAG = "GetLocation";
    //#1 初始化定位
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回掉监听器
   public AMapLocationListener mLocationListener = null;
    //#2 配置参数并启动定位
    public AMapLocationClientOption mLocationClientOption = null;
    //gps信息处理事件
    private gpsInfoHandler mGpsInfoHandler;
    /**
     * 构造函数
     */
    public GetLocation(Context context){
        mLocationClient = new AMapLocationClient(context);
        mLocationListener = new myAmapLocationListener();
        mLocationClientOption = new AMapLocationClientOption();
        mLocationClient.setLocationListener(mLocationListener);
        mGpsInfoHandler = new gpsInfoHandler();
    }


    /**
     * 设置定位模式
     */
    private void SetLocationMode(){
        //设置定位模式为高精度模式
        mLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationClientOption.setNeedAddress(true);
        //获取一次定位结果
        mLocationClientOption.setInterval(1000);
        mLocationClient.setLocationOption(mLocationClientOption);
    }

    /**
     * 启动定位
     */
    private void StartLocationService(){
        mLocationClient.startLocation();
    }

    /**
     * 停止定位
     */
    public void DestroyLocationService(){
        mLocationClient.stopLocation();  //停止定位
        mLocationClient.onDestroy(); //销毁定位客户端
    }

    /**
     * 启动定位服务
     */
    public void EnableLocation(){
        this.SetLocationMode();
        this.StartLocationService();
    }

    //自定义监听回调器
    private class myAmapLocationListener implements AMapLocationListener{

        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            //Log.i("zgy","onLocationChanged");
            if(aMapLocation != null){
                if (aMapLocation.getErrorCode() == 0){  //0表示定位成功，可在下面进行内容解析
                    Z_GPS curGps = getGpsInfo(aMapLocation);

                    mGpsInfoHandler.setGpsInfo(curGps);
                    try{
                        mGpsInfoHandler.sendMsg();
                    }catch (Exception ex){

                    }


                }else { //定位失败，可通过ErrCode信息来确定失败的原因
                    Log.e("zgy","location Error,ErrCode:"+aMapLocation.getErrorCode()
                    +",errorInfo:"+aMapLocation.getErrorInfo());
                }
            }
        }

        /**
         * 获取GPS信息
         * @param aMapLocation
         * @return
         */
        private Z_GPS getGpsInfo(AMapLocation aMapLocation){
            Z_GPS gpsInfo = new Z_GPS(aMapLocation.getLatitude(),aMapLocation.getLongitude(),aMapLocation.getAccuracy(),
                                    aMapLocation.getAltitude(),aMapLocation.getSpeed(),aMapLocation.getBearing(),aMapLocation.getLocationType(),
                        (byte)aMapLocation.getErrorCode());
            return gpsInfo;
        }
    }
}
