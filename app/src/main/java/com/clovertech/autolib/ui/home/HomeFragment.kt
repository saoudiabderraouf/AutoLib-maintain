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

        tacheViewModel = ViewModelProvider(requireActivity()).get(TacheViewModel::class.java)
        var id = PrefUtils.with(requireContext()).getInt(PrefUtils.Keys.ID, 0)
        id =3

        if (id != 0) {
            Toast.makeText(requireContext(), "Test is working", Toast.LENGTH_SHORT)
                .show()

            tacheViewModel.getTacheIdAgent(100)
            tacheViewModel.ResponseTacheById.observe(viewLifecycleOwner, Observer {
                if (it.isSuccessful) {
                    Toast.makeText(requireContext(), "Success in getting data", Toast.LENGTH_SHORT)
                        .show()
                    it.body()?.let { it1 ->
                        adapter.setListTache(it1)
                        nbTaches2.text = it1.size.toString()
                        tachePrem = it1.get(0)
                        loadSteps()
                    }
                } else {
                    Toast.makeText(requireContext(), "failure in getting data", Toast.LENGTH_SHORT)
                        .show()


                }

            })

        }

        details.setOnClickListener() {
            var viewModel = ViewModelProvider(this).get(TacheViewModel::class.java)

            it.findNavController()?.navigate(R.id.action_navigation_home_to_detailTache)
        }


    }

    fun update(id: Int, tache: Tache) {
        //tacheViewModel.tache=tache
        Toast.makeText(
            requireContext(), id.toString(),
            Toast.LENGTH_SHORT
        ).show()
        var viewModel = ViewModelProvider(requireActivity()).get(TacheViewModel::class.java)

        viewModel.getTacheModelid(id)
        viewModel.ResponseTacheModel.observe(viewLifecycleOwner, Observer {
            if (it.isSuccessful) {
                it.body()?.steps?.let { it1 -> adapterSteps.setListSteps(it1) }
                viewModel.taskModel = it.body()!!
                viewModel.task = tache

            } else {
                loadSteps()
            }

        })

    }

    fun loadSteps() {

        var viewModel = ViewModelProvider(requireActivity()).get(TacheViewModel::class.java)
        if (tachePrem != null) {

            viewModel.getTacheModelid(tachePrem.taskModel.id)
            viewModel.ResponseTacheModel.observe(viewLifecycleOwner, Observer {
                if (it.isSuccessful) {
                    it.body()?.steps?.let { it1 -> adapterSteps.setListSteps(it1) }
                    viewModel.taskModel = it.body()!!
                    viewModel.task = tachePrem

                } else {

                }

            })
        }


    }


}




