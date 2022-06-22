package com.drusade.fanfictionbookclub.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.drusade.fanfictionbookclub.model.Result;
import com.drusade.fanfictionbookclub.ui.fragments.TrendingDetailFragment;

import java.util.List;

public class TrendingPagerAdapter extends FragmentPagerAdapter {

    private List<Result> mTrendingResults;

    public TrendingPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Result> results) {
        super(fm, behavior);
        mTrendingResults = results;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return TrendingDetailFragment.newInstance(mTrendingResults.get(position));
    }

    @Override
    public int getCount() {
        return mTrendingResults.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mTrendingResults.get(position).getTitle();
    }
}

