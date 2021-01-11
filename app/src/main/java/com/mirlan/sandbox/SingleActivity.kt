package com.mirlan.sandbox

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.mirlan.sandbox.core.BaseFragment
import com.mirlan.sandbox.utils.Constants.CR_APP_HOLDER
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class SingleActivity : AppCompatActivity(R.layout.activity_main) {

    private val navigatorHolder: NavigatorHolder by inject(named(CR_APP_HOLDER))
    private val navigator = SupportAppNavigator(this, R.id.main_container)

    val currentFragment get() = supportFragmentManager.findFragmentById(R.id.main_container)
    private val viewModel: AppViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.start()
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (currentFragment is BaseFragment) {
            (currentFragment as? BaseFragment)?.onBackPressed()
        } else {
            throw Exception("${currentFragment!!::class.java.canonicalName} is not child of BaseFragment")
        }
    }
}
