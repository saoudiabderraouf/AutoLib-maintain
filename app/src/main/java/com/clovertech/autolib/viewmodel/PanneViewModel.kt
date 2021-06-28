package com.clovertech.autolib.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import com.clovertech.autolib.model.Panne
import com.clovertech.autolib.repository.PanneRepo


class PanneViewModel {
    fun getAllPannes(context: Context): LiveData<List<Panne>>? {
        return PanneRepo.getAllPannes(context)
    }

}