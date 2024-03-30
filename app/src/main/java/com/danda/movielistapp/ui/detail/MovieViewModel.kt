package com.danda.movielistapp.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.danda.movielistapp.offlinedb.MovieModel
import com.danda.movielistapp.offlinedb.repository.MovieRepo

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MovieRepo = MovieRepo(application)
    val allMovies: LiveData<List<MovieModel>>

    init {
        allMovies = repository.getAllMovie()
    }

    fun insert(movie: MovieModel) {
        repository.insert(movie)
    }

    fun delete(movie: MovieModel) {
        repository.delete(movie)
    }

    fun getMovieByTitle(title: String): LiveData<MovieModel?> {
        return repository.getMovieByTitle(title)
    }
}
