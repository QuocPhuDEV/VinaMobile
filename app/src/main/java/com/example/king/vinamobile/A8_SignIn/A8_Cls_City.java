package com.example.king.vinamobile.A8_SignIn;

import java.io.Serializable;

public class A8_Cls_City implements Serializable {
    private String KhuVuc;
    private String TinhTP;
    private String QuanHuyen;

    public A8_Cls_City() {
    }

    public A8_Cls_City(String khuVuc, String tinhTP, String quanHuyen) {
        KhuVuc = khuVuc;
        TinhTP = tinhTP;
        QuanHuyen = quanHuyen;
    }

    public String getKhuVuc() {
        return KhuVuc;
    }

    public void setKhuVuc(String khuVuc) {
        KhuVuc = khuVuc;
    }

    public String getTinhTP() {
        return TinhTP;
    }

    public void setTinhTP(String tinhTP) {
        TinhTP = tinhTP;
    }

    public String getQuanHuyen() {
        return QuanHuyen;
    }

    public void setQuanHuyen(String quanHuyen) {
        QuanHuyen = quanHuyen;
    }

    @Override
    public String toString() {
        return KhuVuc + TinhTP + QuanHuyen;
    }

}
