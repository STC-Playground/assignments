package com.ttpkk.assignments.assignment3

import ItemAdapter
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ttpkk.assignments.R
import com.ttpkk.assignments.databinding.FragmentUpdateDialogBinding


class UpdateDialogFragment(private val pos:Int, private var transactions: ArrayList<Transaction>) : DialogFragment(), View.OnClickListener {

    private lateinit var binding: FragmentUpdateDialogBinding
    private lateinit var databaseHelper: DatabaseInterface
//    private lateinit var edtRemark: EditText
    private lateinit var adapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUpdateDialogBinding.inflate(layoutInflater)
        databaseHelper = DatabaseHelper(requireContext())
        return binding.root
//        return inflater.inflate(R.layout.fragment_update_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.edtRemark.requestFocus()
//        binding.btnOkUpdate.setOnClickListener(this)
//        binding.btnCancelUpdate.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
//        when (v?.id) {
//            R.id.btn_cancel_update -> {
//                hideSoftKeyboard()
//                dismiss()
//            }
//            R.id.btn_ok_update -> {
//                val remarkTxt = "Remark: " + binding.edtRemark.text.toString()
////                Log.d("Update Dialog", edtRemark+ transaction.toString())
//
//                if (databaseHelper.updateRemark(transactions[pos].seq, remarkTxt)) {
////                    Log.d("Remark ", edtRemark)
//                    transactions = databaseHelper.fetchData()
//                    Log.d("Update transaction", transactions[pos].toString())
//                    val alertDialogBuilder = AlertDialog.Builder(requireContext())
//                    alertDialogBuilder.setTitle("Updated")
//                    alertDialogBuilder.setPositiveButton("OK") {dialog,_ ->
//                        Log.d("OK", transactions[pos].remark.toString())
//                    }
//
//                    val alertDialog = alertDialogBuilder.create()
//                    alertDialog.show()
//                }
//
//                hideSoftKeyboard()
//                Toast.makeText(requireContext(), "Updated", Toast.LENGTH_SHORT).show()
//                dismiss()
//
//            }
//        }
    }

    private fun hideSoftKeyboard() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }




}