package com.mirlan.sandbox.presentation.recycler

import android.os.Bundle
import android.view.View
import com.mirlan.sandbox.R
import com.mirlan.sandbox.core.BaseFragment
import com.mirlan.sandbox.databinding.LayoutRecyclerBinding
import com.mirlan.sandbox.utils.EdgeToEdge
import com.mirlan.sandbox.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by crazy on 11/2/20 to long live and prosper !
 */

class ListFragment : BaseFragment(R.layout.layout_recycler), EdgeToEdgeScrolling {

    private val binding by viewBinding(LayoutRecyclerBinding::bind)
    override val viewModel: ListViewModel by viewModel()

    override fun edgeToEdgeScrollingContent() {
        EdgeToEdge.setUpScrollingContent(binding.recycler)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.recycler.adapter = crashesAdapter
    }
}