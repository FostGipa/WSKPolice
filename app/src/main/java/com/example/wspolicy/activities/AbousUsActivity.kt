package com.example.wspolicy.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.wspolicy.R
import com.example.wspolicy.databinding.ActivityAbousUsBinding

class AbousUsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAbousUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbousUsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Abous Us"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}