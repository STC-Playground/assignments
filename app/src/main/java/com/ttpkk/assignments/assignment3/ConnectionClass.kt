package com.ttpkk.assignments.assignment3

import android.os.StrictMode
import android.util.Log
import java.lang.StringBuilder
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.SQLException

class ConnectionClass {
    companion object {
        fun openConnection(server: String, port: String, database: String, username: String,  password: String, timeout: String) : Connection? {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            val conn : Connection?

            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver")
                val connectionURL = "jdbc:jtds:sqlserver://$server:$port;databaseName=$database;loginTimeout=$timeout;socketTimeout=$timeout"
                conn = DriverManager.getConnection(connectionURL, username,password)
            } catch (ex: SQLException) {
                Log.e("error SQL exception: ", ex.message.toString())
                throw ex
            } catch (ex: ClassNotFoundException) {
                Log.e("error class not found exception:", ex.message.toString())
                throw ex
            } catch (ex: Exception) {
                Log.e("error exception:", ex.message.toString())
                throw ex
            }

            return conn
        }

        fun setConnection(conn: Connection, column: String, parameters: ArrayList<ParameterResult>?) : PreparedStatement {
            val sql = StringBuilder()
            sql.append("EXEC $column")

            parameters?.forEachIndexed { index, parameterResult ->
                sql.append(" @${parameterResult.col} = ?")
                if (index != (parameters.size - 1)) {
                    sql.append(",")
                }
            }

            val ps = conn.prepareStatement(sql.toString())

            ps?.setEscapeProcessing(true)
            ps?.queryTimeout = 10

            parameters?.forEachIndexed { index, parameterResult ->
                val i  = index+1
                ps.setString(i, parameterResult.value.toString())
            }

            return ps
        }
    }
}

data class ParameterResult (
    val col: String,
    val value: Any
)


