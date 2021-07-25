package com.avenger.saveoassignment.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.avenger.saveoassignment.repository.SampleRepository

class SampleListViewModelFactory(val repository: SampleRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SampleListViewModel(repository) as T
    }
}