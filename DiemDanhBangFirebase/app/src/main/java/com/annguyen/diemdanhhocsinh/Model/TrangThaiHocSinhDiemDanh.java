package com.annguyen.diemdanhhocsinh.Model;

public class TrangThaiHocSinhDiemDanh {
    private String nameStudent;
    private String dateOfBirthStudent;
    int statusTakeStudent;
    private String maUID;

    public TrangThaiHocSinhDiemDanh(String nameStudent, String dateOfBirthStudent, int statusTakeStudent, String maUID) {
        this.nameStudent = nameStudent;
        this.dateOfBirthStudent = dateOfBirthStudent;
        this.statusTakeStudent = statusTakeStudent;
        this.maUID = maUID;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getDateOfBirthStudent() {
        return dateOfBirthStudent;
    }

    public void setDateOfBirthStudent(String dateOfBirthStudent) {
        this.dateOfBirthStudent = dateOfBirthStudent;
    }

    public int getStatusTakeStudent() {
        return statusTakeStudent;
    }

    public void setStatusTakeStudent(int statusTakeStudent) {
        this.statusTakeStudent = statusTakeStudent;
    }

    public String getMaUID() {
        return maUID;
    }

    public void setMaUID(String maUID) {
        this.maUID = maUID;
    }
}
