package com.example.king.vinamobile.M0_BottomNavigation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class M0_Bottom_Navigation extends CoordinatorLayout.Behavior<BottomNavigationView> {
    public M0_Bottom_Navigation() {
        super();
    }

    public M0_Bottom_Navigation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean layoutDependOn(CoordinatorLayout parent, BottomNavigationView child, View dependency) {
        boolean dependsOn = dependency instanceof FrameLayout;
        return dependsOn;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                       @NonNull BottomNavigationView child,
                                       @NonNull View directTargetChild,
                                       @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                  @NonNull BottomNavigationView child,
                                  @NonNull View target, int dx, int dy,
                                  @NonNull int[] consumed, int type) {
        if (dy < 0) {
            showBottomNavigationView(child);
        } else if (dy > 0) {
            hideBottomNavigationView(child);
        }
    }

    // ẩn thanh bottom navigation
    private void hideBottomNavigationView(BottomNavigationView view) {
        view.animate().translationY(view.getHeight());
    }

    // hiện thanh bottom navigation
    private void showBottomNavigationView(BottomNavigationView view) {
        view.animate().translationY(0);
    }
}
