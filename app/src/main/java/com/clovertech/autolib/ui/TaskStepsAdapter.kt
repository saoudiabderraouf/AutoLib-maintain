import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolib.R
import com.clovertech.autolib.model.Step

class TaskStepsAdapter(val context: Context, var data: List<Step>) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.task_layout, parent, false)
        )

    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.titreStep.text = data[position].titre
        holder.titreStep.setChecked(!data[position].status)
        holder.itemView.setOnClickListener(View.OnClickListener {
            data[position].status = !data[position].status
            holder.titreStep.setChecked(data[position].status)
        })

    }


}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val titreStep = view.findViewById<CheckBox>(R.id.todoCheckBox)
}

