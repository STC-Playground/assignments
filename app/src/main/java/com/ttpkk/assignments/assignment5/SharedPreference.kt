package com.ttpkk.assignments.assignment5

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.ttpkk.assignments.R

class SharedPreference {

    companion object {
        fun isValidIPAddress(ipAddress: String) : Boolean{
            val ipRegex = """\b(?:(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\.){3}(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\b""".toRegex()
            return ipRegex.matches(ipAddress)
        }

        fun isValidPort(port: String) : Boolean {
            val portRegex  ="""^\d{2,5}${'$'}""".toRegex()
            return portRegex.matches(port)
        }

        fun hideKeyboard(activity: Activity) {
            val view = activity.currentFocus
            val methodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            assert(view != null)
            if (view != null) {
                methodManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }

        fun setErrorDialog(context: Context,str: String): AlertDialog {
            val layoutInflater = LayoutInflater.from(context)
            val alertView: View = layoutInflater.inflate(R.layout.dialog_error, null)
            val builder = AlertDialog.Builder(context)
            builder.setView(alertView)

            val errMsg = alertView.findViewById<View>(R.id.tv_error) as TextView
            if (str.isNotEmpty()) errMsg.text = str

            builder.setPositiveButton("OK") { dialog,_ ->
                dialog.dismiss()
            }
            val alertDialog = builder.create()
            alertDialog.show()
            return alertDialog
        }

        fun setSuccessDialog(context: Context, str: String) : AlertDialog {
            val layoutInflater = LayoutInflater.from(context)
            val alertView: View = layoutInflater.inflate(R.layout.dialog_success, null)
            val builder = AlertDialog.Builder(context)
            builder.setView(alertView)

            val errMsg = alertView.findViewById<View>(R.id.tv_error) as TextView
            if (str.isNotEmpty()) errMsg.text = str

            builder.setPositiveButton("OK") { dialog,_ ->
                dialog.dismiss()
            }

            val alertDialog = builder.create()
            alertDialog.show()
            return alertDialog
        }

        fun setLoadingDialog(context: Context) : AlertDialog{
            val layoutInflater = LayoutInflater.from(context)
            val alertView: View = layoutInflater.inflate(R.layout.dialog_loading, null)
            val builder = AlertDialog.Builder(context)
            builder.setView(alertView)
            builder.setCancelable(false)

            val alertDialog = builder.create()
            alertDialog.show()
            return alertDialog
        }

        fun clearFocusFromEditText(context: Context,editText: EditText) {
            editText.clearFocus()
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(editText.windowToken, 0)
        }


    }
}