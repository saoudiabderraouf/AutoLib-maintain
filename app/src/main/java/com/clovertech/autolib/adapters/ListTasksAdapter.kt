package com.clovertech.autolib.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolib.R
import com.clovertech.autolib.model.Tache
import com.clovertech.autolib.views.ui.home.HomeFragment
import com.clovertech.autolib.viewmodel.TacheViewModel

class ListTasksAdapter(val context: Context, val vm: TacheViewModel, frag: HomeFragment) :
    RecyclerView.Adapter<ListTasksAdapter.MyViewHolder>() {

    var fragment = frag
    var data = listOf<Tache>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.card_view_layout, parent, false))

    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.description.text = data[position].description
        holder.idVehicle.text = data[position].idVehicule.toString()
        holder.taskTitle.text = data[position].taskTitle

        holder.itemView.setOnClickListener{
            fragment.goToTaskDetails(data[position])
        }

        holder.progressBar.max = data[position].steps?.size ?: 0
        holder.progressBar.progress = data[position].steps?.filter { it.completed }?.size ?: 0
        var progress =0
        if (holder.progressBar.max != 0) {
            progress = holder.progressBar.progress * 100 / holder.progressBar.max
        }

        holder.progress.text = "$progress %"
    }

    fun setListTache(list: List<Tache>) {
        data = list
        notifyDataSetChanged()
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val taskTitle: TextView = view.findViewById(R.id.Titre)
        val description: TextView = view.findViewById(R.id.description)
        val idVehicle: TextView = view.findViewById(R.id.idVoiture)
        val progress: TextView = view.findViewById(R.id.avancement)
        val progressBar: ProgressBar = view.findViewById(R.id.progressTaskHome)
    }

}


