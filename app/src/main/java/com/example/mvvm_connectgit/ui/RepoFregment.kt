package com.example.mvvm_connectgit.ui

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_connectgit.databinding.RepoFragmentBinding
import com.example.mvvm_connectgit.viewModel.GithubViewModelFactory
import com.example.mvvm_connectgit.viewModel.RepoViewModel


class RepoFragment : Fragment() {
    private var binding: RepoFragmentBinding? = null
    private val factory = GithubViewModelFactory()
    private var viewModel: RepoViewModel? = null
    private val repoAdapter = RepoAdapter(ArrayList())
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RepoFragmentBinding.inflate(inflater, container, false)
        binding!!.edtQuery.setOnKeyListener(View.OnKeyListener { view, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN
                && keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                doSearch()
                return@OnKeyListener true
            }
            false
        })
        binding!!.btnSearch.setOnClickListener { doSearch() }
        binding!!.recyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        binding!!.recyclerView.adapter = repoAdapter
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(RepoViewModel::class.java)
        binding!!.viewModel = viewModel
        viewModel!!.getRepos().observe(this,
            { repos -> repoAdapter.swapItems(repos) })
    }

    private fun doSearch() {
        val query = binding!!.edtQuery.text.toString()
        if (TextUtils.isEmpty(query)) {
            repoAdapter.clearItems()
            return
        }
        viewModel!!.searchRepo(query)
        dismissKeyboard()
    }

    private fun dismissKeyboard() {
        val view = activity!!.currentFocus
        if (view != null) {
            val imm =
                activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    companion object {
        const val TAG = "Repo"
        fun newInstance(): RepoFragment {
            return RepoFragment()
        }
    }
}