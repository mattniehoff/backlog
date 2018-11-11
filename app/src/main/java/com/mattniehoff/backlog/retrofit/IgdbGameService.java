package com.mattniehoff.backlog.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Adapted from: https://futurestud.io/tutorials/retrofit-2-creating-a-sustainable-android-client
public final class IgdbGameService {

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(NetworkUtils.IGDB_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <T> T createClient(
            Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
