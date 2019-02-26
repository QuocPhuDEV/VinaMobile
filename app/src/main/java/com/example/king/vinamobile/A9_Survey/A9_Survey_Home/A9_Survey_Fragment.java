package com.example.king.vinamobile.A9_Survey.A9_Survey_Home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.king.vinamobile.A9_Survey.A9_Survey.A9_Question_Type_Fragment;
import com.example.king.vinamobile.A9_Survey.A9_Survey_History.A9_His_Fragment;
import com.example.king.vinamobile.A9_Survey.A9_Survey_History.A9_History_Fragment;
import com.example.king.vinamobile.R;

/**
 * Create :
 * Activity: Customer survey
 * Day: 10/02/2019
 * By: Phuhq
 */
public class A9_Survey_Fragment extends Fragment {

    //region KHAI BÁO BIẾN TOÀN CỤC
    private Button menu_Survey, menu_History;
    //endregion


    public A9_Survey_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            View view = inflater.inflate(R.layout.fragment_a9__survey_, container, false);

            // Ánh xạ đối tượng
            menu_Survey = (Button) view.findViewById(R.id.a9_btn_survey);
            menu_History = (Button) view.findViewById(R.id.a9_btn_his);

            // Gọi sự kiện
            onClickMenu();

            return view;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //region XỬ LÝ SỰ KIỆN TRÊN FORM
    //xử lý click menu
    public void onClickMenu() {
        try {
            // Menu Survey
            menu_Survey.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    A9_Question_Type_Fragment question_type_fragment = new A9_Question_Type_Fragment();
                    loadFragment(question_type_fragment);
                }
            });

            // Menu History
            menu_History.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    A9_His_Fragment a9_history_fragment = new A9_His_Fragment();
                    loadFragment(a9_history_fragment);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // gọi form
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
