package com.baxi.moviesbrowser.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.baxi.moviesbrowser.R;
import com.baxi.moviesbrowser.models.Movie;
import com.baxi.moviesbrowser.utils.Constants;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Baxi on 2018/04/26.
 * Movie Detail Activity
 */

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailViewInterface{

    @BindView(R.id.mdProgressBar)
    ProgressBar mdProgressBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvToolbarTitle)
    TextView tvToolbarTitle;
    @BindView(R.id.ivMovieCover)
    ImageView ivMovieCover;
    @BindView(R.id.tvMovieTitle)
    TextView tvMovieTitle;
    @BindView(R.id.tvMovieAveVote)
    TextView tvMovieAveVote;
    @BindView(R.id.tvMovieReleaseDate)
    TextView tvMovieReleaseDate;
    @BindView(R.id.tvMovieOverview)
    TextView tvMovieOverview;

    MovieDetailPresenter movieDetailPresenter;
    private int movieID;
    private static final String TAG = "MovieDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_moviedetail);
        ButterKnife.bind(this);

        movieID = getIntent().getIntExtra("movieId", Constants.DEFAULT_VALUE);

        movieDetailPresenter = new MovieDetailPresenter(this);
        setupViews();
        getMovieDetail();
    }

    private void setupViews(){
        tvToolbarTitle.setText(R.string.details);
        setSupportActionBar(toolbar);
    }

    private void getMovieDetail() {
        movieDetailPresenter.getMovieDetails(movieID);
    }

    /** Returns the optimized vote average with one fractional digit */
    private String optimizeAveVote(String aveVote) {

        Float aveVoteFloat = Float.parseFloat(aveVote);
        DecimalFormat df = new DecimalFormat(Constants.DECIMAL_FORMAT);
        df.setMaximumFractionDigits(Constants.MAX_FRACTION_DIGIT);

        return(df.format(aveVoteFloat));
    }

    @Override
    public void displayMovieDetail(Movie movie) {

        if(movie!=null) {

            Glide.with(this).load(Constants.MOVIE_POSTER_BASE_PATH_500+movie.getPosterPath()).into(ivMovieCover);
            tvMovieTitle.setText(movie.getTitle());
            tvMovieAveVote.setText(optimizeAveVote(movie.getVoteAverage()));
            tvMovieReleaseDate.setText(movie.getReleaseDate());
            tvMovieOverview.setText(movie.getOverview());

        }else{
            Log.d(TAG,"Movies response null");
        }

    }

    @Override
    public void showToast(String str) {
        Toast.makeText(MovieDetailActivity.this,str,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {
        mdProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mdProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayError(String e) {
        showToast(e);
    }

}
