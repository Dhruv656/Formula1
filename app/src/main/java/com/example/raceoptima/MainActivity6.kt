package com.example.raceoptima

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.raceoptima.databinding.ActivityMain4Binding
import com.example.raceoptima.databinding.ActivityMain6Binding

class MainActivity6 : AppCompatActivity() {
    private lateinit var binding: ActivityMain6Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivityMain6Binding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.continue1.setOnClickListener{
            val intent = Intent(this,MainActivity9::class.java)
            startActivity(intent)
        }

        val greeting = "Hii,"
        val sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val fullname = sharedPreferences.getString("fullname", "Default Name")

        val greetingsAndName = "Hii,  $fullname"

        binding.hii5.text = greetingsAndName

    }
}