package com.annguyen.diemdanhhocsinh.Controller;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.annguyen.diemdanhhocsinh.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class FirebaseManager extends BroadcastReceiver {
    static public String BOARD_CAST_NAME = "FirebaseManager";
    static public String NOTIFY_ID = "BOARD_CAST_NAME";
    int idNotify = 2;
    private Context mcontext;
    private FirebaseThread firebaseThread;
    static Ringtone ringtone;

    @Override
    public void onReceive(Context context, Intent intent) {
        mcontext = context;
        MainActivity.mData.child("DuLieuDiemDanh").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Intent i = new Intent(mcontext, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(mcontext,
                        0, i, PendingIntent.FLAG_UPDATE_CURRENT);
                NotifiManager.callNotify(mcontext,pendingIntent,idNotify++,NOTIFY_ID,"Có tài khoản tương tác", String.valueOf(MainActivity.arrayHocSinh.size()));
                Toast.makeText(mcontext,dataSnapshot.getValue().toString(),Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        firebaseThread = new FirebaseThread();
        firebaseThread.start();
        NotifiManager.createChanel(context,NOTIFY_ID,"Co Data", NotificationManager.IMPORTANCE_DEFAULT);
    }

    static public void startMe(Context context){
        Intent intent = new Intent();
        intent.setAction(BOARD_CAST_NAME);
        context.sendBroadcast(intent);
    }

    private class FirebaseThread extends Thread{
        @Override
        public void run() {

        }
    }
}
