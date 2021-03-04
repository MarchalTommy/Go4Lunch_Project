package com.aki.go4lunchproject.UI;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.aki.go4lunchproject.R;
import com.aki.go4lunchproject.databinding.FragmentMainBinding;
import com.aki.go4lunchproject.databinding.NavHeaderBinding;
import com.aki.go4lunchproject.databinding.SettingsDialogBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.auth.AuthUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.auth.FirebaseAuth;

import static android.content.ContentValues.TAG;

public class MainFragment extends Fragment {

    //TODO : Gérer la map et places
    //TODO : Gérer ListView et Workmates
    //TODO : Gérer YourLunch et les favoris

    NavController navController;

    // BINDINGS
    FragmentMainBinding mainBinding;
    NavHeaderBinding headerBinding;
    SettingsDialogBinding settingsBinding;

    // UI
    private DrawerLayout drawer;
    private Toolbar toolbar;

    // Listeners
    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavListener =
            item -> {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.nav_map:
                        selectedFragment = new MapsFragment();
                        break;
                    case R.id.nav_list:
                        selectedFragment = new ListFragment();
                        break;
                    case R.id.nav_coworkers:
                        selectedFragment = new WorkmatesFragment();
                        break;
                }
                MainFragment.this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_main,
                        selectedFragment).commit();

                return true;
            };
    private NavigationView.OnNavigationItemSelectedListener drawerListener =
            item -> {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.your_lunch:
                        selectedFragment = new DetailFragment();
                        toolbar.setVisibility(View.GONE);
                        break;
                    case R.id.settings:
                        showSettings();
                        break;
                    case R.id.logout:
                        AuthUI.getInstance()
                                .signOut(getContext());
                        navController.navigate(R.id.action_mainFragment_to_loginFragment);
                        break;
                }
                return true;
            };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainBinding = FragmentMainBinding.bind(view);
        navController = Navigation.findNavController(view);

        toolbar = mainBinding.toolbar;
        toolbar.setTitle("I'm Hungry !");

        drawer = mainBinding.drawerLayout;
        NavigationView navView = mainBinding.navView;
        navView.setNavigationItemSelectedListener(drawerListener);
        headerBinding = NavHeaderBinding.bind(navView.getHeaderView(0));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this.getActivity(), drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        BottomNavigationView bottomNav = mainBinding.bottomNavView;
        bottomNav.setOnNavigationItemSelectedListener(bottomNavListener);

        updateUi();

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_main, new MapsFragment()).commit();
    }

    public void updateUi() {
        Glide.with(this)
                .load(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(headerBinding.profilePic);

        headerBinding.username.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        headerBinding.usermail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }

    public void showSettings() {
        SettingsDialog settingsDialog = new SettingsDialog();
        settingsDialog.show(getChildFragmentManager(), "settings Dialog");
    }

    public static class SettingsDialog extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.settings_dialog, null, false);
            SettingsDialogBinding binding = SettingsDialogBinding.bind(view);

            builder.setView(view)
                    .setCancelable(true)
                    .setNeutralButton("Confirm settings", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                           if (binding.notificationSwitch.isEnabled()) {
                                //TODO : activer notifications
                            } else {
                               //TODO : désactiver notifications
                           }
                        }
                    });

            return builder.create();
        }
    }
}