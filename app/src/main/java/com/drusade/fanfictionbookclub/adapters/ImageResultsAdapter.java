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
import com.drusade.fanfictionbookclub.model.ImageResult;
import com.drusade.fanfictionbookclub.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageResultsAdapter extends RecyclerView.Adapter<ImageResultsAdapter.ImageResultsViewHolder>{
    private Context mContext;
    private List<ImageResult> imageResultList;

    public ImageResultsAdapter(Context mContext, List<ImageResult> imageResultList) {
        this.mContext = mContext;
        this.imageResultList = imageResultList;
    }

    @NonNull
    @Override
    public ImageResultsAdapter.ImageResultsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_list_item, parent, false);
        ImageResultsAdapter.ImageResultsViewHolder imageResultViewHolder = new ImageResultsAdapter.ImageResultsViewHolder(view);

        return imageResultViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageResultsAdapter.ImageResultsViewHolder holder, int position) {
        holder.bindImage(imageResultList.get(position));
    }

    @Override
    public int getItemCount() {
        return imageResultList.size();
    }

    public class ImageResultsViewHolder extends RecyclerView.ViewHolder {
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.img_book) ImageView mImg_book;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.titleListTextView) TextView mTitleListTextView;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.websiteListTextView) TextView mWebsiteListTextView;

        public ImageResultsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindImage(ImageResult imageResult) {
            Picasso.get().load(String.valueOf(imageResult.getLink())).into(mImg_book);
        }
    }
}
