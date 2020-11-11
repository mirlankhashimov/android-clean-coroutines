package com.mirlan.sandbox.utils

import FragmentViewBindingDelegate
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * @sample private val binding by viewBinding(FragmentBinding::bind)
 * */
fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)