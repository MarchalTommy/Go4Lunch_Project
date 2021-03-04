package com.aki.go4lunchproject.UI;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aki.go4lunchproject.R;
import com.aki.go4lunchproject.databinding.FragmentListBinding;
import com.aki.go4lunchproject.helpers.RestaurantCalls;
import com.aki.go4lunchproject.models.Restaurant;
import com.aki.go4lunchproject.viewModels.RestaurantViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ListFragment extends Fragment implements LifecycleOwner, RestaurantCalls.Callbacks {

    RestaurantViewModel viewModel;
    RestaurantListAdapter adapter;

    FragmentListBinding bindings;

    //List of restaurants fetched from the API call, used to populate the RV
    List<Restaurant> restaurantsAround = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: FRAGMENT CREATED");
        super.onViewCreated(view, savedInstanceState);
        bindings = FragmentListBinding.bind(view);

        adapter = new RestaurantListAdapter();

        bindings.restaurantsRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        bindings.restaurantsRecyclerView.setAdapter(adapter);

        executeHttpRequestWithRetrofit();

        if (restaurantsAround.isEmpty()) {
            bindings.noData.setVisibility(View.VISIBLE);
        } else {
            adapter.updateList(restaurantsAround);
            bindings.noData.setVisibility(View.GONE);
        }
    }

    private void executeHttpRequestWithRetrofit() {
        Log.d(TAG, "executeHttpRequestWithRetrofit: FETCHING DATAS FROM API");
        RestaurantCalls.fetchRestaurantsTest(this, "48.865623,2.347057");
    }

    @Override
    public void onResponse(@Nullable Restaurant restaurants) {
        bindings.noData.setVisibility(View.GONE);
        if (restaurants != null) {
            Log.d(TAG, "onResponse: RESTAURANTS HAS BEEN FOUNDS" + restaurants.toString());
            restaurantsAround.clear();
            restaurantsAround.add(restaurants);
        }
    }

    @Override
    public void onResponse(@Nullable List<Restaurant> restaurants) {
        bindings.noData.setVisibility(View.GONE);
        if (restaurants != null) {
            Log.d(TAG, "onResponse: RESTAURANTS HAS BEEN FOUNDS" + restaurants.toString());
            restaurantsAround.clear();
            restaurantsAround.addAll(restaurants);
        }
    }

    @Override
    public void onFailure() {
        Log.d(TAG, "onFailure: RESTAURANTS NOT FOUNDS");

        bindings.noData.setVisibility(View.VISIBLE);
        bindings.noData.setText("An error occured. Please retry later.");
    }
}
