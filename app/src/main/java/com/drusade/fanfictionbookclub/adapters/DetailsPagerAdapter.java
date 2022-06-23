package com.drusade.fanfictionbookclub.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.drusade.fanfictionbookclub.model.Result;
import com.drusade.fanfictionbookclub.ui.fragments.DetailFragment;

import java.util.List;

public class DetailsPagerAdapter extends FragmentPagerAdapter {

    private List<Result> mResults;

    public DetailsPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Result> results) {
        super(fm, behavior);
        mResults = results;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return DetailFragment.newInstance(mResults.get(position));
    }

    @Override
    public int getCount() {
        return mResults.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mResults.get(position).getTitle();
    }
}
