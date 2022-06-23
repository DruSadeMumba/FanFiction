package com.drusade.fanfictionbookclub.ui;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.drusade.fanfictionbookclub.Constants;
import com.drusade.fanfictionbookclub.R;
import com.drusade.fanfictionbookclub.adapters.ImageResultsAdapter;
import com.drusade.fanfictionbookclub.adapters.ResultsAdapter;
import com.drusade.fanfictionbookclub.model.GoogleSearchResponse;
import com.drusade.fanfictionbookclub.model.ImageResult;
import com.drusade.fanfictionbookclub.model.Result;
import com.drusade.fanfictionbookclub.network.GoogleSearchApi;
import com.drusade.fanfictionbookclub.network.GoogleSearchClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentCharacter;

    private static final String TAG = SearchActivity.class.getSimpleName();
    private ResultsAdapter mResultsAdapter;
    private ImageResultsAdapter mImageResultsAdapter;
    public List<Result> books;
    public List<ImageResult> images;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentCharacter = mSharedPreferences.getString(Constants.PREFERENCES_CHARACTER_KEY, null);
        if(mRecentCharacter != null){
            fetchBooks(mRecentCharacter);
            /*fetchImages(mRecentCharacter);*/
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String character) {
                addToSharedPreferences(character);
                fetchBooks(character);
                /*fetchImages(character);*/
                return false;
            }
            @Override
            public boolean onQueryTextChange(String character) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);
    }

    private void showBooks() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void addToSharedPreferences(String character) {
        mEditor.putString(Constants.PREFERENCES_CHARACTER_KEY, character).apply();
    }

    /*private void fetchImages(String character) {
        GoogleSearchApi client = GoogleSearchClient.getClient();
        Call<GoogleSearchResponse> call = client.getImageResult("q=" + character + " " + "fanfiction");

        call.enqueue(new Callback<GoogleSearchResponse>() {
            @Override
            public void onResponse(Call<GoogleSearchResponse> call, Response<GoogleSearchResponse> response) {
                if (response.isSuccessful()) {
                    images = response.body().getImageResults();
                    mImageResultsAdapter = new ImageResultsAdapter(SearchActivity.this, images);
                    mRecyclerView.setAdapter(mImageResultsAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    showBooks();
                } else {

                }
            }
            @Override
            public void onFailure(Call<GoogleSearchResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );

            }
        });

    }*/

    private void fetchBooks(String character){
        GoogleSearchApi client = GoogleSearchClient.getClient();
        Call<GoogleSearchResponse> call = client.getResult("q=" + character + " " + "fanfiction");

        call.enqueue(new Callback<GoogleSearchResponse>() {
            @Override
            public void onResponse(Call<GoogleSearchResponse> call, Response<GoogleSearchResponse> response) {
                if (response.isSuccessful()) {
                    books = response.body().getResults();
                    mResultsAdapter = new ResultsAdapter(SearchActivity.this, books);
                    mRecyclerView.setAdapter(mResultsAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

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
}


