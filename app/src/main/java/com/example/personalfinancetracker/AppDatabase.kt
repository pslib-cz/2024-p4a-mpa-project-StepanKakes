// AppDatabase.kt
package com.example.personalfinancetracker

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FinanceEntry::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun financeDao(): FinanceDao
}
