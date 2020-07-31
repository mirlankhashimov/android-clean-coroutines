package com.mirlan.sandbox.presentation.photos

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mirlan.sandbox.R
import com.mirlan.sandbox.domain.entity.Photo
import com.mirlan.sandbox.utils.diffItemCallback
import com.mirlan.sandbox.utils.inflate
import com.squareup.picasso.Picasso

class PhotosAdapter(
    private val click: (Photo) -> Unit
) : ListAdapter<Photo, PhotosAdapter.ViewHolder>(
        diffItemCallback { it.first == it.second }
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_photo_list))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val image: ImageView = itemView.findViewById(R.id.image)
        fun bind(photo: Photo?) {
            Picasso.get().load(photo?.thumbnailUrl).into(image)
            itemView.setOnClickListener {
                click(getItem(adapterPosition) ?: return@setOnClickListener)
            }
        }
    }
}