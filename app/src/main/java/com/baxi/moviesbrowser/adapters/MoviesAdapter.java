package com.baxi.moviesbrowser.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baxi.moviesbrowser.R;
import com.baxi.moviesbrowser.models.Movie;
import com.baxi.moviesbrowser.ui.MovieDetailActivity;
import com.baxi.moviesbrowser.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Baxi on 2018/04/25.
 * Movies adapter
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesHolder> {

    List<Movie> movieList;
    Context context;

    public MoviesAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @Override
    public MoviesHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.row_movies,parent,false);
        return new MoviesHolder(v);
    }

    @Override
    public void onBindViewHolder(MoviesHolder holder, int position) {
        holder.tvTitle.setText(movieList.get(position).getTitle().trim());
        Glide.with(context).load(Constants.MOVIE_POSTER_BASE_PATH_500+movieList.get(position).getPosterPath()).into(holder.ivMovie);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    protected class MoviesHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.ivMovie)
        ImageView ivMovie;

        MoviesHolder(View v) {
            super(v);

            ButterKnife.bind(this,v);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent movieDetailIntent = new Intent(context, MovieDetailActivity.class);
                    movieDetailIntent.putExtra("movieId", movieList.get(getAdapterPosition()).getId());
                    context.startActivity(movieDetailIntent);
                }
            });
        }
    }
}
