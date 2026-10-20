package com.example.tenantmanagementsystem190050

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantmanagementsystem190050.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val handler = Handler(Looper.getMainLooper())

    // Runs once the splash delay is over: open Login and close the splash
    private val goToLogin = Runnable {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Gentle fade-in of the logo block
        binding.splashContent.alpha = 0f
        binding.splashContent.animate().alpha(1f).setDuration(900).start()

        handler.postDelayed(goToLogin, 1800)
    }

    // Cancel the pending jump if the screen is closed or rotated,
    // otherwise Login could open twice
    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(goToLogin)
    }
}