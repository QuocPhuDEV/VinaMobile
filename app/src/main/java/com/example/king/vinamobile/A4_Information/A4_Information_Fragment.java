package com.example.king.vinamobile.A4_Information;


import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.king.vinamobile.A_Json.Json_Volley;
import com.example.king.vinamobile.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by Phuhq : 15/jan/2019
 */
public class A4_Information_Fragment extends Fragment {

    //region KHAI BÁO BIẾN TOÀN CỤC
    private static final String TAG = A4_Information_Fragment.class.getSimpleName();
    private static final String URL = "https://api.androidhive.info/json/movies_2017.json";
    //private static final String URL = "http://www.json-generator.com/api/json/get/bPyXpvAVDS?indent=2";

    private RecyclerView recyclerView;
    private List<A4_Cls_Products> productsList;
    private A4_Apdater mAdapter;


    //endregion

    public A4_Information_Fragment() {

    }

    // Khởi tạo contrucstor cho fragment
    public static A4_Information_Fragment newInstance(String param1, String param2) {
        A4_Information_Fragment fragment = new A4_Information_Fragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity())
                .getSupportActionBar()
                .setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a4__information_, container, false);

        recyclerView = view.findViewById(R.id.a4_recycler_view);
        productsList = new ArrayList<>();
        mAdapter = new A4_Apdater(getContext(), productsList);

//        ProgressDialog dialog = new ProgressDialog(getActivity());
//
//        dialog.setMessage("hello");
//        dialog.show();

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        fetchInformationItems();
        //dialog.dismiss();

        return view;
    }

    //region XỬ LÝ EVENTS

    // Xử lý file Json
    private void fetchInformationItems() {
        try {
            
            JsonArrayRequest request = new JsonArrayRequest(URL,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            if (response == null) {
                                Toast.makeText(getActivity(), R.string.a4_error_couldnnot_fetch_json, Toast.LENGTH_SHORT).show();
                                return;
                            }
                            List<A4_Cls_Products> items = new Gson().fromJson(response.toString(), new TypeToken<List<A4_Cls_Products>>() {
                            }.getType());

                            productsList.clear();
                            productsList.addAll(items);

                            // refreshing recycler view
                            mAdapter.notifyDataSetChanged();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // lỗi setting json
                    Log.e(TAG, "ERROR: " + error.getMessage());
                    Toast.makeText(getActivity(), R.string.a4_error_couldnnot_no_internet_access, Toast.LENGTH_SHORT).show();
                }
            });
            Json_Volley.getInstance().addToRequestQueue(request);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // xử lý vị trí, khoảng cách
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    // Xử lý chuyển đổi dp sang pixel
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
//endregion

}
