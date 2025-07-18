package com.example.trackforce.presentation.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.trackforce.R;
import com.example.trackforce.databinding.FragmentWeatherUiModeBinding;
import com.example.trackforce.domain.model.WeatherResponseData;
import com.example.trackforce.presentation.util.Helper;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WeatherUIModeFragment extends  BaseFragment {

    private FragmentWeatherUiModeBinding binding;
    private WeatherResponseData responseData;

    @Inject
    Helper helper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentWeatherUiModeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String jsonResult = "";
        if (getArguments() != null) {
            jsonResult = getArguments().getString("json_result", "");
        }

        if (jsonResult != null) {
            responseData = new Gson().fromJson(jsonResult, WeatherResponseData.class);
        }

        bindDataToViews();
        setBackgroundByWeatherAndTime();
    }


    private void bindDataToViews() {
        if (responseData == null) return;

        binding.tvCity.setText(responseData.getCityName());
        binding.tvDate.setText(responseData.getFormattedDateTime());
        binding.tvDescription.setText(responseData.getCloud().getCloudiness());
        binding.tvTemperature.setText(responseData.getMain().getTemp());
        binding.tvFeelsLike.setText(responseData.getMain().getFeelsLike());
        binding.tvHumidity.setText(responseData.getMain().getHumidity());
        binding.tvPressure.setText(responseData.getMain().getPressure());
        String windLabel = responseData.getWind().getSpeed()+ " " + responseData.getWind().getDeg();
        binding.tvWind.setText(windLabel);
        binding.tvVisibility.setText(responseData.getVisibility());

        if (!responseData.getWeatherDataList().isEmpty()) {
            Picasso.get()
                    .load(responseData.getWeatherDataList().get(0).getLogoUrl())
                    .into(binding.ivWeatherIcon);
        }

    }


    private void setBackgroundByWeatherAndTime() {
        if (responseData == null) return;

        String drawableName = helper.getBackgroundDrawableName(
                responseData.getSys().getSunrise(),
                responseData.getSys().getSunset(),
                responseData.getWeatherDataList(),
                System.currentTimeMillis() / 1000L
        );

        int drawableResId = getResources().getIdentifier(drawableName, "drawable", requireContext().getPackageName());

        if (drawableResId != 0) {
            binding.getRoot().setBackgroundResource(drawableResId);
        } else {
            binding.getRoot().setBackgroundResource(R.drawable.bg_gradient);
        }
    }

}
