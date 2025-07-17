package com.example.trackforce.data.remote.module;

import com.example.trackforce.BuildConfig;
import com.example.trackforce.data.remote.ApiServiceFactory;
import com.example.trackforce.data.remote.AuthInterceptor;
import com.example.trackforce.data.remote.WeatherRetrofit;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(AuthInterceptor interceptor) {
        List<ConnectionSpec> specs = Arrays.asList(
                ConnectionSpec.MODERN_TLS,
                new ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS)
                        .tlsVersions(TlsVersion.TLS_1_0, TlsVersion.TLS_1_1, TlsVersion.TLS_1_2)
                        .build(),
                ConnectionSpec.CLEARTEXT
        );

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .connectionSpecs(specs)
                .addInterceptor(interceptor)
                .addInterceptor(logging)
                .build();
    }

    @Provides
    @Singleton
    @WeatherRetrofit
    public static ApiServiceFactory provideWeatherApiServiceFactory(
            @WeatherRetrofit Retrofit retrofit
    ) {
        return new ApiServiceFactory(retrofit);
    }
    @Provides
    @Singleton
    @WeatherRetrofit
    public Retrofit provideWeatherRetrofit(OkHttpClient okHttpClient, Gson gson) {
        String API_URL = BuildConfig.BASE_DOMAIN + BuildConfig.API_VERSION;

        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

}
