package com.baxi.moviesbrowser.ui;

import android.util.Log;

import com.baxi.moviesbrowser.models.Movie;
import com.baxi.moviesbrowser.network.NetworkClient;
import com.baxi.moviesbrowser.network.NetworkInterface;
import com.baxi.moviesbrowser.utils.Constants;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Baxi on 2018/04/26.
 *
 */

public class MovieDetailPresenter implements MovieDetailPresenterInterface {

    MovieDetailViewInterface mdvi;
    private static final String TAG = "MovieDetailPresenter";
    int movieId;

    public MovieDetailPresenter(MovieDetailViewInterface mdvi) {
        this.mdvi = mdvi;
    }

    @Override
    public void getMovieDetails(int id) {
        movieId = id;
        getObservable().subscribeWith(getObserver());
    }

    public Observable<Movie> getObservable(){
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getMovieDetails(movieId, Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<Movie> getObserver(){
        return new DisposableObserver<Movie>() {

            @Override
            public void onNext(@NonNull Movie movie) {
                mdvi.displayMovieDetail(movie);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
                mdvi.displayError("Error fetching movie details");
            }

            @Override
            public void onComplete() {
                mdvi.hideProgressBar();
            }
        };
    }
}
