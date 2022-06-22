package com.drusade.fanfictionbookclub.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.drusade.fanfictionbookclub.R;
import com.drusade.fanfictionbookclub.adapters.TrendingAdapter;
import com.drusade.fanfictionbookclub.model.GoogleSearchResponse;
import com.drusade.fanfictionbookclub.model.ImageResult;
import com.drusade.fanfictionbookclub.model.Result;
import com.drusade.fanfictionbookclub.network.GoogleSearchApi;
import com.drusade.fanfictionbookclub.network.GoogleSearchClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private TextView mProfNameTextView;
    private TextView mProfEmailTextView;
    private ImageView mProfPicImageView;
    /*private Button mAddProfPicButton;*/
    private Button mSearchButton;
    private RecyclerView mRecyclerView1;
    private List <Result> books;

    private static final String TAG = MainActivity.class.getSimpleName();
    private TrendingAdapter mTrendingAdapter;
    public List<ImageResult> images;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProfNameTextView = findViewById(R.id.profNameTextView);
        mProfPicImageView = findViewById(R.id.profPicImageView);
        mProfEmailTextView = findViewById(R.id.profEmailTextView);
        /*mAddProfPicButton = findViewById(R.id.addProfPicButton);*/
        mSearchButton = findViewById(R.id.searchButton);
        mRecyclerView1 = findViewById(R.id.recyclerView1);
        fetchBooks();

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    mProfNameTextView.setText("Hello " + user.getDisplayName() + "!");
                    mProfEmailTextView.setText(user.getEmail());
                }
                else {

                }
            }
        };

        mSearchButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void fetchBooks(){
        GoogleSearchApi client = GoogleSearchClient.getClient();
        Call<GoogleSearchResponse> call = client.getResult("q=trending fanfiction stories");

        call.enqueue(new Callback<GoogleSearchResponse>() {
            @Override
            public void onResponse(Call<GoogleSearchResponse> call, Response<GoogleSearchResponse> response) {
                if (response.isSuccessful()) {
                    books = response.body().getResults();
                    mTrendingAdapter = new TrendingAdapter(MainActivity.this, books);
                    mRecyclerView1.setAdapter(mTrendingAdapter);

                    /*GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.VERTICAL, false);*/
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                    mRecyclerView1.setLayoutManager(layoutManager);
                    mRecyclerView1.setHasFixedSize(true);

                    showBooks();
                } else {

                }
            }
            @Override
            public void onFailure(Call<GoogleSearchResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );

            }
        });
    }

    private void showBooks() {
        mRecyclerView1.setVisibility(View.VISIBLE);
    }
}