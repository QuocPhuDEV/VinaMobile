package com.example.king.vinamobile.A7_Profile;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.king.vinamobile.A0_Sqlite_Connection.Create_Table;
import com.example.king.vinamobile.A1_Login.A1_Cls_Account;
import com.example.king.vinamobile.R;

import java.util.List;

public class A7_Profile_Adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<A1_Cls_Account> accountsList;

    public A7_Profile_Adapter(Context context, int layout, List<A1_Cls_Account> accountsList) {
        this.context = context;
        this.layout = layout;
        this.accountsList = accountsList;
    }

    @Override
    public int getCount() {
        return this.accountsList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    // Khai báo dối tượng view
    static class viewHolder {
        public TextView tvTitle, tvInfo;
        public ImageView img;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        A7_Profile_Adapter.viewHolder viewHolder = null;
        try {
            if (view == null) {
                // Khai báo màn hình
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(layout, null);

                // Ánh xạ đối tượng
                viewHolder = new A7_Profile_Adapter.viewHolder();
                viewHolder.tvTitle = (TextView) view.findViewById(R.id.a7_profile_tv_title);
                viewHolder.tvInfo = (TextView) view.findViewById(R.id.a7_profile_tv_info);
                viewHolder.img = (ImageView) view.findViewById(R.id.a7_profile_img_item_list);

                view.setTag(viewHolder);
                view.setTag(R.id.a7_profile_tv_title, viewHolder.tvTitle);
                view.setTag(R.id.a7_profile_tv_info, viewHolder.tvInfo);
                view.setTag(R.id.a7_profile_img_item_list, viewHolder.img);
            } else {
                viewHolder = (A7_Profile_Adapter.viewHolder) view.getTag();
            }


            // Gán giá trị cho các control

            viewHolder.tvTitle.setText(accountsList.get(position).getMaKH());
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }
}
