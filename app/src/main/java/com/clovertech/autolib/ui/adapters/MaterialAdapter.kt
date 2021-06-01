package com.clovertech.autolib.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolib.R
import com.clovertech.autolib.model.Equipement
import com.clovertech.autolib.model.Materiel
import com.clovertech.autolib.model.Tache
import com.clovertech.autolib.viewmodel.TacheViewModel

class MaterialAdapter(val context: Context, val vm: TacheViewModel) :
    RecyclerView.Adapter<MaterialViewHolder>() {
    var data = listOf<Materiel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialViewHolder {
        return MaterialViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.material_layout, parent, false)
        )

    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MaterialViewHolder, position: Int) {


        holder.nomMateriel.text = data[position].description
        holder.quatite.text = data[position].quantity.toString()

        holder.itemView.setOnClickListener(View.OnClickListener {
            //fragment.update(data[position].taskModel.id, data[position])
        })

    }

    fun setListMaterial(list: List<Materiel>) {
        data = list
        notifyDataSetChanged()
    }

}


class MaterialViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nomMateriel = view.findViewById<TextView>(R.id.nomMateriel)
    val quatite = view.findViewById<TextView>(R.id.quantitéMateriel)


}


