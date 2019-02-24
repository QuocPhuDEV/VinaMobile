package com.example.king.vinamobile.A9_Survey.A9_Survey_History;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.king.vinamobile.A9_Survey.A9_Survey_Class.A9_Cls_Answer;
import com.example.king.vinamobile.A9_Survey.A9_Survey_Class.A9_Cls_Question;
import com.example.king.vinamobile.A9_Survey.A9_Survey_Database.A9_DBHelper;
import com.example.king.vinamobile.R;

import java.util.List;

public class A9_History_Adapter extends BaseAdapter {
    //region KHAI BÁO BIẾN TOÀN CỤC
    private Context context;
    private int layout;
    private List<A9_Cls_Answer> answerList;
    //endregion


    public A9_History_Adapter(Context context, int layout, List<A9_Cls_Answer> answerList) {
        this.context = context;
        this.layout = layout;
        this.answerList = answerList;
    }

    @Override
    public int getCount() {
        return this.answerList.size();
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
        public TextView tvQues, tvAnswer, tvTime;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            A9_History_Adapter.viewHolders viewHolder = null;
            if (view == null) {
                // Khai báo màn hình
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(layout, null);

                // Ánh xạ đối tượng
                viewHolder = new A9_History_Adapter.viewHolders();
                viewHolder.tvQues = (TextView) view.findViewById(R.id.a9_item_his_question);
                viewHolder.tvAnswer = (TextView) view.findViewById(R.id.a9_item_his_answer);
                viewHolder.tvTime = (TextView) view.findViewById(R.id.a9_item_his_time);

                view.setTag(viewHolder);
                view.setTag(R.id.a9_item_question, viewHolder.tvQues);
                view.setTag(R.id.a9_item_his_answer, viewHolder.tvAnswer);
                view.setTag(R.id.a9_item_his_time, viewHolder.tvTime);

            } else {
                viewHolder = (A9_History_Adapter.viewHolders) view.getTag();
            }


            // Lấy câu hỏi theo mã
            String CauHoi;
            Context context = viewGroup.getContext();
            A9_DBHelper a9_dbHelper = new A9_DBHelper(viewGroup.getContext());
            CauHoi = a9_dbHelper.getAllQuestionWithID(answerList.get(i).getMaCH());

            // Gán giá trị cho textView;
            viewHolder.tvQues.setText(CauHoi);
            viewHolder.tvAnswer.setText(answerList.get(i).getTraLoi());
            viewHolder.tvTime.setText(answerList.get(i).getThoiGian());

            return view;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
