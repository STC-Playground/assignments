
import com.ttpkk.assignments.assignment3.ScanActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ttpkk.assignments.R
import java.lang.ref.WeakReference
import java.sql.Timestamp
import java.text.SimpleDateFormat

class ItemAdapter(private val itemList : ArrayList<ScanActivity.Transaction>):
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

        inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

            lateinit var tvBox: TextView
            lateinit var tvPart: TextView
            lateinit var tvTimestamp: TextView

            init {
                tvBox = itemView.findViewById(R.id.tv_box)
                tvPart = itemView.findViewById(R.id.tv_part)
                tvTimestamp = itemView.findViewById(R.id.tv_timestamp)

            }

        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_result,parent,false)
        return ItemViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.tvBox.text = item.box
        holder.tvPart.text = item.part
        holder.tvTimestamp.text = convertTime(item.timestamp)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    private fun convertTime(time: Timestamp) : String {
        var dateFormat = SimpleDateFormat("dd/MM/yyyy\nHH:mm:ss")
        return dateFormat.format(time)
    }

}

