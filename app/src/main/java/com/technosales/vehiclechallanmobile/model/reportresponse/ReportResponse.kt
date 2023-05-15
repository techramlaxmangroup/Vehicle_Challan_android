package com.technosales.vehiclechallanmobile.model.reportresponse

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.technosales.vehiclechallanmobile.model.logresponse.LogData

data class ReportResponse(
    @SerializedName("success") @Expose var success: Boolean? = null,
    @SerializedName("data") @Expose var data: List<LogData>? = null,
    @SerializedName("message") @Expose var message: String? = null
){
    override fun toString(): String {
        return "LogResponse(success=$success, data=$data, message=$message)"
    }
}