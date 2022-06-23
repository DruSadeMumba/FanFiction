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

public class DetailFragment extends Fragment {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.detailTitle) TextView mDetailTitle;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.detailLink) TextView mDetailLink;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.detailDescription) TextView mDetailDescription;

    private Result mResult;

    public DetailFragment() {

    }

    public static DetailFragment newInstance(Result result) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("result", Parcels.wrap(result));
        detailFragment.setArguments(args);
        return detailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        mResult = Parcels.unwrap(getArguments().getParcelable("result"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, v);

        mDetailTitle.setText(mResult.getTitle());
        mDetailLink.setText(mResult.getLink());
        mDetailDescription.setText(mResult.getDescription());

        mDetailLink.setOnClickListener(view -> {
            Intent webIntent = new Intent (Intent.ACTION_VIEW,
                    Uri.parse(mResult.getLink()));
            startActivity(webIntent);
        });

        return v;
    }
}