package com.example.movie_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_test.model.MovieList
import com.example.movie_test.model.MovieResponse
import com.example.movie_test.view.MovieListFragment
import com.example.movie_test.view.MoviesAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*findViewById<RecyclerView>(R.id.move_list).apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = MoviesAdapter(MovieList().apply {
                add(
                    MovieResponse(
                        "X-Men",
                        "https://api.androidhive.info/json/movies/4.jpg",
                        3.6f,
                        2022,
                        listOf("some genre")
                    )
                )
            }, :: openDetails)
        }*/

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MovieListFragment())
            .commit()
    }

    private fun openDetails(movieItem: MovieResponse): Unit {
        Toast.makeText(this, "$movieItem", Toast.LENGTH_SHORT).show()
    }
}