package com.example.raceoptima

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.raceoptima.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.f1video.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.f1_animation))
        binding.f1video.start()


        val handler = Handler(Looper.getMainLooper())


        handler.postDelayed({
            val intent = Intent(this@MainActivity,MainActivity2::class.java)
            startActivity(intent)
            finish()
        },4000)









    }
}