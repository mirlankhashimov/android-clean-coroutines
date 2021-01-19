package com.mirlan.sandbox.presentation.search

import com.mirlan.sandbox.R
import com.mirlan.sandbox.core.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.terrakok.cicerone.android.support.SupportAppScreen

class SearchFragment : BaseFragment(R.layout.fragment_search) {
    class SearchScreen : SupportAppScreen() {
        override fun getFragment() = SearchFragment()
    }
    override val viewModel: SearchViewModel by viewModel()
}