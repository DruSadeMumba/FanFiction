package com.drusade.fanfictionbookclub.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.drusade.fanfictionbookclub.R;
import com.drusade.fanfictionbookclub.model.Result;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>{
    private Context mContext;
    private List<Result> trendingList;

    public TrendingAdapter(Context mContext, List<Result> trendingList) {
        this.mContext = mContext;
        this.trendingList = trendingList;
    }

    @NonNull
    @Override
    public TrendingAdapter.TrendingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview1_list_item, parent, false);
        TrendingAdapter.TrendingViewHolder trendingViewHolder = new TrendingAdapter.TrendingViewHolder(view);

        return trendingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingAdapter.TrendingViewHolder holder, int position) {
        holder.bindBook(trendingList.get(position));
    }

    @Override
    public int getItemCount() {
        return trendingList.size();
    }

    public class TrendingViewHolder extends RecyclerView.ViewHolder {

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.titleTextView2) TextView mTitleTextView2;

        private Context mContext;

        public TrendingViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        @SuppressLint("SetTextI18n")
        public void bindBook(Result trending) {
            mTitleTextView2.setText(trending.getTitle());
        }
    }
}
