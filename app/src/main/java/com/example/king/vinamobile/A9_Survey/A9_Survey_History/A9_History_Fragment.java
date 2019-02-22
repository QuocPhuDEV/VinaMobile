package com.example.king.vinamobile.A9_Survey.A9_Survey_History;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.king.vinamobile.A9_Survey.A9_Survey_Class.A9_Cls_Answer;
import com.example.king.vinamobile.A9_Survey.A9_Survey_Database.A9_DBHelper;
import com.example.king.vinamobile.R;

import java.util.ArrayList;
import java.util.List;

/**
 * phuhq - 22/02/2019
 */
public class A9_History_Fragment extends Fragment {
    //region KHAI BÁO BIẾN TOÀN CỤC
    private ListView listView;
    private A9_History_Adapter adapter;
    private final List<A9_Cls_Answer> answerList = new ArrayList<A9_Cls_Answer>();

    //endregion


    public A9_History_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            View view = inflater.inflate(R.layout.fragment_a9__history_, container, false);

            // ánh xạ
            listView = (ListView) view.findViewById(R.id.a9_list_content_his);

            // gọi sự kiện load form
            formLoad();
            return view;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //region FORM LOAD
    public void formLoad() {
        loadListView();
    }
    //endregion

    //region XỬ LÝ SỰ KIỆN
    // load danh sách loại câu hỏi
    public void loadListView() {
        try {
            // Xóa dữ liệu cũ trên lưới
            this.answerList.clear();

            // Khởi tạo đối tượng SQlite
            A9_DBHelper dbHelper = new A9_DBHelper(getContext());

            // Lấy danh sách loại câu hỏi
            List<A9_Cls_Answer> answerLists = dbHelper.getAllAnswer();
            this.answerList.addAll(answerLists);

            // tạo list adapter
            adapter = new A9_History_Adapter(getContext(), R.layout.a9_item_history_answer, answerList);

            // gán giá trị adapter cho listview
            this.listView.setAdapter(adapter);

            // đăng ký context menu cho listview
            registerForContextMenu(this.listView);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //endregion

}
