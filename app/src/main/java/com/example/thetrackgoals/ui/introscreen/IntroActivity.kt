package com.example.thetrackgoals.ui.introscreen

import ViewPagerAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.thetrackgoals.MainActivity
import com.example.thetrackgoals.R
import com.example.thetrackgoals.data.models.IntroView
import me.relex.circleindicator.CircleIndicator3

class IntroActivity : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var introViewPagerAdapter: ViewPagerAdapter
    val circleIndicator: CircleIndicator3 = findViewById(R.id.circleIndicator)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        viewPager2 = findViewById(R.id.viewPager2) // Replace with your ViewPager2 ID from XML
        introViewPagerAdapter = ViewPagerAdapter(addToIntroView())
        viewPager2.adapter = introViewPagerAdapter
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        circleIndicator.setViewPager(viewPager2)

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if (position == 2) {
                    animationButton()
                }
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        })
    }

    private fun animationButton() {
        val btnStartApp = findViewById<View>(R.id.btn_start_app) // Replace with your Button ID from XML
        btnStartApp.visibility = View.VISIBLE
        btnStartApp.alpha = 0f
        btnStartApp.animate().apply {
            duration = 1400
            alpha(1f)
        }.start()

        btnStartApp.setOnClickListener {
            val i = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    private fun addToIntroView(): List<IntroView> {
        return listOf(
            IntroView("Welcome to Goal Tracker!", R.drawable.ic_tea),
            IntroView("Smoking!", R.drawable.ic_smoking),
            IntroView("Fast Food!", R.drawable.ic_fastfood)
        )
    }
}
