package com.example.thetrackgoals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.thetrackgoals.R
import com.example.thetrackgoals.ui.introscreen.IntroActivity
import com.google.android.material.tabs.TabLayoutMediator
import androidx.core.content.res.ResourcesCompat

class MainActivity : AppCompatActivity() {

//    private var userFirstTime = true
    //action bar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        installSplashScreen()

//        loadData()
//
//        if(userFirstTime) {
//            userFirstTime = false
//            saveData()
//
//            val i = Intent(this, IntroActivity::class.java)
//            startActivity(i)
//            finish()
//        }
//        setupActionBarWithNavController(findNavController(R.id.navHostFragment))
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.navHostFragment)
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }
//
//    private fun saveData() {
//        val sp = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE)
//        sp.edit().apply {
//            putBoolean("BOOLEAN_FIRST_TIME", userFirstTime)
//            apply()
//        }
//    }
//
//    private fun loadData() {
//        val sp = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE)
//        userFirstTime = sp.getBoolean("BOOLEAN_FIRST_TIME", true)
//    }

}
