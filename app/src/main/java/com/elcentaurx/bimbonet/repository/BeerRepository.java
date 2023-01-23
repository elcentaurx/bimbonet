package com.elcentaurx.bimbonet.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.elcentaurx.bimbonet.repository.response.BeerResponse;
import com.elcentaurx.bimbonet.retrofit.BeerApi;
import com.elcentaurx.bimbonet.retrofit.RetrofitRequest;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeerRepository {

    private static final String TAG = BeerRepository.class.getSimpleName();
    private BeerApi beerApi;


    public BeerRepository() {
        beerApi = RetrofitRequest.getRetrofitInstance().create(BeerApi.class);
    }

    public LiveData<List<BeerResponse>> getBears() {
        final MutableLiveData<List<BeerResponse>> data = new MutableLiveData<>();
        beerApi.getBeers(1)
                .enqueue(new Callback<List<BeerResponse>>() {
                    @Override
                    public void onResponse(Call<List<BeerResponse>> call, Response<List<BeerResponse>> response) {
                        data.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<BeerResponse>> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }




//    public ArrayList<Beer> getBeers(Integer page) {
//        beerApi = RetrofitRequest.getRetrofitInstance().create(BeerApi.class);
//       ArrayList <Beer> data = new ArrayList<>();
//        beerApi.getBeers(page)
//                .enqueue(new Callback<List<BeerResponse>>() {
//                    @Override
//                    public void onResponse(Call<List<BeerResponse>> call, Response<List<BeerResponse>> response) {
//                        response.body();
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<BeerResponse>> call, Throwable t) {
//                        Log.e("hey", t.toString());
//                    }
//                });
//        return data;
//    }


}



