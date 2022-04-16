package com.example.movie_test.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.movie_test.R
import com.example.movie_test.model.MovieResponse
import com.squareup.picasso.Picasso

class MovieDetailsFragment : Fragment() {

    companion object {
        private const val MOVIE_DETAIL_DATA = "MOVIE_DETAIL_DATA"
        fun newInstance(movieDetails: MovieResponse) = MovieDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(MOVIE_DETAIL_DATA, movieDetails)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.movie_detail_fragment_layout, container, false)

        arguments?.getParcelable<MovieResponse>(MOVIE_DETAIL_DATA)?.let {
            iniView(view, it)
        }

        return view

    }

    private fun iniView(parent: View, movieDetails: MovieResponse) {
        parent.run {

            findViewById<TextView>(R.id.tv_movie_detail_genre).text = movieDetails.genre.toString()
            findViewById<TextView>(R.id.tv_movie_detail_title).text = movieDetails.title
            findViewById<TextView>(R.id.tv_movie_detail_release).text =
                movieDetails.releaseYear.toString()
            findViewById<RatingBar>(R.id.rb_movie_detail_rating).rating = movieDetails.rating
            Picasso.get().load(movieDetails.image)
                .into(findViewById<ImageView>(R.id.iv_movie_detail_poster))
        }
    }
}