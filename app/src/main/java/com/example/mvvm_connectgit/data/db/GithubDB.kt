package com.example.mvvm_connectgit.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm_connectgit.data.model.Repo
import com.example.mvvm_connectgit.data.model.RepoSearchResult

@Database(entities = [
    RepoSearchResult::class,
    Repo::class],
    version = 1)
abstract class GithubDB: RoomDatabase() {
    abstract fun repoDao(): RepoDao

    companion object {
        private var INSTANCE: GithubDB? = null

        fun getInstance(context: Context): GithubDB? {
            if (INSTANCE == null) {
                synchronized(GithubDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        GithubDB::class.java,
                        "github.db"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destoryInstance() {
            INSTANCE = null
        }
    }
}