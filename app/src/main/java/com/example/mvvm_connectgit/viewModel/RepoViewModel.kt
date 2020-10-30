package com.example.mvvm_connectgit.viewModel

import androidx.arch.core.util.Function
import android.text.TextUtils
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mvvm_connectgit.data.DataModel
import com.example.mvvm_connectgit.data.model.Repo
import com.example.mvvm_connectgit.util.AbsentLiveData


//class RepoViewModel(): ViewModel() {
//    val isLoading = ObservableBoolean(false)
//    val repos = MutableLiveData<List<Repo>>()
//    lateinit var dataModel: DataModel
//
//    constructor(dataModel: DataModel) {
//        this.dataModel = dataModel
//    }
//
//    fun searchRepo(query: String) {
//        isLoading.set(true)
//        dataModel.searchRepo(query, object: DataModel.onDataReadyCallback{
//            override fun onDataReady(data: List<Repo>) {
//                repos.postValue(data)
//                isLoading.set(false)
//            }
//        })
//    }
//}

class RepoViewModel(dataModel: DataModel) : ViewModel() {
    val isLoading = ObservableBoolean(false)
    private var repos: LiveData<List<Repo>>
    private var query = MutableLiveData<String>()

    init {
        repos = Transformations.switchMap(query, object: Function<String, LiveData<List<Repo>>> {
            override fun apply(userInput: String): LiveData<List<Repo>> {
                if (TextUtils.isEmpty(userInput)) {
                    return AbsentLiveData.create()
                } else {
                    return dataModel.searchRepo(userInput)
                }
            }
        })
    }

    fun getRepos(): LiveData<List<Repo>> {
        return repos
    }

    fun searchRepo(userTnput: String?) {
        query.value = userTnput
    }
}