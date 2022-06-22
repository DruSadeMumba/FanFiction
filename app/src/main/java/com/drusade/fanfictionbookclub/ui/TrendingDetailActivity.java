package com.drusade.fanfictionbookclub.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.drusade.fanfictionbookclub.R;
import com.drusade.fanfictionbookclub.adapters.TrendingPagerAdapter;
import com.drusade.fanfictionbookclub.model.Result;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrendingDetailActivity extends AppCompatActivity {

    private List<Result> trendingList;
    private TrendingPagerAdapter adapterViewPager;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.viewPager) ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_detail);
        ButterKnife.bind(this);

        trendingList = Parcels.unwrap(getIntent().getParcelableExtra("trendingList"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new TrendingPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, trendingList);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}