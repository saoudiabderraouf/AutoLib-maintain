package com.clovertech.autolib.ui

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
import com.clovertech.autolib.ui.adapters.OnboardingAdapter
import com.clovertech.autolib.ui.login.LoginActivity


class OnBoardingScreens : AppCompatActivity() {
    private var onboardingAdapter: OnboardingAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (restorePrefData()) {
            val mainActivity = Intent(applicationContext, LoginActivity::class.java)
            startActivity(mainActivity)
            finish()
        }

        setContentView(R.layout.activity_on_boarding_screens)

        var buttonOnboardingAction = findViewById<Button>(R.id.buttonOnBoardingAction)
        setOnboardingItem()
        val onboardingViewPager = findViewById<ViewPager2>(R.id.onboardingViewPager)
        onboardingViewPager.adapter = onboardingAdapter

        setOnboadingIndicator()
        setCurrentOnboardingIndicators(0)
        onboardingViewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentOnboardingIndicators(position)
            }
        })
        buttonOnboardingAction.setOnClickListener(){
            if (onboardingViewPager.currentItem + 1 < onboardingAdapter!!.itemCount) {
                onboardingViewPager.currentItem = onboardingViewPager.currentItem + 1
            } else {

                startActivity(Intent(applicationContext, SampleActivity::class.java))
                savePrefsData()
                finish()
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun setCurrentOnboardingIndicators(index: Int) {
        var layoutOnboardingIndicator = findViewById<LinearLayout>(R.id.layoutOnboardingIndicators)
        var buttonOnboardingAction = findViewById<Button>(R.id.buttonOnBoardingAction)

        val childCount: Int = layoutOnboardingIndicator.getChildCount()
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
        if (index == onboardingAdapter?.getItemCount()?.minus(1)){
            buttonOnboardingAction.setText("Get Started")
        }else {
            buttonOnboardingAction.setText("Next")
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

        onBoardingItems.add(itemFastFood);
        onBoardingItems.add(itemPayOnline);
        onBoardingItems.add(itemEatTogether);
        onBoardingItems.add(itemDayAndNight);

        onboardingAdapter = OnboardingAdapter(onBoardingItems);
    }

    private fun setOnboadingIndicator() {
        var layoutOnboardingIndicator = findViewById<LinearLayout>(R.id.layoutOnboardingIndicators)
        val indicators: Array<ImageView?> = arrayOfNulls<ImageView>(onboardingAdapter!!.itemCount)
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
            indicators[i]?.setLayoutParams(layoutParams)
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
        editor.commit()
    }


    
    
}