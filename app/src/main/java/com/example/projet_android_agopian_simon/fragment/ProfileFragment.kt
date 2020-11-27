package com.example.projet_android_agopian_simon.fragment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences.Editor
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
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

        save.setOnClickListener{
            println(name.text.toString())
            println(surname.text.toString())
            editor.putString("name", name.text.toString())
            editor.putString("surname", surname.text.toString())
            editor.apply()
            createNotificationChannel()
            var notificationBuilder =
                NotificationCompat.Builder(requireContext(), "1")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("Notif")
                    .setContentText("Use just set your user name and surname")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            with(NotificationManagerCompat.from(requireContext())) {
                // notificationId is a unique int for each notification that you must define
                notify(1, notificationBuilder.build())
            }
            surname.setText(null)
            name.setText(null)
        }
        return inf
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("1", "my_channel", importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getActivity()?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}