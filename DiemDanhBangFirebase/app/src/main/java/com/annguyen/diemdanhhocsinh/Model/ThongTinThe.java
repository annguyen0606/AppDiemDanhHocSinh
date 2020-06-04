package com.annguyen.diemdanhhocsinh.Model;

public class ThongTinThe {
    String maUID;
    String inforBasicStudent;

    public ThongTinThe(String maUID, String inforBasicStudent) {
        this.maUID = maUID;
        this.inforBasicStudent = inforBasicStudent;
    }
    public ThongTinThe() {

    }
    public String getMaUID() {
        return maUID;
    }

    public void setMaUID(String maUID) {
        this.maUID = maUID;
    }

    public String getInforBasicStudent() {
        return inforBasicStudent;
    }

    public void setInforBasicStudent(String inforBasicStudent) {
        this.inforBasicStudent = inforBasicStudent;
    }
}
