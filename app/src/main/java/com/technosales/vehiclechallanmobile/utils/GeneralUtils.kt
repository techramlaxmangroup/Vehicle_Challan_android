package com.technosales.vehiclechallanmobile.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.widget.ImageView
import androidx.core.content.pm.PackageInfoCompat
import com.bumptech.glide.Glide
import com.technosales.vehiclechallanmobile.api.ApiClient
import com.technosales.vehiclechallanmobile.model.ApiError
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


object GeneralUtils {

    fun convertErrors(response: ResponseBody?): ApiError? {
        val converter: Converter<ResponseBody, ApiError> =
            ApiClient.retrofitBuilder.build().responseBodyConverter(
                ApiError::class.java, arrayOfNulls<Annotation>(0)
            )
        var apiError: ApiError? = null
        try {
            apiError = converter.convert(response)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return apiError
    }

    @SuppressLint("SimpleDateFormat")
    fun getTodayDate(): String {
        val sdf = SimpleDateFormat("dd/mm/yyyy")
        return sdf.format(Date())
    }

    @SuppressLint("SimpleDateFormat")
    fun getCurrentTime(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return sdf.format(Date())
    }

    fun getCurrentTimeStamp(): Long {
        return System.currentTimeMillis()
    }

    fun formatDate(year: Int, month: Int, day: Int): String {
        return "$year-${(month + 1).toTwoDigit()}-${day.toTwoDigit()}"
    }

    @SuppressLint("SimpleDateFormat")
    fun getFormattedDate(stringDate: String): Date? {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return sdf.parse(stringDate)
    }

    fun getDifferenceInMinutes(generateDate: String, readDate: String): Long {
        val formattedGeneratedDate = getFormattedDate(generateDate)
        val formattedReadDate = getFormattedDate(readDate)

        val diffInMillisec: Long = formattedReadDate!!.time - formattedGeneratedDate!!.time

        return TimeUnit.MILLISECONDS.toMinutes(diffInMillisec)
    }


    fun getAppVersionName(context: Context): String? {
        val manager = context.packageManager
        try {
            val info = manager.getPackageInfo(context.packageName, 0)
            val versionCode = PackageInfoCompat.getLongVersionCode(info).toInt()
            return "Version " + info.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return ""
    }

    fun canImagePath(filename: String, view: ImageView) {
        var imageFile = File(TextConfig.FOLDER_PATH() + filename + ".png")

        if (!imageFile.exists()) {
            imageFile = File(TextConfig.FOLDER_PATH() + filename + ".jpg")
        }
        if (!imageFile.exists()) {
            imageFile = File(TextConfig.FOLDER_PATH() + filename + ".jpeg")
        }

        if (imageFile.exists()) {
            Glide.with(view)
                .load(imageFile)
                .fitCenter()
                .into(view)
        }
    }

}