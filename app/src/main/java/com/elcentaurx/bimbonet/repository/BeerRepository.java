package com.elcentaurx.bimbonet.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.elcentaurx.bimbonet.model.BeerResponse;
import com.elcentaurx.bimbonet.data.remote.BeerApi;
import com.elcentaurx.bimbonet.data.remote.RetrofitRequest;


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

}



