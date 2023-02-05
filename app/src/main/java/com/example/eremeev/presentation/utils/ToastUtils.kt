package com.example.eremeev.presentation.utils

import android.app.Activity
import android.widget.Toast

fun Activity.toast(text: Int) {
    Toast.makeText(applicationContext, getText(text), Toast.LENGTH_SHORT).show()
}