package com.sgssb.instacard.ext

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.isValid(): Boolean {
    if (this.editText!!.text.isEmpty()) {
        this.error = "Field cannot be empty"
    }
    return this.editText!!.text.isEmpty()
}

fun EditText.isValid(): Boolean {
    if (this.text.isEmpty()) {
        this.error = "Field cannot be empty"
    }
    return this.text.isEmpty()
}