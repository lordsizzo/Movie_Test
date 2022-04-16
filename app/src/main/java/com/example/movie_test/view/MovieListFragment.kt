package com.example.movie_test.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_test.R
import com.example.movie_test.model.MovieList
import com.example.movie_test.model.MovieResponse
import com.example.movie_test.model.remote.MoviesService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListFragment : Fragment() {

    val TAG = "MovieListFragment"
    private lateinit var movieList: RecyclerView
    private lateinit var adapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.movie_list_fragment_layout, container, false)
        initViews(view)
        getMovies()
        return view
    }

    private fun initViews(view: View) {
        movieList = view.findViewById(R.id.movie_list)
        movieList.layoutManager = GridLayoutManager(requireContext(), 3)

    }

    private fun getMovies() {
        MoviesService.initRetrofit().getMovies().enqueue(
            object : Callback<MovieList> {
                override fun onResponse(
                    call: Call<MovieList>,
                    response: Response<MovieList>
                ) {
                    Log.d(TAG, "onResponse: $response")
                    if (response.isSuccessful) {
                        response.body()?.let { updateAdapter(it) }
                    } else {
                        showError(response.message())
                        Log.d(TAG, "onResponse: ${response.errorBody()}")
                    }
                }

                override fun onFailure(call: Call<MovieList>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message}")
                    showError(t.message ?: "Unknown error")
                }
            }
        )
    }

    private fun showError(message: String) {

    }

    private fun updateAdapter(body: MovieList) {
        body.let { result ->
            adapter = MoviesAdapter(result) {
                activity?.openMovieDetails(it)
            }
            movieList.adapter = adapter
        } ?: showError("No response from server")
    }

    private fun FragmentActivity.openMovieDetails(movieDetails: MovieResponse) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, MovieDetailsFragment.newInstance(movieDetails))
            .addToBackStack(null)
            .commit()
    }
}
