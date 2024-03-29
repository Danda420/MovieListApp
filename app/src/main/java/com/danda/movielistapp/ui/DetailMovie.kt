package com.danda.movielistapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.danda.movielistapp.R
import com.danda.movielistapp.databinding.DetailMovieBinding
import com.danda.movielistapp.model.Movie

class DetailMovie : AppCompatActivity() {
    private var _binding: DetailMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getParcelableExtra<Movie>("movie")
        movie?.let { bind(it) }
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

        binding.backButton.setOnClickListener {
            onBackPressed()
        }
    }
}
