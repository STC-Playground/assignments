package com.ttpkk.assignments.assignment3

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException

class DatabaseHelper: DatabaseInterface {

    override fun fetchData() : ArrayList<ScanActivity.Transaction> {
        var conn: Connection? = null
        var transactions = ArrayList<ScanActivity.Transaction>()

        try {
            /* Insert  your connection here */

            val ps = ConnectionClass.setConnection(conn!!,"SP_HT_GET_TRANSACTION", null)

            val resultSet = ps.executeQuery()

            var isComplete: Boolean = false
            var result: String
//            transactions = ArrayList()

            while (resultSet!!.next()) {
                isComplete = resultSet.getBoolean("IsComplete")
                result = resultSet.getString("Result")
//                Toast.makeText(this, "$result", Toast.LENGTH_LONG).show()
            }

            ps.moreResults
            val resultSet2: ResultSet = ps.resultSet
            while (resultSet2.next()) {
                val transaction = ScanActivity.Transaction(
                    resultSet2.getInt("Seq"),
                    resultSet2.getString("Box"),
                    resultSet2.getString("Part"),
                    resultSet2.getTimestamp("Timestamp")
                )
                transactions.add(transaction)
            }

            return transactions

//            Log.i("Get item as array:", transactions.toString())

        } catch (e: SQLException) {
            Log.e("Error SQLException:", e.message.toString())
        } catch (e: Exception) {
            Log.e("Error Exception:", e.message.toString())
        } finally {
            conn?.close()
        }
        return ArrayList()
    }

    override fun deleteData(seq: Int) : Boolean {
        var conn: Connection? = null
        try {
            /* Insert  your connection here */
            val parameters = ArrayList<ParameterResult>()
            parameters.add(ParameterResult("Seq",seq))

            val ps = ConnectionClass.setConnection(conn!!,"SP_HT_DELETE_TRANSACTION", parameters)

            val resultSet = ps.executeQuery()

            var isDeleted: Boolean = false
            var result: String

            while (resultSet!!.next()) {
                isDeleted = resultSet.getBoolean("IsDeleted")
                result = resultSet.getString("Result")
//                Toast.makeText(this, "$result", Toast.LENGTH_LONG).show()
            }

            if (!isDeleted) {
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

    override fun insertData(box:String, part:String) : Boolean {
        var conn: Connection? = null

        try {
            /* Insert  your connection here */
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

}