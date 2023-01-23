package com.elcentaurx.bimbonet.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.elcentaurx.bimbonet.R;
import com.elcentaurx.bimbonet.data.database.entity.Item;
import com.elcentaurx.bimbonet.repository.AuthenticationRepository;
import com.elcentaurx.bimbonet.viewmodel.AuthViewModel;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;


public class ProfileFragment extends Fragment {

    private AuthViewModel viewModel;
    private NavController navController;
    private Button signOutBtn, button;
    AuthenticationRepository repository;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this , (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(AuthViewModel.class);
        viewModel.getLoggedStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    navController.navigate(R.id.action_profileFragment_to_signUpFragment);
                }
            }
        });
        repository = new AuthenticationRepository(getActivity().getApplication());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        signOutBtn = view.findViewById(R.id.btnSignOut);
        button = view.findViewById(R.id.button);

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.signOut();
            }
        });
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_profileFragment_to_beerListFragment);
            }
        });








    }

}