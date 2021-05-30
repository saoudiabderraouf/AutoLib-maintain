package com.clovertech.autolib.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolib.R
import com.clovertech.autolib.model.Tache

class TaskDashboardAdapter: RecyclerView.Adapter<TaskDashboardAdapter.TaskViewHolder>() {

    private val tasks = mutableListOf<Tache>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.task_dashboard_item_layout,
            parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.description.text = tasks[position].description
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun setTasks(list: List<Tache>){
        tasks.clear()
        tasks.addAll(list)
        notifyDataSetChanged()
    }

    class TaskViewHolder(view: View): RecyclerView.ViewHolder(view){

        val title: TextView = view.findViewById(R.id.textTaskDashboardTitle)
        val description: TextView = view.findViewById(R.id.textTaskDashboardDescruption)
        val vehicle: TextView = view.findViewById(R.id.textTaskDashboardVehicule)
        val progress: TextView = view.findViewById(R.id.textTaskDashboardProgress)

    }
}