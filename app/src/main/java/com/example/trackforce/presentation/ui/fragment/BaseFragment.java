package com.example.trackforce.presentation.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.trackforce.presentation.util.permission.PermissionManager;
import com.example.trackforce.presentation.util.permission.PermissionManagerFactory;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BaseFragment extends Fragment {

    @Inject
    public BaseFragment() {

    }

    @Inject
    public PermissionManagerFactory permissionManagerFactory;
    public PermissionManager permissionManager;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        permissionManager = permissionManagerFactory.create(new WeakReference<>(this));
    }
}
