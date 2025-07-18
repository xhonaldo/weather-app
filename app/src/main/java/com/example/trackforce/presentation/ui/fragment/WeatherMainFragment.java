package com.example.trackforce.presentation.ui.fragment;

import android.Manifest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.trackforce.R;
import com.example.trackforce.data.remote.models.WeatherResponse;
import com.example.trackforce.databinding.FragmentWeatherMainBinding;
import com.example.trackforce.domain.model.WeatherResponseData;
import com.example.trackforce.presentation.util.permission.Permission;
import com.example.trackforce.presentation.viewmodel.WeatherViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WeatherMainFragment extends BaseFragment {
    private WeatherViewModel weatherViewModel;
    private FragmentWeatherMainBinding binding;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private NavHostFragment childNavHostFragment;
    private WeatherResponseData weatherResponseData;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentWeatherMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        childNavHostFragment = (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.child_nav_host_fragment);
        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        permissionManager
                .request(new Permission.Location())
                .checkPermission(isGranted -> {
                    if (isGranted) {
                        fetchWeatherApi();
                    } else {
                        openPermissionDeniedFragment();
                    }
                });

        handleWeatherApiResponse();


        binding.switchMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                showDevModeFragment(weatherResponseData);
            } else {
                showUIModeFragment(weatherResponseData);
            }
        });
    }


    @RequiresPermission(allOf = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    private void fetchWeatherApi() {
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                weatherViewModel.fetchWeather(location.getLatitude(), location.getLongitude());
            } else {
                openPermissionDeniedFragment();
            }
        });
    }

    private void handleWeatherApiResponse() {
        weatherViewModel.getWeatherResult().observe(getViewLifecycleOwner(), result -> {
            switch (result.getStatus()) {
                case LOADING:
                    binding.progressBar.setVisibility(View.VISIBLE);
                    binding.textError.setVisibility(View.GONE);
                    break;

                case SUCCESS:
                    binding.progressBar.setVisibility(View.GONE);
                    binding.textError.setVisibility(View.GONE);
                    if (result.getData() != null) {
                        weatherResponseData = result.getData();
                        if (binding.switchMode.isChecked()) {
                            showDevModeFragment(result.getData());
                        } else {
                            showUIModeFragment(result.getData());
                        }
                    }
                    break;

                case ERROR:
                    binding.progressBar.setVisibility(View.GONE);
                    binding.textError.setVisibility(View.VISIBLE);
                    binding.textError.setText("Error: " + result.getMessage());
                    break;
            }
        });
    }

    private void openPermissionDeniedFragment() {
        NavController navController = NavHostFragment.findNavController(this);
        if (navController.getCurrentDestination() != null &&
                navController.getCurrentDestination().getId() == R.id.weatherMainFragment) {
            Bundle bundle = new Bundle();
            bundle.putString("permission_key", Manifest.permission.ACCESS_FINE_LOCATION);

            navController.navigate(R.id.permissionDeniedFragment, bundle);
        }
    }

    private void showDevModeFragment(WeatherResponseData responseData) {
        if (childNavHostFragment != null) {
            Bundle bundle = new Bundle();
            bundle.putString("json_result", new Gson().toJson(responseData));
             childNavHostFragment.getNavController().navigate(R.id.weatherDevModeFragment, bundle);
        }
    }

    private void showUIModeFragment(WeatherResponseData responseData) {
        if (childNavHostFragment != null) {
            Bundle bundle = new Bundle();
            bundle.putString("json_result", new Gson().toJson(responseData));
            childNavHostFragment.getNavController().navigate(R.id.weatherUIModeFragment, bundle);
        }
    }
}
