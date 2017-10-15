package com.app.record.record2.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.app.record.record2.GetLocation;

/**
 * Created by a4423 on 2017/9/27.
 */

public class gpsService extends Service {
    GetLocation mGetLocation = null;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        mGetLocation = new GetLocation(getApplicationContext());
        mGetLocation.EnableLocation();

        return START_STICKY;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        mGetLocation.DestroyLocationService();
    }
}
