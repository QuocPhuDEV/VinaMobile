package com.example.king.vinamobile.A9_Survey.A9_Survey;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.king.vinamobile.A9_Survey.A9_Survey_Class.A9_Cls_Question;
import com.example.king.vinamobile.A9_Survey.A9_Survey_Database.A9_DBHelper;
import com.example.king.vinamobile.R;

import java.util.List;

public class A9_Question_Type_Adapter extends BaseAdapter {
    //region KHAI BÁO BIẾN TOÀN CỤC
    private Context context;
    private int layout;
    private List<A9_Cls_Question> questionsTypeList;
    //endregion

    public A9_Question_Type_Adapter(Context context, int layout, List<A9_Cls_Question> questionsTypeList) {
        this.context = context;
        this.layout = layout;
        this.questionsTypeList = questionsTypeList;
    }

    @Override
    public int getCount() {
        return this.questionsTypeList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    // Ánh xạ đối tượng từ item layout
    static class viewHolders {
        public TextView tvQuesType, tvQuesCount;
        public ImageView imageView;
    }

    // Xử lý View
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            A9_Question_Type_Adapter.viewHolders viewHolder = null;
            if (view == null) {
                // Khai báo màn hình
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(layout, null);

                // Ánh xạ đối tượng
                viewHolder = new A9_Question_Type_Adapter.viewHolders();
                viewHolder.tvQuesType = (TextView) view.findViewById(R.id.a9_item_ques_title);
                viewHolder.tvQuesCount = (TextView) view.findViewById(R.id.a9_item_count_ques);
                viewHolder.imageView = (ImageView) view.findViewById(R.id.a9_item_img);

                view.setTag(viewHolder);
                view.setTag(R.id.a9_item_ques_title, viewHolder.tvQuesType);
                view.setTag(R.id.a9_item_count_ques, viewHolder.tvQuesCount);
                view.setTag(R.id.a9_item_img, viewHolder.imageView);
            } else {
                viewHolder = (A9_Question_Type_Adapter.viewHolders) view.getTag();
            }

            // Gán giá trị cho textView, imageView;
            // Kiểm tra loại câu hỏi, gán img phù hợp
            String type = this.questionsTypeList.get(i).getLoaiCH();
            if (type.equals("Trắc Nghiệm")) {
                viewHolder.imageView.setImageResource(R.drawable.a9_type_choice);
            } else if (type.equals("Tự Luận")) {
                viewHolder.imageView.setImageResource(R.drawable.a9_type_qa);
            } else if (type.equals("Đánh Giá")) {
                viewHolder.imageView.setImageResource(R.drawable.a9_type_rating);
            }

            viewHolder.tvQuesType.setText(questionsTypeList.get(i).getLoaiCH());

            A9_DBHelper dbHelper = new A9_DBHelper(context);
            int countType = dbHelper.getCountQuesType(type);
            viewHolder.tvQuesCount.setText("0/" + countType);

            return view;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
