package com.clovertech.autolib.ui.home

import TaskStepsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.clovertech.autolib.R
import com.clovertech.autolib.model.Step
import com.clovertech.autolib.model.Tache
import com.clovertech.autolib.ui.ListTachesAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val vm= ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        recyclerView.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = ListTachesAdapter(requireActivity(), loadData(),vm)


        tasksRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        tasksRecyclerView.adapter = TaskStepsAdapter(requireActivity(),loadSteps())


    }

    fun loadData():List<Tache> {
        val data = mutableListOf<Tache>()
        data.add(Tache(1,1,123456,"Revision","deplacer le vehicule",50,1))
        data.add(Tache(2,1,123456,"Revision","deplacer le vehicule",50,1))
        data.add(Tache(2,1,123456,"Revision","deplacer le vehicule",50,1))
        data.add(Tache(4,1,123456,"Revision","deplacer le vehicule",50,1))
        return data
    }
    fun loadSteps():List<Step> {
        val data = mutableListOf<Step>()
        data.add(Step("faire la vidange",false))
        data.add(Step("faire la vidange",false))
        data.add(Step("faire la vidange",true))
        data.add(Step("faire la vidange",false))
        return data
    }
}