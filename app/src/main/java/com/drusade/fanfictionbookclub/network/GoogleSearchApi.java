package com.drusade.fanfictionbookclub.network;

import android.media.Image;

import com.drusade.fanfictionbookclub.model.GoogleSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GoogleSearchApi {
    @GET("search/{q}")
    Call<GoogleSearchResponse> getResult(
            @Path("q") String q

    );
    @GET("image/{q}")
    Call<GoogleSearchResponse> getImageResult(
            @Path("q") String q
            );

}
