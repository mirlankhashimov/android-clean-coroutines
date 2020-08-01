package com.mirlan.sandbox.presentation.home.detail

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mirlan.sandbox.R
import com.mirlan.sandbox.domain.entity.Service
import com.mirlan.sandbox.utils.diffItemCallback
import com.mirlan.sandbox.utils.inflate
import kotlinx.android.synthetic.main.item_service_list.view.*

class ServiceAdapter() : ListAdapter<Service, ServiceAdapter.ViewHolder>(
    diffItemCallback { it.first == it.second }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_service_list))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        @SuppressLint("SetTextI18n")
        fun bind(service: Service?) {
            with(itemView) {
                name.text = service?.name
                price.text = "${service?.duration} min = ${service?.priceStr}"
            }
        }
    }
}