package com.technosales.vehiclechallanmobile.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.google.android.material.card.MaterialCardView
import com.technosales.vehiclechallanmobile.R

fun Activity.displayLongToast(@StringRes message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Activity.displayLongToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Activity.displayShortToast(@StringRes message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity.displayShortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun <T> Context.startNewActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}

fun String.toThreeDigit(): String = String.format("%03d", this)

fun String.toTwoDigit(): String = String.format("%02d", this)

fun Int.toTwoDigit(): String = String.format("%02d", this)


//fun Context.showAlertDialog(message: String) {
//
//    val dialogBuilder = AlertDialog.Builder(this)
//    val dialogView = LayoutInflater.from(this)
//        .inflate(R.layout.dialog_alert, null, false)
//    //initializing dialog screen
//
//    dialogView.findViewById<TextView>(R.id.tv_message).text = message
//
//    dialogBuilder.setCancelable(true)
//    dialogBuilder.setView(dialogView)
//
//    val dialog = dialogBuilder.show()
//    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//
//    dialogView.findViewById<MaterialCardView>(R.id.mcv_close).setOnClickListener {
//        dialog.dismiss()
//    }
//
//    Handler().postDelayed({
//        dialog.dismiss()
//    }, 5000)
//}