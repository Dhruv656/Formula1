package com.example.raceoptima

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.raceoptima.databinding.ActivityMain8Binding
import com.example.raceoptima.databinding.ActivityMain9Binding
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity



class MainActivity9 : AppCompatActivity() {
    private lateinit var binding: ActivityMain9Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityMain9Binding.inflate(layoutInflater)
        setContentView(binding.root)


        val bottomnavigation = binding.bottomNavigationView
        val navController = findNavController(R.id.fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.firstfrag,R.id.secondfrag,R.id.thirdfrag,R.id.fourthfrag ))
        setupActionBarWithNavController(navController,appBarConfiguration)

        bottomnavigation.setupWithNavController(navController)





















    }


}





