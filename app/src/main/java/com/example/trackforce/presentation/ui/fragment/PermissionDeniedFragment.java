package com.example.trackforce.presentation.ui.fragment;

import android.Manifest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.trackforce.databinding.FragmentPermissionDeniedBinding;
import com.example.trackforce.presentation.util.Helper;
import com.example.trackforce.presentation.util.permission.Permission;


import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PermissionDeniedFragment extends BaseFragment {

    private FragmentPermissionDeniedBinding binding;

    @Inject
    Helper helper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPermissionDeniedBinding.inflate(inflater, container, false);

        binding.permissionSettingsButton.setOnClickListener(view -> {
            helper.openSettings(getContext());
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String permission = "";
        if (getArguments() != null) {
            permission = getArguments().getString("permission_key", "");
        }

        String permissionMessage = "The app needs permission to access" + getPermissionAction(permission)
                + ". This permission allows the app to" +getPermissionName(permission)+". " +
                "You can grant permission in the app settings.";

        binding.permissionMessage.setText(permissionMessage);
    }



    private String getPermissionName(String permission) {
        if (permission == null) return "";

        if (permission.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
            return "Fine Location";
        }
        return "";
    }

    private String getPermissionAction(String permission) {
        if (permission == null) return "";

        if (permission.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
            return "Access your location";
        }
        return "";
    }
}
