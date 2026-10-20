package com.example.tenantmanagementsystem190050

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantmanagementsystem190050.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Email sent from RegisterActivity (null when the app opens normally)
        val registeredEmail = intent.getStringExtra("EMAIL")
        if (registeredEmail != null) {
            binding.emailEditText.setText(registeredEmail)
        }

        // Explicit Intent: Login -> MainActivity
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this,
                    "Please enter your email and password",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Explicit Intent: Login -> RegisterActivity
        binding.registerTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Implicit Intent: open a website
        binding.helpTextView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.strathmore.edu"))
            try {
                startActivity(intent)
            } catch (_: ActivityNotFoundException) {
                Toast.makeText(this, "No browser application found", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
