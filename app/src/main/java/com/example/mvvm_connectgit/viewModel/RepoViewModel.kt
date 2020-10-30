package com.example.mvvm_connectgit.viewModel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_connectgit.data.DataModel
import com.example.mvvm_connectgit.data.DataModel.onDataReadyCallback
import com.example.mvvm_connectgit.data.model.Repo


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

class RepoViewModel(private val dataModel: DataModel) : ViewModel() {
    val isLoading = ObservableBoolean(false)
    private val repos = MutableLiveData<List<Repo>>()
    fun getRepos(): LiveData<List<Repo>> {
        return repos
    }

    fun searchRepo(query: String?) {
        isLoading.set(true)
        dataModel.searchRepo(query, object : onDataReadyCallback {
            override fun onDataReady(data: List<Repo>) {
                repos.value = data
                isLoading.set(false)
            }
        })
    }
}