package com.mirlan.sandbox.presentation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.mirlan.sandbox.R
import com.mirlan.sandbox.core.AbstractDialogFragment
import com.mirlan.sandbox.core.DialogInterface
import com.mirlan.sandbox.databinding.DialogConfirmationBinding
import com.mirlan.sandbox.utils.setSafeOnClickListener
import com.mirlan.sandbox.utils.viewBinding
import ru.terrakok.cicerone.android.support.SupportAppScreen
import timber.log.Timber

class DialogConfirmation : AbstractDialogFragment(R.layout.dialog_confirmation) {

    class DialogScreen(
        private val title: String,
        private val confirmTitle: String,
        private val cancelTitle: String
    ) : SupportAppScreen() {
        override fun getFragment() =
            DialogConfirmation().apply {
                arguments = bundleOf(
                    TITLE to title,
                    CONFIRM to confirmTitle,
                    CANCEL to cancelTitle
                )
            }
    }

    var callBack: (() -> Unit)? = null

    override val binding by viewBinding(DialogConfirmationBinding::bind)

    override val dimAmount: Float?
        get() = 0.6f
    override val isCancellable: Boolean
        get() = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.expl.text = arguments?.getString(TITLE)
        binding.confirm.text = arguments?.getString(CONFIRM)
        binding.cancel.text = arguments?.getString(CANCEL)
        binding.cancel.setSafeOnClickListener {
            callBack?.invoke()
            dismiss()
        }
    }

    companion object {
        const val TITLE = "title"
        const val CONFIRM = "confirm_title"
        const val CANCEL = "cancel_title"
    }

}