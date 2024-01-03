package com.example.thetrackgoals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.thetrackgoals.databinding.FragmentLoginBinding

class Login : AppCompatActivity() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout using ViewBinding
        binding = FragmentLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi elemen-elemen tampilan menggunakan binding
        val username = binding.Username
        val password = binding.Password
        val loginButton = binding.LoginButton

        loginButton.setOnClickListener {
            if (username.text.toString() == "user" && password.text.toString() == "1234") {
                // Jika kredensial valid, pindah ke halaman baru (contoh: HomeActivity)
                val intent = Intent(this, com.example.thetrackgoals.ui.fragments.habitlist.HabitList::class.java)
                startActivity(intent)
                finish() // Tutup aktivitas login agar tidak dapat kembali ke sini
                Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Login Gagal!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}