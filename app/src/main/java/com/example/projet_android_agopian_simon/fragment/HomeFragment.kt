package com.example.projet_android_agopian_simon.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.projet_android_agopian_simon.R

class HomeFragment: Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
            ): View {
        val v = inflater.inflate(R.layout.home_fragment, container, false)
        val txt: TextView = v.findViewById(R.id.home_fragment)
        val preferences =
            this.activity!!.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val name = preferences.getString("name", null);
        val surname = preferences.getString("surname", null);

        if(name != null && surname !=  null) {
            txt.setText("Hello " + name + " " + surname)
        }

        return v
    }
}