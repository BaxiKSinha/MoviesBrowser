package com.baxi.moviesbrowser.ui;

import com.baxi.moviesbrowser.models.MovieResponse;

/**
 * Created by Baxi on 2018/04/25.
 *
 */

public interface MainViewInterface {

    void showToast(String s);
    void displayMovies(MovieResponse movieResponse);
    void displayError(String s);
    void showProgressBar();
    void hideProgressBar();
}
