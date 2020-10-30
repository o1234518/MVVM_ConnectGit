package com.example.mvvm_connectgit.viewModel

import androidx.arch.core.util.Function
import android.text.TextUtils
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mvvm_connectgit.api.ApiResponse
import com.example.mvvm_connectgit.data.DataModel
import com.example.mvvm_connectgit.data.model.Repo
import com.example.mvvm_connectgit.data.model.RepoSearchResponse
import com.example.mvvm_connectgit.util.AbsentLiveData

class RepoViewModel(dataModel: DataModel) : ViewModel() {
    val isLoading = ObservableBoolean(false)
    private var repos: LiveData<ApiResponse<RepoSearchResponse>>
    private var query = MutableLiveData<String>()

    init {
        repos = Transformations.switchMap(query, object: Function<String, LiveData<ApiResponse<RepoSearchResponse>>> {
            override fun apply(userInput: String): LiveData<ApiResponse<RepoSearchResponse>> {
                if (TextUtils.isEmpty(userInput)) {
                    return AbsentLiveData.create()
                } else {
                    return dataModel.searchRepo(userInput)
                }
            }
        })
    }

    fun getRepos(): LiveData<ApiResponse<RepoSearchResponse>> {
        return repos
    }

    fun searchRepo(userTnput: String?) {
        query.value = userTnput
    }
}