package com.elcentaurx.bimbonet.data.remote;

import com.elcentaurx.bimbonet.model.BeerResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BeerApi {

    @GET("v2/beers")
    Call<List<BeerResponse>> getBeers(@Query("page") Integer page);
    
    @GET("v2/beers/{id}")
    Call<BeerResponse> getBeer(@Path("id") Long id);



}
