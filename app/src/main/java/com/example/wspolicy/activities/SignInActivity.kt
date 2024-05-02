package com.example.wspolicy.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.wspolicy.R
import com.example.wspolicy.models.UserData
import com.example.wspolicy.databinding.ActivitySignInBinding
import com.example.wspolicy.retrofit.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.hide()
        val sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        binding.signinButton.setOnClickListener {
            val call = RetrofitClient.apiService.login(binding.loginEditText.text.toString(), binding.passwordEditText.text.toString())

            call.enqueue(object : Callback<UserData> {
                override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        if (responseData != null) {
                            val userData = Gson().fromJson(Gson().toJson(responseData), UserData::class.java)

                            val userId = userData.data.id
                            val userLogin = userData.data.login
                            val userName = userData.data.name
                            val userToken = userData.data.token
                            editor.putBoolean("authorized", true)
                            editor.apply()
                            startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                        } else {
                            Toast.makeText(this@SignInActivity, "", Toast.LENGTH_SHORT).show()
                        }

                    } else {
                        Toast.makeText(this@SignInActivity, "Ошибка подключения к серверу", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<UserData>, t: Throwable) {
                    Toast.makeText(this@SignInActivity, "Ошибка подключения к серверу", Toast.LENGTH_SHORT).show()
                }
            })
        }

        binding.guestButton.setOnClickListener {
            editor.putBoolean("authorized", false)
            editor.apply()
            startActivity(Intent(this@SignInActivity, MainActivity::class.java))
        }

        binding.rememberCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (binding.rememberCheckBox.isChecked) {
                editor.putBoolean("remember", true)
                editor.apply()
            } else {
                editor.putBoolean("remember", false)
                editor.apply()
            }
        }

    }
}