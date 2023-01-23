package com.elcentaurx.bimbonet.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.elcentaurx.bimbonet.R;
import com.elcentaurx.bimbonet.model.Beer;
import com.elcentaurx.bimbonet.repository.response.BeerResponse;

import java.util.ArrayList;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.ViewHolder> {

    private final Context context;
    ArrayList<BeerResponse> beerArrayList;

    public BeerAdapter(Context context, ArrayList<BeerResponse> beerArrayList){
        this.context = context;
        this.beerArrayList = beerArrayList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.beer_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BeerResponse beer = beerArrayList.get(position);
//        BeerResponse beer = beerArrayList.get(position);
        holder.tvTitle.setText(beer.getName());
        Glide.with(context)
                .load(beer.getImage_url())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return beerArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageViewCover);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }
}
