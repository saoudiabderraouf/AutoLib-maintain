package com.clovertech.autolib.views.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.clovertech.autolib.R
import com.clovertech.autolib.utils.PrefUtils
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        if (PrefUtils.with(requireContext()).getString(PrefUtils.Keys.dark,"")=="dark"){
            nightModeStateText.setText("Noir")
        }else{
            if(PrefUtils.with(requireContext()).getString(PrefUtils.Keys.dark,"")=="light"){
                nightModeStateText.setText("Clair")
            }
            else{
                nightModeStateText.setText("Système")
            }
        }
        modeNuitLayout.setOnClickListener(){
            it.findNavController()?.navigate(R.id.action_nav_settings_to_themeFragment)

        }
    }

}