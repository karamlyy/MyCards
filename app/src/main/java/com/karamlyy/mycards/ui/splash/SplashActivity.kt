package com.karamlyy.mycards.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.karamlyy.mycards.R
import com.karamlyy.mycards.StartActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 3000 // 3 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({

            startActivity(Intent(this, StartActivity::class.java))

            // Close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}