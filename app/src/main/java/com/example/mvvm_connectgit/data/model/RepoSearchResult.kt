package com.example.mvvm_connectgit.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mvvm_connectgit.data.db.GithubTypeConverters


@Entity
@TypeConverters(GithubTypeConverters::class)
data class RepoSearchResult(
    @PrimaryKey val resQuery: String,
    val repoIds: List<Int>,
    val totalCount: Int
)