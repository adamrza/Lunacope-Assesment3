package com.example.lunacope.onboarding.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lunacope.MainActivity
import com.example.lunacope.R

class SplashActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash)

    val sharedPreferences: SharedPreferences = getSharedPreferences("USER", MODE_PRIVATE)
    if(sharedPreferences.getString("NAME",null) == null || sharedPreferences.getString("SIGN", null) == null) {
      startActivity(Intent(this@SplashActivity, WelcomeActivity::class.java))
    }
    else {
      Log.i("test","name: " + sharedPreferences.getString("NAME",null) + "\nsign: " + sharedPreferences.getString("SIGN", null))
      startActivity(Intent(this@SplashActivity, MainActivity::class.java))
    }
    finish()
  }
}