package com.technosales.vehiclechallanmobile.model.itemtype

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.technosales.vehiclechallanmobile.model.itemtype.ItemType


data class ItemTypeResponse(
    @SerializedName("success") @Expose var success: Boolean? = null,
    @SerializedName("data") @Expose var data: List<ItemType>? = null,
    @SerializedName("message") @Expose var message: String? = null
) {
    override fun toString(): String {
        return "ItemTypeResponse(success=$success, data=$data, message=$message)"
    }
}