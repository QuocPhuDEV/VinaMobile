package com.example.king.vinamobile.A1_Login;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.king.vinamobile.A0_Sqlite_Connection.Create_Table;
import com.example.king.vinamobile.A2_Change_Password.A2_ChangePass_Activity;
import com.example.king.vinamobile.A_Json.JsonReader;
import com.example.king.vinamobile.MainActivity;
import com.example.king.vinamobile.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

public class A1_Login_Activity extends AppCompatActivity {
    //region KHAI BÁO BIẾN TOÀN CỤC
    private EditText edUser, edPassword;
    private Button btnLogion, btnCancel;
    private CheckBox chkShowPass;

    public static final int REQUEST_ID_PHONE_NUMBER = 100;

    //public static final String URL = "https://next.json-generator.com/api/json/get/Vk0DSK0-U";
    public static final String URL = "http://www.json-generator.com/api/json/get/cenQYrriTC?indent=2";


    //endregion

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a1__login_);

        // Ánh xạ đối tượng
        addEvents();
        // Tạo dữ liệu mặc định
        CreateDefaultData();
        // Hỏi quyền truy cập của ứng dụng
        askPermission();
        // Đồng bộ dữ liệu
        SyncAccountNow();
    }

    @Override
    protected void onResume() {
        super.onResume();
        edPassword.setText(null);
    }

    //region ÁNH XẠ ĐỐI TƯỢNG
    public void addEvents() {
        edUser = (EditText) findViewById(R.id.a1_edUsername);
        edPassword = (EditText) findViewById(R.id.a1_edPassword);
        btnLogion = (Button) findViewById(R.id.a1_btnLogin);
        btnCancel = (Button) findViewById(R.id.a1_btnCancel);
        chkShowPass = (CheckBox) findViewById(R.id.chkShowPass);
    }
    //endregion

    //region LOAD FORM

    // Khởi tạo giá trị mặc định
    public void CreateDefaultData() {
        try {
            Create_Table createTable = new Create_Table(this);
            createTable.CreateDefaultAccount(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Gọi phương thức đồng bộ
    public void SyncAccountNow() {
        try {
            // Xoá dữ liệu trong bảng account
            Create_Table create_table = new Create_Table(this);
            create_table.deleteDataAccount();

            // đồng bộ dữ liệu mới
            new MyJsonTask().execute(URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Load menu change password
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.a2_changepass_menu, menu);
        return true;
    }

    // Mở màn hình change password
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.a2_item_change_pass) {
            Intent intent = new Intent(this, A2_ChangePass_Activity.class);
            intent.putExtra("phoneNumber", edUser.getText().toString());
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    //endregion

    //region XỬ LÝ EVENTS

    // Sự kiện click button Logion
    public void A1_btnLogin(View view) {
        try {
            String sdt = edUser.getText().toString();
            String pass = edPassword.getText().toString();
            boolean result;

            // Kiểm tra rỗng
            if (checkKeycode()) {
                Create_Table account = new Create_Table(this);
                result = account.checkExistsAccount(sdt, pass);

                if (result) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    //Toast.makeText(this, "go", Toast.LENGTH_SHORT).show();
                } else {
                    edPassword.setError(getString(R.string.a1_error_wrong_pass));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Sự kiện click button Cancel
    public void A1_btnCancel(View view) {
        try {
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Sự kiện check hiển thị mật khẩu
    public void ShowPassword(View view) {
        if (chkShowPass.isChecked()) {
            edPassword.setTransformationMethod(null);
        } else {
            edPassword.setTransformationMethod(new PasswordTransformationMethod());
        }
    }

    // Kiểm tra nhập liệu
    public boolean checkKeycode() {

        if (TextUtils.isEmpty(edUser.getText().toString()) || TextUtils.isEmpty(edPassword.getText().toString())) {
            Toast.makeText(this, R.string.a1_error_empty, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    // Xử lý format số điện thoại
    public String changeFormatPhoneNumber(String phoneNumber) {
        String mPhoneNumber = "";

        if (phoneNumber.indexOf("+") != -1) {
            mPhoneNumber = phoneNumber.replace("+84", "0");
        } else {
            mPhoneNumber = phoneNumber;
        }
        return mPhoneNumber;
    }

    // Lấy số điện thoại
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getPhoneNumber() {
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            // Nếu đã được cấp quyền truy cập, lấy sdt, gán lên editText
            TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            String mPhoneNumber = manager.getLine1Number();
            edUser.setText(changeFormatPhoneNumber(mPhoneNumber));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Kiểm tra quyền truy cập sđt
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void askPermission() {
        try {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {

                // Nếu đã được cấp quyền truy cập, lấy sdt, gán lên editText
                getPhoneNumber();
            } else {
                // Nếu chưa được gán quyền truy cập sdt.
                // Hỏi quyền truy cập
                String[] permissions = new String[]{Manifest.permission.READ_PHONE_NUMBERS, Manifest.permission.READ_PHONE_STATE};

                // Hiển thị dialog để xác nhận
                ActivityCompat.requestPermissions(this, permissions, REQUEST_ID_PHONE_NUMBER);

                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Phản hồi quyền truy cập
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        try {
            if (requestCode == REQUEST_ID_PHONE_NUMBER) {

                // Nếu người dùng đã xác nhận cấp quyền
                if (grantResults.length > 1
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    // Nếu đã được cấp quyền truy cập, lấy sdt, gán lên editText
                    getPhoneNumber();
                } else {
                    Toast.makeText(this, R.string.a1_error_permissions, Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Xử lý đa tiến trình
    public class MyJsonTask extends AsyncTask<String, JSONObject, Void> {
        public ProgressDialog dialog = new ProgressDialog(A1_Login_Activity.this);

        @Override
        protected void onPreExecute() {
            dialog.setMessage(getString(R.string.a1_progress_sync));
            dialog.show();
        }

        @Override
        protected Void doInBackground(String... strings) {

            // Lấy url truyền vào
            String url = strings[0];
            JSONObject jsonObject;
            try {
                // Đọc Json và chuyển về Object
//                jsonObject = JsonReader.readFileJsonFromUrl(url);
//                publishProgress(jsonObject);

                JSONArray jsonArray = JsonReader.readFileJsonFromUrl(url);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    publishProgress(object);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(JSONObject... values) {
            super.onProgressUpdate(values);

            try {
                //Get dữ liệu
                JSONObject jsonObject = values[0];

                //JSONObject object = jsonObject.getJSONObject("");

                SyncData(jsonObject);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            dialog.dismiss();
        }
    }

    // Kiểm tra kết nối internet
    private boolean isNetWorkConnected() {
        try {
            // Kiểm tra kết nối, trả về true nếu có kết nối internet
            ConnectivityManager cn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            return cn.getActiveNetworkInfo() != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Thực hiện đồng độ dữ liệu nếu có kết nối internet
    public void SyncData(JSONObject jsonObject) {
        try {
            if (isNetWorkConnected()) {
                Create_Table create_table = new Create_Table(this);
                create_table.syncAccount(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //endregion
}
