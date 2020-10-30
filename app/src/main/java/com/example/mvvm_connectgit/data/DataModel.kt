package com.example.mvvm_connectgit.data

import com.example.mvvm_connectgit.api.GithubService
import com.example.mvvm_connectgit.data.model.Repo
import com.example.mvvm_connectgit.data.model.RepoSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DataModel {
    private val githubService: GithubService = GithubService.Factory.create()

    fun searchRepo(query: String?, callback: onDataReadyCallback) {
        githubService.searchRepos(query!!)
            .enqueue(object : Callback<RepoSearchResponse> {
                override fun onResponse(call: Call<RepoSearchResponse>, response: Response<RepoSearchResponse>) {
                    callback.onDataReady(response.body()!!.items)
                }

                override fun onFailure(call: Call<RepoSearchResponse>, t: Throwable) {
                    // TODO: error handle
                }
            })
    }


    interface onDataReadyCallback {
        fun onDataReady(data: List<Repo>)
    }
}