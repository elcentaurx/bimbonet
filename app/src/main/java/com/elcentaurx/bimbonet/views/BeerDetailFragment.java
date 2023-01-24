package com.elcentaurx.bimbonet.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.elcentaurx.bimbonet.R;
import com.elcentaurx.bimbonet.model.BeerResponse;

import java.util.List;


public class BeerDetailFragment extends Fragment {
    BeerResponse beerResponse;
    private TextView description, title;
    private ImageView imageView;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beerResponse = new BeerResponse();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        beerResponse = (BeerResponse) getArguments().getSerializable("beerSelected");
        Log.d("HERE===========>", beerResponse.getDescription());
        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle(beerResponse.getName());

        return inflater.inflate(R.layout.fragment_beer_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        description = view.findViewById(R.id.tvDescriptionDetail);
        title = view.findViewById(R.id.tvTitleDetail);
        imageView = view.findViewById(R.id.imageViewCoverDetail);

        description.setText(beerResponse.getDescription());
        title.setText(beerResponse.getName());
        Glide.with(getContext())
                .load(beerResponse.getImage_url())
                .into(imageView);



    }

}