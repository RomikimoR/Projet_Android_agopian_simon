package com.example.projet_android_agopian_simon

import android.os.Bundle
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.projet_android_agopian_simon.fragment.HomeFragment
import com.example.projet_android_agopian_simon.fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var spinner: Spinner
    lateinit var locale: Locale
    private var currentLanguage = "en"
    private var currentLang: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val res = resources
        val conf = res.configuration
        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val fragment_home  = HomeFragment()
        val fragment_profile  = ProfileFragment()

        showFragment(R.id.container, fragment_home)
        navView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.tab_home->showFragment(R.id.container, fragment_home)
                R.id.tab_profile->showFragment(R.id.container, fragment_profile)

            }
            true
        }
    }

    fun AppCompatActivity.showFragment(id: Int, frg: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(id, frg)
            .commit()
    }
}