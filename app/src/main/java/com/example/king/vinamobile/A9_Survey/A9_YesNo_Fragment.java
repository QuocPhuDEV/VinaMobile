package com.example.king.vinamobile.A9_Survey;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.king.vinamobile.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Create :
 * Activity: Yes No Survey
 * Day: 15/02/2019
 * By: Phuhq
 */
public class A9_YesNo_Fragment extends Fragment {

    //region KHAI BÁO BIẾN TOÀN CỤC
    private ListView listView;
    private A9_YesNo_Adapter adapter;
    private final List<A9_Cls_Question> quesList = new ArrayList<A9_Cls_Question>();
    //endregion


    public A9_YesNo_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            View view = inflater.inflate(R.layout.fragment_a9__yes_no_, container, false);
            // Ánh xạ đối tượng
            listView = (ListView) view.findViewById(R.id.a9_list_content);

            // Gọi sự kiện
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
            this.quesList.clear();

            // Khởi tạo đối tượng SQlite
            A9_DBHelper dbHelper = new A9_DBHelper(getContext());
            //dbHelper.createDefaultData();

            // Lấy danh sách loại câu hỏi
            List<A9_Cls_Question> questionList = dbHelper.getAllQuestion(getString(R.string.a9_ques_type_choice));
            this.quesList.addAll(questionList);

            // tạo list adapter
            adapter = new A9_YesNo_Adapter(getContext(), R.layout.a9_item_question_list, quesList);

            // gán giá trị adapter cho listview
            this.listView.setAdapter(adapter);

            // đăng ký context menu cho listview
            registerForContextMenu(this.listView);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // xử lý click chọn loại câu hỏi

    //endregion

}
