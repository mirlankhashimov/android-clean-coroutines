package com.mirlan.sandbox.presentation.home

import android.os.Build
import android.text.Html
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.mirlan.sandbox.R
import com.mirlan.sandbox.domain.entity.RecommendedFirm
import com.mirlan.sandbox.utils.Constants.IMG_BASE_URL
import com.mirlan.sandbox.utils.diffItemCallback
import com.mirlan.sandbox.utils.inflate
import kotlinx.android.synthetic.main.item_recommendation_list.view.*
import org.w3c.dom.Text

class RecommendationAdapter(
    private val click: (RecommendedFirm) -> Unit
) : ListAdapter<RecommendedFirm, RecommendationAdapter.ViewHolder>(
    diffItemCallback { it.first == it.second }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_recommendation_list))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bind(recommendedFirm: RecommendedFirm?) {
            with(itemView) {
                name.text = recommendedFirm?.name
                type.text = recommendedFirm?.type
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    address.text =
                        Html.fromHtml(recommendedFirm?.address, Html.FROM_HTML_MODE_COMPACT);
                } else {
                    address.text = Html.fromHtml(recommendedFirm?.address);
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