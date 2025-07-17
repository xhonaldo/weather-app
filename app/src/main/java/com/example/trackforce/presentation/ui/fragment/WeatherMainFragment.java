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
import com.example.trackforce.databinding.FragmentWeatherBinding;
import com.example.trackforce.presentation.util.permission.Permission;
import com.example.trackforce.presentation.viewmodel.WeatherViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WeatherMainFragment extends BaseFragment {
    private WeatherViewModel weatherViewModel;
    private FragmentWeatherBinding binding;
    private FusedLocationProviderClient fusedLocationProviderClient;


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
        binding = FragmentWeatherBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
//        weatherViewModel.getWeatherResult().observe(getViewLifecycleOwner(), result -> {
//            switch (result.getStatus()) {
//                case LOADING:
//                    binding.progressBar.setVisibility(View.VISIBLE);
//                    binding.textError.setVisibility(View.GONE);
//                    break;
//
//                case SUCCESS:
//                    binding.progressBar.setVisibility(View.GONE);
//                    binding.textError.setVisibility(View.GONE);
//
//                    if (result.getData() != null) {
//                        binding.textTemperature.setText("Temperature: " + result.getData().getMain().getTemp() + "°C");
//                        binding.textDescription.setText("Feels Like: " + result.getData().getMain().getFeelsLike());
//                        binding.textHumidity.setText("Humidity: " + result.getData().getMain().getHumidity() + "%");
//                    }
//                    break;
//
//                case ERROR:
//                    binding.progressBar.setVisibility(View.GONE);
//                    binding.textError.setVisibility(View.VISIBLE);
//                    binding.textError.setText("Error: " + result.getMessage());
//                    break;
//            }
//        });
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

}
