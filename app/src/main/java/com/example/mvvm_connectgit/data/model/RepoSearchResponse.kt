package com.example.mvvm_connectgit.data.model

import com.google.gson.annotations.SerializedName

class RepoSearchResponse (@SerializedName("total_count") var total: Int,
                                @SerializedName("items") var items: List<Repo>)