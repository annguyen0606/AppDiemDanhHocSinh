package com.annguyen.diemdanhhocsinh.Model;

public class TrangThaiHocSinhDiemDanh {
    private String nameStudent;
    private String dateOfBirthStudent;
    int statusTakeStudent;

    public TrangThaiHocSinhDiemDanh(String nameStudent, String dateOfBirthStudent, int statusTakeStudent) {
        this.nameStudent = nameStudent;
        this.dateOfBirthStudent = dateOfBirthStudent;
        this.statusTakeStudent = statusTakeStudent;
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
}
