package com.mirlan.sandbox.presentation.profile

import com.mirlan.sandbox.R
import com.mirlan.sandbox.core.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {
  //  private val adapter by lazy { PhotosAdapter(this::onSelect) }

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


//    }
//
//    private fun onSelect(photo: Photo) {
//        val bundle = bundleOf("imageId" to photo.url)
//        navigate(R.id.action_photo_screen_to_detail_screen, bundle)
//    }
//
//    companion object {
//        const val SPAN_COUNT = 3
//    }
}