package com.clovertech.autolib.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.clovertech.autolib.R
import com.clovertech.autolib.model.Step
import com.clovertech.autolib.model.Tache
import com.clovertech.autolib.ui.adapters.ListTachesAdapter
import com.clovertech.autolib.ui.adapters.TaskStepsAdapter
import com.clovertech.autolib.utils.PrefUtils
import com.clovertech.autolib.viewmodel.TacheViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    lateinit var tacheViewModel: TacheViewModel
    lateinit var adapterSteps: TaskStepsAdapter
    lateinit var tachePrem: Tache

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
        var adapter = ListTachesAdapter(requireActivity(), vm, this)
        recyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

        adapterSteps = TaskStepsAdapter(requireActivity())
        tasksRecyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        tasksRecyclerView.adapter = adapterSteps

        tacheViewModel = ViewModelProvider(this).get(TacheViewModel::class.java)
        var id = PrefUtils.with(requireContext()).getInt(PrefUtils.Keys.ID, 0)

        if (id != 0) {
            tacheViewModel.getTacheIdAgent(100)
            tacheViewModel.ResponseTacheById.observe(viewLifecycleOwner, Observer {
                if (it.isSuccessful) {
                    it.body()?.let { it1 ->
                        adapter.setListTache(it1)
                        nbTaches2.text = it1.size.toString()
                        tachePrem = it1.get(0)
                        loadSteps()
                    }
                } else {
                    /*val tache1 = Tache(1, 1, "test", "je teste", 5,"12/12/14","","")
                    val tache2 = Tache(2, 5, "test", "je teste", 5,"12/12/12","","")
                    this.context?.let { tacheViewModel.insertTache(it, tache1) }
                    this.context?.let { tacheViewModel.insertTache(it, tache2) }
                    this.context?.let {
                        tacheViewModel.getAllTaches(it)?.observe(viewLifecycleOwner, Observer {
                            adapter.setListTache(it)

                })
            }*/


                }

            })

        }


    }

    fun update(id: Int) {
        Toast.makeText(
            requireContext(), id.toString(),
            Toast.LENGTH_SHORT
        ).show()
        var viewModel = ViewModelProvider(this).get(TacheViewModel::class.java)

        viewModel.getTacheModelid(id)
        viewModel.ResponseTacheModel.observe(viewLifecycleOwner, Observer {
            if (it.isSuccessful) {
                it.body()?.steps?.let { it1 -> adapterSteps.setListSteps(it1) }
            } else {
               loadSteps()
            }

        })

    }

    fun loadSteps() {

        var viewModel = ViewModelProvider(this).get(TacheViewModel::class.java)
        if (tachePrem!=null){

        viewModel.getTacheModelid(tachePrem.taskModel.id)
        viewModel.ResponseTacheModel.observe(viewLifecycleOwner, Observer {
            if (it.isSuccessful) {
                it.body()?.steps?.let { it1 -> adapterSteps.setListSteps(it1) }
            } else {

            }

        })}


    }


}




