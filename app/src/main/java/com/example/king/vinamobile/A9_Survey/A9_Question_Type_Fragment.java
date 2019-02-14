package com.example.king.vinamobile.A9_Survey;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.king.vinamobile.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Create:
 * From : Question Type
 * By: PhuHQ
 * Date: 14/02/2019
 */
public class A9_Question_Type_Fragment extends Fragment {

    //region KHAI BÁO BIẾN TOÀN CỤC
    private ListView listView;
    private A9_Question_Type_Adapter adapter;
    private final List<A9_Cls_Question> typeList = new ArrayList<A9_Cls_Question>();
    //endregion


    public A9_Question_Type_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a9__question__type_, container, false);

        // Ánh xạ đối tượng
        listView = (ListView) view.findViewById(R.id.a9_list_content);

        // Gọi sự kiện
        formLoad();

        return view;
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
            // Khởi tạo đối tượng SQlite
            A9_DBHelper dbHelper = new A9_DBHelper(getContext());
            dbHelper.createDefaultData();

            // Lấy danh sách loại câu hỏi
            List<A9_Cls_Question> quesTypeList = dbHelper.getAllQuestionType();
            this.typeList.addAll(quesTypeList);

            // tạo list adapter
            adapter = new A9_Question_Type_Adapter(getContext(), R.layout.a9_item_new_survey, typeList);

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