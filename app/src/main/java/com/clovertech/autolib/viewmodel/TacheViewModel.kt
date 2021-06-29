package com.clovertech.autolib.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovertech.autolib.model.Task
import com.clovertech.autolib.repository.TaskRepo
import kotlinx.coroutines.launch
import java.util.*

class TacheViewModel : ViewModel() {

    var task: Task =
        Task(
            0, 0, "", "", 0, Date(), Date(),
            mutableListOf(), mutableListOf(), ""
        )




    fun updateTache(context: Context, task: Task) {
        TaskRepo.updateTask(context, task)
    }


    fun getAllTaches(context: Context): LiveData<List<Task>>? {
        return TaskRepo.getAllTasks(context)
    }

    fun getTacheIdAgent(context: Context, id: Int) {
        viewModelScope.launch {
            TaskRepo.getTaskByIdAgent(context, id)
        }
    }



}