package com.example.king.vinamobile.A9_Survey.A9_Survey_Class;

import java.io.Serializable;

public class A9_Cls_Answer  implements Serializable {
    String MaCH, TraLoi, ThoiGian, SDT;

    public A9_Cls_Answer() {
    }

    public A9_Cls_Answer(String maKH, String traLoi, String thoiGian, String SDT) {
        MaCH = maKH;
        TraLoi = traLoi;
        ThoiGian = thoiGian;
        this.SDT = SDT;
    }

    public String getMaCH() {
        return MaCH;
    }

    public void setMaCH(String maKH) {
        MaCH = maKH;
    }

    public String getTraLoi() {
        return TraLoi;
    }

    public void setTraLoi(String traLoi) {
        TraLoi = traLoi;
    }

    public String getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(String thoiGian) {
        ThoiGian = thoiGian;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    @Override
    public String toString() {
        return "A9_Cls_Answer{" +
                "TraLoi='" + TraLoi + '\'' +
                '}';
    }
}
