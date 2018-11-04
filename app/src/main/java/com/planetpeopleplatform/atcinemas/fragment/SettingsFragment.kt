package com.planetpeopleplatform.atcinemas.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.preference.ListPreference
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import com.planetpeopleplatform.atcinemas.R
import com.planetpeopleplatform.atcinemas.activity.PrivacyPolicyActivity
import com.planetpeopleplatform.atcinemas.activity.TermsAndConditionsActivity

class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreatePreferences(bundle: Bundle?, s: String?) {

        addPreferencesFromResource(R.xml.pref_general)

        val prefScreen = preferenceScreen
        val sharedPreferences = prefScreen.sharedPreferences
        val count = prefScreen.preferenceCount

        // Go through all of the preferences, and set up their preference summary.
        for (i in 0 until count) {
            val preference = prefScreen.getPreference(i)

            if (preference is ListPreference) {
                val value = sharedPreferences.getString(preference.getKey(), "")
                setPreferenceSummary(preference, value)
            }
        }

    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        // Figure out which preference was changed
        val preference = findPreference(key)
        if (null != preference) {
            // Updates the summary for the preference
            if (preference is ListPreference) {
                val value = sharedPreferences.getString(preference.key, "")
                setPreferenceSummary(preference, value)
            }
        }
    }

    /**
     * Updates the summary for the preference
     *
     * @param preference The preference to be updated
     * @param value      The value that the preference was updated to
     */
    private fun setPreferenceSummary(preference: Preference, value: String) {
        if (preference is ListPreference) {
            // For list preferences, figure out the label of the selected value
            val prefIndex = preference.findIndexOfValue(value)
            if (prefIndex >= 0) {
                // Set the summary to that label
                preference.summary = preference.entries[prefIndex]
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        preferenceScreen.sharedPreferences
                .unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        preferenceScreen.sharedPreferences
                .registerOnSharedPreferenceChangeListener(this)

        findPreference(getString(R.string.privacy_policy_key)).onPreferenceClickListener = Preference.OnPreferenceClickListener {
            val privacyPolicyIntent = Intent(context, PrivacyPolicyActivity::class.java)
            activity!!.startActivity(privacyPolicyIntent)
            true
        }

        findPreference(getString(R.string.terms_and_conditions_key)).onPreferenceClickListener = Preference.OnPreferenceClickListener {
            val termsAndConditionsIntent = Intent(context, TermsAndConditionsActivity::class.java)
            activity!!.startActivity(termsAndConditionsIntent)
            true
        }
    }
}