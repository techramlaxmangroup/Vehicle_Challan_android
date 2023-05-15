package com.technosales.vehiclechallanmobile.utils

import android.os.Build
import android.os.Environment

object TextConfig {


    //INTERCEPTOR
    const val CONNECTION_TIMEOUT: Long = 30000 // 30 seconds
    const val READ_TIMEOUT: Long = 3000 // 3 seconds
    const val WRITE_TIMEOUT: Long = 3000 // 3 seconds

    //HEADERS
    const val CONTENT_TYPE = "Content-type: application/json"
    const val ACCEPT = "Accept: application/json"
    const val BEARER = "Bearer "
    const val AUTHORIZATION = "Authorization"

    //URL
    const val BASE_URL = "http://202.52.240.148:5062/vehicle_challan/public/api/"
    const val IMAGE_BASE_URL = "http://202.52.240.148:5062/vehicle_challan/public"

    // API
    const val API_GET_ITEM_TYPE = "items"
    const val API_POST_GENERATE_DATA = "vehicle_logs"
    const val API_POST_READ_DATA = "vehicle_logs/{ticket_number}"
    const val API_GET_REPORT = "search"

    //EXTRA
    const val EXTRA_IMAGE_URL = "extra_image_url"


    fun FOLDER_PATH(): String = when {
        Build.VERSION.SDK_INT > Build.VERSION_CODES.M -> {
            Environment.getExternalStorageDirectory().toString() + "/vehiclechallan/"
        }
        Build.VERSION.SDK_INT == Build.VERSION_CODES.M -> {
            "/storage/extsd/vehiclechallan/"
        }
        else -> {
            "/mnt/extsd/vehiclechallan/"
        }
    }

}
