package com.app.record.record2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.app.record.record2.network.ClientServer;
import com.app.record.record2.service.gpsService;

public class MainActivity extends Activity {

    MapView mMapView = null;
    AMap mAMap = null;
    MyLocationStyle myLocationStyle = null;
    AMapLocationClient mLocationClient = null; //声明AMapLocationClient对象
    AMapLocationListener mapLocationListener = null;  //定位回掉监听器
    AMapLocationClientOption mapLocationClientOption = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(()->{
            startService(new Intent(getBaseContext(),gpsService.class));  //开启服务
        }).start();

        try {
           //new ClientServer().connect();  //开始连接netty
        } catch (Exception e) {
            e.printStackTrace();
        }
        //GetLocation mGetLocation = new GetLocation(getApplicationContext());
        //mGetLocation.EnableLocation();
    }
    private void initMapInfo(Bundle savedInstanceState){
        mMapView = (MapView)findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        if (mAMap==null){
            mAMap = mMapView.getMap();
            myLocationStyle = new MyLocationStyle();
            //myLocationStyle.interval(2000);
            mAMap.setMyLocationStyle(myLocationStyle);
            mAMap.setMyLocationEnabled(true);
            //mAMap.getUiSettings().setMyLocationButtonEnabled(true);
        }
        mLocationClient = new AMapLocationClient(getApplicationContext()); //初始化定位
        mapLocationClientOption = new AMapLocationClientOption();
        mapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mapLocationClientOption.setInterval(1000);
        mapLocationClientOption.setNeedAddress(true);
        mapLocationClientOption.setMockEnable(true);
        mapLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation!=null){
                    if (aMapLocation.getErrorCode() == 0){
                        //可在其解析获取相应内容
                        Log.i("zgy","获取到地理位置为:"+aMapLocation.getCountry()+","+aMapLocation.getProvince()+","
                                +aMapLocation.getCity()+","+aMapLocation.getDistrict()+","+aMapLocation.getStreet());
                    }else {
                        Log.e("zgy","location Error,ErrorCode:"+aMapLocation.getErrorCode()+
                                ",ErrorInfo:"+aMapLocation.getErrorInfo());
                    }
                }
            }
        };
        mLocationClient.setLocationListener(mapLocationListener);
        mLocationClient.setLocationOption(mapLocationClientOption);
        mLocationClient.startLocation();
    }
    @Override
    protected void onResume(){
        super.onResume();
        //mMapView.onResume();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        //mMapView.onDestroy();
    }
    @Override
    protected void onPause(){
        super.onPause();
        //mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        //mMapView.onSaveInstanceState(outState);
    }
}
