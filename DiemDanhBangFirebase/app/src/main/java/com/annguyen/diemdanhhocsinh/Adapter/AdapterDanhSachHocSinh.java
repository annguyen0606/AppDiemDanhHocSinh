package com.annguyen.diemdanhhocsinh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.annguyen.diemdanhhocsinh.Model.TrangThaiHocSinhDiemDanh;
import com.annguyen.diemdanhhocsinh.R;

import java.util.List;

public class AdapterDanhSachHocSinh extends BaseAdapter {
    private Context context;
    private int layout;
    private List<TrangThaiHocSinhDiemDanh> trangThaiHocSinhDiemDanhs;

    public AdapterDanhSachHocSinh(Context context, int layout, List<TrangThaiHocSinhDiemDanh> trangThaiHocSinhDiemDanhs) {
        this.context = context;
        this.layout = layout;
        this.trangThaiHocSinhDiemDanhs = trangThaiHocSinhDiemDanhs;
    }

    @Override
    public int getCount() {
        return trangThaiHocSinhDiemDanhs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView txtTen;
        TextView dateBirth;
        TextView status;
        ImageView imgStatus;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AdapterDanhSachHocSinh.ViewHolder holder;
        if (convertView == null){
            holder = new AdapterDanhSachHocSinh.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.txtTen = convertView.findViewById(R.id.tvNameStudent);
            holder.dateBirth = convertView.findViewById(R.id.tvDateOfBrithStudent);
            holder.status = convertView.findViewById(R.id.tvStatus);
            holder.imgStatus = convertView.findViewById(R.id.imgStatusGetStudent);
            convertView.setTag(holder);
        }else {
            holder = (AdapterDanhSachHocSinh.ViewHolder) convertView.getTag();
        }
        TrangThaiHocSinhDiemDanh trangThaiHocSinhDiemDanh = trangThaiHocSinhDiemDanhs.get(position);
        holder.txtTen.setText(trangThaiHocSinhDiemDanh.getNameStudent());
        holder.dateBirth.setText(trangThaiHocSinhDiemDanh.getDateOfBirthStudent());
        holder.status.setText(String.valueOf(trangThaiHocSinhDiemDanh.getStatusTakeStudent()));
        if(Integer.parseInt(holder.status.getText().toString().trim()) == 1){
            holder.imgStatus.setImageResource(R.drawable.ic_check);
        }else {
            holder.imgStatus.setImageResource(R.drawable.ic_not_check);
        }
        return convertView;
    }
}
