package com.mirlan.sandbox.core.flow

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.activity.addCallback
import com.mirlan.sandbox.R
import com.mirlan.sandbox.core.BaseFragment
import ru.terrakok.cicerone.Navigator
import timber.log.Timber

abstract class FlowFragment(layoutRes: Int) : BaseFragment(layoutRes) {

    val currentFragment get() = childFragmentManager.findFragmentById(R.id.container)

    abstract override val viewModel: FlowViewModel

    protected open val navigator: Navigator by lazy {
        FlowSupportAppNavigator(
            requireActivity(),
            childFragmentManager,
            R.id.container,
            this::onExit
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            viewModel.childRouter.exit()
        }
    }

    override fun setRouter(childFragment: Fragment) {
        (childFragment as? BaseFragment)?.viewModel?.apply {
            router = viewModel.childRouter
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        viewModel.navigatorHolder.removeNavigator()
        Timber.e("${fragmentManager?.isDestroyed }adfasdf")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.e("${fragmentManager?.isDestroyed }adfasdfasdfasd")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.e("${fragmentManager?.isDestroyed }adfasdfadfasdfasdfasdf")
    }

    open fun onExit() {
        viewModel.router.exit()
    }

}