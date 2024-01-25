package com.ttpkk.assignments.assignment4

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

class Shared {
    companion object {
        fun hideKeyboard(activity: Activity) {
            val view = activity.currentFocus
            val methodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if (view != null) {
                methodManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }


    }
}