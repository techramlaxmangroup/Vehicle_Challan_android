package com.technosales.vehiclechallanmobile.db.itemtype

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "item_type")
class ItemType(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "item_id") var itemId: Int? = null,
    @ColumnInfo(name = "item_name") var itemName: String? = null,
    @ColumnInfo(name = "item_code") var itemCode: String? = null
) {
    override fun toString(): String {
        return "ItemType(id=$id, itemId=$itemId, itemName=$itemName, itemCode=$itemCode)"
    }
}