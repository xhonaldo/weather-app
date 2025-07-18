package com.example.trackforce.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.trackforce.domain.model.WeatherResponseData;
import com.example.trackforce.domain.usecases.GetWeatherUseCase;
import com.example.trackforce.domain.util.ErrorHandler;
import com.example.trackforce.domain.util.Result;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.Disposable;

@HiltViewModel
public class WeatherViewModel extends BaseViewModel {

    private final GetWeatherUseCase getWeatherUseCase;

    private final MutableLiveData<Result<WeatherResponseData>> weatherResult = new MutableLiveData<>();

    @Inject
    public WeatherViewModel(GetWeatherUseCase getWeatherUseCase) {
        this.getWeatherUseCase = getWeatherUseCase;
    }

    public LiveData<Result<WeatherResponseData>> getWeatherResult() {
        return weatherResult;
    }

    public void fetchWeather(double lat, double lon) {
        weatherResult.postValue(Result.loading());

        Disposable disposable = getWeatherUseCase.execute(lat, lon)
                .subscribe(
                        weatherResponse -> weatherResult.postValue(Result.success(weatherResponse)),
                        throwable -> weatherResult.setValue(Result.error(ErrorHandler.getErrorMessage(throwable)))

                );

        disposables.add(disposable);
    }


}