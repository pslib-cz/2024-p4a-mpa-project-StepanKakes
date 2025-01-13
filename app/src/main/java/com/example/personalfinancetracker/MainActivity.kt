// MainActivity.kt
package com.example.personalfinancetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RecyclerView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var financeDao: FinanceDao
    private lateinit var financeAdapter: FinanceAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "finance-db")
            .fallbackToDestructiveMigration()
            .build()

        financeDao = db.financeDao()
        recyclerView = findViewById(R.id.recyclerView)
        addButton = findViewById(R.id.addButton)

        financeAdapter = FinanceAdapter(mutableListOf())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = financeAdapter

        // Load data from database
        loadFinanceData()

        addButton.setOnClickListener {
            // Open AddTransactionActivity to add a new entry
            val intent = Intent(this, AddTransactionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadFinanceData() {
        // Load finance data asynchronously
        CoroutineScope(Dispatchers.Main).launch {
            val entries = withContext(Dispatchers.IO) { financeDao.getAllEntries() }
            financeAdapter.updateEntries(entries)
        }
    }
}
