package com.example.king.vinamobile.A3_Scan;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.king.vinamobile.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class A3_Scan_Home_Fragment extends Fragment {

    //region KHAI BÁO BIẾN TOÀN CỤC
    Button btnScan;
    //endregion

    public A3_Scan_Home_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a3__scan__home_, container, false);
        btnScan = (Button) view.findViewById(R.id.btn_scan);
        OpenScanFragment();
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

    // Open Scan Fragment Activity
    public void OpenScanFragment() {
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                A3_Scan_Fragment fragment = new A3_Scan_Fragment();
                loadFragment(fragment);
            }
        });
    }

    //endregion

}
