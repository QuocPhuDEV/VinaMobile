package com.example.king.vinamobile.A6_Menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.king.vinamobile.A3_Scan.A3_Scan_Fragment;
import com.example.king.vinamobile.A8_SignIn.A8_SignIn_Fragment;
import com.example.king.vinamobile.A9_Survey.A9_Survey_Home.A9_Survey_Fragment;
import com.example.king.vinamobile.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class A6_Menu_Fragment extends Fragment {

    // region KHAI BÁO BIẾN TOÀN CỤC
    private ImageView menuScan, menuProducts, menuAccount, menuSurvey;

    //endregion


    public A6_Menu_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a6__menu_, container, false);
        menuScan = (ImageView) view.findViewById(R.id.a6_img_menu_scan);
        menuProducts = (ImageView) view.findViewById(R.id.a6_img_menu_product);
        menuAccount = (ImageView) view.findViewById(R.id.a6_img_menu_account);
        menuSurvey = (ImageView) view.findViewById(R.id.a6_img_menu_survey);


        // Gọi sự kiện xử lý
        onClickMenu();

        return view;
    }

    //region XỬ LÝ EVENTS

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

    // xử lý sự kiệ click menu
    public void onClickMenu() {
        try {
            // Menu Scan
            menuScan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Fragment fragment;
                        fragment = new A3_Scan_Fragment();
                        loadFragment(fragment);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            // Menu Account
            menuAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Fragment fragment;
                        fragment = new A8_SignIn_Fragment();
                        loadFragment(fragment);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            // Menu Customer Survey
            menuSurvey.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Fragment fragment;
                        fragment = new A9_Survey_Fragment();
                        loadFragment(fragment);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //endregion

}
