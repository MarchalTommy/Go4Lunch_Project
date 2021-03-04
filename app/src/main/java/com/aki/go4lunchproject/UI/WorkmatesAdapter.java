package com.aki.go4lunchproject.UI;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aki.go4lunchproject.R;
import com.aki.go4lunchproject.databinding.WorkmatesRecyclerviewItemBinding;
import com.aki.go4lunchproject.models.User;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class WorkmatesAdapter extends FirestoreRecyclerAdapter<User, WorkmatesAdapter.WorkmatesHolder> {

    WorkmatesRecyclerviewItemBinding binding;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public WorkmatesAdapter(@NonNull FirestoreRecyclerOptions<User> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull WorkmatesHolder holder, int position, @NonNull User model) {
        binding.workmatesProfilePic.setImageURI(Uri.parse(model.getUrlPicture()));
        binding.workmatesNameAndLunch.setText(model.getUsername() + " is eating at //QUELQUE PART" );
        binding.workmatesProfilePic.setImageResource(R.drawable.com_facebook_profile_picture_blank_portrait);
    }

    @NonNull
    @Override
    public WorkmatesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.workmates_recyclerview_item, parent, false);
        return new WorkmatesHolder(v);
    }

    class WorkmatesHolder extends RecyclerView.ViewHolder {


        public WorkmatesHolder(@NonNull View itemView) {
            super(itemView);
            binding = WorkmatesRecyclerviewItemBinding.bind(itemView);
        }
    }
}
