// AddTransactionActivity.kt
package com.example.personalfinancetracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddTransactionActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var categoryEditText: EditText
    private lateinit var amountEditText: EditText
    private lateinit var dateEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "finance-db")
            .fallbackToDestructiveMigration()
            .build()

        categoryEditText = findViewById(R.id.categoryEditText)
        amountEditText = findViewById(R.id.amountEditText)
        dateEditText = findViewById(R.id.dateEditText)
        saveButton = findViewById(R.id.saveButton)

        saveButton.setOnClickListener {
            val category = categoryEditText.text.toString()
            val amount = amountEditText.text.toString().toDouble()
            val date = dateEditText.text.toString()

            val entry = FinanceEntry(category = category, amount = amount, date = date)

            CoroutineScope(Dispatchers.Main).launch {
                withContext(Dispatchers.IO) {
                    db.financeDao().insert(entry)
                }
                finish() // Close the activity and go back
            }
        }
    }
}
