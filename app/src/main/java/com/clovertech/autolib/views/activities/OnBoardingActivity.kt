package com.clovertech.autolib.views.activities

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.clovertech.autolib.R
import com.clovertech.autolib.adapters.OnBoardingAdapter
import com.clovertech.autolib.model.OnBoardingItem


class OnBoardingActivity : AppCompatActivity() {

    private var onBoardingAdapter: OnBoardingAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (restorePrefData()) {
            val mainActivity = Intent(applicationContext, HomeActivity::class.java)
            startActivity(mainActivity)
            finish()
        }

        setContentView(R.layout.activity_on_boarding_screens)

        val buttonOnbBoardingAction = findViewById<Button>(R.id.buttonOnBoardingAction)
        val skipButton = findViewById<Button>(R.id.skip_button)
        val backButton = findViewById<Button>(R.id.backward_button)
        val onBoardingViewPager = findViewById<ViewPager2>(R.id.onboardingViewPager)

        setOnBoardingItems()
        onBoardingViewPager.adapter = onBoardingAdapter

        setOnBoardingIndicator()
        setCurrentOnBoardingIndicator(0)

        onBoardingViewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentOnBoardingIndicator(position)
            }
        })

        buttonOnbBoardingAction.setOnClickListener{
            if (onBoardingViewPager.currentItem + 1 < onBoardingAdapter!!.itemCount) {
                onBoardingViewPager.currentItem = onBoardingViewPager.currentItem + 1
            } else {
                startActivity(Intent(applicationContext, HomeActivity::class.java))
                savePrefsData()
                finish()
            }
        }

        skipButton.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            savePrefsData()
            finish()
        }

        backButton.setOnClickListener {
            if (onBoardingViewPager.currentItem - 1 >= 0) {
                onBoardingViewPager.currentItem = onBoardingViewPager.currentItem - 1
            }
        }

    }

    private fun setOnBoardingItems() {
        val onBoardingItems: MutableList<OnBoardingItem> = mutableListOf()

        val itemFastFood = OnBoardingItem(R.drawable.choose_your_meal
            ,"check your activities"
            ,"You can easily check your activities!")

        val itemEatTogether = OnBoardingItem(R.drawable.fast_delivery
            ,"See your calendar"
            ,"Our system provide a good calendar for you")

        val itemDayAndNight = OnBoardingItem(R.drawable.day_and_night
            ,"Day and Night"
            ,"Our service is on day and night!")

        onBoardingItems.add(itemFastFood)
        onBoardingItems.add(itemEatTogether)
        onBoardingItems.add(itemDayAndNight)

        onBoardingAdapter = OnBoardingAdapter(onBoardingItems)
    }

    private fun setCurrentOnBoardingIndicator(index: Int) {

        val layoutOnBoardingIndicator = findViewById<LinearLayout>(R.id.layoutOnboardingIndicators)
        val buttonOnBoardingAction = findViewById<Button>(R.id.buttonOnBoardingAction)
        val childCount: Int = layoutOnBoardingIndicator.childCount


        for (i in 0 until childCount) {
            val imageView = layoutOnBoardingIndicator.getChildAt(i) as ImageView

            if (i == index) {
                imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext,
                        R.drawable.onboarding_indicator_active))
            } else {
                imageView.setImageDrawable(ContextCompat
                    .getDrawable(applicationContext, R.drawable.onboarding_indicator_inactive))
            }
        }

        if (index == onBoardingAdapter?.itemCount?.minus(1)){
            buttonOnBoardingAction.text = getString(R.string.action_get_started)
        }else {
            buttonOnBoardingAction.text = getString(R.string.action_next)
        }
    }

    private fun setOnBoardingIndicator() {

        val layoutOnBoardingIndicator = findViewById<LinearLayout>(R.id.layoutOnboardingIndicators)
        val indicators: Array<ImageView?> = arrayOfNulls(onBoardingAdapter!!.itemCount)
        val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
            , ViewGroup.LayoutParams.WRAP_CONTENT)


        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext, R.drawable.onboarding_indicator_inactive
                )
            )
            indicators[i]?.layoutParams = layoutParams
            layoutOnBoardingIndicator!!.addView(indicators[i])
        }
    }

    private fun restorePrefData(): Boolean {
        val pref = applicationContext.getSharedPreferences("AUTOLIB_MAINTAIN", MODE_PRIVATE)
        return pref.getBoolean("IS_OPENED", false)
    }

    private fun savePrefsData() {
        val pref = applicationContext.getSharedPreferences("AUTOLIB_MAINTAIN", MODE_PRIVATE)
        pref.edit {
            putBoolean("IS_OPENED", true)
            apply()
        }
    }


    
    
}