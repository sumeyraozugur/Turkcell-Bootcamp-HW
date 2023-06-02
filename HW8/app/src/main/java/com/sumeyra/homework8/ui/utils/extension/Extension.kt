package com.sumeyra.homework8.ui.utils.extension

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.sumeyra.homework8.R


fun Navigation.sent(v: View, id: Int) = findNavController(v).navigate(id)

fun View.showErrorSnackBar(message: String, errorMessage: Boolean) {
    val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    val snackBarView = snackBar.view

    if (errorMessage) {
        snackBarView.setBackgroundColor(
            ContextCompat.getColor(
                context,
                R.color.color_snack_bar_error
            )
        )
    } else {
        snackBarView.setBackgroundColor(
            ContextCompat.getColor(
                context,
                R.color.color_snack_bar_success
            )
        )
    }
    snackBar.show()
}

fun EditText.trimmedText(): String {
    return this.text.toString().trim()
}

fun Context.showToast(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
