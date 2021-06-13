package com.clovertech.autolib.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
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
        tacheViewModel = ViewModelProvider(requireActivity()).get(TacheViewModel::class.java)
        var adapter = ListTachesAdapter(requireActivity(), vm, this)
        recyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

        adapterSteps = TaskStepsAdapter(requireActivity(), tacheViewModel)
        tasksRecyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        tasksRecyclerView.adapter = adapterSteps

        tacheViewModel = ViewModelProvider(requireActivity()).get(TacheViewModel::class.java)
        var id = PrefUtils.with(requireContext()).getInt(PrefUtils.Keys.ID, 0)
        id = 3

        if (id != 0) {
           /* Toast.makeText(requireContext(), "Test is working", Toast.LENGTH_SHORT)
                .show()*/

            //tacheViewModel.getTacheIdAgent(requireContext(), 100)
            tacheViewModel.getTacheAllModel(requireContext())

            tacheViewModel.getAllTaches(requireContext())?.observe(viewLifecycleOwner, Observer {
                adapter.setListTache(it)
                nbTaches2.text = it.size.toString()
            })

        }

        details.setOnClickListener() {
            //var viewModel = ViewModelProvider(this).get(TacheViewModel::class.java)

            it.findNavController()?.navigate(R.id.action_navigation_home_to_detailTache)
        }

    }

    fun update(tache: Tache) {
       /* Toast.makeText(
            requireContext(), tache.uuid.toString(),
            Toast.LENGTH_SHORT
        ).show()*/
        var viewModel = ViewModelProvider(requireActivity()).get(TacheViewModel::class.java)
        tache.steps?.let { adapterSteps.setListSteps(it) }
        viewModel.task = tache

    }


}

fun updateTask(step: List<Step>) {


}


/*fun loadSteps() {

    var viewModel = ViewModelProvider(requireActivity()).get(TacheViewModel::class.java)
    if (tachePrem != null) {

        viewModel.getTacheModelid(tachePrem.taskModel.id)
        viewModel.getAllTacheModel().observe(viewLifecycleOwner, Observer {
            if (it.isSuccessful) {
                it.body()?.steps?.let { it1 -> adapterSteps.setListSteps(it1) }
                viewModel.taskModel = it.body()!!
                viewModel.task = tachePrem

            } else {

            }

        })
    }


}*/







