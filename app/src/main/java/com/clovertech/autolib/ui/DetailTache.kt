package com.clovertech.autolib.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.clovertech.autolib.R
import com.clovertech.autolib.ui.adapters.TaskStepsAdapter
import com.clovertech.autolib.viewmodel.TacheViewModel
import kotlinx.android.synthetic.main.fragment_detail_tache.*


class DetailTache : Fragment() {
    lateinit var adapterSteps: TaskStepsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_tache, container, false)

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var viewModel = ViewModelProvider(requireActivity()).get(TacheViewModel::class.java)

        titreTask.text = viewModel.task.taskTitle
        descriptTask.text = viewModel.task.description
        idVoiture.text = viewModel.task.idVehicule.toString()


        adapterSteps = TaskStepsAdapter(requireActivity())
        tasksRecyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        tasksRecyclerView.adapter = adapterSteps
        adapterSteps.setListSteps(viewModel.taskModel.steps)
        addMaterial.setOnClickListener() {
            it.findNavController()?.navigate(R.id.action_detailTache_to_ajouterMateriel)
        }


    }


}