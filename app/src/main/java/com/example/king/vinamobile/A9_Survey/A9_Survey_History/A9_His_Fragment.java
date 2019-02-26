package com.example.king.vinamobile.A9_Survey.A9_Survey_History;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.king.vinamobile.A9_Survey.A9_Survey.A9_Question_Type_Fragment;
import com.example.king.vinamobile.A9_Survey.A9_Survey_Class.A9_Cls_Answer;
import com.example.king.vinamobile.A9_Survey.A9_Survey_Database.A9_DBHelper;
import com.example.king.vinamobile.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Create: by: phuhq 25/02/2019
 */
public class A9_His_Fragment extends Fragment {
    //region KHAI BÁO BIẾN TOÀN CỤC
    private SwipeMenuListView mListView;
    private A9_History_Adapter historyAdapter;
    private final List<A9_Cls_Answer> answerList = new ArrayList<A9_Cls_Answer>();
    //endregion


    public A9_His_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            View view = inflater.inflate(R.layout.fragment_a9__his_, container, false);

            // Ánh xạ
            mListView = (SwipeMenuListView) view.findViewById(R.id.a9_his_lvShow);

            // Gọi function xử lý
            loadForm();
            return view;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //region LOAD FORM
    public void loadForm() {
        try {
            loadListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region XỬ LÝ EVENTS
    public void loadListView() {
        try {
            // Xóa dữ liệu cũ trên lưới
            this.answerList.clear();

            // Gán chuyển động
            mListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);

            // Khởi tạo đối tượng SQlite
            A9_DBHelper dbHelper = new A9_DBHelper(getContext());

            // Lấy danh sách loại câu hỏi
            List<A9_Cls_Answer> answerLists = dbHelper.getAllAnswer();
            this.answerList.addAll(answerLists);

            // tạo list adapter
            historyAdapter = new A9_History_Adapter(getContext(), R.layout.a9_item_history_answer, answerList);

            // gán giá trị adapter cho listview
            this.mListView.setAdapter(historyAdapter);

            // thêm hiệu ứng của menu
            creatorSwipeMenu();

            // thêm sự kiện click của menu
            onClickSwipeMenu();

            // thêm sự kiện đóng mở của menu
            openCloseSwipeMenu();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Khởi tạo Swipe menu kéo từ bên phải sang
    public void creatorSwipeMenu() {
        try {
            SwipeMenuCreator creator = new SwipeMenuCreator() {
                @Override
                public void create(SwipeMenu menu) {
                    // BUTTON THAY ĐỔI
                    SwipeMenuItem itemChange = new SwipeMenuItem(getContext());
                    // set màu cho button
                    itemChange.setBackground(R.drawable.a9_radius_button_change);
                    // set icon cho button
                    itemChange.setIcon(R.drawable.a9_img_change);
                    // set width cho button
                    itemChange.setWidth(dp2px(80));
                    // add menu
                    menu.addMenuItem(itemChange);

                    // BUTTON XÓA
                    SwipeMenuItem itemDelete = new SwipeMenuItem(getContext());
                    // set màu cho button
                    itemDelete.setBackground(R.drawable.a9_radius_button_delete);
                    // set icon cho button
                    itemDelete.setIcon(R.drawable.a9_item_his_answer_trash);
                    // set width cho button
                    itemDelete.setWidth(dp2px(80));
                    // add menu
                    menu.addMenuItem(itemDelete);
                }
            };

            // set creator
            mListView.setMenuCreator(creator);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Xử lý click Swipe menu
    public void onClickSwipeMenu() {
        try {
            mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                    switch (index) {
                        case 0:
                            // change answer
                            changeItem();
                            break;
                        case 1:
                            // delete item
                            confirmDelete(position);
                            break;
                    }
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Xử lý open/close Swipe menu
    public void openCloseSwipeMenu() {
        try {
            mListView.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {
                @Override
                public void onMenuOpen(int position) {
                    //Toast.makeText(getContext(), "Open", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onMenuClose(int position) {
                    //Toast.makeText(getContext(), "Close", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // onClick Button thay đổi
    public void changeItem() {
        try {
            A9_Question_Type_Fragment question_type_fragment = new A9_Question_Type_Fragment();
            loadFragment(question_type_fragment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // onClick Button xóa
    public void deleteItem(int position) {
        try {
            String MaCH = answerList.get(position).toString();
            A9_DBHelper dbHelper = new A9_DBHelper(getContext());
            dbHelper.deleteAnswer(MaCH);
            loadListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Xác nhận xóa
    public void confirmDelete(final int position) {
        try {
            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setTitle(getString(R.string.main_confirm_logout_title_alert));
            dialog.setMessage(getString(R.string.a9_title_message_confirm_delete));

            DialogInterface.OnClickListener clickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    switch (i) {
                        case DialogInterface.BUTTON_POSITIVE:
                            deleteItem(position);
                            break;
                        case DialogInterface.BUTTON_NEGATIVE:
                            break;
                    }
                }
            };

            dialog.setPositiveButton(getString(R.string.a9_context_menu_delete), clickListener);
            dialog.setNegativeButton(getString(R.string.a9_context_menu_cancel), clickListener);
            dialog.setIcon(R.drawable.main_user_confirm);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    // Xử lý đơn vị dp
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
    //endregion

}
