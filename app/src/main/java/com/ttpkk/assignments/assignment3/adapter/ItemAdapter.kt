
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.util.Log
import com.ttpkk.assignments.assignment3.ScanActivity

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.MenuItem.OnMenuItemClickListener
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ttpkk.assignments.R
import com.ttpkk.assignments.assignment3.ConnectionClass
import com.ttpkk.assignments.assignment3.DatabaseHelper
import com.ttpkk.assignments.assignment3.DatabaseInterface
import com.ttpkk.assignments.assignment3.ParameterResult
import java.lang.ref.WeakReference
import java.sql.Connection
import java.sql.SQLException
import java.sql.Timestamp
import java.text.SimpleDateFormat

class ItemAdapter(private val context: Context, private val itemList : ArrayList<ScanActivity.Transaction>):
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

//        private lateinit var listener: onItemClickListener
//
//        interface onItemClickListener {
//            fun onItemClick(pos: Int)
//        }
//
//    fun setOnItemClickListener(l: onItemClickListener) {
//        listener = l
//    }

        inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), OnClickListener, PopupMenu.OnMenuItemClickListener {

            var tvBox: TextView
            var tvPart: TextView
            var tvTimestamp: TextView
            var btnMore : ImageButton
            var databaseHelper: DatabaseInterface

            init {
                tvBox = itemView.findViewById(R.id.tv_box)
                tvPart = itemView.findViewById(R.id.tv_part)
                tvTimestamp = itemView.findViewById(R.id.tv_timestamp)
                btnMore = itemView.findViewById(R.id.btn_more)
                databaseHelper = DatabaseHelper()
                btnMore.setOnClickListener(this)
            }

            private fun showPopupMenus(v: View) {
                val popupMenu = PopupMenu(v.context,v)
                popupMenu.inflate(R.menu.my_menu)
                popupMenu.setOnMenuItemClickListener(this)
                popupMenu.show()
            }

            override fun onClick(v: View) {
                showPopupMenus(v)
            }

            override fun onMenuItemClick(item: MenuItem): Boolean {
                return when(item.itemId) {
                    R.id.menu_delete -> {
                        Log.d("Delete", "Deleted " + itemList[adapterPosition].seq)

//                        databaseHelper.deleteData(itemList[adapterPosition].seq)
//                        databaseHelper.fetchData()



                        val deleteDialogBuilder = AlertDialog.Builder(context)

                        deleteDialogBuilder.setTitle("Deleted")
                        deleteDialogBuilder.setCancelable(false)
                        deleteDialogBuilder.setPositiveButton("OK") {dialog,_ ->
                            dialog.dismiss()

                            databaseHelper.deleteData(itemList[adapterPosition].seq)
//                            databaseHelper.fetchData()
                            Log.d("Delete", "Deleted " + itemList.toString())
                            itemList.remove(itemList[adapterPosition])
                            notifyItemRemoved(adapterPosition)
                        }

                        val deleteDialog = deleteDialogBuilder.create()
                        deleteDialog.show()
                        true
                    }

                    else -> false
                }

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

