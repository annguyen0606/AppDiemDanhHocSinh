package com.annguyen.diemdanhhocsinh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.annguyen.diemdanhhocsinh.Adapter.AdapterDanhSachHocSinh;
import com.annguyen.diemdanhhocsinh.Model.DuLieuDiemDanh;
import com.annguyen.diemdanhhocsinh.Model.ThongTinThe;
import com.annguyen.diemdanhhocsinh.Model.TrangThaiHocSinhDiemDanh;
import com.annguyen.diemdanhhocsinh.Service.FirebaseServiceConek;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static DatabaseReference mData;
    ListView listViewHocSinh;
    SwipeRefreshLayout refreshLayout;
    public static AdapterDanhSachHocSinh adapterDanhSachHocSinh;
    public static ArrayList<TrangThaiHocSinhDiemDanh> arrayHocSinh;
    ArrayList<ThongTinThe> arrayThongTinThe;
    ArrayList<DuLieuDiemDanh> arrayDuLieuDiemDanh;
    private Intent intent = null;
    SimpleDateFormat dateFormat;
    String dateCurrent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mData = FirebaseDatabase.getInstance().getReference();
        AnhXa();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateCurrent = dateFormat.format(new Date());

        //Toast.makeText(MainActivity.this,dateCurrent,Toast.LENGTH_SHORT).show();
        /*intent = new Intent(this, FirebaseServiceConek.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);
        }else {
            startService(intent);
        }*/
    }

    private void AnhXa() {
        arrayHocSinh = new ArrayList<>();
        arrayThongTinThe = new ArrayList<>();
        arrayDuLieuDiemDanh = new ArrayList<>();
        refreshLayout = findViewById(R.id.pullResetDataStudent);
        listViewHocSinh = findViewById(R.id.lvDanhSachHocSinh);
        adapterDanhSachHocSinh = new AdapterDanhSachHocSinh(MainActivity.this,R.layout.dong_du_lieu_hoc_sinh,arrayHocSinh);
        listViewHocSinh.setAdapter(adapterDanhSachHocSinh);

        mData.child("Lop1A").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //arrayHocSinh = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String[] abc = snapshot.getValue().toString().split(",");
                    TrangThaiHocSinhDiemDanh trangThaiHocSinhDiemDanh = new TrangThaiHocSinhDiemDanh(" "," ",1," ");
                    trangThaiHocSinhDiemDanh.setNameStudent(abc[0]);
                    trangThaiHocSinhDiemDanh.setDateOfBirthStudent(abc[1]);
                    trangThaiHocSinhDiemDanh.setStatusTakeStudent(0);
                    trangThaiHocSinhDiemDanh.setMaUID(snapshot.getKey());
                    arrayHocSinh.add(trangThaiHocSinhDiemDanh);
                }
                adapterDanhSachHocSinh.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mData.child("DuLieuDiemDanh").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String[] abc = snapshot.getValue().toString().split(",");
                    DuLieuDiemDanh duLieuDiemDanh = new DuLieuDiemDanh(" "," "," ");
                    duLieuDiemDanh.setMaUID(abc[0]);
                    duLieuDiemDanh.setNgayDiemDanh(abc[1]);
                    duLieuDiemDanh.setThoiGianDiemDanh(abc[2]);
                    arrayDuLieuDiemDanh.add(duLieuDiemDanh);
                    for (int i = 0; i < arrayHocSinh.size(); i++){
                        if(arrayHocSinh.get(i).getMaUID().equals(abc[0]) && abc[1].equals(dateCurrent)){
                            //Toast.makeText(MainActivity.this,"Thanks",Toast.LENGTH_SHORT).show();
                            arrayHocSinh.get(i).setStatusTakeStudent(1);
                        }
                    }
                }
                //Toast.makeText(MainActivity.this,String.valueOf(arrayHocSinh.size()),Toast.LENGTH_SHORT).show();
                adapterDanhSachHocSinh.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /*arrayThongTinThe = new ArrayList<>();
                arrayHocSinh = new ArrayList<>();*/
                LoadDataFromFirebase();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                },1000);
            }
        });
    }

    private void LoadDataFromFirebase(){
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("hh:mm:ss");
        String timeCurrent = dateFormat1.format(new Date());
        mData.child("DuLieuDiemDanh").child("Test").setValue("ANC,06-06-1996,"+timeCurrent);
    }
    @Override
    public void onClick(View v) {

    }
}
