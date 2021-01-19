package com.mirlan.sandbox.presentation.bottom_sheet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mirlan.sandbox.R
import com.mirlan.sandbox.core.BaseFragment
import com.mirlan.sandbox.core.BaseViewModel
import com.mirlan.sandbox.databinding.FragmentFullScreenBinding
import com.mirlan.sandbox.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.terrakok.cicerone.android.support.SupportAppScreen

class FullScreenFragment : BaseFragment(R.layout.fragment_full_screen) {

    object FullDialogScreen : SupportAppScreen() {
        override fun getFragment() = FullScreenFragment()
    }

    override val viewModel: FullScreenDialogViewModel by viewModel()

    private val binding by viewBinding(FragmentFullScreenBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener { viewModel.back() }
        binding.recycler.apply {
                layoutManager = LinearLayoutManager(requireContext())
                addItemDecoration(
                    DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
                )
            }
    }
}
