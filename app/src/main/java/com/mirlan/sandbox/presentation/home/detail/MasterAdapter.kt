package com.mirlan.sandbox.presentation.home.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mirlan.sandbox.databinding.ItemMasterListBinding
import com.mirlan.sandbox.domain.entity.Master
import com.mirlan.sandbox.utils.Constants.IMG_BASE_URL
import com.mirlan.sandbox.utils.diffItemCallback
import com.mirlan.sandbox.utils.inflate

class MasterAdapter : ListAdapter<Master, MasterAdapter.ViewHolder>(
    diffItemCallback { it.first == it.second }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMasterListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(private val binding: ItemMasterListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(master: Master?) {
            with(binding) {
                name.text = master?.name
                type.text = master?.profession
                rating.text = master?.rating?.toString()
                imageView.load(IMG_BASE_URL + master?.avatarUrl)
            }
        }
    }
}