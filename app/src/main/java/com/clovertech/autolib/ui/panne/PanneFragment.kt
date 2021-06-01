package com.clovertech.autolib.ui.panne

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.clovertech.autolib.R
import com.clovertech.autolib.model.Panne
import com.clovertech.autolib.ui.adapters.ListTachesAdapter
import com.clovertech.autolib.ui.adapters.PanneAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_panne.*
import java.util.*

class PanneFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_panne, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var adapter = PanneAdapter(requireActivity())
        panneRecyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        panneRecyclerView.adapter = adapter
        adapter.setListPannes(loadPannes())
        panneRecyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        signalerPanne.setOnClickListener(){

        }
    }
    fun loadPannes():List<Panne>{
        return listOf(
            Panne(1, Date(),Date(),"Ene cours","Description de la panne",0,1,1,"Severe"),
            Panne(1, Date(),Date(),"Ene cours","Description de la panne",0,1,1,"Severe"),
            Panne(1, Date(),Date(),"Ene cours","Description de la panne",0,1,1,"Severe"),
            Panne(1, Date(),Date(),"Ene cours","Description de la panne",0,1,1,"Severe")
        )
    }

}