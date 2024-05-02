package com.example.wspolicy.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.wspolicy.R
import com.example.wspolicy.databinding.ActivityShowWantedBinding

class ShowWantedActivity : AppCompatActivity() {

    lateinit var binidng : ActivityShowWantedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binidng = ActivityShowWantedBinding.inflate(layoutInflater)
        setContentView(binidng.root)

        supportActionBar?.title = "Wanted"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binidng.showWantedStatusEditText.setText(intent.getStringExtra("wantedStatus").toString())
        binidng.showWantedFirstNameEditText.setText(intent.getStringExtra("wantedFirstName").toString())
        binidng.showWantedLastNameEditText.setText(intent.getStringExtra("wantedLastName").toString())
        binidng.showWantedMiddleNameEditText.setText("-")
        binidng.showWantedNicknameEditText.setText(intent.getStringExtra("wantedNickname").toString())
        binidng.showWantedLocationEditText.setText(intent.getStringExtra("wantedLocation").toString())
        binidng.showWantedDescriptionEditText.setText(intent.getStringExtra("wantedDescription").toString())
        Glide.with(this).load(intent.getStringExtra("wantedPhoto").toString()).into(binidng.showWantedImageView)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.show_wanted_ab, menu)
        return true
    }
}