package com.example.mvvm_connectgit.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvm_connectgit.data.model.Repo
import com.example.mvvm_connectgit.data.model.RepoSearchResult


@Dao
abstract class RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertRepos(repositories: List<Repo>)

    @Query("SELECT * FROM Repo WHERE id in (:repoIds)")
    protected abstract fun loadById(repoIds: List<Int>): List<Repo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(result: RepoSearchResult)

    @Query("SELECT * FROM RepoSearchResult WHERE resQuery = :query")
    abstract  fun search(query: String): RepoSearchResult
}