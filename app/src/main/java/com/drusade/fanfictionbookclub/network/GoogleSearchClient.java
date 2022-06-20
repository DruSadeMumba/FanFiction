package com.drusade.fanfictionbookclub.network;

import static com.drusade.fanfictionbookclub.Constants.GOOGLE_SEARCH_API_KEY;
import static com.drusade.fanfictionbookclub.Constants.GOOGLE_SEARCH_HOST_URL;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoogleSearchClient {
    private static Retrofit retrofit = null;
    public static GoogleSearchApi getClient() {
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient
                    .Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request newRequest  = chain
                                    .request()
                                    .newBuilder()
                                    .addHeader("X-RapidAPI-Key", GOOGLE_SEARCH_API_KEY)
                                    .build();
                            return chain.proceed(newRequest);
                        }
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(GOOGLE_SEARCH_HOST_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(GoogleSearchApi.class);
    }
}
