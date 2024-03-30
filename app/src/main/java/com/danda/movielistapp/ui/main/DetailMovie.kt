package com.danda.movielistapp.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.danda.movielistapp.databinding.DetailMovieBinding
import com.danda.movielistapp.model.Movie
import com.danda.movielistapp.offlinedb.MovieModel
import com.danda.movielistapp.offlinedb.repository.MovieRepo

class DetailMovie : AppCompatActivity() {
    private var _binding: DetailMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieRepo: MovieRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val movie = intent.getParcelableExtra<Movie>("movie")
        movieRepo = MovieRepo(application)

        movie?.let { bind(it) }

        binding.fabFav.setOnClickListener {
            movie?.let { addToFavorites(it) }
        }

        binding.backButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun bind(movie: Movie) {
        Glide.with(binding.root)
            .load(movie.poster)
            .into(binding.imageMoviePoster)

        binding.tvNamaMovie.text = movie.judul
        binding.tvGenreMovie.text = movie.genre
        binding.tvRatingMovie.text = movie.rating.toString()
        binding.tvTahunRelease.text = movie.tahunRilis.toString()
        binding.tvDescMovie.text = movie.desc
        binding.tvDirectorMovie.text = movie.director
    }

    private fun addToFavorites(movie: Movie) {
        val movieModel = MovieModel(
            judul = movie.judul,
            genre = movie.genre,
            rating = movie.rating,
            poster = movie.poster,
            desc = movie.desc,
            director = movie.director,
            tahunRilis = movie.tahunRilis
        )

        movieRepo.getAllMovie().observe(this, { movieList ->
            val isMovieInFavorites = movieList.any { it.judul == movie.judul }
            if (!isMovieInFavorites) {
                movieRepo.insert(movieModel)
                Toast.makeText(this, "${movie.judul} added to favorites", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "${movie.judul} has already been added to favorites", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
