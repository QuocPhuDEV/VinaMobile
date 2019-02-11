package com.example.king.vinamobile.A8_SignIn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class A8_DBHelper extends SQLiteOpenHelper {
    //region KHAI BÁO BIẾN TOÀN CỤC
    // Phiên bản SQLite
    private static final int DATABASE_VERSION = 1;

    // Tên CSDL
    private static final String DATABASE_NAME = "DB_VinaMobile";

    // Tên bảng
    private static final String TABLE_NAME_ZONE = "TBM_KHUVUC";

    // các cột
    //BẢNG TBT_CITY
    private static final String TBM_ZONE_KHUVUC = "KhuVuc";
    private static final String TBM_ZONE_TINHTP = "TinhTP";
    private static final String TBM_ZONE_QUANHUYEN = "QuanHuyen";

    //endregion

    public A8_DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    //region THÊM DỮ LIỆU MẶC ĐỊNH CHO BẢNG

    // Function thêm dữ liệu
    public void addValueCity(A8_Cls_City a8_cls_city) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TBM_ZONE_KHUVUC, a8_cls_city.getKhuVuc());
        values.put(TBM_ZONE_TINHTP, a8_cls_city.getTinhTP());
        values.put(TBM_ZONE_QUANHUYEN, a8_cls_city.getQuanHuyen());

        // Thêm dữ liệu
        database.insert(TABLE_NAME_ZONE, null, values);

        // đóng kết nối
        database.close();
    }

    // Fuction kiểm tra secord trong bảng
    public int getCountRecord() {
        String countQuery = "SELECT * FROM " + TABLE_NAME_ZONE;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery(countQuery, null);
        int count = cursor.getCount();

        cursor.close();

        return count;
    }

    // thêm dữ liệu mặc định cho table city
    public void createDefaultData() {

        int count = this.getCountRecord();
        if (count == 0) {
            // TÂY BẮC BỘ
            A8_Cls_City dienbien1= new A8_Cls_City("Tây Bắc Bộ", "Điện Biên","Tuần Giáo");
            A8_Cls_City dienbien2= new A8_Cls_City("Tây Bắc Bộ", "Điện Biên","Tủa Chùa");
            A8_Cls_City dienbien3= new A8_Cls_City("Tây Bắc Bộ", "Điện Biên","Nậm Pồ");
            A8_Cls_City dienbien4= new A8_Cls_City("Tây Bắc Bộ", "Điện Biên","Mường Nhé");
            A8_Cls_City dienbien5= new A8_Cls_City("Tây Bắc Bộ", "Điện Biên","Mường Lay");
            A8_Cls_City dienbien6= new A8_Cls_City("Tây Bắc Bộ", "Điện Biên","Mường Chà");
            A8_Cls_City dienbien7= new A8_Cls_City("Tây Bắc Bộ", "Điện Biên","Mường Ảng");
            A8_Cls_City dienbien8= new A8_Cls_City("Tây Bắc Bộ", "Điện Biên","Điện Biên Phủ");
            A8_Cls_City dienbien9 = new A8_Cls_City("Tây Bắc Bộ", "Điện Biên","Điện Biên Đông");
            A8_Cls_City dienbien10 = new A8_Cls_City("Tây Bắc Bộ", "Điện Biên","TP.Điện Biên");

            A8_Cls_City hoabinh1 = new A8_Cls_City("Tây Bắc Bộ", "Hòa Bình","Yên Thủy");
            A8_Cls_City hoabinh2 = new A8_Cls_City("Tây Bắc Bộ", "Hòa Bình","Tân Lạc");
            A8_Cls_City hoabinh3 = new A8_Cls_City("Tây Bắc Bộ", "Hòa Bình","Mai Châu");
            A8_Cls_City hoabinh4 = new A8_Cls_City("Tây Bắc Bộ", "Hòa Bình","Lương Sơn");
            A8_Cls_City hoabinh5 = new A8_Cls_City("Tây Bắc Bộ", "Hòa Bình","Lạc Thủy");
            A8_Cls_City hoabinh6 = new A8_Cls_City("Tây Bắc Bộ", "Hòa Bình","Lạc Sơn");
            A8_Cls_City hoabinh7 = new A8_Cls_City("Tây Bắc Bộ", "Hòa Bình","Kỳ Sơn");
            A8_Cls_City hoabinh8 = new A8_Cls_City("Tây Bắc Bộ", "Hòa Bình","Kim Bôi");
            A8_Cls_City hoabinh9 = new A8_Cls_City("Tây Bắc Bộ", "Hòa Bình","Hoà Bình");
            A8_Cls_City hoabinh10 = new A8_Cls_City("Tây Bắc Bộ", "Hòa Bình","Đà Bắc");
            A8_Cls_City hoabinh11 = new A8_Cls_City("Tây Bắc Bộ", "Hòa Bình","Cao Phong");


        }

    }
    //endregion
}
