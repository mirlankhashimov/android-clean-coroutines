package com.mirlan.sandbox.presentation.home.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mirlan.sandbox.databinding.ItemServiceListBinding
import com.mirlan.sandbox.domain.entity.Service
import com.mirlan.sandbox.utils.diffItemCallback

class ServiceAdapter() : ListAdapter<Service, ServiceAdapter.ViewHolder>(
    diffItemCallback { it.first == it.second }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemServiceListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(private val binding: ItemServiceListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(service: Service?) {
            with(binding) {
                name.text = service?.name
                price.text = "${service?.duration} min = ${service?.priceStr}"
            }
        }
    }
}