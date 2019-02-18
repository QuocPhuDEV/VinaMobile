package com.example.king.vinamobile.A9_Survey;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.king.vinamobile.R;

import java.util.List;

public class A9_YesNo_Adapter extends BaseAdapter {
    //region KHAI BÁO BIẾN TOÀN CỤC
    private Context context;
    private int layout;
    private List<A9_Cls_Question> questionsList;
    //endregion


    public A9_YesNo_Adapter(Context context, int layout, List<A9_Cls_Question> questionsList) {
        this.context = context;
        this.layout = layout;
        this.questionsList = questionsList;
    }

    @Override
    public int getCount() {
        return this.questionsList.size();
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
        public TextView tvQues, tvAnswerCount;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            A9_YesNo_Adapter.viewHolders viewHolder = null;
            if (view == null) {
                // Khai báo màn hình
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(layout, null);

                // Ánh xạ đối tượng
                viewHolder = new A9_YesNo_Adapter.viewHolders();
                viewHolder.tvQues = (TextView) view.findViewById(R.id.a9_item_question);
                //viewHolder.tvAnswerCount = (TextView) view.findViewById(R.id.a9_item_count_answer);

                view.setTag(viewHolder);
                view.setTag(R.id.a9_item_question, viewHolder.tvQues);
               //view.setTag(R.id.a9_item_count_answer, viewHolder.tvAnswerCount);
            } else {
                viewHolder = (A9_YesNo_Adapter.viewHolders) view.getTag();
            }

            // Gán giá trị cho textView;
            viewHolder.tvQues.setText(questionsList.get(i).getCauHoi());

            return view;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
