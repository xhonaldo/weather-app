package com.example.trackforce.presentation.viewmodel;

import androidx.lifecycle.ViewModel;


import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.CompositeDisposable;


@HiltViewModel
public class BaseViewModel extends ViewModel {
    public final CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public BaseViewModel() {

    }

    @Override
    protected void onCleared() {
        disposables.clear();
        super.onCleared();
    }



}
