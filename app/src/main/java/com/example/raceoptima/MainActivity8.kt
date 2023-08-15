package com.example.raceoptima

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.raceoptima.databinding.ActivityMain3Binding
import com.example.raceoptima.databinding.ActivityMain8Binding

class MainActivity8 : AppCompatActivity() {
    private lateinit var binding: ActivityMain8Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivityMain8Binding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button2.setOnClickListener{
            val intent = Intent(this,MainActivity4::class.java)
            startActivity(intent)

        }

    }
}