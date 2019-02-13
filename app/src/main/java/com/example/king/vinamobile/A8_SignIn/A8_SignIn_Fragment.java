package com.example.king.vinamobile.A8_SignIn;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.king.vinamobile.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Create:
 * Activity: Sign In
 * Date: 10/02/2019
 * By: Phuhq
 */
public class A8_SignIn_Fragment extends Fragment {
    //region KHAI BÁO BIẾN TOÀN CỤC
    //private TextView DateOfBirth;
    private Spinner spZone, spCity, spCounty;
    private FloatingActionButton addNewImage;
    private CircleImageView imgAvt;
    private EditText edFullName, edDateOfBirth, edPhoneNo, edDetailAddress;
    //private Button btnCancel, btnSignIn;

    private DatePickerDialog.OnDateSetListener onDateSetListener;

    public static final int PICK_IMAGE = 1;

    //endregion


    public A8_SignIn_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            View view = inflater.inflate(R.layout.fragment_a8__sign_in_, container, false);

            // ánh xạ
            edDateOfBirth = (EditText) view.findViewById(R.id.a8_ed_date_of_birth);
            spZone = (Spinner) view.findViewById(R.id.a8_sp_zone);
            spCity = (Spinner) view.findViewById(R.id.a8_sp_city);
            spCounty = (Spinner) view.findViewById(R.id.a8_sp_county);
            addNewImage = (FloatingActionButton) view.findViewById(R.id.a8_btnAddNewImg);
            imgAvt = (CircleImageView) view.findViewById(R.id.a8_img_avt);
            edFullName = (EditText) view.findViewById(R.id.a8_ed_full_name);
            edPhoneNo = (EditText) view.findViewById(R.id.a8_ed_phone_number);
            edDetailAddress = (EditText) view.findViewById(R.id.a8_ed_address);
//            btnCancel = (Button) view.findViewById(R.id.a8_btn_cancel);
//            btnSignIn = (Button) view.findViewById(R.id.a8_btn_submit);


            // Sự kiệm form load
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
        setAddress();
        setImageAvt();

    }
    //endregion

    //region XỬ LÝ LOAD FORM
    // khởi tạo date picker ngày tháng
    public void setDateOfBirth() {
        try {
            edDateOfBirth.setOnClickListener(new View.OnClickListener() {
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
                    edDateOfBirth.setText(date);
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // khởi tạo giá trị cho các spinner địa chỉ
    public void setAddress() {
        try {

            // Khởi tạo giá trị mặc định address
            A8_DBHelper dbHelper = new A8_DBHelper(getContext());
            dbHelper.createDefaultData();

            // Khởi tạo tiêu đề cho từng spinner
            String title_spZone = getString(R.string.a8_title_spinner_zone);
            final String title_spCity = getString(R.string.a8_title_spinner_city);
            final String title_spCounty = getString(R.string.a8_title_spinner_county);

            // Lấy danh sách các record có trong bảng
            List<A8_Cls_City> listKhuVuc = dbHelper.getAllDataZone(title_spZone);
            // thêm danh sách dữ liệu vào adapter
            ArrayAdapter<String> adapterZone = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listKhuVuc);
            adapterZone.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
            // Gán giá trị cho spZone
            spZone.setAdapter(adapterZone);


            spZone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    A8_DBHelper dbHelper = new A8_DBHelper(getContext());
                    String khuvuc = spZone.getSelectedItem().toString();

                    // Lấy danh sách các tỉnh tương ứng với khu vục
                    List<A8_Cls_City> listTinhTP = dbHelper.getAllDataCity(khuvuc, title_spCity);
                    ArrayAdapter<String> adapterCity = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listTinhTP);
                    adapterCity.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                    spCity.setAdapter(adapterCity);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    A8_DBHelper dbHelper = new A8_DBHelper(getContext());
                    String TinhTP = spCity.getSelectedItem().toString();

                    List<A8_Cls_City> listQuanHuyen = dbHelper.getAllDataCounty(TinhTP, title_spCounty);
                    ArrayAdapter<String> adapterCounty = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listQuanHuyen);
                    adapterCounty.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                    spCounty.setAdapter(adapterCounty);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // khởi tạo menu form
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.a8_menu_complete, menu);
    }
    //endregion

    //region XỬ LÝ TÁC VỤ TRONG FORM
    // chọn ảnh từ thư viện
    public void setImageAvt() {
        addNewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    getIntent.setType("image/*");

                    Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    pickIntent.setType("image/*");

                    Intent chooserIntent = Intent.createChooser(getIntent, getString(R.string.a8_title_select_photo));

                    startActivityForResult(chooserIntent, PICK_IMAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // gán ảnh đã chọn lên imageView
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (requestCode == PICK_IMAGE) {
                Uri selectedImage = data.getData();
                imgAvt.setImageURI(selectedImage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // sự kiện button đăng ký
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        checkEmpty();

        return super.onOptionsItemSelected(item);

    }

    //endregion

    //region RÀNG BUỘC ĐIỀU KIỆN TRÊN FORM

    // Kiểm tra rỗng
    public void checkEmpty() {
        try {

            if (TextUtils.isEmpty(edDetailAddress.getText().toString())) {
                edDetailAddress.setError(getString(R.string.a8_error_empty_detail_address));
            }

            if (TextUtils.isEmpty(edDateOfBirth.getText().toString())) {
                edDateOfBirth.setError(getString(R.string.a8_error_empty_date_of_birth));
            }else {
                edDateOfBirth.setError(null);
            }

            if (TextUtils.isEmpty(edPhoneNo.getText().toString())) {
                edPhoneNo.setError(getString(R.string.a8_error_empty_phone_number));
            }

            if (TextUtils.isEmpty(edFullName.getText().toString())) {
                edFullName.setError(getString(R.string.a8_error_empty_full_name));
            }

            if (spZone.getSelectedItem().toString().equals(getString(R.string.a8_title_spinner_zone))) {
                Toast.makeText(getContext(), R.string.a8_error_empty_zone, Toast.LENGTH_SHORT).show();
            } else if (spCity.getSelectedItem().toString().equals(getString(R.string.a8_title_spinner_city))) {
                Toast.makeText(getActivity(), R.string.a8_error_empty_city, Toast.LENGTH_SHORT).show();
            } else if (spCounty.getSelectedItem().toString().equals(getString(R.string.a8_title_spinner_county))) {
                Toast.makeText(getActivity(), R.string.a8_error_empty_county, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //endregion

}
