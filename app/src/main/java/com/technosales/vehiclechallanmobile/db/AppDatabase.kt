package com.technosales.vehiclechallanmobile.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.technosales.vehiclechallanmobile.db.itemtype.ItemType
import com.technosales.vehiclechallanmobile.db.itemtype.ItemTypeDao

@Database(entities = [ItemType::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemTypeDao(): ItemTypeDao

    companion object {
        var DATABASE_NAME = "app_database"

        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}