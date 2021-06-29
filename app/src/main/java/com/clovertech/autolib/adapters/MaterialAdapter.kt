package com.clovertech.autolib.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolib.R
import com.clovertech.autolib.model.Materiel
import com.clovertech.autolib.viewmodel.TaskViewModel

class MaterialAdapter(val context: Context, val vm: TaskViewModel) :
    RecyclerView.Adapter<MaterialAdapter.MaterialViewHolder>() {
    var data = listOf<Materiel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialViewHolder {
        return MaterialViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.material_layout, parent, false)
        )

    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MaterialViewHolder, position: Int) {

        holder.materialName.text = data[position].description
        holder.quantity.text = data[position].quantity
        holder.itemView.setOnClickListener{
            //fragment.update(data[position].taskModel.id, data[position])
        }

    }

    fun setListMaterial(list: List<Materiel>) {
        data = list
        notifyDataSetChanged()
    }

    inner class MaterialViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val materialName: TextView = view.findViewById(R.id.nomMateriel)
        val quantity: TextView = view.findViewById(R.id.quantitéMateriel)
    }

}


