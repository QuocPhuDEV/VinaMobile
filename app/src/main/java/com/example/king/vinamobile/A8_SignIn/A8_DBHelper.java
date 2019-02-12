package com.example.king.vinamobile.A8_SignIn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.king.vinamobile.R;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

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
            A8_Cls_City dienbien1 = new A8_Cls_City("Tây Bắc Bộ", "Điện Biên", "Tuần Giáo");
            A8_Cls_City dienbien2 = new A8_Cls_City("Tây Bắc Bộ", "Điện Biên", "Tủa Chùa");
            A8_Cls_City dienbien3 = new A8_Cls_City("Tây Bắc Bộ", "Điện Biên", "Nậm Pồ");
            A8_Cls_City dienbien4 = new A8_Cls_City("Tây Bắc Bộ", "Điện Biên", "Mường Nhé");
            A8_Cls_City dienbien5 = new A8_Cls_City("Tây Bắc Bộ", "Điện Biên", "Mường Lay");
            A8_Cls_City dienbien6 = new A8_Cls_City("Tây Bắc Bộ", "Điện Biên", "Mường Chà");
            A8_Cls_City dienbien7 = new A8_Cls_City("Tây Bắc Bộ", "Điện Biên", "Mường Ảng");
            A8_Cls_City dienbien8 = new A8_Cls_City("Tây Bắc Bộ", "Điện Biên", "Điện Biên Phủ");
            A8_Cls_City dienbien9 = new A8_Cls_City("Tây Bắc Bộ", "Điện Biên", "Điện Biên Đông");
            A8_Cls_City dienbien10 = new A8_Cls_City("Tây Bắc Bộ", "Điện Biên", "TP.Điện Biên");

            A8_Cls_City hoabinh1 = new A8_Cls_City("Tây Bắc Bộ", "Hòa Bình", "Yên Thủy");
            A8_Cls_City hoabinh2 = new A8_Cls_City("Tây Bắc Bộ", "Hòa Bình", "Tân Lạc");
            A8_Cls_City hoabinh3 = new A8_Cls_City("Tây Bắc Bộ", "Hòa Bình", "Mai Châu");
            A8_Cls_City hoabinh4 = new A8_Cls_City("Tây Bắc Bộ", "Hòa Bình", "Lương Sơn");
            A8_Cls_City hoabinh5 = new A8_Cls_City("Tây Bắc Bộ", "Hòa Bình", "Lạc Thủy");
            A8_Cls_City hoabinh6 = new A8_Cls_City("Tây Bắc Bộ", "Hòa Bình", "Lạc Sơn");
            A8_Cls_City hoabinh7 = new A8_Cls_City("Tây Bắc Bộ", "Hòa Bình", "Kỳ Sơn");
            A8_Cls_City hoabinh8 = new A8_Cls_City("Tây Bắc Bộ", "Hòa Bình", "Kim Bôi");
            A8_Cls_City hoabinh9 = new A8_Cls_City("Tây Bắc Bộ", "Hòa Bình", "Hoà Bình");
            A8_Cls_City hoabinh10 = new A8_Cls_City("Tây Bắc Bộ", "Hòa Bình", "Đà Bắc");
            A8_Cls_City hoabinh11 = new A8_Cls_City("Tây Bắc Bộ", "Hòa Bình", "Cao Phong");

            A8_Cls_City laichau1 = new A8_Cls_City("Tây Bắc Bộ", "Lai Châu", "Than Uyên");
            A8_Cls_City laichau2 = new A8_Cls_City("Tây Bắc Bộ", "Lai Châu", "Tân Uyên");
            A8_Cls_City laichau3 = new A8_Cls_City("Tây Bắc Bộ", "Lai Châu", "Tam Đường");
            A8_Cls_City laichau4 = new A8_Cls_City("Tây Bắc Bộ", "Lai Châu", "Sìn Hồ");
            A8_Cls_City laichau5 = new A8_Cls_City("Tây Bắc Bộ", "Lai Châu", "Phong Thổ");
            A8_Cls_City laichau6 = new A8_Cls_City("Tây Bắc Bộ", "Lai Châu", "Nậm Nhùn");
            A8_Cls_City laichau7 = new A8_Cls_City("Tây Bắc Bộ", "Lai Châu", "Mường Tè");
            A8_Cls_City laichau8 = new A8_Cls_City("Tây Bắc Bộ", "Lai Châu", "Lai Châu");

            A8_Cls_City laocai1 = new A8_Cls_City("Tây Bắc Bộ", "Lào Cai", "Văn Bàn");
            A8_Cls_City laocai2 = new A8_Cls_City("Tây Bắc Bộ", "Lào Cai", "Si Ma Cai");
            A8_Cls_City laocai3 = new A8_Cls_City("Tây Bắc Bộ", "Lào Cai", "Sa Pa");
            A8_Cls_City laocai4 = new A8_Cls_City("Tây Bắc Bộ", "Lào Cai", "Mường Khương");
            A8_Cls_City laocai5 = new A8_Cls_City("Tây Bắc Bộ", "Lào Cai", "Lào Cai");
            A8_Cls_City laocai6 = new A8_Cls_City("Tây Bắc Bộ", "Lào Cai", "Bát Xát");
            A8_Cls_City laocai7 = new A8_Cls_City("Tây Bắc Bộ", "Lào Cai", "Bảo Yên");
            A8_Cls_City laocai8 = new A8_Cls_City("Tây Bắc Bộ", "Lào Cai", "Bảo Thắng");
            A8_Cls_City laocai9 = new A8_Cls_City("Tây Bắc Bộ", "Lào Cai", "Bắc Hà");

            A8_Cls_City sonla1 = new A8_Cls_City("Tây Bắc Bộ", "Sơn La", "Yên Châu");
            A8_Cls_City sonla2 = new A8_Cls_City("Tây Bắc Bộ", "Sơn La", "Vân Hồ");
            A8_Cls_City sonla3 = new A8_Cls_City("Tây Bắc Bộ", "Sơn La", "Thuận Châu");
            A8_Cls_City sonla4 = new A8_Cls_City("Tây Bắc Bộ", "Sơn La", "Sốp Cộp");
            A8_Cls_City sonla5 = new A8_Cls_City("Tây Bắc Bộ", "Sơn La", "Sông Mã");
            A8_Cls_City sonla6 = new A8_Cls_City("Tây Bắc Bộ", "Sơn La", "Sơn La");
            A8_Cls_City sonla7 = new A8_Cls_City("Tây Bắc Bộ", "Sơn La", "Quỳnh Nhai");
            A8_Cls_City sonla8 = new A8_Cls_City("Tây Bắc Bộ", "Sơn La", "Phù Yên");
            A8_Cls_City sonla9 = new A8_Cls_City("Tây Bắc Bộ", "Sơn La", "Mường La");
            A8_Cls_City sonla10 = new A8_Cls_City("Tây Bắc Bộ", "Sơn La", "Mộc Châu");
            A8_Cls_City sonla11 = new A8_Cls_City("Tây Bắc Bộ", "Sơn La", "Mai Sơn");
            A8_Cls_City sonla12 = new A8_Cls_City("Tây Bắc Bộ", "Sơn La", "Bắc Yên");


            A8_Cls_City yenbai1 = new A8_Cls_City("Tây Bắc Bộ", "Yên Bái", "Yên Bình");
            A8_Cls_City yenbai2 = new A8_Cls_City("Tây Bắc Bộ", "Yên Bái", "Yên Bái");
            A8_Cls_City yenbai3 = new A8_Cls_City("Tây Bắc Bộ", "Yên Bái", "Văn Yên");
            A8_Cls_City yenbai4 = new A8_Cls_City("Tây Bắc Bộ", "Yên Bái", "Văn Chấn");
            A8_Cls_City yenbai5 = new A8_Cls_City("Tây Bắc Bộ", "Yên Bái", "Trấn Yên");
            A8_Cls_City yenbai6 = new A8_Cls_City("Tây Bắc Bộ", "Yên Bái", "Trạm Tấu");
            A8_Cls_City yenbai7 = new A8_Cls_City("Tây Bắc Bộ", "Yên Bái", "Nghĩa Lộ");
            A8_Cls_City yenbai8 = new A8_Cls_City("Tây Bắc Bộ", "Yên Bái", "Mù Căng Chải");
            A8_Cls_City yenbai9 = new A8_Cls_City("Tây Bắc Bộ", "Yên Bái", "Lục Yên");


            // BẮC TRUNG BỘ
            A8_Cls_City thanhhoa1 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Yên Định");
            A8_Cls_City thanhhoa2 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Vĩnh Lộc");
            A8_Cls_City thanhhoa3 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Triệu Sơn");
            A8_Cls_City thanhhoa4 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Tĩnh Gia");
            A8_Cls_City thanhhoa5 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Thường Xuân");
            A8_Cls_City thanhhoa6 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Thọ Xuân");
            A8_Cls_City thanhhoa7 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Thiệu Hóa");
            A8_Cls_City thanhhoa8 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Thanh Hóa");
            A8_Cls_City thanhhoa9 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Thạch Thành");
            A8_Cls_City thanhhoa10 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Sầm Sơn");
            A8_Cls_City thanhhoa11 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Quảng Xương");
            A8_Cls_City thanhhoa12 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Quan Sơn");
            A8_Cls_City thanhhoa13 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Quan Hóa");
            A8_Cls_City thanhhoa14 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Nông Cống");
            A8_Cls_City thanhhoa15 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Như Xuân");
            A8_Cls_City thanhhoa16 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Như Thanh");
            A8_Cls_City thanhhoa17 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Ngọc Lặc");
            A8_Cls_City thanhhoa18 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Nga Sơn");
            A8_Cls_City thanhhoa19 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Mường Lát");
            A8_Cls_City thanhhoa20 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Lang Chánh");
            A8_Cls_City thanhhoa21 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Hoằng Hóa");
            A8_Cls_City thanhhoa22 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Hậu Lộc");
            A8_Cls_City thanhhoa23 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Hà Trung");
            A8_Cls_City thanhhoa24 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Đông Sơn");
            A8_Cls_City thanhhoa25 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Cẩm Thủy");
            A8_Cls_City thanhhoa26 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Bỉm Sơn");
            A8_Cls_City thanhhoa27 = new A8_Cls_City("Bắc Trung Bộ", "Thanh Hóa", "Bá Thước");

            A8_Cls_City nghean1 = new A8_Cls_City("Bắc Trung Bộ", "Nghệ An", "Yên Thành");
            A8_Cls_City nghean2 = new A8_Cls_City("Bắc Trung Bộ", "Nghệ An", "TP.Vinh");
            A8_Cls_City nghean3 = new A8_Cls_City("Bắc Trung Bộ", "Nghệ An", "Tương Dương");
            A8_Cls_City nghean4 = new A8_Cls_City("Bắc Trung Bộ", "Nghệ An", "Thanh Chương");
            A8_Cls_City nghean5 = new A8_Cls_City("Bắc Trung Bộ", "Nghệ An", "Thái Hòa");
            A8_Cls_City nghean6 = new A8_Cls_City("Bắc Trung Bộ", "Nghệ An", "Tân Kỳ");
            A8_Cls_City nghean7 = new A8_Cls_City("Bắc Trung Bộ", "Nghệ An", "Quỳnh Lưu");
            A8_Cls_City nghean8 = new A8_Cls_City("Bắc Trung Bộ", "Nghệ An", "Quỳ Hợp");
            A8_Cls_City nghean9 = new A8_Cls_City("Bắc Trung Bộ", "Nghệ An", "Quỳ Châu");
            A8_Cls_City nghean10 = new A8_Cls_City("Bắc Trung Bộ", "Nghệ An", "Quế Phong");
            A8_Cls_City nghean11 = new A8_Cls_City("Bắc Trung Bộ", "Nghệ An", "Nghĩa Đàn");
            A8_Cls_City nghean12 = new A8_Cls_City("Bắc Trung Bộ", "Nghệ An", "Nghi Lộc");
            A8_Cls_City nghean13 = new A8_Cls_City("Bắc Trung Bộ", "Nghệ An", "Nam Đàn");
            A8_Cls_City nghean14 = new A8_Cls_City("Bắc Trung Bộ", "Nghệ An", "Kỳ Sơn");
            A8_Cls_City nghean15 = new A8_Cls_City("Bắc Trung Bộ", "Nghệ An", "Hưng Nguyên");
            A8_Cls_City nghean16 = new A8_Cls_City("Bắc Trung Bộ", "Nghệ An", "Hoàng Mai");
            A8_Cls_City nghean17 = new A8_Cls_City("Bắc Trung Bộ", "Nghệ An", "Đô Lương");
            A8_Cls_City nghean18 = new A8_Cls_City("Bắc Trung Bộ", "Nghệ An", "Diễn Châu");
            A8_Cls_City nghean19 = new A8_Cls_City("Bắc Trung Bộ", "Nghệ An", "Cửa Lò");
            A8_Cls_City nghean20 = new A8_Cls_City("Bắc Trung Bộ", "Nghệ An", "Con Cuông");
            A8_Cls_City nghean21 = new A8_Cls_City("Bắc Trung Bộ", "Nghệ An", "Anh Sơn");

            A8_Cls_City hatinh1 = new A8_Cls_City("Bắc Trung Bộ", "Hà Tĩnh", "Vũ Quang");
            A8_Cls_City hatinh2 = new A8_Cls_City("Bắc Trung Bộ", "Hà Tĩnh", "Thạch Hà");
            A8_Cls_City hatinh3 = new A8_Cls_City("Bắc Trung Bộ", "Hà Tĩnh", "Nghi Xuân");
            A8_Cls_City hatinh4 = new A8_Cls_City("Bắc Trung Bộ", "Hà Tĩnh", "Lộc Hà");
            A8_Cls_City hatinh5 = new A8_Cls_City("Bắc Trung Bộ", "Hà Tĩnh", "Kỳ Anh");
            A8_Cls_City hatinh6 = new A8_Cls_City("Bắc Trung Bộ", "Hà Tĩnh", "Hương Sơn");
            A8_Cls_City hatinh7 = new A8_Cls_City("Bắc Trung Bộ", "Hà Tĩnh", "Hương Khê");
            A8_Cls_City hatinh8 = new A8_Cls_City("Bắc Trung Bộ", "Hà Tĩnh", "Hồng Lĩnh");
            A8_Cls_City hatinh9 = new A8_Cls_City("Bắc Trung Bộ", "Hà Tĩnh", "TP.Hà Tĩnh");
            A8_Cls_City hatinh10 = new A8_Cls_City("Bắc Trung Bộ", "Hà Tĩnh", "Đức Thọ");
            A8_Cls_City hatinh11 = new A8_Cls_City("Bắc Trung Bộ", "Hà Tĩnh", "Can Lộc");
            A8_Cls_City hatinh12 = new A8_Cls_City("Bắc Trung Bộ", "Hà Tĩnh", "Cẩm Xuyên");

            A8_Cls_City quangbinh1 = new A8_Cls_City("Bắc Trung Bộ", "Quảng Bình", "Tuyên Hóa");
            A8_Cls_City quangbinh2 = new A8_Cls_City("Bắc Trung Bộ", "Quảng Bình", "Quảng Trạch");
            A8_Cls_City quangbinh3 = new A8_Cls_City("Bắc Trung Bộ", "Quảng Bình", "Quảng Ninh");
            A8_Cls_City quangbinh4 = new A8_Cls_City("Bắc Trung Bộ", "Quảng Bình", "Minh Hóa");
            A8_Cls_City quangbinh5 = new A8_Cls_City("Bắc Trung Bộ", "Quảng Bình", "Lệ Thủy");
            A8_Cls_City quangbinh6 = new A8_Cls_City("Bắc Trung Bộ", "Quảng Bình", "Đồng Hới");
            A8_Cls_City quangbinh7 = new A8_Cls_City("Bắc Trung Bộ", "Quảng Bình", "Bố Trạch");
            A8_Cls_City quangbinh8 = new A8_Cls_City("Bắc Trung Bộ", "Quảng Bình", "Ba Đồn");

            A8_Cls_City quangtri1 = new A8_Cls_City("Bắc Trung Bộ", "Quảng Trị", "Vĩnh Linh");
            A8_Cls_City quangtri2 = new A8_Cls_City("Bắc Trung Bộ", "Quảng Trị", "Triệu Phong");
            A8_Cls_City quangtri3 = new A8_Cls_City("Bắc Trung Bộ", "Quảng Trị", "Quảng Trị");
            A8_Cls_City quangtri4 = new A8_Cls_City("Bắc Trung Bộ", "Quảng Trị", "Hướng Hóa");
            A8_Cls_City quangtri5 = new A8_Cls_City("Bắc Trung Bộ", "Quảng Trị", "Hải Lăng");
            A8_Cls_City quangtri6 = new A8_Cls_City("Bắc Trung Bộ", "Quảng Trị", "Gio Linh");
            A8_Cls_City quangtri7 = new A8_Cls_City("Bắc Trung Bộ", "Quảng Trị", "Đông Hà");
            A8_Cls_City quangtri8 = new A8_Cls_City("Bắc Trung Bộ", "Quảng Trị", "Đa Krông");
            A8_Cls_City quangtri9 = new A8_Cls_City("Bắc Trung Bộ", "Quảng Trị", "Cồn Cỏ");
            A8_Cls_City quangtri10 = new A8_Cls_City("Bắc Trung Bộ", "Quảng Trị", "Cam Lộ");

            A8_Cls_City hue1 = new A8_Cls_City("Bắc Trung Bộ", "Thừa Thiên-Huế", "Quảng Điền");
            A8_Cls_City hue2 = new A8_Cls_City("Bắc Trung Bộ", "Thừa Thiên-Huế", "Phú Vang");
            A8_Cls_City hue3 = new A8_Cls_City("Bắc Trung Bộ", "Thừa Thiên-Huế", "Phú Lộc");
            A8_Cls_City hue4 = new A8_Cls_City("Bắc Trung Bộ", "Thừa Thiên-Huế", "Phong Điền");
            A8_Cls_City hue5 = new A8_Cls_City("Bắc Trung Bộ", "Thừa Thiên-Huế", "Nam Đông");
            A8_Cls_City hue6 = new A8_Cls_City("Bắc Trung Bộ", "Thừa Thiên-Huế", "Hương Trà");
            A8_Cls_City hue7 = new A8_Cls_City("Bắc Trung Bộ", "Thừa Thiên-Huế", "Hương Thủy");
            A8_Cls_City hue8 = new A8_Cls_City("Bắc Trung Bộ", "Thừa Thiên-Huế", "TP.Huế");
            A8_Cls_City hue9 = new A8_Cls_City("Bắc Trung Bộ", "Thừa Thiên-Huế", "A Lưới");


            this.addValueCity(dienbien1);
            this.addValueCity(dienbien2);
            this.addValueCity(dienbien3);
            this.addValueCity(dienbien4);
            this.addValueCity(dienbien5);
            this.addValueCity(dienbien6);
            this.addValueCity(dienbien7);
            this.addValueCity(dienbien8);
            this.addValueCity(dienbien9);
            this.addValueCity(dienbien10);

            this.addValueCity(hoabinh1);
            this.addValueCity(hoabinh2);
            this.addValueCity(hoabinh3);
            this.addValueCity(hoabinh4);
            this.addValueCity(hoabinh5);
            this.addValueCity(hoabinh6);
            this.addValueCity(hoabinh7);
            this.addValueCity(hoabinh8);
            this.addValueCity(hoabinh9);
            this.addValueCity(hoabinh10);
            this.addValueCity(hoabinh11);

            this.addValueCity(laichau1);
            this.addValueCity(laichau2);
            this.addValueCity(laichau3);
            this.addValueCity(laichau4);
            this.addValueCity(laichau5);
            this.addValueCity(laichau6);
            this.addValueCity(laichau7);
            this.addValueCity(laichau8);

            this.addValueCity(laocai1);
            this.addValueCity(laocai2);
            this.addValueCity(laocai3);
            this.addValueCity(laocai4);
            this.addValueCity(laocai5);
            this.addValueCity(laocai6);
            this.addValueCity(laocai7);
            this.addValueCity(laocai8);
            this.addValueCity(laocai9);

            this.addValueCity(sonla1);
            this.addValueCity(sonla2);
            this.addValueCity(sonla3);
            this.addValueCity(sonla4);
            this.addValueCity(sonla5);
            this.addValueCity(sonla6);
            this.addValueCity(sonla7);
            this.addValueCity(sonla8);
            this.addValueCity(sonla9);
            this.addValueCity(sonla10);
            this.addValueCity(sonla11);
            this.addValueCity(sonla12);

            this.addValueCity(yenbai1);
            this.addValueCity(yenbai2);
            this.addValueCity(yenbai3);
            this.addValueCity(yenbai4);
            this.addValueCity(yenbai5);
            this.addValueCity(yenbai6);
            this.addValueCity(yenbai7);
            this.addValueCity(yenbai8);
            this.addValueCity(yenbai9);

            this.addValueCity(thanhhoa1);
            this.addValueCity(thanhhoa2);
            this.addValueCity(thanhhoa3);
            this.addValueCity(thanhhoa4);
            this.addValueCity(thanhhoa5);
            this.addValueCity(thanhhoa6);
            this.addValueCity(thanhhoa7);
            this.addValueCity(thanhhoa8);
            this.addValueCity(thanhhoa9);
            this.addValueCity(thanhhoa10);
            this.addValueCity(thanhhoa11);
            this.addValueCity(thanhhoa12);
            this.addValueCity(thanhhoa13);
            this.addValueCity(thanhhoa14);
            this.addValueCity(thanhhoa15);
            this.addValueCity(thanhhoa16);
            this.addValueCity(thanhhoa17);
            this.addValueCity(thanhhoa18);
            this.addValueCity(thanhhoa19);
            this.addValueCity(thanhhoa20);
            this.addValueCity(thanhhoa21);
            this.addValueCity(thanhhoa22);
            this.addValueCity(thanhhoa23);
            this.addValueCity(thanhhoa24);
            this.addValueCity(thanhhoa25);
            this.addValueCity(thanhhoa26);
            this.addValueCity(thanhhoa27);

            this.addValueCity(nghean1);
            this.addValueCity(nghean2);
            this.addValueCity(nghean3);
            this.addValueCity(nghean4);
            this.addValueCity(nghean5);
            this.addValueCity(nghean6);
            this.addValueCity(nghean7);
            this.addValueCity(nghean8);
            this.addValueCity(nghean9);
            this.addValueCity(nghean10);
            this.addValueCity(nghean11);
            this.addValueCity(nghean12);
            this.addValueCity(nghean13);
            this.addValueCity(nghean14);
            this.addValueCity(nghean15);
            this.addValueCity(nghean16);
            this.addValueCity(nghean17);
            this.addValueCity(nghean18);
            this.addValueCity(nghean19);
            this.addValueCity(nghean20);
            this.addValueCity(nghean21);

            this.addValueCity(hatinh1);
            this.addValueCity(hatinh2);
            this.addValueCity(hatinh3);
            this.addValueCity(hatinh4);
            this.addValueCity(hatinh5);
            this.addValueCity(hatinh6);
            this.addValueCity(hatinh7);
            this.addValueCity(hatinh8);
            this.addValueCity(hatinh9);
            this.addValueCity(hatinh10);
            this.addValueCity(hatinh11);
            this.addValueCity(hatinh12);

            this.addValueCity(quangbinh1);
            this.addValueCity(quangbinh2);
            this.addValueCity(quangbinh3);
            this.addValueCity(quangbinh4);
            this.addValueCity(quangbinh5);
            this.addValueCity(quangbinh6);
            this.addValueCity(quangbinh7);
            this.addValueCity(quangbinh8);

            this.addValueCity(quangtri1);
            this.addValueCity(quangtri2);
            this.addValueCity(quangtri3);
            this.addValueCity(quangtri4);
            this.addValueCity(quangtri5);
            this.addValueCity(quangtri6);
            this.addValueCity(quangtri7);
            this.addValueCity(quangtri8);
            this.addValueCity(quangtri9);
            this.addValueCity(quangtri10);

            this.addValueCity(hue1);
            this.addValueCity(hue2);
            this.addValueCity(hue3);
            this.addValueCity(hue4);
            this.addValueCity(hue5);
            this.addValueCity(hue6);
            this.addValueCity(hue7);
            this.addValueCity(hue8);
            this.addValueCity(hue9);

        }

    }
    //endregion

    //region SEARCH DỮ LIỆU

    // Lấy khu vực
    public List<A8_Cls_City> getAllDataZone(String title) {
        try {
            List<A8_Cls_City> addressList = new ArrayList<A8_Cls_City>();

            // Tạo tiêu đề cho Spinner
            A8_Cls_City clsCity2 = new A8_Cls_City();
            clsCity2.setKhuVuc(title);
            clsCity2.setTinhTP("");
            clsCity2.setQuanHuyen("");
            addressList.add(clsCity2);
            // kết thúc tạo tiêu đề

            // script search
            String script = "SELECT " + TBM_ZONE_KHUVUC + " FROM " + TABLE_NAME_ZONE
                    + " GROUP BY " + TBM_ZONE_KHUVUC
                    + " ORDER BY " + TBM_ZONE_KHUVUC;

            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.rawQuery(script, null);

            // Duyệt danh sách search
            if (cursor.moveToFirst()) {
                do {
                    A8_Cls_City clsCity = new A8_Cls_City();
                    clsCity.setKhuVuc(cursor.getString(0));
                    clsCity.setTinhTP("");
                    clsCity.setQuanHuyen("");

                    // Thêm dữ liệu vào list
                    addressList.add(clsCity);
                } while (cursor.moveToNext());
            }
            return addressList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Lấy tỉnh/ tp
    public List<A8_Cls_City> getAllDataCity(String KhuVuc, String title) {
        try {
            List<A8_Cls_City> addressList = new ArrayList<A8_Cls_City>();

            // Tạo tiêu đề cho Spinner
            A8_Cls_City clsCity2 = new A8_Cls_City();
            clsCity2.setKhuVuc("");
            clsCity2.setTinhTP(title);
            clsCity2.setQuanHuyen("");
            addressList.add(clsCity2);
            // kết thúc tạo tiêu đề

            // script search
            String script = "SELECT " + TBM_ZONE_TINHTP + " FROM " + TABLE_NAME_ZONE
                    + " WHERE " + TBM_ZONE_KHUVUC + " = " + "'" + KhuVuc + "'"
                    + " GROUP BY " + TBM_ZONE_TINHTP
                    + " ORDER BY " + TBM_ZONE_TINHTP;

            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.rawQuery(script, null);

            // Duyệt danh sách search
            if (cursor.moveToFirst()) {
                do {
                    A8_Cls_City clsCity = new A8_Cls_City();
                    clsCity.setTinhTP(cursor.getString(0));
                    clsCity.setKhuVuc("");
                    clsCity.setQuanHuyen("");

                    // Thêm dữ liệu vào list
                    addressList.add(clsCity);
                } while (cursor.moveToNext());
            }
            return addressList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // lấy quận/ huyện
    public List<A8_Cls_City> getAllDataCounty(String TinhTp, String title) {
        try {
            List<A8_Cls_City> addressList = new ArrayList<A8_Cls_City>();

            // Tạo tiêu đề cho Spinner
            A8_Cls_City clsCity2 = new A8_Cls_City();
            clsCity2.setKhuVuc("");
            clsCity2.setTinhTP("");
            clsCity2.setQuanHuyen(title);
            addressList.add(clsCity2);
            // kết thúc tạo tiêu đề

            // script search
            String script = "SELECT " + TBM_ZONE_QUANHUYEN + " FROM " + TABLE_NAME_ZONE
                    + " WHERE " + TBM_ZONE_TINHTP + " = " + "'" + TinhTp + "'"
                    + " GROUP BY " + TBM_ZONE_QUANHUYEN;

            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.rawQuery(script, null);

            // Duyệt danh sách search
            if (cursor.moveToFirst()) {
                do {
                    A8_Cls_City clsCity = new A8_Cls_City();
                    clsCity.setQuanHuyen(cursor.getString(0));
                    clsCity.setKhuVuc("");
                    clsCity.setTinhTP("");

                    // Thêm dữ liệu vào list
                    addressList.add(clsCity);
                } while (cursor.moveToNext());
            }
            return addressList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //endregion
}
