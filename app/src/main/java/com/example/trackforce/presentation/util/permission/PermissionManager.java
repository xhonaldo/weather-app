package com.example.trackforce.presentation.util.permission;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;

public class PermissionManager {

    private final WeakReference<Fragment> fragment;
    private final List<Permission> requiredPermissions = new ArrayList<>();

    private Callback callback = result -> {};
    private DetailedCallback detailedCallback = result -> {};

    private final ActivityResultLauncher<String[]> permissionCheck;

    @AssistedInject
    public PermissionManager(@Assisted WeakReference<Fragment> fragment) {
        this.fragment = fragment;

        Fragment frag = fragment.get();
        if (frag != null) {
            permissionCheck = frag.registerForActivityResult(
                    new ActivityResultContracts.RequestMultiplePermissions(),
                    this::sendResultAndCleanUp
            );
        } else {
            permissionCheck = null;
        }
    }

    public PermissionManager request(Permission... permissions) {
        Collections.addAll(requiredPermissions, permissions);
        return this;
    }

    public void checkPermission(@NonNull Callback callback) {
        this.callback = callback;
        handlePermissionRequest();
    }

    public boolean isPermissionGranted() {
        Fragment frag = fragment.get();
        if (frag == null) return false;
        return areAllPermissionsGranted(frag);
    }


    private void handlePermissionRequest() {
        Fragment frag = fragment.get();
        if (frag != null) {
            if (areAllPermissionsGranted(frag)) {
                sendPositiveResult();
            } else {
                requestPermissions();
            }
        }
    }

    private void sendPositiveResult() {
        Map<Permission, Boolean> allGranted = new HashMap<>();
        for (Permission permission : requiredPermissions) {
            allGranted.put(permission, true);
        }
        callback.onResult(true);
        detailedCallback.onResult(allGranted);
        cleanUp();
    }

    private void sendResultAndCleanUp(Map<String, Boolean> grantResults) {
        boolean allGranted = !grantResults.containsValue(false);
        callback.onResult(allGranted);

        Map<Permission, Boolean> result = new HashMap<>();
        for (Map.Entry<String, Boolean> entry : grantResults.entrySet()) {
            result.put(Permission.from(entry.getKey()), entry.getValue());
        }
        detailedCallback.onResult(result);
        cleanUp();
    }

    private void cleanUp() {
        requiredPermissions.clear();
        callback = result -> {};
        detailedCallback = result -> {};
    }

    private void requestPermissions() {
        if (permissionCheck != null) {
            permissionCheck.launch(getPermissionList());
        }
    }

    private boolean areAllPermissionsGranted(Fragment fragment) {
        for (Permission permission : requiredPermissions) {
            if (!isGranted(fragment, permission)) return false;
        }
        return true;
    }

    private String[] getPermissionList() {
        List<String> all = new ArrayList<>();
        for (Permission permission : requiredPermissions) {
            Collections.addAll(all, permission.getPermissions());
        }
        return all.toArray(new String[0]);
    }

    private boolean isGranted(Fragment fragment, Permission permission) {
        for (String perm : permission.getPermissions()) {
            if (!hasPermission(fragment, perm)) return false;
        }
        return true;
    }

    private boolean hasPermission(Fragment fragment, String permission) {
        return ContextCompat.checkSelfPermission(fragment.requireContext(), permission)
                == PackageManager.PERMISSION_GRANTED;
    }

    public boolean areNotificationsEnabled(Context context) {
        return NotificationManagerCompat.from(context).areNotificationsEnabled();
    }


    public interface Callback {
        void onResult(boolean granted);
    }

    public interface DetailedCallback {
        void onResult(Map<Permission, Boolean> grantResult);
    }
}