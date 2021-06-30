package com.clovertech.autolib.views.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.clovertech.autolib.R
import com.clovertech.autolib.databinding.FragmentSettingsBinding
import com.clovertech.autolib.utils.PrefUtils

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (PrefUtils.with(requireContext()).getString(PrefUtils.Keys.DARK_MODE,"")=="dark"){
            binding.nightModeStateText.text = "Noir"
        }else{
            if(PrefUtils.with(requireContext()).getString(PrefUtils.Keys.DARK_MODE,"")=="light"){
                binding.nightModeStateText.text = "Clair"
            }
            else{
                binding.nightModeStateText.text = "Système"
            }
        }
        binding.modeNuitLayout.setOnClickListener{
            findNavController().navigate(R.id.action_nav_settings_to_themeFragment)

        }

    }

}