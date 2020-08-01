package com.mirlan.sandbox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.mirlan.sandbox.utils.hide
import com.mirlan.sandbox.utils.show
import kotlinx.android.synthetic.main.activity_main.*

class SingleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpNavigation()
        visibilityNavElements(findNavController(R.id.my_nav_host_fragment))
    }

    private fun setUpNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment?
        navHostFragment?.let {
            NavigationUI.setupWithNavController(bottom_navigation_view, it.navController)
        }
    }

    private fun visibilityNavElements(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.screen_detail -> bottom_navigation_view.hide()
                else -> bottom_navigation_view?.show()
            }
        }
    }
}
