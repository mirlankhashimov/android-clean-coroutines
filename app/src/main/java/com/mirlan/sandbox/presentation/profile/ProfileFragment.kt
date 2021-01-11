package com.mirlan.sandbox.presentation.profile

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.mirlan.sandbox.R
import com.mirlan.sandbox.core.BaseFragment
import com.mirlan.sandbox.databinding.FragmentProfileBinding
import com.mirlan.sandbox.utils.Settings
import com.mirlan.sandbox.utils.viewBinding
import ru.terrakok.cicerone.android.support.SupportAppScreen

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    object ProfileScreen : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return ProfileFragment()
        }
    }

    private val binding by viewBinding(FragmentProfileBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mode = AppCompatDelegate.getDefaultNightMode()
        binding.switchDarkTheme.isChecked = mode == AppCompatDelegate.MODE_NIGHT_YES
        setNightMode(mode)
    }

    private fun setNightMode(mode: Int) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putInt(Settings.NIGHT_MODE, mode).apply()
        AppCompatDelegate.setDefaultNightMode(mode)
        binding.switchDarkTheme.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) setNightMode(
                AppCompatDelegate.MODE_NIGHT_YES
            ) else setNightMode(Settings.MODE_NIGHT_DEFAULT)
        }
    }

    override fun onBackPressed() {
        TODO("Not yet implemented")
    }
}