package com.mirlan.sandbox.utils

import FragmentViewBindingDelegate
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar

/**
 * @sample private val binding by viewBinding(FragmentBinding::bind)
 * */
fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)

fun Fragment.showSnackMessage(message: String) {
    view?.showSnackMessage(message)
}