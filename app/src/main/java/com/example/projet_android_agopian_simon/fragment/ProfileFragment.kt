package com.example.projet_android_agopian_simon.fragment

import android.content.Context
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.projet_android_agopian_simon.R


class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val inf = inflater.inflate(R.layout.profile_fragment, container, false)

        var name: EditText = inf.findViewById(R.id.name)
        var surname: EditText = inf.findViewById(R.id.surname)
        var save: Button = inf.findViewById(R.id.save_profile)

        val preferences =
            this.activity!!.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor: Editor = preferences.edit()

        save.setOnClickListener({
            println(name.text.toString())
            println(surname.text.toString())
            editor.putString("name", name.text.toString())
            editor.putString("surname", surname.text.toString())
            editor.apply()

            surname.setText(null)
            name.setText(null)
        })
        return inf
    }
}