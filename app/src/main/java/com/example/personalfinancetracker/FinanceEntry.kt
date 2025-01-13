// FinanceEntry.kt
package com.example.personalfinancetracker

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "finance_entries")
data class FinanceEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val category: String,
    val amount: Double,
    val date: String // Můžeš použít Long pro timestamp nebo String pro datum
)
