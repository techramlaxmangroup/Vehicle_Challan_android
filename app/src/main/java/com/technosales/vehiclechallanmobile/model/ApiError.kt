package com.technosales.vehiclechallanmobile.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiError(
    @Expose @SerializedName("errors") var errors: Map<String, List<String>>? = null,
    @Expose @SerializedName("message") var message: String = "",
    @Expose @SerializedName("success") var success: Boolean? = null
) {
}