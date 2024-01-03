package com.ttpkk.assignments.assignment3

import ItemAdapter
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ttpkk.assignments.R
import com.ttpkk.assignments.databinding.ActivityScanBinding
import java.sql.Timestamp

class ScanActivity : AppCompatActivity(), View.OnKeyListener {

    private lateinit var binding: ActivityScanBinding
    private lateinit var adapter: ItemAdapter
    private lateinit var recyclerView: RecyclerView

    private val databaseHelper: DatabaseInterface = DatabaseHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.rvItem.setHasFixedSize(true)

        binding.edtBox.post {
            binding.edtBox.requestFocus()
            window.setSoftInputMode((WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE))
        }

        initEvent()
        showData()

    }

    private fun initEvent() {
        binding.edtBox.setOnKeyListener(this)
        binding.edtPart.setOnKeyListener(this)

    }



    private fun setCustomDialogBox() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.error_dialog)
//        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btnOK: Button = dialog.findViewById(R.id.btn_ok)

        btnOK.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun clearEdt() {
        binding.edtBox.setText("")
        binding.edtPart.setText("")
        binding.edtBox.requestFocus()
    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        when (v?.id) {
            R.id.edt_box -> {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event?.action == KeyEvent.ACTION_UP) {
                    val box = binding.edtBox.text.toString()
                    when {
                        box.isEmpty() -> {
                            binding.edtBox.error = "Please box scan barcode"
                            binding.edtBox.requestFocus()
                            return true
                        }
                        else -> binding.edtPart.requestFocus()
                    }
                }
            }
            R.id.edt_part -> {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event?.action == KeyEvent.ACTION_UP) {
                    val box = binding.edtBox.text.toString()
                    val part = binding.edtPart.text.toString()
                    when {
                        box.isEmpty() -> {
                            binding.edtBox.error = "Please box scan barcode"
                            binding.edtBox.requestFocus()
                            return true
                        }
                        part.isEmpty() -> {
                            binding.edtPart.error = "Please part scan barcode"
                            binding.edtPart.requestFocus()
                            return true
                        }
                        else -> {
                            if (databaseHelper.insertData(box,part)) {
                                Toast.makeText(this, "Recorded", Toast.LENGTH_SHORT).show()
                                showData()
                            }
                            clearEdt()
                            return true
                        }
                    }
                }
            }
        }

        return false
    }

    private fun showData() {
        val transactions = databaseHelper.fetchData()
        val adapter = ItemAdapter(this,transactions)
        binding.rvItem.isEnabled = true
        binding.rvItem.adapter = adapter
        binding.rvItem.layoutManager = LinearLayoutManager(applicationContext)
    }

    data class Transaction (
        val seq: Int,
        val box: String,
        val part: String,
        val timestamp: Timestamp
    )

}

