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

    private List<Result> results = new ArrayList<>();

    void updateList(final List<Result> results) {
        this.results = results;
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
        holder.bind(results.get(position));
    }

    @Override
    public int getItemCount() {
        if(results.isEmpty()){
            return 0;
        } else {
            return results.size();
        }
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {

        RestaurantsRecyclerviewItemBinding binding;

        public RestaurantViewHolder(View view) {
            super(view);
            binding = RestaurantsRecyclerviewItemBinding.bind(view);
        }

        public void bind(Result result) {
            if (result != null) {
                binding.restaurantTime.setText((result.getOpeningHours() == null ? " " : result.getOpeningHours().toString()));
                binding.restaurantAddress.setText(result.getVicinity());
                binding.restaurantName.setText(result.getName());

                if(result.getPhotos() != null){
                    Glide.with(binding.getRoot())
                            .load(result.getPhotos().get(0))
                            .centerCrop()
                            .into(binding.restaurantPic);
                }

                //TODO : finir les binds (distance, et ce qu'il manquera...)

                if (result.getRating() <= 2 && result.getRating() > 0) {
                    binding.restaurantRatingBar.setRating(1);
                }else if(result.getRating() <= 4 && result.getRating() > 2){
                    binding.restaurantRatingBar.setRating(2);
                } else if(result.getRating() > 4){
                    binding.restaurantRatingBar.setRating(3);
                }else {
                    binding.restaurantRatingBar.setRating(0);
                }
            }
        }
    }
}
