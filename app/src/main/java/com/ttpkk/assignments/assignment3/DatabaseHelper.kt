package com.ttpkk.assignments.assignment3


//class DatabaseHelper(private val context: Context) : DatabaseInterface {
//    override fun fetchData() : ArrayList<Transaction> {
//        var conn: Connection? = null
//        var transactions = ArrayList<Transaction>()
//
//        try {
//            conn = ConnectionClass.openConnection("","","","","","")
//
//            val ps = ConnectionClass.setConnection(conn!!,"SP_HT_GET_TRANSACTION", null)
//            val resultSet = ps.executeQuery()
//
//            var isComplete: Boolean = false
//            var result: String
////            transactions = ArrayList()
//
//            while (resultSet!!.next()) {
//                isComplete = resultSet.getBoolean("IsComplete")
//                result = resultSet.getString("Result")
////                Toast.makeText(this, "$result", Toast.LENGTH_LONG).show()
//            }
//
//            ps.moreResults
//            val resultSet2: ResultSet = ps.resultSet
//            while (resultSet2.next()) {
//                val transaction = Transaction(
//                    resultSet2.getInt("Seq"),
//                    resultSet2.getString("Box"),
//                    resultSet2.getString("Part"),
//                    resultSet2.getTimestamp("Timestamp"),
//                    resultSet2.getString("Remark")
//                )
//                transactions.add(transaction)
//            }
//
//            Log.i("Transaction From DatabaseHelper: ", transactions.toString())
//
//            return transactions
//
////            Log.i("Get item as array:", transactions.toString())
//
//        } catch (e: SQLException) {
//            Log.e("Error SQLException:", e.message.toString())
//            showErrorDialog("Error", e.message.toString())
//        } catch (e: Exception) {
//            Log.e("Error Exception:", e.message.toString())
//        } finally {
//            conn?.close()
//        }
//        return ArrayList()
//    }
//
//    override fun deleteData(seq: Int) : Boolean {
//        var conn: Connection? = null
//        try {
//            conn = ConnectionClass.openConnection("","","","","","")
//            val parameters = ArrayList<ParameterResult>()
//            parameters.add(ParameterResult("Seq",seq))
//
//            val ps = ConnectionClass.setConnection(conn!!,"SP_HT_DELETE_TRANSACTION", parameters)
//
//            val resultSet = ps.executeQuery()
//
//            var isDeleted: Boolean = false
//            var result: String
//
//            while (resultSet!!.next()) {
//                isDeleted = resultSet.getBoolean("IsDeleted")
//                result = resultSet.getString("Result")
////                Toast.makeText(this, "$result", Toast.LENGTH_LONG).show()
//            }
//
//            if (!isDeleted) {
//                return false
//            } else {
//                return true
//            }
//
//        } catch (e: SQLException) {
//            Log.e("Error SQLException:", e.message.toString())
//            showErrorDialog("Error", e.message.toString())
//            return false
//
//        } catch (e: Exception) {
//            Log.e("Error Exception:", e.message.toString())
//            return false
//        } finally {
//            conn?.close()
//        }
//    }
//
//    override fun insertData(box:String, part:String) : Boolean {
//        var conn: Connection? = null
//
//        try {
//            conn = ConnectionClass.openConnection("","","","","","")
//            val parameters = ArrayList<ParameterResult>()
//            parameters.add(ParameterResult("Box",box))
//            parameters.add(ParameterResult("Part",part))
//
//            val ps = ConnectionClass.setConnection(conn!!,"SP_HT_INSERT", parameters)
//
//            val resultSet = ps.executeQuery()
//
//            var isComplete: Boolean = false
//            var result: String
//
//            while (resultSet!!.next()) {
//                isComplete = resultSet.getBoolean("IsComplete")
//                result = resultSet.getString("Result")
////                Toast.makeText(this, "$result", Toast.LENGTH_LONG).show()
//            }
//
//            if (!isComplete) {
//                Toast.makeText(this.context,"Duplicated record",Toast.LENGTH_SHORT).show()
//                return false
//            } else {
//                return true
//            }
//
//        } catch (e: SQLException) {
//            Log.e("Error SQLException:", e.message.toString())
//            showErrorDialog("Error", e.message.toString())
//            return false
//
//        } catch (e: Exception) {
//            Log.e("Error Exception:", e.message.toString())
//            return false
//        } finally {
//            conn?.close()
//        }
//    }
//
//    override fun updateRemark(seq:Int,remark: String): Boolean {
//        var conn: Connection? = null
//
//        try {
//            conn = ConnectionClass.openConnection("","","","","","")
//            val parameters = ArrayList<ParameterResult>()
//            parameters.add(ParameterResult("Seq",seq))
//            parameters.add(ParameterResult("Remark",remark))
//
//            val ps = ConnectionClass.setConnection(conn!!,"SP_HT_UPDATE_REMARK", parameters)
//
//            val resultSet = ps.executeQuery()
//
//            var isUpdated: Boolean = false
//            var result: String
//
//            while (resultSet!!.next()) {
//                isUpdated = resultSet.getBoolean("IsUpdated")
//                result = resultSet.getString("Result")
////                Toast.makeText(this, "$result", Toast.LENGTH_LONG).show()
//            }
//
//            if (!isUpdated) {
//                return false
//            } else {
//                return true
//            }
//
//        } catch (e: SQLException) {
//            Log.e("Error SQLException:", e.message.toString())
//            showErrorDialog("Error", e.message.toString())
//            return false
//
//        } catch (e: Exception) {
//            Log.e("Error Exception:", e.message.toString())
//            return false
//        } finally {
//            conn?.close()
//        }
//
//    }
//
//    private fun showErrorDialog(title: String, message: String) {
//        val alertDialogBuilder = AlertDialog.Builder(context)
//        alertDialogBuilder.setTitle(title)
//        alertDialogBuilder.setMessage(message)
//        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
//            // Dismiss the dialog
//            dialog.dismiss()
//        }
//
//        val alertDialog = alertDialogBuilder.create()
//        alertDialog.show()
//    }
//
//}