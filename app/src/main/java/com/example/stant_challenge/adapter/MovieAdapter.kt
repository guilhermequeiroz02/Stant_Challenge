package com.example.stant_challenge.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.stant_challenge.R
import com.example.stant_challenge.models.Movie
import kotlinx.android.synthetic.main.movie_item.view.*


class MovieAdapter(
    private val movies: List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class MovieViewHolder(view: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(view) {

        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        fun bindMovie(movie: Movie) {
            itemView.movie_title.text = movie.title
            itemView.release_date.text = movie.release
            itemView.small_overView.text = movie.overview
            itemView.movie_poster.load(IMAGE_BASE + movie.poster) {
                transformations(RoundedCornersTransformation(50F))
            }
            itemView.movie_genrer.text = movie.firstGenreName
        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false),
            mListener
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies[position])
    }

}