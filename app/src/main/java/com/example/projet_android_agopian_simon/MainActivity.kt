package com.example.projet_android_agopian_simon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projet_android_agopian_simon.fragment.HomeFragment
import com.example.projet_android_agopian_simon.fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment_home  = HomeFragment()
        val fragment_profile  = ProfileFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()


        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.tab_home -> {
                    // Respond to navigation item 1 click
                    print("in tab home")
                    fragmentTransaction.replace(R.id.home_fragment, fragment_home)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                    print("commit home")
                    true

                }
                R.id.tab_profile -> {
                    print("in tab profile")
                    fragmentTransaction.replace(R.id.profile_fragment, fragment_profile)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                    print("commit profile")
                    true
                    // Respond to navigation item 2 click

                }
                else -> false
            }
        }
    }
}