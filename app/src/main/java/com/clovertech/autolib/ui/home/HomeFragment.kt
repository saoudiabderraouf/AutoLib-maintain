package com.clovertech.autolib.ui.home

import TaskStepsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.clovertech.autolib.R
import com.clovertech.autolib.model.Step
import com.clovertech.autolib.model.Tache
import com.clovertech.autolib.ui.ListTachesAdapter
import com.clovertech.autolib.utils.PrefUtils
import com.clovertech.autolib.viewmodel.TacheViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    lateinit var tacheViewModel: TacheViewModel

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
        val vm = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        var adapter = ListTachesAdapter(requireActivity(), vm)


        recyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

        tasksRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        tasksRecyclerView.adapter = TaskStepsAdapter(requireActivity(), loadSteps())
        tacheViewModel = ViewModelProvider(this).get(TacheViewModel::class.java)

        var id= PrefUtils.with(requireContext()).getInt(PrefUtils.Keys.ID,0)
        if (id != 0) {
            tacheViewModel.getTacheIdAgent(100)
            tacheViewModel.ResponseTacheById.observe(viewLifecycleOwner, Observer {
                if(it.isSuccessful){
                    it.body()?.let { it1 -> adapter.setListTache(it1) }
                }
                else{
                    val tache1 = Tache(1, 1, "test", "je teste", 5,"12/12/14","","")
                    val tache2 = Tache(2, 5, "test", "je teste", 5,"12/12/12","","")
                    this.context?.let { tacheViewModel.insertTache(it, tache1) }
                    this.context?.let { tacheViewModel.insertTache(it, tache2) }
                    this.context?.let {
                        tacheViewModel.getAllTaches(it)?.observe(viewLifecycleOwner, Observer {
                            adapter.setListTache(it)

                        })
                    }

                }
            })
        }



    }


    fun loadData(listTaches: List<Tache>): List<Tache> {
        /*val data = mutableListOf<Tache>()
        data.add(Tache(1,1,123456,"Revision","deplacer le vehicule",50,1))
        data.add(Tache(2,1,123456,"Revision","deplacer le vehicule",50,1))
        data.add(Tache(2,1,123456,"Revision","deplacer le vehicule",50,1))
        data.add(Tache(4,1,123456,"Revision","deplacer le vehicule",50,1))*/

        return listTaches
    }

    fun loadSteps(): List<Step> {
        val data = mutableListOf<Step>()
        data.add(Step("faire la vidange", false))
        data.add(Step("faire la vidange", false))
        data.add(Step("faire la vidange", true))
        data.add(Step("faire la vidange", false))
        return data
    }
}




