package com.mirlan.sandbox.presentation.home.detail

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.mirlan.sandbox.R
import com.mirlan.sandbox.domain.entity.Master
import com.mirlan.sandbox.utils.Constants.IMG_BASE_URL
import com.mirlan.sandbox.utils.diffItemCallback
import com.mirlan.sandbox.utils.inflate
import kotlinx.android.synthetic.main.item_master_list.view.*

class MasterAdapter() : ListAdapter<Master, MasterAdapter.ViewHolder>(
    diffItemCallback { it.first == it.second }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_master_list))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bind(master: Master?) {
            with(itemView) {
                name.text = master?.name
                type.text = master?.profession
                rating.text = master?.rating?.toString()
                imageView.load(IMG_BASE_URL + master?.avatarUrl)
            }
        }
    }
}