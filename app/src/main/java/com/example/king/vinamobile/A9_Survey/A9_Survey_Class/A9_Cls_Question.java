package com.example.king.vinamobile.A9_Survey.A9_Survey_Class;

import java.io.Serializable;

public class A9_Cls_Question implements Serializable {
    private String MaCH, CauHoi, LoaiCH;

    public A9_Cls_Question() {
    }

    public A9_Cls_Question(String maCH, String cauHoi, String loaiCH) {
        MaCH = maCH;
        CauHoi = cauHoi;
        LoaiCH = loaiCH;
    }

    public String getMaCH() {
        return MaCH;
    }

    public void setMaCH(String maCH) {
        MaCH = maCH;
    }

    public String getCauHoi() {
        return CauHoi;
    }

    public void setCauHoi(String cauHoi) {
        CauHoi = cauHoi;
    }

    public String getLoaiCH() {
        return LoaiCH;
    }

    public void setLoaiCH(String loaiCH) {
        LoaiCH = loaiCH;
    }

    @Override
    public String toString() {
        return CauHoi + LoaiCH;
    }
}
