package com.drusade.fanfictionbookclub.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.drusade.fanfictionbookclub.R;
import com.drusade.fanfictionbookclub.model.Result;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrendingDetailFragment extends Fragment {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.detailTitle) TextView mDetailTitle;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.detailLink) TextView mDetailLink;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.detailDescription) TextView mDetailDescription;

    private Result mTrendingResult;

    public TrendingDetailFragment() {

    }

    public static TrendingDetailFragment newInstance(Result result) {
        TrendingDetailFragment fragment = new TrendingDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("result", Parcels.wrap(result));
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        mTrendingResult = Parcels.unwrap(getArguments().getParcelable("result"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trending_detail, container, false);

        ButterKnife.bind(this, view);

        mDetailTitle.setText(mTrendingResult.getTitle());
        mDetailLink.setText(mTrendingResult.getLink());
        mDetailDescription.setText(mTrendingResult.getDescription());

        mDetailLink.setOnClickListener(v -> {
            Intent webIntent = new Intent (Intent.ACTION_VIEW,
                    Uri.parse(mTrendingResult.getLink()));
            startActivity(webIntent);
        });

        return view;
    }
}