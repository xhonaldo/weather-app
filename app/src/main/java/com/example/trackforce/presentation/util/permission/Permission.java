package com.example.trackforce.presentation.util.permission;

import android.Manifest;

public abstract class Permission {

    private final String[] permissions;

    protected Permission(String... permissions) {
        this.permissions = permissions;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public static final class Location extends Permission {
        public Location() {
            super(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
        }
    }

    public static Permission from(String permission) {
        if (Manifest.permission.ACCESS_FINE_LOCATION.equals(permission)
                || Manifest.permission.ACCESS_COARSE_LOCATION.equals(permission)) {
            return new Location();
        } else {
            throw new IllegalArgumentException("Unknown permission: " + permission);
        }
    }
}