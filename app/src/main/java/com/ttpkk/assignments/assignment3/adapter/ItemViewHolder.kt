//package com.ttpkk.assignments.assignment3.adapter
//
//import android.view.View
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.ttpkk.assignments.R
//
//import java.lang.ref.WeakReference
//
//
//class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//
//    val view = WeakReference(itemView)
//
//    var tvBox: TextView? = null
//    var tvPart: TextView? = null
//    var tvTimestamp: TextView? = null
//
//    init {
//        view.get()?.let {
//            tvBox = it.findViewById(R.id.tv_box)
//            tvPart = it.findViewById(R.id.tv_part)
//            tvTimestamp = it.findViewById(R.id.tv_timestamp)
//        }
//    }
//
//
//}