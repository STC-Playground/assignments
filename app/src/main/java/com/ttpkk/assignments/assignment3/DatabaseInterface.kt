package com.ttpkk.assignments.assignment3

import java.sql.ResultSet

interface DatabaseInterface {
    fun fetchData() : ArrayList<ScanActivity.Transaction>

    fun deleteData(seq: Int) : Boolean

    fun insertData(box: String, part: String) : Boolean
}