package com.flashfuse.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.flashfuse.fragment.user.AddDeckFragment;
import com.flashfuse.fragment.user.ProfileFragment;
import com.flashfuse.fragment.user.SearchFragment;
import com.flashfuse.fragment.user.UserHomeFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new UserHomeFragment();
            case 1: return new SearchFragment();
            case 2: return new AddDeckFragment();
            case 3: return new ProfileFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
