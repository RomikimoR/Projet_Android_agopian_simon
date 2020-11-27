package com.example.projet_android_agopian_simon

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projet_android_agopian_simon.fragment.HomeFragment
import com.example.projet_android_agopian_simon.fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    var REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment_home  = HomeFragment()
        val fragment_profile  = ProfileFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val buttonPhoto = findViewById<Button>(R.id.button_Photo)
        buttonPhoto.setOnClickListener(View.OnClickListener { c: View? -> onClickPhoto() })





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

    fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent,  REQUEST_IMAGE_CAPTURE)
        }catch (e: ActivityNotFoundException) {
            Log.e("error", "can't take photo")
        }

    }


    fun onClickPhoto() {
        dispatchTakePictureIntent()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val extras = data?.extras
            val imageBitmap = extras!!["data"] as Bitmap?

            //ImageView imageView = findViewById(R.id.imageView2);
            CapturePhotoUtils.insertImage(this.contentResolver, imageBitmap, "title", "uberIMG")
            val text = "Image saved!"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }
    }
}