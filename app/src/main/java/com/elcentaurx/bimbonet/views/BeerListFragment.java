package com.elcentaurx.bimbonet.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.elcentaurx.bimbonet.R;
import com.elcentaurx.bimbonet.adapters.BeerAdapter;
import com.elcentaurx.bimbonet.model.Beer;
import com.elcentaurx.bimbonet.repository.response.BeerResponse;
import com.elcentaurx.bimbonet.viewmodel.BeerViewModel;

import java.util.ArrayList;
import java.util.List;


public class BeerListFragment extends Fragment {

    private static final String TAG = BeerListFragment.class.getSimpleName();

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private LinearLayoutManager layoutManager;
    private ArrayList<BeerResponse> beerArrayList = new ArrayList<>();
    BeerViewModel beerViewModel;
    private BeerAdapter adapter;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        init();


    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beer_list, container, false);
    }





    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progress_bar);
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new BeerAdapter(getContext(), beerArrayList);
        recyclerView.setAdapter(adapter);
        beerViewModel = ViewModelProviders.of(this).get(BeerViewModel.class);
        getBeers();

    }

    private void getBeers() {
        beerViewModel.getBeerResponseLiveData().observe(getViewLifecycleOwner(), bearResponse ->{
            if (!bearResponse.isEmpty()){
                progressBar.setVisibility(View.GONE);
                List<BeerResponse> beerList = bearResponse;
                beerArrayList.addAll(beerList);
                adapter.notifyDataSetChanged();
            }
        });



    }

}