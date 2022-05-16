package com.example.stant_challenge.view.viewmodel

import androidx.lifecycle.ViewModel
import com.example.stant_challenge.models.Genre
import com.example.stant_challenge.models.GenreResponse
import com.example.stant_challenge.models.Movie
import com.example.stant_challenge.models.MovieResponse
import com.example.stant_challenge.services.MovieApiInterface
import com.example.stant_challenge.services.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.ISO_DATE
import java.util.*

class MoviesViewModel : ViewModel() {

    private var movies: MutableList<Movie> = mutableListOf()
    private var genres: MutableList<Genre> = mutableListOf()

    fun getMovieData(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                movies = response.body()!!.movies.toMutableList()
                applyFormats()
                return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
            }

        })
    }


    fun getFilteredMovies(search: String, callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getFilteredMovies(search).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                movies = response.body()!!.movies.toMutableList()
                applyFormats()
                return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getGenreData() {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getGenresList().enqueue(object : Callback<GenreResponse> {
            override fun onResponse(call: Call<GenreResponse>, response: Response<GenreResponse>) {
                genres = response.body()!!.genres.toMutableList()
            }

            override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun applyFormats() {
        var newMovies: MutableList<Movie> = mutableListOf()

        movies.forEach {
            var movie = it
            var genresNames = ""
            var firstGenreName = ""
            val splittedDate = movie.release?.split("-")
            val formattedDate = "${splittedDate?.getOrNull(2)}-${splittedDate?.getOrNull(1)}-${splittedDate?.getOrNull(0)}"

            it.genres?.forEach { genreId ->
                val genre = genres.filter { it.id == genreId }.first()
                genresNames += "${genre.name}, "
                if (firstGenreName.isEmpty()) {
                    firstGenreName = genre.name.toString()
                }
            }
            movie.release = formattedDate
            movie.firstGenreName = firstGenreName
            movie.genreNames = genresNames
            newMovies.add(movie)
        }
        movies = newMovies
    }

    fun selectItem(position: Int): Movie {
        return movies.elementAt(position)

    }
}