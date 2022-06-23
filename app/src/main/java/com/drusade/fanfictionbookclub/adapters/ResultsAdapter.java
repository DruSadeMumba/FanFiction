package com.drusade.fanfictionbookclub.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.drusade.fanfictionbookclub.Constants;
import com.drusade.fanfictionbookclub.R;
import com.drusade.fanfictionbookclub.model.ImageResult;
import com.drusade.fanfictionbookclub.model.Result;
import com.drusade.fanfictionbookclub.ui.DetailsActivity;
import com.drusade.fanfictionbookclub.ui.TrendingDetailActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

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
        holder.mSavebookButton.setOnClickListener(v -> {
            Result mResult = new Result ();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            assert user != null;
            String uid = user.getUid();
            DatabaseReference activityRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_BOOKS)
                    .child(uid);

            DatabaseReference pushRef = activityRef.push();

            String pushId = pushRef.getKey();
            mResult.setPushId(pushId);
            pushRef.setValue(mResult);
            Toast.makeText(mContext, "Saved", Toast.LENGTH_SHORT).show();
        });


    }

    @Override
    public int getItemCount() {

        return resultList.size();
    }

    public class ResultsViewHolder extends RecyclerView.ViewHolder {
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.img_book) ImageView mImg_book;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.titleListTextView) TextView mTitleListTextView;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.saveBookButton) Button mSavebookButton;

        private Context mContext;

        public ResultsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();

            itemView.setOnClickListener(v -> {
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra("position", itemPosition);
                intent.putExtra("resultList", Parcels.wrap(resultList));
                mContext.startActivity(intent);
            });
        }

        @SuppressLint("SetTextI18n")
        public void bindBook(Result book) {
            mTitleListTextView.setText(book.getTitle());
        }
    }
}