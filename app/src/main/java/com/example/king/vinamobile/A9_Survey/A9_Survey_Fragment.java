package com.example.king.vinamobile.A9_Survey;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.king.vinamobile.R;

/**
 * Create :
 * Activity: Customer survey
 * Day: 10/02/2019
 * By: Phuhq
 */
public class A9_Survey_Fragment extends Fragment {


    public A9_Survey_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a9__survey_, container, false);
    }

}
