package com.mirlan.sandbox.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mirlan.sandbox.R
import com.mirlan.sandbox.core.flow.FlowFragment
import com.mirlan.sandbox.core.navigation.Screens
import com.mirlan.sandbox.utils.*
import org.koin.android.ext.android.get
import ru.terrakok.cicerone.android.support.SupportAppScreen
import timber.log.Timber

abstract class BaseFragment(val layoutRes: Int) : BottomSheetDialogFragment() {

    abstract val viewModel: BaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutRes, container, false)

    fun initToolbar(title: String?, hideBack: Boolean = false) {
        if (title == null) return
        (activity as? AppCompatActivity)?.apply {
            val toolbar = view?.rootView.findViewByIdLast<Toolbar>(R.id.toolbar)
            setSupportActionBar(toolbar)
            supportActionBar?.apply {
                setTitle(title)
                setDisplayHomeAsUpEnabled(!hideBack)
            }
        }
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        setRouter(childFragment)
    }

    open fun setRouter(childFragment: Fragment) {
        (childFragment as? BaseFragment)?.viewModel?.router = viewModel.router
    }

    fun <T> LiveData<T>.observe(on: (T) -> Unit) = observe(viewLifecycleOwner, Observer(on))

    fun showDialog() {
        val submitFlowFragment =
            (get<Screens>().bottomDialog() as? SupportAppScreen)?.fragment as? BaseFragment
        submitFlowFragment?.sharedElementEnterTransition = BottomSheetSharedTransition()
        submitFlowFragment?.show(childFragmentManager, submitFlowFragment::javaClass.name)
    }
}