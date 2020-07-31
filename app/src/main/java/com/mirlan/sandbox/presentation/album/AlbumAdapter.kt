package com.mirlan.sandbox.presentation.album

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mirlan.sandbox.R
import com.mirlan.sandbox.utils.diffItemCallback
import com.mirlan.sandbox.utils.inflate
import com.mirlan.sandbox.domain.entity.Album

class AlbumAdapter(
    private val click: (Album) -> Unit
) : ListAdapter<Album, AlbumAdapter.ViewHolder>(
    diffItemCallback { it.first == it.second }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_album_list))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val name: TextView = itemView.findViewById(R.id.title)
        fun bind(album: Album?) {
            name.text = album?.title
            itemView.setOnClickListener {
                click(getItem(adapterPosition) ?: return@setOnClickListener)
            }
        }
    }
}