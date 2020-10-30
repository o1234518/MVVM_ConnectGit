package com.example.mvvm_connectgit.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_connectgit.api.ApiResponse
import com.example.mvvm_connectgit.api.GithubService
import com.example.mvvm_connectgit.data.model.Repo
import com.example.mvvm_connectgit.data.model.RepoSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DataModel {
    private val githubService: GithubService = GithubService.Factory.create()

    fun searchRepo(query: String?): LiveData<ApiResponse<RepoSearchResponse>> {
        return githubService.searchRepos(query!!)
    }


    interface onDataReadyCallback {
        fun onDataReady(data: List<Repo>)
    }
}