package com.example.mvvm_connectgit.data.model

import com.google.gson.annotations.SerializedName


data class Owner (@SerializedName("login") val login: String,
                  @SerializedName("avatar_url") val avatarUrl: String,
                  @SerializedName("url") val url: String)