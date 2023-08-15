package com.example.raceoptima

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes


class thirdfrag : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_thirdfrag, container, false)

        setupCardViewClickListeners(rootView)

        return rootView
    }

    private fun setupCardViewClickListeners(rootView: View) {
        setCardViewClickListener(rootView, R.id.bahrain, bahrain::class.java)
        setCardViewClickListener(rootView, R.id.saudi, saudiarabia::class.java)

        // Add OnClickListener for other card views
    }

    private fun setCardViewClickListener(rootView: View, @IdRes cardViewId: Int, activityClass: Class<*>) {
        val cardView = rootView.findViewById<View>(cardViewId)
        cardView.setOnClickListener {
            val intent = Intent(requireContext(), activityClass)
            startActivity(intent)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()

    }
}
