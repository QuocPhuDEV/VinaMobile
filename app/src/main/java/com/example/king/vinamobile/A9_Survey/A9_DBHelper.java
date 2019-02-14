package com.example.king.vinamobile.A9_Survey;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class A9_DBHelper extends SQLiteOpenHelper {

    //region KHAI BÁO BIẾN TOÀN CỤC
    // Phiên bản SQLite
    private static final int DATABASE_VERSION = 1;

    // Tên CSDL
    private static final String DATABASE_NAME = "DB_VinaMobile";

    // Tên bảng
    private static final String TABLE_NAME_QUESTION = "TBM_QUESTION";

    // các cột
    //BẢNG TBT_CITY
    private static final String TBM_QUES_ID = "MaCH";
    private static final String TBM_QUES_NAME = "CauHoi";
    private static final String TBM_QUES_TYPE = "LoaiCH";

    //endregion

    public A9_DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //region THÊM DỮ LIỆU MẶC ĐỊNH CHO BẢNG

    // function thêm dữ liệu
    public void addValueQuestion(A9_Cls_Question ClsQuestion) {
        try {
            // Khởi tạo đối tượng SQLite
            SQLiteDatabase database = this.getWritableDatabase();

            // Khởi tạo đối tượng ContentValues ( dùng để lưu dữ liệu)
            ContentValues values = new ContentValues();
            values.put(TBM_QUES_ID, ClsQuestion.getMaCH());
            values.put(TBM_QUES_NAME, ClsQuestion.getCauHoi());
            values.put(TBM_QUES_TYPE, ClsQuestion.getLoaiCH());

            // Thêm dữ liệu
            database.insert(TABLE_NAME_QUESTION, null, values);

            // Đóng kết nối
            database.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //function kiểm tra số record trong bảng
    public int getCountRecord() {
        try {
            String countQuery = "SELECT * FROM " + TABLE_NAME_QUESTION;
            SQLiteDatabase database = this.getReadableDatabase();

            Cursor cursor = database.rawQuery(countQuery, null);

            int count = cursor.getCount();

            cursor.close();

            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //function thêm dữ liệu mặc định cho bảng
    public void createDefaultData() {
        try {
            int count = this.getCountRecord();
            if (count == 0) {
                A9_Cls_Question question1 = new A9_Cls_Question("CH01", "Bạn biết đến công ty chúng tôi qua hình thức nào ?", "Trắc Nghiệm");
                A9_Cls_Question question2 = new A9_Cls_Question("CH02", "Những yêu cầu bạn muốn chúng tôi thay đổi để phục vụ tốt hơn ?", "Tự Luận");
                A9_Cls_Question question3 = new A9_Cls_Question("CH03", "Bạn đã sử dụng sản phẩm của công ty chúng tôi trong bao lâu ?", "Tự Luận");
                A9_Cls_Question question4 = new A9_Cls_Question("CH04", "Hãy đánh giá mức độ hài lòng của bạn đối với sản phẩm công ty chúng tôi ?", "Đánh Giá");
                A9_Cls_Question question5 = new A9_Cls_Question("CH05", "Bạn sẽ tiếp tục ủng hộ sản phẩm của công ty chúng tôi chứ ?", "Đánh Giá");

                // thêm câu hỏi vào database
                this.addValueQuestion(question1);
                this.addValueQuestion(question2);
                this.addValueQuestion(question3);
                this.addValueQuestion(question4);
                this.addValueQuestion(question5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region SEARCH DỮ LIỆU

    // Search loại câu hỏi
    public List<A9_Cls_Question> getAllQuestionType() {
        try {
            List<A9_Cls_Question> typeList = new ArrayList<A9_Cls_Question>();

            // Script query search
            String script = "SELECT " + TBM_QUES_TYPE + " FROM " + TABLE_NAME_QUESTION
                    + " GROUP BY " + TBM_QUES_TYPE;

            // khởi tạo đối tượng SQLite
            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.rawQuery(script, null);

            // Duyệt danh sách search
            if (cursor.moveToFirst()) {
                do {
                    A9_Cls_Question clsQues = new A9_Cls_Question();
                    clsQues.setMaCH("");
                    clsQues.setCauHoi("");
                    clsQues.setLoaiCH(cursor.getString(0));

                    // thêm dữ liệu vào list
                    typeList.add(clsQues);

                } while (cursor.moveToNext());
            }

            return typeList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Đếm loại câu hỏi
    public int getCountQuesType(String type) {
        try {
            String countQuery = "SELECT * FROM " + TABLE_NAME_QUESTION
                    + " WHERE " + TBM_QUES_TYPE + " = '" + type + "'";
            SQLiteDatabase database = this.getReadableDatabase();

            Cursor cursor = database.rawQuery(countQuery, null);

            int count = cursor.getCount();

            cursor.close();

            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    //endregion
}
