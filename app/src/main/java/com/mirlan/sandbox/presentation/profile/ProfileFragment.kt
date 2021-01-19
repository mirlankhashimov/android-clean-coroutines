package com.mirlan.sandbox.presentation.profile

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import com.mirlan.sandbox.R
import com.mirlan.sandbox.core.BaseFragment
import com.mirlan.sandbox.databinding.FragmentProfileBinding
import com.mirlan.sandbox.utils.Settings
import com.mirlan.sandbox.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.terrakok.cicerone.android.support.SupportAppScreen
import timber.log.Timber

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    class ProfileScreen : SupportAppScreen() {
        override fun getFragment() = ProfileFragment()
    }

    override val viewModel: ProfileViewModel by viewModel()
    private val binding by viewBinding(FragmentProfileBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mode = AppCompatDelegate.getDefaultNightMode()
        binding.switchDarkTheme.isChecked = mode == AppCompatDelegate.MODE_NIGHT_YES
        setNightMode(mode)
        binding.switchDarkTheme.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                setNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                setNightMode(Settings.MODE_NIGHT_DEFAULT)
            }
        }
    }

    private fun setNightMode(mode: Int) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit { putInt(Settings.NIGHT_MODE, mode) }
        Timber.e("${fragmentManager?.isDestroyed }ewrer")
        AppCompatDelegate.setDefaultNightMode(mode)
        Timber.e("${fragmentManager?.isDestroyed }adfasdf")
    }
}