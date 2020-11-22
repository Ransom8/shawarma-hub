package com.example.shawarmahub.ui.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shawarmahub.repository.Repository
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return  MainViewModel(repository) as T
        }
        throw IllegalArgumentException("unknown view model class")
    }
}