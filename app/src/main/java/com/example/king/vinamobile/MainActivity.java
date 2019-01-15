package com.example.king.vinamobile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.king.vinamobile.A3_Scan.A3_Scan_Fragment;
import com.example.king.vinamobile.A3_Scan.A3_Scan_Home_Fragment;
import com.example.king.vinamobile.A4_Information.A4_Information_Fragment;
import com.example.king.vinamobile.M0_BottomNavigation.M0_Bottom_Navigation;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        AddObject();
        loadHomeFragment();

    }

    //region ÁNH XẠ ĐỐI TƯỢNG
    public void AddObject() {

        // Thêm đối tượng tool bar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Gọi navigation view
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Thêm events cho navagation view
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        // Gọi navigation buttom
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setItemIconTintList(null);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new M0_Bottom_Navigation());

    }
    //endregion

    //region LOAD FORM

    // load menu phải (menu)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // Load trang chủ
    public void loadHomeFragment() {
        toolbar.setTitle(getString(R.string.bottom_Information));
        loadFragment(new A4_Information_Fragment());
    }

    //endregion

    //region SELECT MENNU
    // Xử lý click menu bên phải (bottom view)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Xử lý click menu bên trái (navigation view)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_input) {

        } else if (id == R.id.nav_scan) {

        } else if (id == R.id.nav_inventory) {

        } else if (id == R.id.nav_report) {

        } else if (id == R.id.nav_account) {

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Load menu bên dưới (buttom navigation view)
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.bottom_information:
                    // Gán tên màn hình
                    toolbar.setTitle(getString(R.string.bottom_Information));

                    // Gọi màn hình
                    fragment = new A4_Information_Fragment();
                    loadFragment(fragment);

                    return true;
                case R.id.bottom_scan:
                    // Gán tên màn hình
                    toolbar.setTitle(getString(R.string.bottom_Scan));

                    // Gọi màn hình
                    fragment = new A3_Scan_Home_Fragment();
                    loadFragment(fragment);

                    return true;
                case R.id.bottom_inventory:
                    // Gán tên màn hình
                    toolbar.setTitle(getString(R.string.bottom_Inventory));

                    return true;
                case R.id.bottom_report:
                    // Gán tên màn hình
                    toolbar.setTitle(getString(R.string.bottom_Report));

                    return true;
            }
            return false;
        }
    };
    //endregion

    //region XỬ LÝ EVENTS

    // Xử lý sự kiện onBackPressed
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // Load Fragment
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //endregion


}
