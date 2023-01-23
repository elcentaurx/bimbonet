package com.elcentaurx.bimbonet.data.remote;

import static com.elcentaurx.bimbonet.constant.AppConstant.BASE_URL;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;

public class RetrofitRequest {

    private static Retrofit retrofit;
    public static Retrofit getRetrofitInstance() {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;
    }
}
