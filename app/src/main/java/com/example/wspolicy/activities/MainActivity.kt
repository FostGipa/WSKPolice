package com.example.wspolicy.activities


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.wspolicy.R
import com.example.wspolicy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Main"

        val sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE)
        if (sharedPreferences.getBoolean("authorized", false)) {
            binding.paintButton.visibility = View.GONE
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            binding.criminalButton.visibility = View.GONE
            binding.detectivesButton.visibility = View.GONE
            binding.logoutButton.visibility = View.INVISIBLE
            binding.logoutButton.isEnabled = false
        }

        binding.departamentsButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, DepartmentsActivity::class.java))
        }

        binding.aboutButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, AbousUsActivity::class.java))
        }

        binding.wantedButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, WantedActivity::class.java))
        }

        binding.logoutButton.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.putBoolean("authorized", false)
            editor.putBoolean("remember", false)
            editor.apply()
            startActivity(Intent(this@MainActivity, SignInActivity::class.java))
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.photorobot_ab, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.photorobotAddMenu -> {
                startActivity(Intent(this@MainActivity, NewPhotorobotActivity::class.java))
            }

        }
        return true
    }
}