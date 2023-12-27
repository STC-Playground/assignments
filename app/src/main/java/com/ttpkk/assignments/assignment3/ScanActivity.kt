package com.ttpkk.assignments.assignment3

import ItemAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ttpkk.assignments.R
import com.ttpkk.assignments.databinding.ActivityScanBinding
import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Timestamp

class ScanActivity : AppCompatActivity(), View.OnKeyListener {

    private lateinit var binding: ActivityScanBinding
    private lateinit var adapter: ItemAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvItem.setHasFixedSize(true)

        initEvent()

        readData()

    }

    private fun initEvent() {
        binding.edtBox.setOnKeyListener(this)
        binding.edtPart.setOnKeyListener(this)

    }


    private fun insertData(box: String, part: String) : Boolean {
        var conn: Connection? = null

        try {
            conn = ConnectionClass.openConnection("192.168.0.148","1433","Testy","sa","12345","15")
            val parameters = ArrayList<ParameterResult>()
            parameters.add(ParameterResult("Box",box))
            parameters.add(ParameterResult("Part",part))

//            val query = "EXEC SP_HT_INSERT @Box = ?, @Part = ?"
//            val ps = conn?.prepareStatement(query)
//            ps?.setEscapeProcessing(true)
//            ps?.queryTimeout = 10
//            ps?.setString(1,box)
//            ps?.setString(2,part)

            val ps = ConnectionClass.setConnection(conn!!,"SP_HT_INSERT", parameters)

            val resultSet = ps.executeQuery()

            var isComplete: Boolean = false
            var result: String

            while (resultSet!!.next()) {
                isComplete = resultSet.getBoolean("IsComplete")
                result = resultSet.getString("Result")
//                Toast.makeText(this, "$result", Toast.LENGTH_LONG).show()
            }

            if (!isComplete) {
                return false
            } else {
                return true
            }

        } catch (e: SQLException) {
            Log.e("Error SQLException:", e.message.toString())
            return false

        } catch (e: Exception) {
            Log.e("Error Exception:", e.message.toString())
            return false
        } finally {
            conn?.close()
        }

    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        when (v?.id) {
            R.id.edt_box -> {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event?.action == KeyEvent.ACTION_DOWN) {
                    val box = binding.edtBox.text.toString()
                    when {
                        box.isEmpty() -> {
                            binding.edtBox.error = "Please box scan barcode"
                            binding.edtBox.requestFocus()
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
                        }
                        part.isEmpty() -> {
                            binding.edtPart.error = "Please part scan barcode"
                            binding.edtPart.requestFocus()
                        }
                        else -> {
//                            Toast.makeText(this, "Box: $box / Part: $part", Toast.LENGTH_LONG).show()
                            if (insertData(box,part)) {
                                readData()
                            }
                        }
                    }
                }
            }
        }

        return false
    }

    private fun readData() {
        var conn: Connection? = null

        try {
            conn = ConnectionClass.openConnection("192.168.0.148","1433","Testy","sa","12345","15")

            val ps = ConnectionClass.setConnection(conn!!,"SP_HT_GET_TRANSACTION", null)

            val resultSet = ps.executeQuery()

            var isComplete: Boolean = false
            var result: String
            var transactions = ArrayList<Transaction>()

            while (resultSet!!.next()) {
                isComplete = resultSet.getBoolean("IsComplete")
                result = resultSet.getString("Result")
//                Toast.makeText(this, "$result", Toast.LENGTH_LONG).show()
            }

            ps.moreResults
            val resultSet2: ResultSet = ps.resultSet
            while (resultSet2.next()) {
                val transaction = Transaction(
                resultSet2.getInt("Seq"),
                resultSet2.getString("Box"),
                resultSet2.getString("Part"),
                resultSet2.getTimestamp("Timestamp"))
                transactions.add(transaction)
            }

            val adapter = ItemAdapter(transactions)
            binding.rvItem.adapter = adapter
            binding.rvItem.layoutManager = LinearLayoutManager(this)
            Log.i("Get item as array:", transactions.toString())


        } catch (e: SQLException) {
            Log.e("Error SQLException:", e.message.toString())
        } catch (e: Exception) {
            Log.e("Error Exception:", e.message.toString())
        } finally {
            conn?.close()
        }
    }
    data class Transaction (
        val seq: Int,
        val box: String,
        val part: String,
        val timestamp: Timestamp
    )
}

