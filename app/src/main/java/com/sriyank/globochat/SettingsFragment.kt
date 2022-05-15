package com.sriyank.globochat

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        val accSettingsPref = findPreference<Preference>(getString(R.string.key_account_settings))
        accSettingsPref?.setOnPreferenceClickListener {

            val navHostFragment =
                activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_frag) as NavHostFragment
            val navController = navHostFragment.navController
            val action = SettingsFragmentDirections.actionSettingsToAccSettings()
            navController.navigate(action)


            true
        }
        //read preference values in a fragment
        //step 1:Get reference to the SharedPreferences (XML File)
        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(context)
        //step 2:Get the 'value' using the 'key'
        val autoReplyTime = sharedPreference.getString(getString(R.string.key_auto_reply_time), "")
        Log.i("SettingsFragment", "Auto Reply Time : $autoReplyTime")

        val autoDownload = sharedPreference.getBoolean(getString(R.string.key_auto_download), false)
        Log.i("SettingsFragment", "Auto Reply Time : $autoDownload")

        val statusPref = findPreference<EditTextPreference>(getString(R.string.key_status))
        statusPref?.setOnPreferenceChangeListener { preference, newValue ->
            Log.i("SettingsFragment", "New Status : $newValue")
            val newStatus = newValue as String
            if(newStatus.contains("bad")){
                Toast.makeText(context,"inappropriate status. please maintain community guidelines.",Toast.LENGTH_SHORT).show()
                false
            }else{
                true
            }
             //true accept new values // false reject the new value.
        }

    }


}