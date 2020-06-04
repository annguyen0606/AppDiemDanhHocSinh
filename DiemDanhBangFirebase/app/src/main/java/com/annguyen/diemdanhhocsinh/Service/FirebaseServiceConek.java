package com.annguyen.diemdanhhocsinh.Service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.annguyen.diemdanhhocsinh.Controller.FirebaseManager;
import com.annguyen.diemdanhhocsinh.Controller.NotifiManager;
import com.annguyen.diemdanhhocsinh.MainActivity;

public class FirebaseServiceConek extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver(new FirebaseManager(),new IntentFilter(FirebaseManager.BOARD_CAST_NAME));
        FirebaseManager.startMe(this);
    }
    private void initFore() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        NotifiManager.createChanel(this,"MainService","main_service", NotificationManager.IMPORTANCE_HIGH);
        startForeground(1,NotifiManager.callNotify4MainService(this,pendingIntent,"MainService","Trường mầm non","Đang chạy"));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initFore();
        return START_NOT_STICKY;
    }
}
