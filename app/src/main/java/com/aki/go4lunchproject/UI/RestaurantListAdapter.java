package com.aki.go4lunchproject.UI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aki.go4lunchproject.R;
import com.aki.go4lunchproject.databinding.RestaurantsRecyclerviewItemBinding;
import com.aki.go4lunchproject.models.Restaurant;
import com.aki.go4lunchproject.models.Result;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder> {

    Result mRestaurant;
    private List<Restaurant> restaurants = new ArrayList<>();

    void updateList(final List<Restaurant> restaurants) {
        this.restaurants = restaurants;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurants_recyclerview_item, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        holder.bind(restaurants.get(position));
    }

    @Override
    public int getItemCount() {
        if(restaurants.isEmpty()){
            return 0;
        } else {
            return restaurants.size();
        }
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {

        RestaurantsRecyclerviewItemBinding binding;

        public RestaurantViewHolder(View view) {
            super(view);
            binding = RestaurantsRecyclerviewItemBinding.bind(view);
        }

        public void bind(Restaurant restaurant) {
            if (restaurant != null) {
                Result result = restaurant.getResults().get(0);

                binding.restaurantType.setText(result.getTypes().get(0));
                binding.restaurantTime.setText(result.getOpeningHours().toString());
                binding.restaurantAddress.setText(result.getVicinity());
                binding.restaurantName.setText(result.getName());
                Glide.with(binding.getRoot())
                        .load(result.getPhotos().get(0))
                        .centerCrop()
                        .into(binding.restaurantPic);
                //TODO : finir les binds (RatingBar, distance, et ce qu'il manquera...)
            }
        }
    }
}
