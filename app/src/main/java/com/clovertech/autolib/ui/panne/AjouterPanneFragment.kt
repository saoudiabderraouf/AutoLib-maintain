package com.clovertech.autolib.ui.panne

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.clovertech.autolib.R
import com.clovertech.autolib.model.Materiel
import com.clovertech.autolib.model.Panne
import com.clovertech.autolib.ui.login.LoginActivity
import com.clovertech.autolib.ui.settings.SettingsViewModel
import com.clovertech.autolib.utils.PrefUtils
import com.clovertech.autolib.viewmodel.PanneViewModel
import kotlinx.android.synthetic.main.activity_login_agent.*
import kotlinx.android.synthetic.main.activity_login_agent.numChasis
import kotlinx.android.synthetic.main.fragment_ajouter_materiel.*
import kotlinx.android.synthetic.main.fragment_ajouter_panne.*
import kotlinx.android.synthetic.main.fragment_settings.*
import java.text.SimpleDateFormat
import java.util.*


class AjouterPanneFragment : Fragment() {
    lateinit var panneModel: com.clovertech.autolib.viewmodel.PanneViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ajouter_panne, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        panneModel = ViewModelProvider(this).get(PanneViewModel::class.java)

        val sdf = SimpleDateFormat("yyyy-mm-dd")
        val cuurentDate= sdf.format(Date())
        envoyer_signalement.setOnClickListener(){

        var Panne= Panne("2021-06-03", PrefUtils.with(requireContext()).getInt(PrefUtils.Keys.ID,3), "ready", numChasis.text.toString().toInt(),panneDescript.text.toString(), severite.text.toString().toInt() )
        if (Panne!= null){
            panneModel.addPanne(Panne)

            panneModel.ResponsePanne.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                if (it.body()==Panne) {
                    Toast.makeText(requireContext(), "ekhrodj", Toast.LENGTH_SHORT).show()
                }

            })


        }


    }}

}