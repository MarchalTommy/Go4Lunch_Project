package com.aki.go4lunchproject.UI;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aki.go4lunchproject.R;
import com.aki.go4lunchproject.databinding.DetailRecyclerviewItemBinding;
import com.aki.go4lunchproject.databinding.FragmentRestaurantDetailBinding;
import com.aki.go4lunchproject.viewModels.RestaurantViewModel;

public class DetailFragment extends Fragment {

    Context context;
    RestaurantViewModel viewModel;
    DetailRecyclerviewItemBinding detailAdapter;

    FragmentRestaurantDetailBinding bindings;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_restaurant_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindings = FragmentRestaurantDetailBinding.bind(view);

        bindings.detailRatingBar.setNumStars(3);
        bindings.detailRatingBar.setRating(1.5f);
        bindings.detailRatingBar.setStepSize(0.5f);
        bindings.detailRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                //TODO : do something eventually
            }
        });


    }
}
