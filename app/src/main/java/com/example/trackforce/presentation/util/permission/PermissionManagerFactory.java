package com.example.trackforce.presentation.util.permission;

import androidx.fragment.app.Fragment;

import java.lang.ref.WeakReference;

import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface PermissionManagerFactory {
    PermissionManager create(WeakReference<Fragment> fragment);
}