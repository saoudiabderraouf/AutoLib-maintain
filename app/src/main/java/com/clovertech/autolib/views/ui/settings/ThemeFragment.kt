package com.clovertech.autolib.views.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.clovertech.autolib.databinding.FragmentThemeBinding
import com.clovertech.autolib.utils.PrefUtils
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ThemeFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentThemeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentThemeBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (PrefUtils.with(requireContext()).getString(PrefUtils.Keys.DARK_MODE,"")=="dark"){
            binding.darkButton.isActivated=true
        }else{
            if(PrefUtils.with(requireContext()).getString(PrefUtils.Keys.DARK_MODE,"")=="light"){
                binding.lightButton.isActivated=true
            }
            else{
                binding.systemButton.isActivated=true
            }
        }
        binding.systemButton.setOnClickListener{
            PrefUtils.with(requireActivity()).save(PrefUtils.Keys.DARK_MODE,"system")
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
        binding.lightButton.setOnClickListener{
            PrefUtils.with(requireActivity()).save(PrefUtils.Keys.DARK_MODE,"dark")
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        binding.darkButton.setOnClickListener{
            PrefUtils.with(requireActivity()).save(PrefUtils.Keys.DARK_MODE,"light")
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

}