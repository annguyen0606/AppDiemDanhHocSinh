package com.annguyen.diemdanhhocsinh.Model;

public class DuLieuDiemDanh {
    String maUID;
    String ngayDiemDanh;
    String thoiGianDiemDanh;

    public String getMaUID() {
        return maUID;
    }

    public void setMaUID(String maUID) {
        this.maUID = maUID;
    }

    public String getNgayDiemDanh() {
        return ngayDiemDanh;
    }

    public void setNgayDiemDanh(String ngayDiemDanh) {
        this.ngayDiemDanh = ngayDiemDanh;
    }

    public String getThoiGianDiemDanh() {
        return thoiGianDiemDanh;
    }

    public void setThoiGianDiemDanh(String thoiGianDiemDanh) {
        this.thoiGianDiemDanh = thoiGianDiemDanh;
    }

    public DuLieuDiemDanh(String maUID, String ngayDiemDanh, String thoiGianDiemDanh) {
        this.maUID = maUID;
        this.ngayDiemDanh = ngayDiemDanh;
        this.thoiGianDiemDanh = thoiGianDiemDanh;
    }
}
