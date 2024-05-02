package com.example.wspolicy.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.wspolicy.databinding.ActivityAddNewWantedBinding


class AddNewWantedActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddNewWantedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewWantedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Add New Wanted"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.addWantedDescriptionEditText.addTextChangedListener(mTextEditorWatcher)
    }

    private val mTextEditorWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        @SuppressLint("SetTextI18n")
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            binding.addWantedLengthTextView.text = s.length.toString() + " / 255"
        }

        override fun afterTextChanged(s: Editable) {
        }
    }
}