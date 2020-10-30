package com.example.mvvm_connectgit.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_connectgit.data.DataModel
import org.jetbrains.annotations.NotNull

class GithubViewModelFactory: ViewModelProvider.Factory {
    private var dataModel: DataModel

    init {
        dataModel = DataModel()
    }

    @SuppressWarnings("unchecked")
    @NotNull
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepoViewModel::class.java)) {
            return RepoViewModel(dataModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class");
    }
}