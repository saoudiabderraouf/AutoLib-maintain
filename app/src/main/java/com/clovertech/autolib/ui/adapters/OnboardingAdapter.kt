package com.clovertech.autolib.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolib.R

class OnboardingAdapter(private val onBoardingItems: List<OnBoardingItem>) :
    RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        return OnboardingViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        holder.setOnBoardingData(onBoardingItems[position])
    }

    override fun getItemCount(): Int {
        return onBoardingItems.size
    }

    inner class OnboardingViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val textTitle: TextView
        private val textDescription: TextView
        private val imageOnboarding: ImageView

        fun setOnBoardingData(onBoardingItem: OnBoardingItem) {
            textTitle.text = onBoardingItem.title
            textDescription.text = onBoardingItem.description
            imageOnboarding.setImageResource(onBoardingItem.image)
        }

        init {
            textTitle = itemView.findViewById(R.id.textTitle)
            textDescription = itemView.findViewById(R.id.textDescription)
            imageOnboarding = itemView.findViewById(R.id.imageOnboarding)
        }

    }
}