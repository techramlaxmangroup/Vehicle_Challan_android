package com.technosales.vehiclechallanmobile.model.itemtype

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class ItemType(
    @SerializedName("id") @Expose var id: Int? = null,
    @SerializedName("item_name") @Expose var itemName: String? = null,
    @SerializedName("item_code") @Expose var itemCode: String? = null,
    @SerializedName("created_at") @Expose var createdAt: String? = null,
    @SerializedName("updated_at") @Expose var updatedAt: String? = null,
){
    override fun toString(): String {
        return "ItemType(id=$id, itemName=$itemName, itemCode=$itemCode, createdAt=$createdAt, updatedAt=$updatedAt)"
    }
}