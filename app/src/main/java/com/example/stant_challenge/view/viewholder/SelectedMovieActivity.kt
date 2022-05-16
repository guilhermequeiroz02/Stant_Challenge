package com.example.stant_challenge.view.viewholder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Toast
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.stant_challenge.R
import com.example.stant_challenge.models.Movie
import kotlinx.android.synthetic.main.activity_selected_movie.*
import kotlinx.android.synthetic.main.movie_item.*
import kotlinx.android.synthetic.main.movie_item.view.*

class SelectedMovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_movie)

        val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        val movie: Movie? = intent.getParcelableExtra("SelectedMovie")

        selected_movie_poster.load(IMAGE_BASE + movie?.poster) {
            transformations(RoundedCornersTransformation(50F))
        }

       release_movie_details.text = movie?.release
        big_overview.text = movie?.overview
        original_language_movie_details.text = "lingua nativa: ${movie?.language}"
        rating_movie.text = "Nota: ${movie?.rate}"
        big_overview.movementMethod = ScrollingMovementMethod()
        selected_movie_tittle.text = movie?.title
        genrer_movie_details.text = movie?.genreNames

    }
}