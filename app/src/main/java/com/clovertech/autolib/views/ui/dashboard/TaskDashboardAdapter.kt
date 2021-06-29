package com.clovertech.autolib.views.ui.dashboard

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
        val task = tasks[position]
        holder.description.text = task.description
        holder.title.text = task.taskTitle
        val progress = task.steps!!.filter { it.completed }.size * 100 / task.steps!!.size
        holder.progress.text = progress.toString() + "%"
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