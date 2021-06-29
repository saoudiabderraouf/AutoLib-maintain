package com.clovertech.autolib.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolib.R
import com.clovertech.autolib.views.ui.onboarding.OnBoardingItem

class OnBoardingAdapter(private val onBoardingItems: List<OnBoardingItem>) :
    RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.setOnBoardingData(onBoardingItems[position])
    }

    override fun getItemCount(): Int {
        return onBoardingItems.size
    }

    inner class OnBoardingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textTitle: TextView = itemView.findViewById(R.id.textTitle)
        private val textDescription: TextView = itemView.findViewById(R.id.textDescription)
        private val imageOnBoarding: ImageView = itemView.findViewById(R.id.imageOnboarding)

        fun setOnBoardingData(onBoardingItem: OnBoardingItem) {
            textTitle.text = onBoardingItem.title
            textDescription.text = onBoardingItem.description
            imageOnBoarding.setImageResource(onBoardingItem.image)
        }

    }
}