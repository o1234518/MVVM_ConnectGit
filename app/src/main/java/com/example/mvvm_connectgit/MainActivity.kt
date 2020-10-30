package com.example.mvvm_connectgit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvm_connectgit.ui.RepoFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tag: String = RepoFragment.TAG
        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            val fragment: RepoFragment = RepoFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .add(R.id.container, fragment, tag)
                .commit()
        }
    }
}