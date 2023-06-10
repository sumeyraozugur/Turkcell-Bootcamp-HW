package com.sumeyra.tripapp.utils.extension

import android.util.Patterns
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.sumeyra.tripapp.R

fun View.showErrorSnackBar(message: String, errorMessage: Boolean) {
    val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    val snackBarView = snackBar.view

    if (errorMessage) {
        snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.color_snack_bar_error))
    } else {
        snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.color_snack_bar_success))
    }
    snackBar.show()
}

fun Navigation.sent(v: View, id: Int) {
    findNavController(v).navigate(id)
}

fun EditText.isValidEmail(errorString: String): Boolean {
    val textInputLayout = this.parent.parent as TextInputLayout
    return if (Patterns.EMAIL_ADDRESS.matcher(text.toString()).matches()) {
        textInputLayout.isErrorEnabled = false
        true
    } else {
        textInputLayout.error = errorString
        false
    }
}

fun EditText.isNullorEmpty(errorString: String): Boolean {
    val textInputLayout = this.parent.parent as TextInputLayout
    return if (text.toString().trim().isNotEmpty()) {
        textInputLayout.isErrorEnabled = false
        true
    } else {
        textInputLayout.error = errorString
        false
    }
}
