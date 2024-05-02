package com.example.wspolicy.activities

import android.animation.LayoutTransition
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.wspolicy.R
import com.example.wspolicy.databinding.ActivityShowDepartmentBinding

class ShowDepartmentActivity : AppCompatActivity() {

    lateinit var binding: ActivityShowDepartmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowDepartmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Show department"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.departmentNameTextView.text = intent.getStringExtra("departmentName")
        binding.departmentAddressTextView.text = intent.getStringExtra("departmentAddress")
        binding.departmentPhoneTextView.text = intent.getStringExtra("departmentPhone")
        binding.departmentEmailTextView.text = intent.getStringExtra("departmentEmail")
        binding.departmentBossTextView.text = intent.getStringExtra("departmentBoss")
        binding.departmentDescriptionTextView.text = intent.getStringExtra("departmentDescription")

        binding.departmentDescriptionTextView.visibility = View.GONE

        binding.expandButton.setOnClickListener {
            binding.expandedView.let { expandedView ->
                expandedView.layoutTransition?.enableTransitionType(LayoutTransition.CHANGING)
                val newVisibility = if (binding.departmentDescriptionTextView.visibility == View.GONE) View.VISIBLE else View.GONE
                binding.departmentDescriptionTextView.visibility = newVisibility
            }
        }

        binding.mapShowButton.setOnClickListener {
            startActivity(Intent(this@ShowDepartmentActivity, ShowDepartmentMapActivity::class.java)
                .putExtra("departmentCoords", intent.getStringExtra("departmentCoords")))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}