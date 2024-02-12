package com.ttpkk.assignments.assignment3

import java.sql.Timestamp

data class Transaction (
    val seq: Int,
    val box: String,
    val part: String,
    val timestamp: Timestamp,
    var remark: String?
)