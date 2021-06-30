package com.clovertech.autolib.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolib.databinding.TaskLayoutBinding
import com.clovertech.autolib.model.Step
import com.clovertech.autolib.viewmodel.TaskViewModel

class TaskStepsAdapter(val viewModel: TaskViewModel) :
    RecyclerView.Adapter<TaskStepsAdapter.ViewHolderTask>() {

    var data = listOf<Step>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTask {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TaskLayoutBinding.inflate(inflater,parent, false)
        return ViewHolderTask(binding)

    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolderTask, position: Int) {
        val context = holder.itemView.context
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

    inner class ViewHolderTask(binding:TaskLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val stepTitle: CheckBox = binding.todoCheckBox
        val taskTitle: TextView = binding.taskTitle
    }


}



