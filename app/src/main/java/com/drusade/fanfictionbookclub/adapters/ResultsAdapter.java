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

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ResultsViewHolder>{

    private Context mContext;
    private List<Result> resultList;

    public ResultsAdapter(Context mContext, List<Result> resultList) {
        this.mContext = mContext;
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public ResultsAdapter.ResultsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_list_item, parent, false);
        ResultsViewHolder resultViewHolder = new ResultsViewHolder(view);

        return resultViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ResultsViewHolder holder, int position) {
        holder.bindBook(resultList.get(position));
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class ResultsViewHolder extends RecyclerView.ViewHolder {
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.img_book) ImageView mImg_book;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.titleListTextView)
        TextView mTitleListTextView;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.websiteListTextView)
        TextView mWebsiteListTextView;

        public ResultsViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        @SuppressLint("SetTextI18n")
        public void bindBook(Result book) {
            mTitleListTextView.setText("Title: " + book.getTitle());
            mWebsiteListTextView.setText("Website: " + book.getLink());

        }
    }
}
