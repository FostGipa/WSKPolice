package com.example.wspolicy.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wspolicy.R
import com.example.wspolicy.adapters.WantedsAdapter
import com.example.wspolicy.databinding.ActivityWantedBinding
import com.example.wspolicy.retrofit.RetrofitClient
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WantedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWantedBinding

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWantedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Wanted"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.wantedRecyclerView.layoutManager = LinearLayoutManager(this@WantedActivity)

        GlobalScope.launch (Dispatchers.Main){
           try {
               val response = RetrofitClient.apiService.wanteds()
               if (response.isSuccessful) {
                   val responseData = response.body()
                   val wantedList = responseData?.data ?: emptyList()
                   binding.wantedRecyclerView.adapter = WantedsAdapter(wantedList, this@WantedActivity)
               }
           } catch (e : Exception) {
               Toast.makeText(this@WantedActivity, e.message.toString(), Toast.LENGTH_SHORT).show()
           }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.wanted_ab, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.wantedMenuAdd -> startActivity(Intent(this@WantedActivity, AddNewWantedActivity::class.java))
        }
        return true
    }
}