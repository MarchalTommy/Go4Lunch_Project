package com.aki.go4lunchproject.UI;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aki.go4lunchproject.R;
import com.aki.go4lunchproject.databinding.DetailRecyclerviewItemBinding;
import com.aki.go4lunchproject.databinding.RestaurantsRecyclerviewItemBinding;
import com.aki.go4lunchproject.models.Restaurant;

import java.util.ArrayList;

public class RestaurantDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity context;
    ArrayList<Restaurant> restaurantArrayList;
    DetailRecyclerviewItemBinding bindings;

    public RestaurantDetailAdapter (Activity context, ArrayList<Restaurant> restaurantsArrayList) {
        this.context = context;
        this.restaurantArrayList = restaurantsArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.detail_recyclerview_item, parent, false);
        return new RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Restaurant restaurant = restaurantArrayList.get(position);
        RecyclerViewViewHolder viewHolder = (RecyclerViewViewHolder) holder;


    }

    @Override
    public int getItemCount() {
        return restaurantArrayList.size();
    }

    private class RecyclerViewViewHolder extends RecyclerView.ViewHolder {

        public RecyclerViewViewHolder(View itemView) {
            super(itemView);
            bindings = DetailRecyclerviewItemBinding.bind(itemView);
        }
    }
}
