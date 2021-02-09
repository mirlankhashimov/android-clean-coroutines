package com.mirlan.sandbox.presentation.search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.mirlan.sandbox.R
import com.mirlan.sandbox.core.BaseFragment
import com.mirlan.sandbox.utils.launch
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.terrakok.cicerone.android.support.SupportAppScreen

class SearchFragment : BaseFragment(R.layout.fragment_search) {
    class SearchScreen : SupportAppScreen() {
        override fun getFragment() = SearchFragment()
    }

    override val viewModel: SearchViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {

        }
    }
}