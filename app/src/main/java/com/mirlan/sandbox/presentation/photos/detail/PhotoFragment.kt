package com.mirlan.sandbox.presentation.photos.detail

import android.os.Bundle
import android.view.View
import com.mirlan.sandbox.R
import com.mirlan.sandbox.core.BaseFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*


class PhotoFragment: BaseFragment(R.layout.fragment_detail) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get().load(arguments?.getString("imageId")).into(image)
    }
}