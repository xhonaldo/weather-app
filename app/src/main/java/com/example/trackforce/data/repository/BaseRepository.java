package com.example.trackforce.data.repository;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class BaseRepository {

    @Inject
    public BaseRepository() {

    }

    public static <T> T getValueBlocking(LiveData<T> liveData, long timeoutMillis) throws TimeoutException {
        final CountDownLatch latch = new CountDownLatch(1);
        final Object[] result = new Object[1];

        Runnable observeRunnable = () -> {
            Observer<T> observer = new Observer<T>() {
                @Override
                public void onChanged(T t) {
                    result[0] = t;
                    latch.countDown();
                    liveData.removeObserver(this);
                }
            };
            liveData.observeForever(observer);
        };

        new Handler(Looper.getMainLooper()).post(observeRunnable);

        try {
            if (!latch.await(timeoutMillis, TimeUnit.MILLISECONDS)) {
                throw new TimeoutException("LiveData value was never set");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        @SuppressWarnings("unchecked")
        T value = (T) result[0];
        return value;
    }
}
