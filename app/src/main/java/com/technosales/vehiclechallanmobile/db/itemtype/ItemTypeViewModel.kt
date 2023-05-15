package com.technosales.vehiclechallanmobile.db.itemtype

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.technosales.vehiclechallanmobile.db.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemTypeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ItemTypeRepository

    val allItemType: LiveData<List<ItemType>>

    init {
        val itemTypeDao = AppDatabase.getDatabase(application).itemTypeDao()
        repository = ItemTypeRepository(itemTypeDao)
        allItemType = repository.allItemType
    }

    fun insertAll(logDataList:List<ItemType>) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertAll(logDataList)
    }

    fun insert(logData: ItemType) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(logData)
    }

    fun deleteById(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteById(id)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }
}