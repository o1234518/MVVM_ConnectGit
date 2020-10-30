package com.example.mvvm_connectgit.util

import androidx.lifecycle.LiveData


/**
 * A LiveData class that has `null` value.
 */
class AbsentLiveData<T> private constructor() : LiveData<T>() {
    init {
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {
            return AbsentLiveData()
        }
    }
}