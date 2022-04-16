package com.example.movie_test.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

class MovieList : ArrayList<MovieResponse>()

@Parcelize
data class MovieResponse(
    val title: String,
    val image: String,
    val rating: Float,
    val releaseYear: Int,
    val genre: List<String>
) : Parcelable