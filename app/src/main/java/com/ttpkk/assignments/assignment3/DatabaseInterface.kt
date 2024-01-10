package com.ttpkk.assignments.assignment3

import android.content.Context
import java.sql.ResultSet

interface DatabaseInterface {
    fun fetchData() : ArrayList<Transaction>
    fun deleteData(seq: Int) : Boolean
    fun insertData(box: String, part: String) : Boolean
    fun updateRemark(seq: Int,remark: String) : Boolean
}