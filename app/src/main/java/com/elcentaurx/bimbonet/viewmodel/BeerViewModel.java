package com.elcentaurx.bimbonet.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.elcentaurx.bimbonet.repository.BeerRepository;
import com.elcentaurx.bimbonet.repository.response.BeerResponse;

import java.util.List;

public class BeerViewModel extends AndroidViewModel {

    private BeerRepository beerRepository;
    private LiveData<List<BeerResponse>> bearResponseLiveData;
    private Integer page= 1;

    public BeerViewModel(@NonNull Application application) {
        super(application);
        this.beerRepository = new BeerRepository();
        this.bearResponseLiveData = beerRepository.getBears();
    }
//    public BeerViewModel(@NonNull Application application) {
//        super(application);
//        this.beerRepository = new BeerRepository();
//        this.beerResponseLiveData = beerRepository.getBeerResponseLiveData();
//
//    }
//
    public LiveData<List<BeerResponse>>  getBeerResponseLiveData() {
        return  bearResponseLiveData;
    }


}
