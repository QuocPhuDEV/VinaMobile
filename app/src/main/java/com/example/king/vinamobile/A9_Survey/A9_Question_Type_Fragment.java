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
import android.widget.Toast;

import com.example.king.vinamobile.A8_SignIn.A8_SignIn_Fragment;
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
        onSelectItemQuestionType();
    }
    //endregion

    //region XỬ LÝ SỰ KIỆN
    // load danh sách loại câu hỏi
    public void loadListView() {
        try {
            // Xóa dữ liệu cũ trên lưới
            this.typeList.clear();

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

    // xử lý click chọn loại câu hỏi
    public void onSelectItemQuestionType() {
        try {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String type = typeList.get(i).toString();
                    if (type.equals(getString(R.string.a9_ques_type_choice))) {
                        A9_YesNo_Fragment king = new A9_YesNo_Fragment();
                        loadFragment(king);
                        //Toast.makeText(getContext(), type, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Load Fragment
    private void loadFragment(Fragment fragment) {
        try {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frame_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //endregion

}
