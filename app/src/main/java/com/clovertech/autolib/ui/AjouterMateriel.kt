package com.clovertech.autolib.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolib.R
import com.clovertech.autolib.model.NewEquipement
import com.clovertech.autolib.utils.PrefUtils
import com.clovertech.autolib.viewmodel.EquipmentViewModel
import com.clovertech.autolib.viewmodel.MaterielViewModel
import kotlinx.android.synthetic.main.fragment_ajouter_materiel.*


class AjouterMateriel : Fragment() {
    lateinit var equipmentViewModel: EquipmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ajouter_materiel, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var viewModel = ViewModelProvider(requireActivity()).get(MaterielViewModel::class.java)
        equipmentViewModel =
            ViewModelProvider(requireActivity()).get(EquipmentViewModel::class.java)
        ajouter_materiel.setOnClickListener {
            val token = PrefUtils.with(requireContext()).getString(PrefUtils.Keys.taskUuid, "")
            /*var newEquipement = token?.let { it1 ->
                NewEquipement(
                    materielDescript.text.toString(), materielQuantite.text.toString(), "",
                    it1
                )
            }*/
            var newEquipement= NewEquipement("test", 12,"282d4458-aaeb-4e92-a674-12320b1de46a","969f0417-0611-4f7c-9fc3-1f4b3ca22573" )
            if (newEquipement != null) {
                equipmentViewModel.addMateriel(newEquipement)

            equipmentViewModel.Response.observe(viewLifecycleOwner, Observer {
                if (it.isSuccessful) {
                    Toast.makeText(requireContext(), "Login success", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), it.body()?.token, Toast.LENGTH_SHORT).show()
                }

            })
        }}


    }


}