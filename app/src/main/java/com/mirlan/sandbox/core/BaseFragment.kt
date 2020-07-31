package com.mirlan.sandbox.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.navigation.Navigation
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseFragment(@LayoutRes val layoutRes: Int) : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(layoutRes, container, false)
    }

    open fun navigate(action: Int, bundle: Bundle) {
        view?.let { _view ->
            Navigation.findNavController(_view).navigate(action, bundle)
        }
    }
}