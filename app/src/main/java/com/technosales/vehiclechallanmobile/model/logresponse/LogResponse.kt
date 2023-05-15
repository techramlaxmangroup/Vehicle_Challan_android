package com.technosales.vehiclechallanmobile.model.logresponse

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class LogResponse(
    @SerializedName("success") @Expose var success: Boolean? = null,
    @SerializedName("data") @Expose var data: LogData? = null,
    @SerializedName("message") @Expose var message: String? = null
){
    override fun toString(): String {
        return "LogResponse(success=$success, data=$data, message=$message)"
    }
}