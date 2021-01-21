package com.mirlan.sandbox.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.mirlan.sandbox.R

abstract class AbstractDialogFragment(private val layoutId: Int) : DialogFragment() {

    abstract val binding: ViewBinding
    abstract val dimAmount: Float?
    abstract val isCancellable: Boolean

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = View.inflate(context, layoutId, container)

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        with(super.onCreateDialog(savedInstanceState)) {
            setCancelable(isCancelable)
            window?.setDimAmount(dimAmount ?: 0f)
            setCanceledOnTouchOutside(isCancellable)
            window?.setBackgroundDrawableResource(R.drawable.rounded_dialog_bg)
            this
        }
}