package com.mirlan.sandbox

import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.mirlan.sandbox.core.BaseFragment
import com.mirlan.sandbox.utils.Constants.CR_APP_HOLDER
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import timber.log.Timber

class SingleActivity : AppCompatActivity(R.layout.activity_main) {

    private val navigatorHolder: NavigatorHolder by inject(named(CR_APP_HOLDER))
    private val navigator = SupportAppNavigator(this, R.id.main_container)

    val currentFragment get() = supportFragmentManager.findFragmentById(R.id.main_container)
    private val viewModel: AppViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.start()
        Timber.e("adfkjasdfk")
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
        lifecycleScope.launch {

        }
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
        Timber.e("onPause")
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        (fragment as? BaseFragment)?.viewModel?.router = viewModel.appRouter
    }

    override fun onRestart() {
        super.onRestart()
        Timber.e("restart")
    }

    override fun onStart() {
        super.onStart()
        Timber.e("ontart")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Timber.e("attach")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Timber.e("${fragmentManager?.isDestroyed}adfasdf")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.e("destroty")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
