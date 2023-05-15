package com.technosales.vehiclechallanmobile.db.itemtype

import androidx.lifecycle.LiveData
import com.technosales.vehiclechallanmobile.db.itemtype.ItemType
import com.technosales.vehiclechallanmobile.db.itemtype.ItemTypeDao

class ItemTypeRepository(private val itemTypeDao: ItemTypeDao) {

    val allItemType: LiveData<List<ItemType>> = itemTypeDao.getAllItemType()

    suspend fun insert(thermalData: ItemType) {
        itemTypeDao.insert(thermalData)
    }

    suspend fun insertAll(itemTypeList: List<ItemType>) {
        itemTypeDao.insertAll(itemTypeList)
    }

    suspend fun deleteById(id: Int) {
        itemTypeDao.deleteById(id)
    }

    suspend fun deleteAll() {
        itemTypeDao.deleteAll()
    }
}