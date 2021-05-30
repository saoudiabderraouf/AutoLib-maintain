
package com.clovertech.autolib.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolib.R
import  com.clovertech.autolib.ui.Notif


class NotificationsAdapter(val context: Context, var data:List<Notif>):RecyclerView.Adapter<NotificationHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationHolder {
        return NotificationHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.notification_layout, parent, false)
        )

    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: NotificationHolder, position: Int) {
        holder.titre.text = data[position].titre
        holder.date.text=data[position].date
        holder.image.setImageResource(data[position].image)
        holder.lu.isVisible= !data[position].lu


        holder.itemView.setOnClickListener(View.OnClickListener{

        })

    }


}

public class NotificationHolder(view: View) : RecyclerView.ViewHolder(view) {
    val titre = view.findViewById<TextView>(R.id.contenu)
    val date = view.findViewById<TextView>(R.id.date)
    val image = view.findViewById<ImageView>(R.id.roundedimage)
    val lu = view.findViewById<ImageView>(R.id.lu)
}

