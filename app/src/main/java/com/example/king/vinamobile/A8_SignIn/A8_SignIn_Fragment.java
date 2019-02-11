package com.example.king.vinamobile.A8_SignIn;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.king.vinamobile.R;

import java.util.Calendar;

/**
 * Create:
 * Activity: Sign In
 * Date: 10/02/2019
 * By: Phuhq
 */
public class A8_SignIn_Fragment extends Fragment {
    //region KHAI BÁO BIẾN TOÀN CỤC
    private TextView DateOfBirth;

    private DatePickerDialog.OnDateSetListener onDateSetListener;
    //endregion


    public A8_SignIn_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            View view = inflater.inflate(R.layout.fragment_a8__sign_in_, container, false);

            // ánh xạ
            DateOfBirth = (TextView) view.findViewById(R.id.a8_ed_date_of_birth);
            LoadForm();

            return view;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //region LOAD FORM
    public void LoadForm() {
        setDateOfBirth();
    }
    //endregion

    //region XỬ LÝ SỰ KIỆN

    //set date of birth
    public void setDateOfBirth() {
        try {
            DateOfBirth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dialog = new DatePickerDialog(
                            getActivity(),
                            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                            onDateSetListener,
                            year, month, day);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            });

            onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    month = month + 1;
                    String date = day + "/" + month + "/" + year;
                    DateOfBirth.setText(date);
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //endregion

}
