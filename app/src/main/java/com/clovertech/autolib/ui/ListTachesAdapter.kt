package com.clovertech.autolib.ui

import com.clovertech.autolib.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolib.model.Tache
import com.clovertech.autolib.ui.home.HomeViewModel

class ListTachesAdapter (val context: Context, val vm: HomeViewModel):RecyclerView.Adapter<MyViewHolder>()
{   var data = listOf<Tache>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.card_view_layout, parent, false)
        )

    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //holder.titreTache.text = data[position].titre
        holder.descript.text = data[position].description
        holder.idVoiture.text = data[position].idVehicule.toString()
        holder.progres.text = data[position].idTaskState.toString()+"%"


        holder.itemView.setOnClickListener(View.OnClickListener{
            //vm.dr=data[position]
            holder.itemView.findNavController()?.navigate(R.id.action_navigation_home_to_detailTache)

        })

    }
    fun setListTache(list: List<Tache>){
        data = list
        notifyDataSetChanged()

    }

}


class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val titreTache = view.findViewById<TextView>(R.id.Titre)
    val descript = view.findViewById<TextView>(R.id.description)
    val idVoiture = view.findViewById<TextView>(R.id.idVoiture)
    val progres = view.findViewById<TextView>(R.id.avancement)

}


