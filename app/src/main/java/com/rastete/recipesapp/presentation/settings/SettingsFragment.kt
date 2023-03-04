package com.rastete.recipesapp.presentation.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.rastete.recipesapp.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}