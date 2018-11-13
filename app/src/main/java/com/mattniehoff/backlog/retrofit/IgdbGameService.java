package com.mattniehoff.backlog.retrofit;

// Adapted from: https://code.tutsplus.com/tutorials/sending-data-with-retrofit-2-http-client-for-android--cms-27845
public class IgdbGameService {
    private  IgdbGameService() {}

    public static final String BASE_URL = NetworkUtils.IGDB_BASE_URL;

    public static IgdbGamesClient getApiClient(){
        return RetrofitClient.getClient(BASE_URL).create(IgdbGamesClient.class);
    }

}
