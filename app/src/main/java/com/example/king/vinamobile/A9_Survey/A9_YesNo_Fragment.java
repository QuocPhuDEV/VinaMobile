package com.example.king.vinamobile.A9_Survey;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.king.vinamobile.R;

/**
 * Create :
 * Activity: Yes No Survey
 * Day: 15/02/2019
 * By: Phuhq
 */
public class A9_YesNo_Fragment extends Fragment {


    public A9_YesNo_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a9__yes_no_, container, false);
    }

}
