package com.ttpkk.assignments.assignment5

import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.lifecycleScope
import com.ttpkk.assignments.R
import com.ttpkk.assignments.assignment5.SharedPreference.Companion.hideKeyboard
import com.ttpkk.assignments.assignment5.SharedPreference.Companion.isValidIPAddress
import com.ttpkk.assignments.assignment5.SharedPreference.Companion.isValidPort
import com.ttpkk.assignments.databinding.ActivitySocketBinding
import kotlinx.coroutines.launch


class SocketActivity : AppCompatActivity(), View.OnKeyListener, View.OnClickListener, View.OnFocusChangeListener, View.OnTouchListener {

    private lateinit var binding: ActivitySocketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySocketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.edtAddress.requestFocus()

        binding.edtAddress.setOnKeyListener(this)
        binding.edtPort.setOnKeyListener(this)
        binding.edtScanBarcode.setOnKeyListener(this)

        binding.edtAddress.onFocusChangeListener = this
        binding.edtPort.onFocusChangeListener = this
        binding.edtScanBarcode.onFocusChangeListener = this

        binding.btnPrint.setOnClickListener(this)

    }

    private fun validateInput(v: View?) {
        when (v?.id) {
            R.id.edt_address -> {
                val address = binding.edtAddress.text.toString()
                when {
                    address.isEmpty() -> {
                        binding.edtAddress.error = "Please input IP address"
                    }
                    !isValidIPAddress(address) -> {
                        binding.edtAddress.error = "Invalid IP address"
                        binding.edtAddress.selectAll()
                    }
                    else -> {
                        binding.edtAddress.error = null
                    }
                }
            }
            R.id.edt_port -> {
                if (binding.edtPort.text?.isEmpty() == true) {
                    binding.edtPort.error = "Please input port"
                }
                else {
                    binding.edtPort.error = null
                }
            }
            R.id.edt_scan_barcode -> {
                if (binding.edtScanBarcode.text.isEmpty()) {
                    binding.edtScanBarcode.error = "Please input scan barcode"
                } else {
                    binding.edtScanBarcode.error = null
                }
            }
        }
    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        val address = binding.edtAddress.text.toString()
        val port = binding.edtPort.text.toString()
        val scanBarcode = binding.edtScanBarcode.text.toString()
        when (v?.id) {
            R.id.edt_address -> {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event?.action == KeyEvent.ACTION_UP) {
                    when {
                        address.isEmpty() -> {
                            if (isValidPort(port)) {
                                binding.edtPort.error = null
                                binding.edtPort.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,
                                    AppCompatResources.getDrawable(this,R.drawable.ic_baseline_check_circle_24),null)
                            }
                            if (scanBarcode.isNotEmpty()) {
                                binding.edtScanBarcode.error = null
                                binding.edtScanBarcode.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,
                                    AppCompatResources.getDrawable(this,R.drawable.ic_baseline_check_circle_24),null)
                            }
                            binding.edtAddress.error = "Please input IP address"
                            binding.edtAddress.requestFocus()
                            return true
                        }
                        !(isValidIPAddress(address)) -> {
                            binding.edtAddress.error = "Invalid IP Address"
                            binding.edtAddress.requestFocus()
                            return true
                        }
                        isValidIPAddress(address) -> {
                            binding.edtAddress.clearFocus()
                            binding.edtAddress.error = null
                            binding.edtAddress.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,
                                AppCompatResources.getDrawable(this,R.drawable.ic_baseline_check_circle_24),null)
                            binding.edtPort.requestFocus()
                            return true
                        }
                        port.isNotEmpty() -> binding.edtPort.error = null
                        scanBarcode.isNotEmpty() -> binding.edtScanBarcode.error = null
                    }

                }
            }
            R.id.edt_port -> {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event?.action == KeyEvent.ACTION_DOWN) {
                    when {
                        address.isEmpty() -> {
                            binding.edtAddress.error = "Please input IP address"
                            binding.edtAddress.requestFocus()
                            return true
                        }
                        port.isEmpty() -> {
                            binding.edtPort.error = "Please input port"
                            binding.edtPort.requestFocus()
                            return true
                        }
                        port.isNotEmpty() && !(isValidPort(port)) -> {
                            if (isValidIPAddress(address)) {
                                binding.edtAddress.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,
                                    AppCompatResources.getDrawable(this,R.drawable.ic_baseline_check_circle_24),null)
                            }
                            binding.edtPort.error = "Invalid port"
                            binding.edtPort.requestFocus()
                        }
                        port.isNotEmpty() && isValidPort(port)  -> {
                            if (isValidIPAddress(address)) {
                                binding.edtAddress.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,
                                    AppCompatResources.getDrawable(this,R.drawable.ic_baseline_check_circle_24),null)
                            }
                            if (isValidPort(port)) {
                                binding.edtPort.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,
                                    AppCompatResources.getDrawable(this,R.drawable.ic_baseline_check_circle_24),null)
                            }
                            binding.edtPort.error = null
                            binding.edtScanBarcode.requestFocus()
                            return true
                        }
                    }
                }
            }
            R.id.edt_scan_barcode -> {
                hideKeyboard(this)
                if (keyCode == KeyEvent.KEYCODE_ENTER && event?.action == KeyEvent.ACTION_DOWN ) {
                    when {
                        address.isEmpty() -> {
                            binding.edtAddress.error = "Please input IP address"
                            binding.edtAddress.requestFocus()
                            return true
                        }
                        port.isEmpty() -> {
                            binding.edtPort.error = "Please input port"
                            binding.edtPort.requestFocus()
                            return true
                        }
                        scanBarcode.isEmpty() -> {
                            if (isValidIPAddress(address)) {
                                binding.edtAddress.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,
                                    AppCompatResources.getDrawable(this,R.drawable.ic_baseline_check_circle_24),null)
                            }
                            if (isValidPort(port)) {
                                binding.edtPort.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,
                                    AppCompatResources.getDrawable(this,R.drawable.ic_baseline_check_circle_24),null)
                            }
                            binding.edtScanBarcode.error = "Please input barcode"
                            binding.edtScanBarcode.requestFocus()
                            return true
                        }
                        scanBarcode.isNotEmpty() -> {
//                            hideKeyboard(this)
                            if (isValidIPAddress(address)) {
                                binding.edtAddress.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,
                                    AppCompatResources.getDrawable(this,R.drawable.ic_baseline_check_circle_24),null)
                            }
                            if (isValidPort(port)) {
                                binding.edtPort.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,
                                    AppCompatResources.getDrawable(this,R.drawable.ic_baseline_check_circle_24),null)
                            }
                            binding.edtAddress.error = null
                            binding.edtAddress.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,
                                AppCompatResources.getDrawable(this,R.drawable.ic_baseline_check_circle_24),null)
//                            binding.edtScanBarcode.onFocusChangeListener = this
                            return true
                        }

                    }
                }
            }
        }
        return false
    }

    override fun onClick(v: View?) {
        val address = binding.edtAddress.text.toString()
        val port = binding.edtPort.text.toString()
        val scanBarcode = binding.edtScanBarcode.text.toString()

        if (address.isEmpty() || port.isEmpty() || scanBarcode.isEmpty()) {
            SharedPreference.setErrorDialog(this, "Unable to print")
            binding.edtAddress.requestFocus()
            return
        }


        val format = "\u001BA\n" +
                "\u001BA3V+00000H+0000\u001BCS4\u001B#F5\u001BA1V1960H0780" +
                "\u001BZ\n" +
                "\u001BA\u001BPS\n"  +
                "\u001B%0\u001BH0260\u001BV00380\u001B2D30,L,05,1,0\u001BQV07\u001BDN00${scanBarcode.length},^$1\n" +
                "\u001BQ1\n" +
                "\u001BZ"

        val printFormat = format.replace("^$1",scanBarcode)

        val printerSocket = PrinterSocket(address, port.toInt())

        lifecycleScope.launch {
            printerSocket.checkPrinterConnection(this@SocketActivity) { isConnected ->
                if (isConnected) {
                    printerSocket.sendPrintCommand(printFormat) { isSuccess ->
                        if (isSuccess) {
                            SharedPreference.setSuccessDialog(this@SocketActivity, "Printing")
                            binding.edtScanBarcode.error=null
                            binding.edtScanBarcode.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,
                                AppCompatResources.getDrawable(this@SocketActivity,R.drawable.ic_baseline_close_24),null)
                            binding.edtScanBarcode.requestFocus()
                            binding.edtScanBarcode.selectAll()
                        } else {
                            SharedPreference.setErrorDialog(this@SocketActivity, "Unable to print")
                            binding.edtScanBarcode.requestFocus()
                            binding.edtScanBarcode.selectAll()
                        }
                        binding.edtAddress.error = null
                        binding.edtPort.error = null
                        binding.edtScanBarcode.error = null
                    }

                } else {
                    SharedPreference.setErrorDialog(this@SocketActivity, "Unable to print")
                    binding.edtAddress.setText("")
                    binding.edtPort.setText("")
                    binding.edtScanBarcode.setText("")

                    binding.edtAddress.error = null
                    binding.edtPort.error = null
                    binding.edtScanBarcode.error = null

                    binding.edtAddress.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,null,null)
                    binding.edtPort.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,null,null)
                    binding.edtScanBarcode.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,null,null)

                    binding.edtAddress.requestFocus()

                }
            }

        }

    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (!hasFocus) {
            validateInput(v)
        }
        if (hasFocus && v is EditText) {
            val editText = v
            if (editText.error == null && editText.text.isNotEmpty()) {
                editText.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,AppCompatResources.getDrawable(this,R.drawable.ic_baseline_close_24),null)
            }

            editText.setOnTouchListener(this)
        }
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (v is EditText && event != null && event.action == MotionEvent.ACTION_UP) {
            val editText = v
            val compoundDrawableEnd: Drawable? = editText.compoundDrawablesRelative[2]
            compoundDrawableEnd?.let {
                val bounds: Rect = it.bounds
                if (event.rawX >= (editText.right - bounds.width())) {
                    editText.setText("")
                    editText.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,null,null)
                    return true
                }
            }
        }
        return false
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        Log.d("Key Pressed", event?.keyCode.toString())
        return super.dispatchKeyEvent(event)
    }
}