package com.clovertech.autolib.cache.converter

import androidx.room.TypeConverter
import com.clovertech.autolib.model.Materiel
import com.clovertech.autolib.model.Step
import com.clovertech.autolib.model.TaskModelToken
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }


    @TypeConverter
    fun listMaterial(list: List<Materiel>?): String {
        val gson = Gson()
        val result: String = if(list != null )
            gson.toJson(list)
        else ""
        return result
    }

    @TypeConverter
    fun listToStringMaterial(listMateriel: String): List<Materiel>? {
        val gson = Gson()
        val type = object : TypeToken<List<Materiel>>() {}.type
        val result = if(listMateriel.isNotEmpty()) gson.fromJson<List<Materiel>>(listMateriel, type)
        else null
        return result

    }
    @TypeConverter
     fun taskModelToString(taskModel: TaskModelToken?): String{
        val gson = Gson()
        val result: String = if(taskModel != null)
            gson.toJson(taskModel)
        else ""
        return result
    }
    @TypeConverter
    fun StringToTaskModel(token : String): TaskModelToken?{
        val gson = Gson()
        val type = object : TypeToken<TaskModelToken>() {}.type
        val result = if(token.isNotEmpty()) gson.fromJson<TaskModelToken>(token, type)
        else null
        return result
    }
    @TypeConverter
    fun stepsToString(listSteps: List<Step>?): String{
        val gson = Gson()
        val result: String = if(listSteps != null)
            gson.toJson(listSteps)
        else ""
        return result
    }
    @TypeConverter
    fun StringToListSteps(token : String): List<Step>?{
        val gson = Gson()
        val type = object : TypeToken<List<Step>>() {}.type
        val result = if(token.isNotEmpty()) gson.fromJson<List<Step>>(token, type)
        else null
        return result
    }

}