package com.example.stant_challenge.view.viewholder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stant_challenge.adapter.MovieAdapter
import com.example.stant_challenge.R
import com.example.stant_challenge.models.Movie
import com.example.stant_challenge.view.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : AppCompatActivity(), SearchView.OnQueryTextListener, MovieAdapter.onItemClickListener{

    private lateinit var mMoviesViewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        mMoviesViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)

        mMoviesViewModel.getGenreData()
        recycler_movies.layoutManager = LinearLayoutManager(this)
        recycler_movies.setHasFixedSize(true)
        mMoviesViewModel.getMovieData { movies: List<Movie> ->
            var adapter = MovieAdapter(movies)
            recycler_movies.adapter = adapter
            adapter.setOnItemClickListener(this)
        }


        search_movie.setOnQueryTextListener(this)

        supportActionBar?.hide()
    }

    override fun onItemClick(position: Int) {
        callSelectedMovieActivity(position)
    }

   private fun callSelectedMovieActivity(position: Int){
        val movie = mMoviesViewModel.selectItem(position)
        val intent = Intent(this, SelectedMovieActivity::class.java).putExtra("SelectedMovie", movie)
        startActivity(intent)
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(text: String?): Boolean {
        if (text.isNullOrEmpty()) {
            mMoviesViewModel.getMovieData { movies: List<Movie> ->
                var adapter = MovieAdapter(movies)
                recycler_movies.adapter = adapter
                adapter.setOnItemClickListener(this)
            }
        } else {
            mMoviesViewModel.getFilteredMovies(text) { movies: List<Movie> ->
                var adapter = MovieAdapter(movies)
                recycler_movies.adapter = adapter
                adapter.setOnItemClickListener(this)
            }
        }
        return true
    }
}