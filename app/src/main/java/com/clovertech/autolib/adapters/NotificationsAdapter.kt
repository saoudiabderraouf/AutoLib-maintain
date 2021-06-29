
package com.clovertech.autolib.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolib.R
import com.clovertech.autolib.model.Task
import  com.clovertech.autolib.views.ui.notifications.Notif


class NotificationsAdapter(val context: Context):RecyclerView.Adapter<NotificationsAdapter.NotificationHolder>()
{

    var data = mutableListOf<Notif>()
    val tasks = mutableListOf<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationHolder {
        return NotificationHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.notification_layout, parent, false)
        )

    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: NotificationHolder, position: Int) {
        holder.titre.text = context.getString(R.string.notif_title)
        //holder.date.text=data[position].date
        //holder.image.setImageResource(data[position].image)
        holder.lu.isVisible= !data[position].lu
        holder.itemView.setOnClickListener{

        }

    }

    fun setNotificationList(list: List<Notif>){
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    inner class NotificationHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titre: TextView = view.findViewById(R.id.contenu)
        val date: TextView = view.findViewById(R.id.date)
        //val image: ImageView = view.findViewById(R.id.roundedimage)
        val lu: ImageView = view.findViewById(R.id.lu)
    }


}

