package com.example.thetrackgoals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.thetrackgoals.databinding.ActivityMainBinding

class BarActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        replaceFragment(Profile())
//
//        binding.bottomNavigationView.setOnItemSelectedListener {
//            when (it.itemId){
//
//                R.id.profile -> replaceFragment(Profile())
//                R.id.task_complete -> replaceFragment(Calender())
//                R.id.task -> replaceFragment(Task())
//
//                else ->{
//
//                }
//
//            }
//            true
//        }
//    }
//
//    private fun replaceFragment(fragment: Fragment){
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.frameLayout, fragment)
//        fragmentTransaction.commit()
//    }
}