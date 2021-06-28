package com.clovertech.autolib.ui.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.clovertech.autolib.R
import com.clovertech.autolib.activities.SampleActivity
import com.clovertech.autolib.ui.login.LoginActivity


class OnBoardingScreens : AppCompatActivity() {

    private var onBoardingAdapter: OnboardingAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Agent_APP)

        if (restorePrefData()) {
            val mainActivity = Intent(applicationContext, SampleActivity::class.java)
            startActivity(mainActivity)
            finish()
        }

        setContentView(R.layout.activity_on_boarding_screens)

        val buttonOnbBoardingAction = findViewById<Button>(R.id.buttonOnBoardingAction)
        val skipButton = findViewById<Button>(R.id.skip_button)
        val backButton = findViewById<Button>(R.id.backward_button)

        setOnboardingItem()
        val onboardingViewPager = findViewById<ViewPager2>(R.id.onboardingViewPager)
        onboardingViewPager.adapter = onBoardingAdapter

        setOnboadingIndicator()
        setCurrentOnboardingIndicators(0)
        onboardingViewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentOnboardingIndicators(position)
            }
        })
        buttonOnbBoardingAction.setOnClickListener{
            if (onboardingViewPager.currentItem + 1 < onBoardingAdapter!!.itemCount) {
                onboardingViewPager.currentItem = onboardingViewPager.currentItem + 1
            } else {
                startActivity(Intent(applicationContext, SampleActivity::class.java))
                savePrefsData()
                finish()
            }
        }

        skipButton.setOnClickListener {
            startActivity(Intent(applicationContext, SampleActivity::class.java))
            savePrefsData()
            finish()
        }

        backButton.setOnClickListener {
            if (onboardingViewPager.currentItem - 1 >= 0) {
                onboardingViewPager.currentItem = onboardingViewPager.currentItem - 1
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun setCurrentOnboardingIndicators(index: Int) {
        val layoutOnboardingIndicator = findViewById<LinearLayout>(R.id.layoutOnboardingIndicators)
        val buttonOnboardingAction = findViewById<Button>(R.id.buttonOnBoardingAction)

        val childCount: Int = layoutOnboardingIndicator.childCount
        for (i in 0 until childCount) {
            val imageView = layoutOnboardingIndicator.getChildAt(i) as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.onboarding_indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.onboarding_indicator_inactive
                    )
                )
            }
        }
        if (index == onBoardingAdapter?.itemCount?.minus(1)){
            buttonOnboardingAction.text = "Get Started"
        }else {
            buttonOnboardingAction.text = "Next"
        }
    }



    private fun setOnboardingItem() {
        val onBoardingItems: MutableList<OnBoardingItem> = mutableListOf()
        val itemFastFood = OnBoardingItem()
        itemFastFood.title="check your activities"
        itemFastFood.description="You can easily check your activities!"
        itemFastFood.image=R.drawable.choose_your_meal

        val itemPayOnline = OnBoardingItem()
        itemPayOnline.title="Choose your payment"
        itemPayOnline.description="You can pay us using any methods, online or offline!"
        itemPayOnline.image=R.drawable.choose_your_payment

        val itemEatTogether = OnBoardingItem()

        itemEatTogether.title="See your calendar"
        itemEatTogether.description="Our system provide a good calendar for you"
        itemEatTogether.image=R.drawable.fast_delivery

        val itemDayAndNight = OnBoardingItem()
        itemDayAndNight.title="Day and Night"
        itemDayAndNight.description="Our service is on day and night!"
        itemDayAndNight.image=R.drawable.day_and_night

        onBoardingItems.add(itemFastFood)
        onBoardingItems.add(itemPayOnline)
        onBoardingItems.add(itemEatTogether)
        onBoardingItems.add(itemDayAndNight)

        onBoardingAdapter = OnboardingAdapter(onBoardingItems);
    }

    private fun setOnboadingIndicator() {
        val layoutOnboardingIndicator = findViewById<LinearLayout>(R.id.layoutOnboardingIndicators)
        val indicators: Array<ImageView?> = arrayOfNulls(onBoardingAdapter!!.itemCount)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext, R.drawable.onboarding_indicator_inactive
                )
            )
            indicators[i]?.layoutParams = layoutParams
            layoutOnboardingIndicator!!.addView(indicators[i])
        }
    }
    private fun restorePrefData(): Boolean {
        val pref =
            applicationContext.getSharedPreferences("myPrefs", MODE_PRIVATE)
        return pref.getBoolean("isOpnend", false)
    }

    private fun savePrefsData() {
        val pref = applicationContext.getSharedPreferences("myPrefs", MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean("isOpnend", true)
        editor.apply()
    }


    
    
}