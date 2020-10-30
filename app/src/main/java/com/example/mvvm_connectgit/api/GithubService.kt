package com.example.mvvm_connectgit.api

import com.example.mvvm_connectgit.data.model.RepoSearchResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET("search/repositories")
    fun searchRepos(@Query("q") query: String): Call<RepoSearchResponse>

    object Factory {
        fun create(client: OkHttpClient? = null): GithubService {
            val web_api_url = "https://api.github.com/"
            var retrofit: Retrofit? = null
            retrofit = Retrofit.Builder()
                .baseUrl(web_api_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit!!.create<GithubService>(GithubService::class.java)
        }
    }
}