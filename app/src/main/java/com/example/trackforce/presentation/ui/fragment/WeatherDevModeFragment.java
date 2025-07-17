package com.example.trackforce.presentation.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.trackforce.databinding.FragmentWeatherDevModeBinding;
import com.example.trackforce.presentation.util.Helper;
import com.example.trackforce.presentation.util.json_highlight.JsonHighlighterUtils;

import javax.inject.Inject;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WeatherDevModeFragment extends BaseFragment {

    private FragmentWeatherDevModeBinding binding;

    @Inject
    Helper helper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentWeatherDevModeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String jsonResult = "";
        if (getArguments() != null) {
            jsonResult = getArguments().getString("json_result", "");
        }

        String prettyJson = helper.jsonPrettyPrinting(jsonResult);
        binding.jsonText.setText(JsonHighlighterUtils.highlightJson(prettyJson, getContext()));
    }

}
