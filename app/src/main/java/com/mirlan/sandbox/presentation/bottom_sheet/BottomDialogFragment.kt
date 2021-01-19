package com.mirlan.sandbox.presentation.bottom_sheet

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.mirlan.sandbox.R
import com.mirlan.sandbox.core.BaseFragment
import com.mirlan.sandbox.databinding.LayoutBottomSheetBinding
import com.mirlan.sandbox.utils.setSafeOnClickListener
import com.mirlan.sandbox.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.terrakok.cicerone.android.support.SupportAppScreen

class BottomDialogFragment : BaseFragment(R.layout.layout_bottom_sheet) {
    object BottomDialogScreen : SupportAppScreen() {
        override fun getFragment() = BottomDialogFragment()
    }

    private val binging by viewBinding(LayoutBottomSheetBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binging.buttonShowFullScreenFragment.setSafeOnClickListener { viewModel.nav() }
    }

    override val viewModel: BottomDialogViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }
}