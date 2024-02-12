package com.ttpkk.assignments.assignment5

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.InetSocketAddress
import java.net.Socket

class PrinterSocket(private val ipAddress: String, private val port: Int) {
    fun checkPrinterConnection(lifecycleOwner: LifecycleOwner, callback: (Boolean) -> Unit) {
        lifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val isConnected = try {
                val socket = Socket()
                socket.connect(InetSocketAddress(ipAddress, port), 2000) // Timeout set to 2 seconds
                socket.close()
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
            withContext(Dispatchers.Main) {
                callback(isConnected)
            }
        }
    }

    fun sendPrintCommand(printData: String, callback: (Boolean) -> Unit) {
        Thread {
            var socket: Socket? = null
            try {
                socket = Socket()
                socket.connect(InetSocketAddress(ipAddress, port), 2000) // Timeout set to 2 seconds
                val outputStream = socket.getOutputStream()
                outputStream.write(printData.toByteArray())
                Handler(Looper.getMainLooper()).post {
                    callback(true)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Handler(Looper.getMainLooper()).post {
                    callback(false)
                }
            } finally {
                socket?.close()
            }
        }.start()
    }

}