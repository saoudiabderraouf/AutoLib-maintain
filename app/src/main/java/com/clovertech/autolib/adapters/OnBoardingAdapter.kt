package com.clovertech.autolib.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolib.databinding.ItemLayoutBinding
import com.clovertech.autolib.model.OnBoardingItem

class OnBoardingAdapter(private val onBoardingItems: List<OnBoardingItem>) :
    RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutBinding.inflate(inflater,parent, false)
        return OnBoardingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.setOnBoardingData(onBoardingItems[position])
    }

    override fun getItemCount(): Int {
        return onBoardingItems.size
    }

    inner class OnBoardingViewHolder(binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        private val textTitle: TextView = binding.textTitle
        private val textDescription: TextView = binding.textDescription
        private val imageOnBoarding: ImageView = binding.imageOnboarding

        fun setOnBoardingData(onBoardingItem: OnBoardingItem) {
            textTitle.text = onBoardingItem.title
            textDescription.text = onBoardingItem.description
            imageOnBoarding.setImageResource(onBoardingItem.image)
        }

    }
}