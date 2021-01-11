package com.mirlan.sandbox.presentation.search

import androidx.fragment.app.Fragment
import com.mirlan.sandbox.R
import com.mirlan.sandbox.core.BaseFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class SearchFragment : BaseFragment(R.layout.fragment_search) {
    object SearchScreen : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return SearchFragment()
        }
    }

    override fun onBackPressed() {
        TODO("Not yet implemented")
    }
}