package com.example.projet_android_agopian_simon

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.projet_android_agopian_simon.fragment.HomeFragment
import com.example.projet_android_agopian_simon.fragment.ProfileFragment
import com.example.projet_android_agopian_simon.fragment.RocketFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private var currentLanguage = "en"
    private var currentLang: String? = null
    private val cameraRequest = 1888


    var REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val fragment_home  = HomeFragment()
        val fragment_profile  = ProfileFragment()

        if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), cameraRequest)
        val fragment_rocket  = RocketFragment()
        val buttonPhoto = findViewById<Button>(R.id.button_Photo)
        buttonPhoto.setOnClickListener(View.OnClickListener { c: View? -> onClickPhoto() })

        showFragment(R.id.container, fragment_home)
        navView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.tab_home->showFragment(R.id.container, fragment_home)
                R.id.tab_profile->showFragment(R.id.container, fragment_profile)
                R.id.tab_rocket->showFragment(R.id.container, fragment_rocket)
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
            val text = "Image saved on files !"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }
    }
}