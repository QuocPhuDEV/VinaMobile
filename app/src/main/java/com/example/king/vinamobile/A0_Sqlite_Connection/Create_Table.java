package com.example.king.vinamobile.A0_Sqlite_Connection;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.king.vinamobile.A1_Login.A1_Cls_Account;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Create_Table extends SQLiteOpenHelper {
    //region KHAI BÁO BIẾN TOÀN CỤC

    // Phiên bản SQLite
    private static final int DATABASE_VERSION = 1;

    // Tên CSDL
    private static final String DATABASE_NAME = "DB_VinaMobile";

    // Tên bảng
    private static final String TABLE_NAME_ACCOUNT = "TBT_ACCOUNT";
    private static final String TABLE_NAME_ZONE = "TBM_KHUVUC";
    private static final String TABLE_NAME_QUESTION = "TBM_QUESTION";
    private static final String TABLE_NAME_ANSWER = "TBT_ANSWER";


    // các cột

    // BẢNG TBT_ACCOUNT
    private static final String TBT_ACCOUNT_NGAYTAO = "Ngaytao";
    private static final String TBT_ACCOUNT_NGAYCN = "NgayCN";
    private static final String TBT_ACCOUNT_SDT = "SDT";
    private static final String TBT_ACCOUNT_PASSWORD = "MatKhau";
    private static final String TBT_ACCOUNT_MAKH = "MaKH";
    private static final String TBT_ACCOUNT_TENKH = "TenKH";
    private static final String TBT_ACCOUNT_DIACHI = "DiaChi";
    private static final String TBT_ACCOUNT_HINHANH = "HinhAnh";

    //BẢNG TBM_KHUVUC
    private static final String TBM_ZONE_KHUVUC = "KhuVuc";
    private static final String TBM_ZONE_TINHTP = "TinhTP";
    private static final String TBM_ZONE_QUANHUYEN = "QuanHuyen";

    //BẢNG TBM_QUESTION
    private static final String TBM_QUES_ID = "MaCH";
    private static final String TBM_QUES_NAME = "CauHoi";
    private static final String TBM_QUES_TYPE = "LoaiCH";

    //BẢNG TBT_ANSWER
    private static final String TBM_ANSWER_MACH = "MaCH";
    private static final String TBM_ANSWER_ANSWER = "TraLoi";
    private static final String TBM_ANSWER_TIME = "ThoiGian";
    private static final String TBM_ANSWER_SDT = "SDT";

    //endregion

    public Create_Table(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Create_Table_TBT_ACCOUNT(sqLiteDatabase);
        Create_Table_TBM_ZONE(sqLiteDatabase);
        Create_Table_TBM_QUESTION(sqLiteDatabase);
        Create_Table_TBT_ANSWER(sqLiteDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Drop_Table_TBT_ACCOUNT(sqLiteDatabase);
        Drop_Table_TBM_ZONE(sqLiteDatabase);
        Drop_Table_TBM_QUESTION(sqLiteDatabase);
        Drop_Table_TBT_ANSWER(sqLiteDatabase);
    }

    //region TẠO BẢNG TRONG DATABASE
    // Tạo bảng TBT_ACCOUNT
    public void Create_Table_TBT_ACCOUNT(SQLiteDatabase sqLiteDatabase) {
        try {
            // script tạo bảng
            String script = "CREATE TABLE " + TABLE_NAME_ACCOUNT + "("
                    + TBT_ACCOUNT_NGAYTAO + " DATETIME, "
                    + TBT_ACCOUNT_NGAYCN + " DATETIME, "
                    + TBT_ACCOUNT_SDT + " VARCHAR(20) PRIMARY KEY, "
                    + TBT_ACCOUNT_PASSWORD + " VARCHAR(20), "
                    + TBT_ACCOUNT_MAKH + " VARCHAR(20), "
                    + TBT_ACCOUNT_TENKH + " VARCHAR(50), "
                    + TBT_ACCOUNT_DIACHI + " VARCHAR(100), "
                    + TBT_ACCOUNT_HINHANH + " VARCHAR(100) " + ")";

            // chạy lệnh tạo bảng
            sqLiteDatabase.execSQL(script);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Tạo bảng TBM_ZONE
    public void Create_Table_TBM_ZONE(SQLiteDatabase sqLiteDatabase) {
        try {
            // script tạo bảng
            String script = "CREATE TABLE " + TABLE_NAME_ZONE + " ("
                    + TBM_ZONE_KHUVUC + " VARCHAR(100), "
                    + TBM_ZONE_TINHTP + " VARCHAR(100), "
                    + TBM_ZONE_QUANHUYEN + " VARCHAR(100) PRIMARY KEY " + " )";

            // chạy lệnh tạo bảng
            sqLiteDatabase.execSQL(script);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Tạo bảng TBM_QUESTION
    public void Create_Table_TBM_QUESTION(SQLiteDatabase sqLiteDatabase) {
        try {
            // script tạo bảng
            String script = "CREATE TABLE " + TABLE_NAME_QUESTION + " ( "
                    + TBM_QUES_ID + " VARCHAR(20) PRIMARY KEY , "
                    + TBM_QUES_NAME + " VARCHAR(200), "
                    + TBM_QUES_TYPE + " VARCHAR(100) ) ";

            // chạy lệnh tạo bảng
            sqLiteDatabase.execSQL(script);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Tạo bảng TBT_ANSWER
    public void Create_Table_TBT_ANSWER(SQLiteDatabase sqLiteDatabase) {
        try {
            // script tạo bảng
            String script = "CREATE TABLE " + TABLE_NAME_ANSWER + " ( "
                    + TBM_ANSWER_MACH + " VARCHAR(20) PRIMARY KEY , "
                    + TBM_ANSWER_ANSWER + " VARCHAR(200), "
                    + TBM_ANSWER_TIME + " DATETIME, "
                    + TBM_ANSWER_SDT + " VARCHAR(20) ) ";

            // chạy lệnh tạo bảng
            sqLiteDatabase.execSQL(script);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //endregion

    //region XÓA BẢNG TRONG DATABASE
    // xóa bảng TBT_ACCOUNT nếu tồn tại, tạo lại
    public void Drop_Table_TBT_ACCOUNT(SQLiteDatabase sqLiteDatabase) {

        try {
            //script xóa bảng
            String script = "DROP TABLE IF EXISTS " + TABLE_NAME_ACCOUNT;

            // Xóa bảng
            sqLiteDatabase.execSQL(script);

            // tạo lại bảng
            onCreate(sqLiteDatabase);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // xóa bảng TBT_ZONE nếu tồn tại, tạo lại
    public void Drop_Table_TBM_ZONE(SQLiteDatabase sqLiteDatabase) {

        try {
            //script xóa bảng
            String script = "DROP TABLE IF EXISTS " + TABLE_NAME_ZONE;

            // Xóa bảng
            sqLiteDatabase.execSQL(script);

            // tạo lại bảng
            onCreate(sqLiteDatabase);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // xóa bảng TBM_QUESTION nếu tồn tại, tạo lại
    public void Drop_Table_TBM_QUESTION(SQLiteDatabase sqLiteDatabase) {
        try {
            //script xóa bảng
            String script = "DROP TABLE IF EXISTS " + TABLE_NAME_QUESTION;

            // Xóa bảng
            sqLiteDatabase.execSQL(script);

            // tạo lại bảng
            onCreate(sqLiteDatabase);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // xóa bảng TBT_ANSWER nếu tồn tại, tạo lại
    public void Drop_Table_TBT_ANSWER(SQLiteDatabase sqLiteDatabase) {
        try {
            //script xóa bảng
            String script = "DROP TABLE IF EXISTS " + TABLE_NAME_ANSWER;

            // Xóa bảng
            sqLiteDatabase.execSQL(script);

            // tạo lại bảng
            onCreate(sqLiteDatabase);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //endregion


    // XỬ LÝ RIÊNG ACCOUNT

    //region THÊM DỮ LIỆU MẶC ĐỊNH CHO CÁC BẢNG

    // Thêm dữ liệu mặc định cho bảng TBT_ACCOUNT
    public void AddNewAccount(A1_Cls_Account account, Context context) {
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(TBT_ACCOUNT_NGAYTAO, account.getNgaytao());
            values.put(TBT_ACCOUNT_NGAYCN, account.getNgayCN());
            values.put(TBT_ACCOUNT_SDT, account.getSDT());
            values.put(TBT_ACCOUNT_PASSWORD, account.getMatKhau());
            values.put(TBT_ACCOUNT_MAKH, account.getMaKH());
            values.put(TBT_ACCOUNT_TENKH, account.getTenKH());
            values.put(TBT_ACCOUNT_DIACHI, account.getDiaChi());
            values.put(TBT_ACCOUNT_HINHANH, account.getHinhAnh());

            // thêm 1 dòng dữ liệu vào bảng
            database.insert(TABLE_NAME_ACCOUNT, null, values);

            // Thông báo
            //Toast.makeText(context, "Thêm account thành công", Toast.LENGTH_SHORT).show();

            // đóng kết nối
            database.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // THÊM DỮ LIỆU MẶC ĐỊNH CHO CUSTOMER
    public void CreateDefaultAccount(Context context) {
        try {
            int count = this.GetAccountCount();
            if (count == 0) {
                // tạo dữ liệu mẫu
                A1_Cls_Account account = new A1_Cls_Account("05/01/2019", null, "15555218135", "123", "KH01", "Hoàng Quốc Phú", "VN", "http://vinamobile.somee.com/phudeptrai.jpg");
                A1_Cls_Account account2 = new A1_Cls_Account("07/01/2019", null, "0941628268", "321", "KH02", "Nguyễn Mộng Nga", "Quận 9 - TP.HCM", "http://vinamobile.somee.com/aNga.jpg");

                // thêm dữ liệu mẫu vào bảng account
                this.AddNewAccount(account, context);
                this.AddNewAccount(account2, context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region ĐẾM DỮ LIỆU TRONG BẢNG
    public int GetAccountCount() {
        try {
            String countQuery = "SELECT  * FROM " + TABLE_NAME_ACCOUNT;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);

            int count = cursor.getCount();

            cursor.close();

            // return count
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }
    //endregion

    //region SEARCH, DELETE, SYNC DỮ LIỆU

    // Kiểm tra tồn tại account
    public boolean checkExistsAccount(String sdt, String pass) {
        boolean result = false;
        try {
            String script = "SELECT * FROM " + TABLE_NAME_ACCOUNT +
                    " WHERE SDT = '" + sdt + "' and MatKhau = '" + pass + "' ";

            SQLiteDatabase database = this.getReadableDatabase();
            Cursor cursor = database.rawQuery(script, null);

            int count = cursor.getCount();
            cursor.close();

            // return count
            if (count > 0) {
                result = true;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

    // Xoá dữ liệu trong bảng table
    public void deleteDataAccount() {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME_ACCOUNT, null, null);
        database.close();
    }

    // Đồng bộ dữ liệu từ Json về SQLite database
    public void syncAccount(JSONObject jsonObject) {
        try {
            // bắt đầu thực hiện sync
            ContentValues values = new ContentValues();
            SQLiteDatabase database = this.getWritableDatabase();

            values.put(TBT_ACCOUNT_NGAYTAO, jsonObject.getString("Ngaytao"));
            values.put(TBT_ACCOUNT_NGAYCN, jsonObject.getString("NgayCN"));
            values.put(TBT_ACCOUNT_SDT, jsonObject.getString("SDT"));
            values.put(TBT_ACCOUNT_PASSWORD, jsonObject.getString("MatKhau"));
            values.put(TBT_ACCOUNT_MAKH, jsonObject.getString("MaKH"));
            values.put(TBT_ACCOUNT_TENKH, jsonObject.getString("TenKH"));
            values.put(TBT_ACCOUNT_DIACHI, jsonObject.getString("DiaChi"));
            values.put(TBT_ACCOUNT_HINHANH, jsonObject.getString("Img"));

            // Thêm dữ liệu lấy được từ Json vào bảng Account
            database.insert(TABLE_NAME_ACCOUNT, null, values);

            // Đóng kết nối
            database.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Search dữ liệu
    public List<A1_Cls_Account> getDataAccount(String sdt, String pass) {
        try {
            List<A1_Cls_Account> accountList = new ArrayList<A1_Cls_Account>();

            // Script search
            String script = "SELECT * FROM " + TABLE_NAME_ACCOUNT +
                    " WHERE SDT = '" + sdt + "' and MatKhau = '" + pass + "' ";

            // Khởi tạo đối tượng database
            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.rawQuery(script, null);

            // Duyệt danh sách search được
            if (cursor.moveToFirst()) {
                do {
                    A1_Cls_Account account = new A1_Cls_Account();
                    account.setNgaytao(cursor.getString(0));
                    account.setNgayCN(cursor.getString(1));
                    account.setSDT(cursor.getString(2));
                    account.setMatKhau(cursor.getString(3));
                    account.setMaKH(cursor.getString(4));
                    account.setTenKH(cursor.getString(5));
                    account.setDiaChi(cursor.getString(6));
                    account.setHinhAnh(cursor.getString(7));

                    // thêm dữ liệu vào list
                    accountList.add(account);
                } while (cursor.moveToNext());
            }
            return accountList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //endregion

}
