package com.clovertech.autolib

import android.app.UiModeManager
import android.content.Context
import android.content.Context.UI_MODE_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.clovertech.autolib.utils.PrefUtils
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_theme.*


class ThemeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_theme, container, false)
    }


    fun NightModeON(view: View?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    fun NightModeOFF(view: View?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    fun SystemMode(view: View?){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (PrefUtils.with(requireContext()).getString(PrefUtils.Keys.dark,"")=="dark"){
            darkButton.isActivated=true
        }else{
            if(PrefUtils.with(requireContext()).getString(PrefUtils.Keys.dark,"")=="light"){
                lightButton.isActivated=true
            }
            else{
                systemButton.isActivated=true
            }
        }
        systemButton.setOnClickListener(){
            PrefUtils.with(requireActivity()).save(PrefUtils.Keys.dark,"system")
            SystemMode(it)
        }
        lightButton.setOnClickListener(){
            PrefUtils.with(requireActivity()).save(PrefUtils.Keys.dark,"dark")
            NightModeOFF(it)
        }
        darkButton.setOnClickListener(){
            PrefUtils.with(requireActivity()).save(PrefUtils.Keys.dark,"light")
            NightModeON(it)
        }
    }

}