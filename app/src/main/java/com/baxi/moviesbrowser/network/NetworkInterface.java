package com.baxi.moviesbrowser.network;

import com.baxi.moviesbrowser.models.Movie;
import com.baxi.moviesbrowser.models.MovieResponse;
import com.baxi.moviesbrowser.utils.Constants;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Baxi on 2018/04/25.
 * Network interface
 */

public interface NetworkInterface {

    @GET(Constants.MOVIES_NOW_PLAYING)
    public Observable<MovieResponse> getMovies(@Query("api_key") String api_key);

    @GET(Constants.MOVIE_DETAILS)
    public Observable<Movie> getMovieDetails(@Path("id") int id, @Query("api_key") String api_key);

}
