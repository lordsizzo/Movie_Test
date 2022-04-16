package com.example.movie_test.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_test.R
import com.example.movie_test.model.MovieList
import com.example.movie_test.model.MovieResponse
import com.squareup.picasso.Picasso
import java.lang.Exception

class MoviesAdapter(val items: MovieList, private val openDetails: (MovieResponse) -> Unit) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    class MoviesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val moviePosters: ImageView = view.findViewById(R.id.im_movie_poster)
        private val movieTitle: TextView = view.findViewById(R.id.tv_movie_title)

        fun onBind(dataItem: MovieResponse, openDetails: (MovieResponse) -> Unit) {
            movieTitle.text = dataItem.title
            Picasso.get().load(dataItem.image).into(moviePosters)

            movieTitle.setOnClickListener {
                openDetails(dataItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movies_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        try {
            holder.onBind(items[position], openDetails)

        } catch (e: Exception) {
            Log.d("MoviesAdapter", "onBindViewHolder: ${e.message}")
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}