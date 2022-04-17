package com.vedant.x_name.Adapters;

import android.content.Context;

import com.vedant.x_name.Fragments.outer_fragments.LoginFragment;
import com.vedant.x_name.Fragments.outer_fragments.SignUpFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class loginAdapter extends FragmentStateAdapter {
    public Context context1;
    int totalTabs1;
    public static final int NUM_PAGES = 2;

    public loginAdapter(FragmentManager fragmentManager, Lifecycle lifecycle, Context context, int totalTabs) {
        super(fragmentManager, lifecycle);
        this.context1 = context;
        this.totalTabs1 = totalTabs;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {

            case 0:
                return new LoginFragment();

            case 1:
                return new SignUpFragment();

            default:
                return null;
        }

    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}
