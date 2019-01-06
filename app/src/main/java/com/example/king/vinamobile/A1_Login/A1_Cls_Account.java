package com.example.king.vinamobile.A1_Login;

import java.io.Serializable;

public class A1_Cls_Account implements Serializable {
    private String Ngaytao;
    private String NgayCN;
    private String SDT;
    private String MatKhau;
    private String MaKH;
    private String TenKH;
    private String DiaChi;

    public A1_Cls_Account() {
    }

    public A1_Cls_Account(String ngaytao, String ngayCN, String SDT, String matKhau, String maKH, String tenKH, String diaChi) {
        Ngaytao = ngaytao;
        NgayCN = ngayCN;
        this.SDT = SDT;
        MatKhau = matKhau;
        MaKH = maKH;
        TenKH = tenKH;
        DiaChi = diaChi;
    }

    public String getNgaytao() {
        return Ngaytao;
    }

    public String getNgayCN() {
        return NgayCN;
    }

    public String getSDT() {
        return SDT;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public String getMaKH() {
        return MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    @Override
    public String toString() {
        return "A1_Cls_Account{" +
                "Ngaytao='" + Ngaytao + '\'' +
                ", NgayCN='" + NgayCN + '\'' +
                ", SDT='" + SDT + '\'' +
                ", MatKhau='" + MatKhau + '\'' +
                ", MaKH='" + MaKH + '\'' +
                ", TenKH='" + TenKH + '\'' +
                ", DiaChi='" + DiaChi + '\'' +
                '}';
    }
}
