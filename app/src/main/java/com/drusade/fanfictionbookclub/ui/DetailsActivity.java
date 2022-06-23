package com.drusade.fanfictionbookclub.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.drusade.fanfictionbookclub.R;
import com.drusade.fanfictionbookclub.adapters.DetailsPagerAdapter;
import com.drusade.fanfictionbookclub.adapters.TrendingPagerAdapter;
import com.drusade.fanfictionbookclub.model.Result;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    private List<Result> searchList;
    private DetailsPagerAdapter detailAdapterViewPager;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.viewPager2) ViewPager mViewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        searchList = Parcels.unwrap(getIntent().getParcelableExtra("resultList"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        detailAdapterViewPager = new DetailsPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, searchList);
        mViewPager2.setAdapter(detailAdapterViewPager);
        mViewPager2.setCurrentItem(startingPosition);
    }
}