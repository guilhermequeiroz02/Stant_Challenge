package com.example.stant_challenge.services

import com.example.stant_challenge.models.GenreResponse
import com.example.stant_challenge.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiInterface {

    @GET("/3/movie/popular?api_key=f321a808e68611f41312aa8408531476&language=pt-BR")
    fun getMovieList(): Call<MovieResponse>

    @GET("/3/search/movie?api_key=f321a808e68611f41312aa8408531476&language=pt-BR")
    fun getFilteredMovies(@Query("query") search: String): Call<MovieResponse>

    @GET("/3/genre/movie/list?api_key=f321a808e68611f41312aa8408531476&language=pt-BR")
    fun getGenresList(): Call<GenreResponse>


}