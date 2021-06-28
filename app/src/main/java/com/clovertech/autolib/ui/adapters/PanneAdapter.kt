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
import com.clovertech.autolib.model.Panne
import com.clovertech.autolib.model.Tache
import com.clovertech.autolib.viewmodel.TacheViewModel

class PanneAdapter(val context: Context) :
    RecyclerView.Adapter<PanneViewHolder>() {
    var data = listOf<Panne>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PanneViewHolder {
        return PanneViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.panne_layout, parent, false)
        )

    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: PanneViewHolder, position: Int) {


        holder.panneText.text = data[position].Description
        //holder.panneDate.text = data[position].dateNotification.toString()

        holder.itemView.setOnClickListener(View.OnClickListener {
            //fragment.update(data[position].taskModel.id, data[position])
        })

    }

    fun setListPannes(list: List<Panne>) {
        data = list
        notifyDataSetChanged()
    }

}


class PanneViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val panneText = view.findViewById<TextView>(R.id.contenuPanne)
    val panneDate = view.findViewById<TextView>(R.id.dateSignalement)


}


