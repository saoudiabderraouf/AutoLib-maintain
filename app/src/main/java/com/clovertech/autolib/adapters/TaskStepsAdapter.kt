package com.clovertech.autolib.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolib.R
import com.clovertech.autolib.model.Step
import com.clovertech.autolib.viewmodel.TaskViewModel

class TaskStepsAdapter(val context: Context, val viewModel: TaskViewModel) :
    RecyclerView.Adapter<TaskStepsAdapter.ViewHolderTask>() {

    var data = listOf<Step>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTask {
        return ViewHolderTask(
            LayoutInflater.from(context).inflate(R.layout.task_layout, parent, false)
        )

    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolderTask, position: Int) {
        holder.taskTitle.text = data[position].step
        holder.stepTitle.isChecked = data[position].completed
        holder.stepTitle.setOnClickListener{
            data[position].completed = !data[position].completed
            holder.stepTitle.isChecked = data[position].completed
            viewModel.task.steps?.get(position)?.completed = data[position].completed
            viewModel.updateTask(context, viewModel.task)
        }

    }

    fun setListSteps(list: List<Step>) {
        data = list
        notifyDataSetChanged()
    }

    inner class ViewHolderTask(view: View) : RecyclerView.ViewHolder(view) {
        val stepTitle: CheckBox = view.findViewById(R.id.todoCheckBox)
        val taskTitle: TextView = view.findViewById(R.id.task_title)
    }


}



