package com.example.movie_test.model.remote

import com.example.movie_test.common.BASE_URL
import com.example.movie_test.common.END_POINT
import com.example.movie_test.model.MovieList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MoviesService {
    @GET(END_POINT)
    fun getMovies(): Call<MovieList>


    companion object{
        fun initRetrofit(): MoviesService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MoviesService::class.java)
        }
    }
}