package com.drusade.fanfictionbookclub.network;

import com.drusade.fanfictionbookclub.model.GoogleSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleSearchApi {

    @GET()
    Call<GoogleSearchResponse> getResult(
            @Query("q") String character,
            @Query("q") String fanfiction
    );
}
