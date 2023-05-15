package com.technosales.vehiclechallanmobile.db.itemtype

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.technosales.vehiclechallanmobile.db.itemtype.ItemType

@Dao
interface ItemTypeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(itemType: ItemType)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(itemTypeList: List<ItemType>)

    @Query("SELECT * from item_type ORDER BY item_name")
    fun getAllItemType(): LiveData<List<ItemType>>

    @Query("DELETE FROM item_type WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM item_type")
    suspend fun deleteAll()
}