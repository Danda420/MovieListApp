package com.danda.movielistapp.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.danda.movielistapp.offlinedb.MovieModel
import com.danda.movielistapp.offlinedb.repository.MovieRepo

class FavViewModel(application: Application) : ViewModel() {
    private val mMasjidRepository: MovieRepo = MovieRepo(application)


    fun getAllMember(): LiveData<List<MovieModel>> = mMasjidRepository.getAllMovie()
}