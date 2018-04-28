package com.baxi.moviesbrowser.ui;

import com.baxi.moviesbrowser.models.Movie;

/**
 * Created by Baxi on 2018/04/26.
 *
 */

public interface MovieDetailViewInterface {

    void showToast(String s);
    void displayMovieDetail(Movie movie);
    void displayError(String s);
    void showProgressBar();
    void hideProgressBar();
}
