package com.elcentaurx.bimbonet.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.elcentaurx.bimbonet.R;
import com.elcentaurx.bimbonet.adapters.BeerAdapter;
import com.elcentaurx.bimbonet.data.database.AppDataBase;
import com.elcentaurx.bimbonet.data.database.dao.ItemDao;
import com.elcentaurx.bimbonet.data.database.entity.Item;
import com.elcentaurx.bimbonet.data.database.repository.ItemRepository;
import com.elcentaurx.bimbonet.data.database.repository.ItemRepositoryImpl;
import com.elcentaurx.bimbonet.data.preferences.Preferences;
import com.elcentaurx.bimbonet.model.BeerResponse;
import com.elcentaurx.bimbonet.repository.AuthenticationRepository;
import com.elcentaurx.bimbonet.viewmodel.AuthViewModel;
import com.elcentaurx.bimbonet.viewmodel.BeerViewModel;

import java.util.ArrayList;
import java.util.List;


public class BeerListFragment extends Fragment implements BeerAdapter.RecyclerItemClick {

    private static final String TAG = BeerListFragment.class.getSimpleName();
    private AuthViewModel viewModel;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private LinearLayoutManager layoutManager;
    private ArrayList<BeerResponse> beerArrayList = new ArrayList<>();
    private BeerAdapter adapter;
    private BeerDetailFragment beerDetailFragment;
    private NavController navController;
    Preferences preferences;
    Bundle bundle;
    BeerViewModel beerViewModel;
    AppDataBase dataBase;
    ItemDao itemDao;
    ItemRepository repo;
    List<Item> items;
    String isDBInitialize;
    BeerResponse beerResponse;
    AuthenticationRepository repository;
    Button signOut;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataBase = AppDataBase.getInstance(this.getContext());
        itemDao = dataBase.itemDao();
        repo = new ItemRepositoryImpl(itemDao);
        preferences = new Preferences();
        bundle = new Bundle();
        beerDetailFragment = new BeerDetailFragment();

        viewModel = new ViewModelProvider(this , (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(AuthViewModel.class);
        viewModel.getLoggedStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){

                    navController.navigate(R.id.action_beerListFragment_to_signUpFragment2);
//                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });
        repository = new AuthenticationRepository(getActivity().getApplication());


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
        navController = Navigation.findNavController(view);
        progressBar = view.findViewById(R.id.progress_bar);
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new BeerAdapter(getContext(), beerArrayList, this);
        signOut = view.findViewById(R.id.signOut);
        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle(R.string.beerList);



        recyclerView.setAdapter(adapter);
        beerViewModel = ViewModelProviders.of(this).get(BeerViewModel.class);

        isDBInitialize = preferences.getDefaults("isDbinitialized",getContext());
        if(isDBInitialize == "N"){
            getBeers();
        }
        else {
            setDbtoAdapter();
        }
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.signOut();
            }
        });
    }

    private void getBeers() {

        beerViewModel.getBeerResponseLiveData().observe(getViewLifecycleOwner(), bearResponse ->{
            if (!bearResponse.isEmpty()){

                List<BeerResponse> beerList = bearResponse;
                beerArrayList.addAll(beerList);
                adapter.notifyDataSetChanged();

                try {
                    setOnDatabase(beerList);
                    progressBar.setVisibility(View.GONE);
                    preferences.setDefaults("isDbinitialized", "Y", getContext());

                }catch (Throwable throwable){
                    Log.e("Error ->" , "Something wrong", throwable);
                    preferences.setDefaults("isDbinitialized", "N", getContext());
                }
            }
        });
    }
    public void setOnDatabase(List<BeerResponse> beerResponses) {
        for (BeerResponse beerResponse: beerResponses){
            Item item = new Item();
            item.setId(beerResponse.getId());
            item.setName(beerResponse.getName());
            item.setDescription(beerResponse.getDescription());
            item.setImageUrl(beerResponse.getImage_url());
            repo.insertItem(item);
        }
    }

    public void setDbtoAdapter(){
        items  =  itemDao.getAll();
        for (Item item: items) {
            beerResponse = new BeerResponse();
            beerResponse.setImage_url(item.getImageUrl());
            beerResponse.setDescription(item.getDescription());
            beerResponse.setId(item.getId());
            beerResponse.setName(item.getName());
            beerArrayList.add(beerResponse);
        }
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void itemClick(BeerResponse beerResponse) {
        bundle.putSerializable("beerSelected", beerResponse);
        beerDetailFragment.setArguments(bundle);
        navController.navigate(R.id.action_beerListFragment_to_beerDetailFragment,bundle);


        Log.d("CLICKKK---->", beerResponse.getDescription());
    }

}