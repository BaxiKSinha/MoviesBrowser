package com.baxi.moviesbrowser.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.baxi.moviesbrowser.R;
import com.baxi.moviesbrowser.adapters.MoviesAdapter;
import com.baxi.moviesbrowser.models.MovieResponse;
import com.baxi.moviesbrowser.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Baxi on 2018/04/25.
 * Home screen for now playing movies
 */

public class MainActivity extends AppCompatActivity implements MainViewInterface {

    @BindView(R.id.rvMovies)
    RecyclerView rvMovies;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvToolbarTitle)
    TextView tvToolbarTitle;

    private static final String TAG = "MainActivity";
    RecyclerView.Adapter adapter;
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainPresenter = new MainPresenter(this);
        setupViews();
        getMovieList();
    }

    private void setupViews(){
        tvToolbarTitle.setText(R.string.now_playing);
        setSupportActionBar(toolbar);
        rvMovies.setLayoutManager(new GridLayoutManager(this, Constants.NO_OF_COLUMNS));
    }

    private void getMovieList() {
        mainPresenter.getMovies();
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(MainActivity.this,str,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayMovies(MovieResponse movieResponse) {
        if(movieResponse!=null) {
            adapter = new MoviesAdapter(movieResponse.getResults(), MainActivity.this);
            rvMovies.setAdapter(adapter);
        }else{
            Log.d(TAG,"Response null");
        }
    }

    @Override
    public void displayError(String e) {
        showToast(e);
    }

}
