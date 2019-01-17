package com.example.king.vinamobile.A7_Profile;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.king.vinamobile.A0_Sqlite_Connection.Create_Table;
import com.example.king.vinamobile.A1_Login.A1_Cls_Account;
import com.example.king.vinamobile.MainActivity;
import com.example.king.vinamobile.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class A7_Profile_Fragment extends Fragment {
    //region KHAI BÁO BIẾN TOÀN CỤC

    private final List<A1_Cls_Account> listAccount = new ArrayList<A1_Cls_Account>();

    public static final String PHONE = "PHONE";
    public static final String PASSWORD = "PASSWORD";

    private TextView tvHoTen, tvDiaChi, tvSDT;
    private CircleImageView imgAvt;

    public String NgayTao;
    public String NgayCN;
    public String SDT;
    public String MatKhau;
    public String MaKH;
    public String TenKH;
    public String DiaChi;
    public String Img;


    //endregion


    public A7_Profile_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Lấy dữ liệu từ form main
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            this.SDT = bundle.getString(PHONE);
            this.MatKhau = bundle.getString(PASSWORD);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a7__profile_, container, false);
        tvHoTen = (TextView) view.findViewById(R.id.a7_profile_tv_full_name);
        tvDiaChi = (TextView) view.findViewById(R.id.a7_profile_tv_address);
        tvSDT = (TextView) view.findViewById(R.id.a7_profile_tv_phone_number);
        imgAvt = (CircleImageView) view.findViewById(R.id.a7_profile_img_avt);

        //getActivity().getActionBar().hide();


        getAllDataUserLogin();

        return view;
    }

    //region ÁNH XẠ ĐỐI TƯỢNG
    public void AddObject() {

    }
    //endregion

    //region XỬ LÝ EVENTS

    // Get thông tin user logion
    public void getAllDataUserLogin() {
        try {
            // Khởi tạo đối tượng database
            Create_Table database = new Create_Table(getContext());

            // Lấy thông tin từ user đã login
            List<A1_Cls_Account> list = database.getDataAccount(this.SDT, this.MatKhau);
            this.listAccount.addAll(list);
            this.TenKH = listAccount.get(0).getTenKH();
            this.SDT = listAccount.get(0).getSDT();
            this.DiaChi = listAccount.get(0).getDiaChi();
            this.Img = listAccount.get(0).getHinhAnh();

            // Gán giá trị lên form
            tvHoTen.setText(this.TenKH);
            tvSDT.setText(this.SDT);
            tvDiaChi.setText(this.DiaChi);


            // Load image cho avatar
            // Kiểm tra nết nối internet
            if (isNetWorkConnected()) {
                if (TextUtils.isEmpty(this.Img)) {
                    imgAvt.setImageResource(R.drawable.a1_image_login);
                } else {
                    new loadImageFromURL(imgAvt).execute(Img);
                }
            } else {
                imgAvt.setImageResource(R.drawable.a1_image_login);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Kiểm tra kết nối internet
    private boolean isNetWorkConnected() {
        try {
            // Kiểm tra kết nối, trả về true nếu có kết nối internet
            ConnectivityManager cn = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            return cn.getActiveNetworkInfo() != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Load image avatar
    private class loadImageFromURL extends AsyncTask<String, Void, Bitmap> {
        ImageView image;

        public loadImageFromURL(ImageView bmImage) {
            this.image = bmImage;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            try {
                image.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //endregion

}
