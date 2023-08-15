package com.example.raceoptima

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent

import androidx.cardview.widget.CardView
import com.example.raceoptima.databinding.FragmentFourthfragBinding

class fourthfrag : Fragment() {
    // ... (other code)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFourthfragBinding.inflate(inflater, container, false)
        val view = binding.root

        // Find the CardViews by their IDs
        val cardView1: CardView = view.findViewById(R.id.Cardview1)
        val cardView2: CardView = view.findViewById(R.id.Cardview2)

        // Set click listeners for the CardViews
        cardView1.setOnClickListener {
            // Handle click for the first CardView
            openFirstCardScreen()
        }

        cardView2.setOnClickListener {
            // Handle click for the second CardView
            openSecondCardScreen()
        }

        return view
    }

    // Function to open the screen for the first CardView
    private fun openFirstCardScreen() {
        val intent = Intent(activity, MainActivity10::class.java)
        startActivity(intent)
    }

    // Function to open the screen for the second CardView
    private fun openSecondCardScreen() {
        val intent = Intent(activity, MainActivity11::class.java)
        startActivity(intent)
    }

    // ... (other code)
}
