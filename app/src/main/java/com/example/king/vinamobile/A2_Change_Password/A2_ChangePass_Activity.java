package com.example.king.vinamobile.A2_Change_Password;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.king.vinamobile.A0_Sqlite_Connection.Create_Table;
import com.example.king.vinamobile.R;

public class A2_ChangePass_Activity extends AppCompatActivity {
    //region KHAI BÁO BIẾN TOÀN CỤC
    private EditText edOldPassword, edNewPassword, edConfirmPassword;
    private Button btnChangePass, btnCancel;
    private CheckBox chkShowPass;

    public String phoneNumber;

    public static final int REQUEST_ID_PHONE_NUMBER = 100;

    public static final String URL = "http://www.json-generator.com/api/json/get/cenQYrriTC?indent=2";

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a2__change_pass_);
        addEvents();
        LoadForm();
    }

    //region ÁNH XẠ ĐỐI TƯỢNG
    public void addEvents() {
        edOldPassword = (EditText) findViewById(R.id.a2_edOldPassword);
        edNewPassword = (EditText) findViewById(R.id.a2_edNewPassword);
        edConfirmPassword = (EditText) findViewById(R.id.a2_edConfirmPassword);
        btnChangePass = (Button) findViewById(R.id.a2_btnChangePass);
        btnCancel = (Button) findViewById(R.id.a2_btnCancel);
        chkShowPass = (CheckBox) findViewById(R.id.a2_chkShowPass);
    }
    //endregion

    //region LOAD FORM
    public void LoadForm() {
        try {
            Intent intent = getIntent();
            phoneNumber = intent.getStringExtra("phoneNumber");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region XỬ LÝ FUNCTION

    // Kiểm tra mật khẩu cũ
    public boolean checkOldPassword() {
        boolean result = false;
        try {
            String oldPassword = edOldPassword.getText().toString();
            Create_Table create_table = new Create_Table(this);
            Boolean check = create_table.checkExistsAccount(phoneNumber, oldPassword);
            if (check) {
                result = true;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

    // Kiểm tra trùng khớp mật khẩu mới
    public boolean checkMatchPassword() {
        boolean result = false;
        try {
            String newPassWord = edNewPassword.getText().toString();
            String confirmPassWord = edConfirmPassword.getText().toString();
            if (newPassWord.equals(confirmPassWord)) {
                result = true;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

    // Kiểm tra độ dài của mật khẩu
    public boolean checkLengthPassWord() {
        boolean result = false;
        try {
            String newPassword = edNewPassword.getText().toString();
            if (newPassword.length() < 4 || newPassword.length() > 6) {
                result = false;
            } else {
                result = true;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

    // Hiển thị mật khẩu
    public void showPassword(View view) {
        try {
            if (chkShowPass.isChecked()) {
                edOldPassword.setTransformationMethod(null);
                edNewPassword.setTransformationMethod(null);
                edConfirmPassword.setTransformationMethod(null);
            } else {
                edOldPassword.setTransformationMethod(new PasswordTransformationMethod());
                edNewPassword.setTransformationMethod(new PasswordTransformationMethod());
                edConfirmPassword.setTransformationMethod(new PasswordTransformationMethod());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region XỬ LÝ EVENTS

    // Xử lý button change pasword
    public void btnChangePass(View view) {
        try {
            if (checkOldPassword()) {
                if (checkLengthPassWord()) {
                    if (checkMatchPassword()) {
                        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
                    } else {
                        edConfirmPassword.setError(getString(R.string.a2_error_donotMatch));
                    }
                } else {
                    edNewPassword.setError(getString(R.string.a2_error_length));
                }
            } else {
                edOldPassword.setError(getString(R.string.a2_error_notExistsPass));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Xử lý button cancel
    public void btnCancel(View view) {
        this.onBackPressed();
    }

    //endregion
}
