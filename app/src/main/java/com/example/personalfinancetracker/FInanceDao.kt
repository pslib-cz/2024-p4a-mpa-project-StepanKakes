// FinanceDao.kt
package com.example.personalfinancetracker

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FinanceDao {
    @Insert
    suspend fun insert(entry: FinanceEntry)

    @Update
    suspend fun update(entry: FinanceEntry)

    @Delete
    suspend fun delete(entry: FinanceEntry)

    @Query("SELECT * FROM finance_entries")
    suspend fun getAllEntries(): List<FinanceEntry>

    @Query("SELECT * FROM finance_entries ORDER BY date DESC")
    suspend fun getEntriesSortedByDate(): List<FinanceEntry>
}
