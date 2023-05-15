package com.technosales.vehiclechallanmobile.utils

import android.content.res.Resources


fun Int.toDp(): Int = ((this / Resources.getSystem().displayMetrics.density).toInt())

fun String.toBase64(): String {
    return android.util.Base64.encode(this.toByteArray(), android.util.Base64.DEFAULT)
        .toString(charset("UTF-8"))
}


fun String.fromBase64(): ByteArray? {
    return android.util.Base64.decode(this, android.util.Base64.DEFAULT)
}


fun String.fromBase64ToString(): String {
    return try {
        android.util.Base64.decode(this, android.util.Base64.DEFAULT)
            .toString(charset("UTF-8"))
    }catch (e:java.lang.IllegalArgumentException){
        e.printStackTrace()
        ""
    }
}