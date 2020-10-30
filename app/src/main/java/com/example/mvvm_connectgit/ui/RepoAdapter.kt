package com.example.mvvm_connectgit.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_connectgit.data.model.Repo
import com.example.mvvm_connectgit.databinding.RepoItemBinding
import com.bumptech.glide.Glide


internal class RepoAdapter internal constructor(private val items: MutableList<Repo>) :
    RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {
    internal inner class RepoViewHolder(internal val binding: RepoItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RepoItemBinding.inflate(layoutInflater, parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val (_, _, fullName, description, stars, owner) = items[position]
        Glide.with(holder.itemView.context)
            .load(owner.avatarUrl)
            .into(holder.binding.ownerAvatar)
        holder.binding.name.text = fullName
        holder.binding.desc.text = description
        holder.binding.stars.text = "" + stars
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun clearItems() {
        val size = items.size
        items.clear()
        notifyItemRangeRemoved(0, size)
    }

    fun swapItems(newItems: List<Repo>?) {
        val result = DiffUtil.calculateDiff(RepoDiffCallback(items, newItems))
        items.clear()
        items.addAll(newItems!!)
        result.dispatchUpdatesTo(this)
    }

    private inner class RepoDiffCallback internal constructor(
        private val mOldList: List<Repo>?,
        private val mNewList: List<Repo>?
    ) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return mOldList?.size ?: 0
        }

        override fun getNewListSize(): Int {
            return mNewList?.size ?: 0
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldId = mOldList!![oldItemPosition].id
            val newId = mNewList!![newItemPosition].id
            return oldId == newId
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldRepo = mOldList!![oldItemPosition]
            val newRepo = mNewList!![newItemPosition]
            return oldRepo == newRepo
        }
    }
}