package com.technosales.vehiclechallanmobile.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object BindingUtils {
    @JvmStatic
    fun formatToThreeDigit(value: Int?): String = String.format("%03d", value)

    @JvmStatic
    fun formatToTwoDigit(value: Int?): String = String.format("%02d", value)

    @JvmStatic
    @SuppressLint("SimpleDateFormat")
    fun formatDate(dateString: String?): String = try {
        val sdf = SimpleDateFormat("yyyy-mm-dd HH:mm:ss")
        val date: Date = sdf.parse(dateString)
        val format = "dd MMM, yyyy hh:mm aa"
        val newSdf = SimpleDateFormat(format)
        newSdf.format(date)
    } catch (e: Exception) {
        dateString.toString()
    }

}