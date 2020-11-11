package com.mirlan.sandbox.presentation.home

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mirlan.sandbox.databinding.ItemRecommendationListBinding
import com.mirlan.sandbox.domain.entity.RecommendedFirm
import com.mirlan.sandbox.utils.Constants.IMG_BASE_URL
import com.mirlan.sandbox.utils.diffItemCallback


class RecommendationAdapter(
    private val click: (RecommendedFirm) -> Unit
) : ListAdapter<RecommendedFirm, RecommendationAdapter.ViewHolder>(
    diffItemCallback { it.first == it.second }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemRecommendationListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(private val binding: ItemRecommendationListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recommendedFirm: RecommendedFirm?) {
            with(binding) {
                name.text = recommendedFirm?.name
                type.text = recommendedFirm?.type
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.address.text =
                        Html.fromHtml(recommendedFirm?.address, Html.FROM_HTML_MODE_COMPACT);
                } else {
                    binding.address.text = Html.fromHtml(recommendedFirm?.address);
                }
                workSchedule.text = recommendedFirm?.workSchedule
                rating.text = recommendedFirm?.averageRating?.toString()
                imageView.load(IMG_BASE_URL + recommendedFirm?.avatarUrl)
            }
            itemView.setOnClickListener {
                click(getItem(adapterPosition) ?: return@setOnClickListener)
            }
        }
    }
}