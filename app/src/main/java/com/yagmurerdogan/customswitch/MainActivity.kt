package com.yagmurerdogan.customswitch

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.yagmurerdogan.customswitch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        updateUI(sharedPreferences)

        binding.switchButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                editor.putBoolean(SWITCH_BUTTON_KEY, true).apply()
                updateUI(sharedPreferences)
            } else {
                editor.putBoolean(SWITCH_BUTTON_KEY, false).apply()
                updateUI(sharedPreferences)
            }
        }
    }

    private fun updateUI(sharedPreferences: SharedPreferences) {
        binding.switchButton.apply {
            isChecked = sharedPreferences.getBoolean(SWITCH_BUTTON_KEY, false)
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    companion object {
        const val SWITCH_BUTTON_KEY = "switch"
        const val PREF_KEY = "pref"
    }

}