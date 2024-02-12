//package com.ttpkk.assignments.assignment3
//
//import ItemAdapter
//import android.os.Bundle
//import android.util.Log
//import android.view.KeyEvent
//import android.view.View
//import android.view.WindowManager
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.ttpkk.assignments.R
//import com.ttpkk.assignments.databinding.ActivityScanBinding
//
//class ScanActivity : AppCompatActivity(), View.OnKeyListener {
//
//    private lateinit var binding: ActivityScanBinding
////    private lateinit var adapter: ItemAdapter
////    private lateinit var recyclerView: RecyclerView
//    private lateinit var databaseHelper: DatabaseInterface
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityScanBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.edtBox.post {
//            binding.edtBox.requestFocus()
//            window.setSoftInputMode((WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE))
//        }
//
////        databaseHelper = DatabaseHelper(this)
//
//        initEvent()
//        showData()
//
//    }
//
//    private fun initEvent() {
//        binding.edtBox.setOnKeyListener(this)
//        binding.edtPart.setOnKeyListener(this)
//
//    }
//
//    private fun clearEdt() {
//        binding.edtBox.setText("")
//        binding.edtPart.setText("")
//        binding.edtBox.requestFocus()
//    }
//
//    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
//        when (v?.id) {
//            R.id.edt_box -> {
//                if (keyCode == KeyEvent.KEYCODE_ENTER && event?.action == KeyEvent.ACTION_UP) {
//                    val box = binding.edtBox.text.toString()
//                    when {
//                        box.isEmpty() -> {
//                            binding.edtBox.error = "Please box scan barcode"
//                            binding.edtBox.requestFocus()
//                            return true
//                        }
//                        else -> binding.edtPart.requestFocus()
//                    }
//                }
//            }
//            R.id.edt_part -> {
//                if (keyCode == KeyEvent.KEYCODE_ENTER && event?.action == KeyEvent.ACTION_UP) {
//                    val box = binding.edtBox.text.toString()
//                    val part = binding.edtPart.text.toString()
//                    when {
//                        box.isEmpty() -> {
//                            binding.edtBox.error = "Please box scan barcode"
//                            binding.edtBox.requestFocus()
//                            return true
//                        }
//                        part.isEmpty() -> {
//                            binding.edtPart.error = "Please part scan barcode"
//                            binding.edtPart.requestFocus()
//                            return true
//                        }
//                        else -> {
//                            if (databaseHelper.insertData(box,part)) {
//                                Toast.makeText(this, "Recorded", Toast.LENGTH_SHORT).show()
//                                showData()
//                            }
//                            clearEdt()
//                            return true
//                        }
//                    }
//                }
//            }
//        }
//        return false
//    }
//
//    private fun showData() {
//        val transactions = databaseHelper.fetchData()
//        Log.d("Transaction: ", transactions.toString());
//        val adapter = ItemAdapter(this,transactions)
//        binding.rvItem.isEnabled = true
//        binding.rvItem.adapter = adapter
//        binding.rvItem.layoutManager = LinearLayoutManager(applicationContext)
//    }
//
//}
//
