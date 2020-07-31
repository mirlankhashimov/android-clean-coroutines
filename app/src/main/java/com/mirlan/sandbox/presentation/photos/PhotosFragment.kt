package com.mirlan.sandbox.presentation.photos

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import com.mirlan.sandbox.R
import com.mirlan.sandbox.core.BaseFragment
import com.mirlan.sandbox.domain.entity.Photo
import com.mirlan.sandbox.utils.GridItemDecoration
import kotlinx.android.synthetic.main.fragment_photo.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotosFragment : BaseFragment(R.layout.fragment_photo) {
    private val viewModel: PhotosViewModel by viewModel()
    private val adapter by lazy { PhotosAdapter(this::onSelect) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photos_rv.layoutManager = GridLayoutManager(context, SPAN_COUNT)
        val gridItemDecoration =
            GridItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_grid))
        photos_rv.addItemDecoration(gridItemDecoration)
        photos_rv.adapter = adapter
        //viewModel.photos(arguments?.getInt("albumId") ?: 0).observe(viewLifecycleOwner) {
//                result ->
//            when (result.status) {
//                Result.Status.SUCCESS -> {
//                    progressBar.visibility = View.GONE
//                    result.data?.let { adapter.submitList(it) }
//                }
//                Result.Status.LOADING -> {
//                    progressBar.visibility = View.VISIBLE
//                }
//                Result.Status.ERROR -> {
//                    progressBar.visibility = View.GONE
//                    Toast.makeText(context, result.message, Toast.LENGTH_LONG).show()
//                }


    }

    private fun onSelect(photo: Photo) {
        val bundle = bundleOf("imageId" to photo.url)
        navigate(R.id.action_photo_screen_to_detail_screen, bundle)
    }

    companion object {
        const val SPAN_COUNT = 3
    }
}