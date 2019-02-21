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
    private static final String TABLE_NAME_ANSWER = "TBT_ANSWER";

    // các cột
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

    //function thêm dữ liệu cho bảng TBM_QUESTION
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

    //function thêm dữ liệu cho bảng TBT_ANSWER
    public void addValueAnswer(A9_Cls_Answer ClsAnswer) {
        try {
            // nếu đã trả lời, update câu trả lời
            if (getCountAnswer(ClsAnswer.MaCH) >= 1) {
                UpdateValueAnswer(ClsAnswer);
            } else {
                // Khởi tạo đối tượng SQLite
                SQLiteDatabase database = this.getWritableDatabase();

                // Khởi tạo đối tượng ContentValues ( dùng để lưu dữ liệu)
                ContentValues values = new ContentValues();
                values.put(TBM_ANSWER_MACH, ClsAnswer.getMaCH());
                values.put(TBM_ANSWER_ANSWER, ClsAnswer.getTraLoi());
                values.put(TBM_ANSWER_TIME, ClsAnswer.getThoiGian());
                values.put(TBM_ANSWER_SDT, ClsAnswer.getSDT());

                // Thêm dữ liệu
                database.insert(TABLE_NAME_ANSWER, null, values);

                // Đóng kết nối
                database.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //function update dữ liệu answer
    public void UpdateValueAnswer(A9_Cls_Answer ClsAnswer) {
        try {
            // Khởi tạo đối tượng SQLite
            SQLiteDatabase database = this.getWritableDatabase();

            // Khởi tạo đối tượng ContentValues ( dùng để lưu dữ liệu)
            ContentValues values = new ContentValues();
            values.put(TBM_ANSWER_MACH, ClsAnswer.getMaCH());
            values.put(TBM_ANSWER_ANSWER, ClsAnswer.getTraLoi());
            values.put(TBM_ANSWER_TIME, ClsAnswer.getThoiGian());
            values.put(TBM_ANSWER_SDT, ClsAnswer.getSDT());

            // Thêm dữ liệu
            database.update(TABLE_NAME_ANSWER, values, TBM_ANSWER_MACH, null);

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

    //function kiểm tra câu hỏi đã trả lời chưa ?
    public int getCountAnswer(String MaCH) {
        try {
            String countQuery = "SELECT * FROM " + TABLE_NAME_ANSWER
                    + " WHERE " + TBM_ANSWER_MACH + " = '" + MaCH + "'";
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
                A9_Cls_Question question1 = new A9_Cls_Question("CH01", "Bạn có ý định mua thêm sản phẩm của chúng tôi ?", "Trắc Nghiệm");
                A9_Cls_Question question2 = new A9_Cls_Question("CH02", "Những yêu cầu bạn muốn chúng tôi thay đổi để phục vụ tốt hơn ?", "Tự Luận");
                A9_Cls_Question question3 = new A9_Cls_Question("CH03", "Bạn đã sử dụng sản phẩm của công ty chúng tôi trong bao lâu ?", "Tự Luận");
                A9_Cls_Question question4 = new A9_Cls_Question("CH04", "Hãy đánh giá mức độ hài lòng của bạn đối với sản phẩm công ty chúng tôi ?", "Đánh Giá");
                A9_Cls_Question question5 = new A9_Cls_Question("CH05", "Bạn sẽ tiếp tục ủng hộ sản phẩm của công ty chúng tôi chứ ?", "Đánh Giá");
                A9_Cls_Question question6 = new A9_Cls_Question("CH06", "Bạn sẽ vẫn tiếp tục ủng hộ dòng sản phẩm này chứ ?", "Trắc Nghiệm");
                A9_Cls_Question question7 = new A9_Cls_Question("CH07", "Bạn sẽ giới thiệu cho người khác về sản phẩm này ?", "Trắc Nghiệm");

                // thêm câu hỏi vào database
                this.addValueQuestion(question1);
                this.addValueQuestion(question2);
                this.addValueQuestion(question3);
                this.addValueQuestion(question4);
                this.addValueQuestion(question5);
                this.addValueQuestion(question6);
                this.addValueQuestion(question7);
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

    // Search câu hỏi
    public List<A9_Cls_Question> getAllQuestion(String quesType) {
        try {
            List<A9_Cls_Question> typeList = new ArrayList<A9_Cls_Question>();

            // Script query search
            String script = "SELECT " + TBM_QUES_NAME + " FROM " + TABLE_NAME_QUESTION
                    + " WHERE " + TBM_QUES_TYPE + " = '" + quesType + "'";

            // khởi tạo đối tượng SQLite
            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.rawQuery(script, null);

            // Duyệt danh sách search
            if (cursor.moveToFirst()) {
                do {
                    A9_Cls_Question clsQues = new A9_Cls_Question();
                    clsQues.setMaCH("");
                    clsQues.setCauHoi(cursor.getString(0));
                    clsQues.setLoaiCH("");

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

    // Search câu hỏi
    public String getAllQuestionID(String question) {
        try {
            String QuesID = "";

            // Script query search
            String script = "SELECT " + TBM_QUES_ID + " FROM " + TABLE_NAME_QUESTION
                    + " WHERE " + TBM_QUES_NAME + " = '" + question + "'";

            // khởi tạo đối tượng SQLite
            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.rawQuery(script, null);

            // Duyệt danh sách search
            if (cursor.moveToFirst()) {
                do {
                    A9_Cls_Question clsQues = new A9_Cls_Question();
                    clsQues.setMaCH(cursor.getString(0));
                    clsQues.setCauHoi("");
                    clsQues.setLoaiCH("");

                    // thêm dữ liệu vào list
                    QuesID = clsQues.getMaCH();

                } while (cursor.moveToNext());
            }

            return QuesID;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Search toàn bộ câu trả lời
    public List<A9_Cls_Answer> getAllAnswer() {
        try {
            List<A9_Cls_Answer> answerList = new ArrayList<A9_Cls_Answer>();

            // Script query search
            String script = "SELECT * FROM " + TABLE_NAME_ANSWER;

            // khởi tạo đối tượng SQLite
            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.rawQuery(script, null);

            // Duyệt danh sách search
            if (cursor.moveToFirst()) {
                do {
                    A9_Cls_Answer clsAnswer = new A9_Cls_Answer();
                    clsAnswer.setMaCH(cursor.getString(0));
                    clsAnswer.setTraLoi(cursor.getString(1));
                    clsAnswer.setThoiGian(cursor.getString(2));
                    clsAnswer.setSDT(cursor.getString(3));

                    // thêm dữ liệu vào list
                    answerList.add(clsAnswer);

                } while (cursor.moveToNext());
            }
            return answerList;
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
