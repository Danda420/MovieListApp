package com.danda.movielistapp.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.danda.movielistapp.databinding.FavoriteMoviesBinding
import com.danda.movielistapp.helper.FavViewModelFactory
import com.danda.movielistapp.offlinedb.MovieModel
import com.danda.movielistapp.ui.detail.MovieViewModel

class FavoriteMovies : AppCompatActivity() {
    private lateinit var binding: FavoriteMoviesBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FavoriteMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvFavorites.layoutManager = LinearLayoutManager(this)
        binding.rvFavorites.setHasFixedSize(true)

        val mainViewModel = obtainViewModel(this@FavoriteMovies)
        mainViewModel.getAllMember().observe(this) {movieList ->
            if (movieList != null) {
                showData(movieList)
            }
        }
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
    }
    private fun obtainViewModel(activity: AppCompatActivity): FavViewModel {
        val factory = FavViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(FavViewModel::class.java)
    }

    private fun showData(data: List<MovieModel>) {
        binding.rvFavorites.adapter = FavMoviesAdapter().apply {
            setOnItemClickCallback(object : FavMoviesAdapter.OnItemClickCallback {
                override fun onItemClicked(data: MovieModel) {
                    val intent = Intent(this@FavoriteMovies, DetailFavorite::class.java)
                    intent.putExtra("extra_member", data)
                    startActivity(intent)
                }
            })
            differ.submitList(data.toMutableList())
        }
    }
}
