package com.example.wspolicy.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wspolicy.adapters.DepartmentsAdapter
import com.example.wspolicy.databinding.ActivityDepartamentsBinding
import com.example.wspolicy.retrofit.RetrofitClient
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DepartmentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDepartamentsBinding

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDepartamentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Departaments"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.departamentsRecyclerView.layoutManager = LinearLayoutManager(this@DepartmentsActivity)

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = RetrofitClient.apiService.departments()
                if (response.isSuccessful) {
                    val departments = response.body()
                    val departmentItems = departments?.data ?: emptyList()
                    binding.departamentsRecyclerView.adapter = DepartmentsAdapter(departmentItems, this@DepartmentsActivity)
                } else {
                    Toast.makeText(this@DepartmentsActivity, "Ошибка подключения к серверу", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@DepartmentsActivity, e.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}