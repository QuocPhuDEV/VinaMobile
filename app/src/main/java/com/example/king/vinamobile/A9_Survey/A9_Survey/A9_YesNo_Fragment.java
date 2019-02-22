package com.example.king.vinamobile.A9_Survey.A9_Survey;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.king.vinamobile.A9_Survey.A9_Survey_Class.A9_Cls_Answer;
import com.example.king.vinamobile.A9_Survey.A9_Survey_Class.A9_Cls_Question;
import com.example.king.vinamobile.A9_Survey.A9_Survey_Database.A9_DBHelper;
import com.example.king.vinamobile.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Create :
 * Activity: Yes No Survey
 * Day: 15/02/2019
 * By: Phuhq
 */
public class A9_YesNo_Fragment extends Fragment {

    //region KHAI BÁO BIẾN TOÀN CỤC
    private ListView listView;
    private A9_YesNo_Adapter adapter;
    private final List<A9_Cls_Question> quesList = new ArrayList<A9_Cls_Question>();

    // biến nhận giá trị câu hỏi và trả lời
    public String MaCH, CauHoi;
    //endregion


    public A9_YesNo_Fragment() {
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
            View view = inflater.inflate(R.layout.fragment_a9__yes_no_, container, false);
            // Ánh xạ đối tượng
            listView = (ListView) view.findViewById(R.id.a9_list_content);

            // Gọi sự kiện
            formLoad();

            return view;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //region FORM LOAD
    public void formLoad() {
        loadListView();
        onSelectItemQuestions();
    }

    // khởi tạo menu form
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.a9_yesno_answer_complete_menu, menu);
    }
    //endregion

    //region XỬ LÝ SỰ KIỆN
    // load danh sách loại câu hỏi
    public void loadListView() {
        try {
            // Xóa dữ liệu cũ trên lưới
            this.quesList.clear();

            // Khởi tạo đối tượng SQlite
            A9_DBHelper dbHelper = new A9_DBHelper(getContext());
            //dbHelper.createDefaultData();

            // Lấy danh sách loại câu hỏi
            List<A9_Cls_Question> questionList = dbHelper.getAllQuestion(getString(R.string.a9_ques_type_choice));
            this.quesList.addAll(questionList);

            // tạo list adapter
            adapter = new A9_YesNo_Adapter(getContext(), R.layout.a9_item_question_list, quesList);

            // gán giá trị adapter cho listview
            this.listView.setAdapter(adapter);

            // đăng ký context menu cho listview
            registerForContextMenu(this.listView);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // xử lý click trả lời câu hỏi
    public void onSelectItemQuestions() {
        try {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    // lấy câu hỏi đã chọn
                    CauHoi = quesList.get(i).toString();
                    // lấy mã câu hỏi đã chọn
                    A9_DBHelper dbHelper = new A9_DBHelper(getContext());
                    MaCH = dbHelper.getAllQuestionID(CauHoi);

                    // xác nhận trả lời câu hỏi
                    confirmAnswer();

                    // đổi màu sau khi trả lời xong
//                    if (changeColor) {
//                        view.setBackgroundColor(getResources().getColor(R.color.blue));
//                    }


                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Xác nhận câu trả lời
    public void confirmAnswer() {
        try {
            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setTitle(getString(R.string.main_confirm_logout_title_alert));
            //dialog.setMessage(getString(R.string.main_confirm_logout_message_alert));

            DialogInterface.OnClickListener clickListener = new DialogInterface.OnClickListener() {

                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String traLoi, time, sdt;
                    switch (i) {
                        case DialogInterface.BUTTON_POSITIVE:
                            // trả lời có
                            traLoi = getString(R.string.main_confirm_logout_button_ok_alert);
                            time = getCurrentDateTime();
                            sdt = getPhoneNumber();
                            addAnswer(MaCH, traLoi, time, sdt);
                            break;
                        case DialogInterface.BUTTON_NEGATIVE:
                            // trả lời không
                            traLoi = getString(R.string.main_confirm_logout_button_cancel_alert);
                            time = getCurrentDateTime();
                            sdt = getPhoneNumber();
                            addAnswer(MaCH, traLoi, time, sdt);
                            break;
                        case DialogInterface.BUTTON_NEUTRAL:
                            break;
                    }
                }
            };

            dialog.setPositiveButton(getString(R.string.main_confirm_logout_button_ok_alert), clickListener);
            dialog.setNegativeButton(getString(R.string.main_confirm_logout_button_cancel_alert), clickListener);
            dialog.setNeutralButton(getString(R.string.a9_ys_answer_cancel), clickListener);
            dialog.setIcon(R.drawable.main_user_confirm);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Thêm dữ liệu vào bảng trả lời câu hỏi
    public void addAnswer(String maCH, String traLoi, String time, String sdt) {
        try {
            A9_Cls_Answer a9ClsAnswer = new A9_Cls_Answer(maCH, traLoi, time, sdt);
            A9_DBHelper dbHelper = new A9_DBHelper(getContext());
            dbHelper.addValueAnswer(a9ClsAnswer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Lấy ngày tháng giờ phút hiện tại
    public String getCurrentDateTime() {
        try {
            String currentDateTime;
            Calendar c = Calendar.getInstance();
            SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            currentDateTime = dateformat.format(c.getTime());
            return currentDateTime;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Lấy số điện thoại
    @RequiresApi(api = Build.VERSION_CODES.M)
    public String getPhoneNumber() {
        try {
            String phoneNumber;
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return null;
            }

            // Nếu đã được cấp quyền truy cập, lấy sdt, gán lên editText
            TelephonyManager manager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
            String mPhoneNumber = manager.getLine1Number();
            phoneNumber = changeFormatPhoneNumber(mPhoneNumber);

            return phoneNumber;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Xử lý format số điện thoại
    public String changeFormatPhoneNumber(String phoneNumber) {
        String mPhoneNumber = "";
        try {
            if (phoneNumber.indexOf("+") != -1) {
                mPhoneNumber = phoneNumber.replace("+84", "0");
            } else {
                mPhoneNumber = phoneNumber;
            }
            return mPhoneNumber;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    // sự kiện button hoàn tất
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try {
//            A9_Question_Type_Fragment typeFragment = new A9_Question_Type_Fragment();
//            loadFragment(typeFragment);
            getActivity().onBackPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onOptionsItemSelected(item);
    }

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
