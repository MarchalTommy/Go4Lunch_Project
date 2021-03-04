package com.aki.go4lunchproject.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aki.go4lunchproject.R;
import com.aki.go4lunchproject.databinding.FragmentWorkmatesBinding;
import com.aki.go4lunchproject.models.User;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class WorkmatesFragment extends Fragment {

    FragmentWorkmatesBinding binding;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference workmatesRef = db.collection("users");
    private WorkmatesAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workmates, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentWorkmatesBinding.bind(view);

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        Query query = workmatesRef.orderBy("username", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>()
                .setQuery(query, User.class)
                .build();

        adapter = new WorkmatesAdapter(options);

        binding.workmatesRecyclerView.setHasFixedSize(true);
        binding.workmatesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.workmatesRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
