
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EdgeEffect
import android.widget.EditText
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.ttpkk.assignments.R
import com.ttpkk.assignments.assignment3.DatabaseHelper
import com.ttpkk.assignments.assignment3.DatabaseInterface
import com.ttpkk.assignments.assignment3.Transaction
import com.ttpkk.assignments.assignment3.UpdateDialogFragment
import java.sql.Timestamp
import java.text.SimpleDateFormat

class ItemAdapter(private val context: Context, private var itemList: ArrayList<Transaction>):
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

        inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), OnClickListener, PopupMenu.OnMenuItemClickListener {

            var tvBox: TextView
            var tvPart: TextView
            var tvTimestamp: TextView
            var btnMore : ImageButton
            var databaseHelper: DatabaseInterface
            var tvRemark: TextView
            init {
                tvBox = itemView.findViewById(R.id.tv_box)
                tvPart = itemView.findViewById(R.id.tv_part)
                tvTimestamp = itemView.findViewById(R.id.tv_timestamp)
                btnMore = itemView.findViewById(R.id.btn_more)
                tvRemark = itemView.findViewById(R.id.tv_remark)
                databaseHelper = DatabaseHelper(context)
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

                        val deleteDialogBuilder = AlertDialog.Builder(context)

                        deleteDialogBuilder.setTitle("Confirm")
                        deleteDialogBuilder.setMessage("Do you want to delete?")
                        deleteDialogBuilder.setPositiveButton("OK") {dialog,_ ->
                            databaseHelper.deleteData(itemList[adapterPosition].seq)
//                            databaseHelper.fetchData()
                            Log.d("Delete", "Deleted " + itemList.toString())
                            itemList.remove(itemList[adapterPosition])
                            notifyItemRemoved(adapterPosition)
                            dialog.dismiss()
                        }

                        deleteDialogBuilder.setNegativeButton("Cancel") {dialog,_ ->
                            dialog.dismiss()
                        }

                        val deleteDialog = deleteDialogBuilder.create()
                        deleteDialog.show()
                        true
                    }
                    R.id.menu_remark -> {
//                        val updateDialogFragment = UpdateDialogFragment(adapterPosition,itemList)
//                        updateDialogFragment.show((context as AppCompatActivity).supportFragmentManager, "updateRemark")
//                        updateDialogFragment.parentFragment.toString()
//                        itemList = databaseHelper.fetchData()

                        val dialog = Dialog(context)
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                        dialog.setCancelable(false)
                        dialog.setContentView(R.layout.fragment_update_dialog)
                        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                        val btnOK = dialog.findViewById<Button>(R.id.btn_ok_update)
                        val btnCancel = dialog.findViewById<Button>(R.id.btn_cancel_update)
                        val edtRemark = dialog.findViewById<EditText>(R.id.edt_remark)
                        val seq = itemList[adapterPosition].seq

//                        Log.d("Remark Update Message", itemList[adapterPosition].seq.toString())

                        btnOK.setOnClickListener {
                            val remarkTxt = "Remark: " + edtRemark.text.toString()
//                            Log.d("Remark Update Message", remarkTxt)
                            if (databaseHelper.updateRemark(seq,remarkTxt)) {
                                Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show()
                                itemList = databaseHelper.fetchData()
                                notifyDataSetChanged()
                            }

                            hideSoftKeyboard(it)
                            dialog.dismiss()

                        }

                        btnCancel.setOnClickListener{
                            hideSoftKeyboard(it)
                            dialog.dismiss()
                        }
                        dialog.show()


//                        Log.d("Remark Menu", itemList[adapterPosition].remark.toString())
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
        holder.tvRemark.text = item.remark

//        Log.d("Remark Holder", itemList[position].remark.toString())
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    private fun convertTime(time: Timestamp) : String {
        var dateFormat = SimpleDateFormat("dd/MM/yyyy\nHH:mm:ss")
        return dateFormat.format(time)
    }

    private fun hideSoftKeyboard(view: View) {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


}

