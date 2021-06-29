package com.clovertech.autolib.views.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolib.R
import com.clovertech.autolib.model.Tache

class TaskPagerFragment: Fragment() {

    private lateinit var taskRecyclerView: RecyclerView
    private val taskAdapter = TaskDashboardAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.task_pager_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            //val textView: TextView = view.findViewById(android.R.id.text1)
            //textView.text = getInt(ARG_OBJECT).toString()
        //}
        taskRecyclerView = view.findViewById(R.id.recyclerTaskDashboard)
        taskRecyclerView.adapter = taskAdapter
        taskRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    fun updateTasks(tasks: List<Tache>) {
        taskAdapter.setTasks(tasks)
    }


}