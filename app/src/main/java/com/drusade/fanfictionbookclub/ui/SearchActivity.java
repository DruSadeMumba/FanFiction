package com.drusade.fanfictionbookclub.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.drusade.fanfictionbookclub.Constants;
import com.drusade.fanfictionbookclub.R;
import com.drusade.fanfictionbookclub.adapters.ResultsAdapter;
import com.drusade.fanfictionbookclub.model.GoogleSearchResponse;
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
    private ResultsAdapter mAdapter;
    public List<Result> books;

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

    private void fetchBooks(String character){
        GoogleSearchApi client = GoogleSearchClient.getClient();
        Call<GoogleSearchResponse> call = client.getResult("q=" + character + " " + "fanfiction");
        call.enqueue(new Callback<GoogleSearchResponse>() {
            @Override
            public void onResponse(Call<GoogleSearchResponse> call, Response<GoogleSearchResponse> response) {

                if (response.isSuccessful()) {
                    books = response.body().getResults();
                    mAdapter = new ResultsAdapter(SearchActivity.this, books);
                    mRecyclerView.setAdapter(mAdapter);
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