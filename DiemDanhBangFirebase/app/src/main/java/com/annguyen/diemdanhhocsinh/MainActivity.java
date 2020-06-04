package com.annguyen.diemdanhhocsinh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.annguyen.diemdanhhocsinh.Adapter.AdapterDanhSachHocSinh;
import com.annguyen.diemdanhhocsinh.Model.ThongTinThe;
import com.annguyen.diemdanhhocsinh.Model.TrangThaiHocSinhDiemDanh;
import com.annguyen.diemdanhhocsinh.Service.FirebaseServiceConek;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static DatabaseReference mData;
    ListView listViewHocSinh;
    SwipeRefreshLayout refreshLayout;
    public static AdapterDanhSachHocSinh adapterDanhSachHocSinh;
    public static ArrayList<TrangThaiHocSinhDiemDanh> arrayHocSinh;
    ArrayList<ThongTinThe> arrayThongTinThe;
    private Intent intent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mData = FirebaseDatabase.getInstance().getReference();
        AnhXa();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        intent = new Intent(this, FirebaseServiceConek.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);
        }else {
            startService(intent);
        }
    }

    private void AnhXa() {
        arrayHocSinh = new ArrayList<>();
        arrayThongTinThe = new ArrayList<>();
        refreshLayout = findViewById(R.id.pullResetDataStudent);
        listViewHocSinh = findViewById(R.id.lvDanhSachHocSinh);
        adapterDanhSachHocSinh = new AdapterDanhSachHocSinh(MainActivity.this,R.layout.dong_du_lieu_hoc_sinh,arrayHocSinh);
        listViewHocSinh.setAdapter(adapterDanhSachHocSinh);

        mData.child("Lop1A").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String[] abc = snapshot.getValue().toString().split(",");
                    TrangThaiHocSinhDiemDanh trangThaiHocSinhDiemDanh = new TrangThaiHocSinhDiemDanh(" "," ",1);
                    trangThaiHocSinhDiemDanh.setNameStudent(abc[0]);
                    trangThaiHocSinhDiemDanh.setDateOfBirthStudent(abc[1]);
                    trangThaiHocSinhDiemDanh.setStatusTakeStudent(1);
                    arrayHocSinh.add(trangThaiHocSinhDiemDanh);
                }
                adapterDanhSachHocSinh.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                arrayThongTinThe = new ArrayList<>();
                arrayHocSinh = new ArrayList<>();
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

        //Toast.makeText(MainActivity.this,arrayHocSinh.get(0).getNameStudent().toString(),Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View v) {

    }
}
