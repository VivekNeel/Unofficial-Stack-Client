package com.vivek.stack.client.network;

import com.vivek.stack.client.util.Constants;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by vivekneel on 8/28/16.
 */
public class Client {

    private static Retrofit retrofit;

    public static Retrofit getInstance(){
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .build();
        }
        else{
           return retrofit;
        }
        return retrofit;
    }
}
