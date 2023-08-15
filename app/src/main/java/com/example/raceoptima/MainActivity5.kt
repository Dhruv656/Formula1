package com.example.raceoptima

import android.content.res.ColorStateList
import android.graphics.Color
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.raceoptima.databinding.ActivityMain3Binding
import com.example.raceoptima.databinding.ActivityMain4Binding
import com.example.raceoptima.databinding.ActivityMain5Binding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity5 : AppCompatActivity() {

    private lateinit var binding: ActivityMain5Binding
    private lateinit var femail1: String
    private lateinit var newpasword1: String
    private lateinit var cnewpassword1: String


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivityMain5Binding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.femail.addTextChangedListener {
            binding.femaillayout.isErrorEnabled = false
        }

        binding.newpassword.addTextChangedListener {
            binding.newpasswordlayout.isErrorEnabled = false
        }

        binding.cnewpassword.addTextChangedListener {
            binding.cnewpasswordlayout.isErrorEnabled = false
        }

        binding.updatepassword.setOnClickListener {

            femail1 = binding.femail.text.toString().trim()
            newpasword1 = binding.newpassword.text.toString().trim()
            cnewpassword1 = binding.cnewpassword.text.toString().trim()


            if (femail1.isEmpty()) {
                binding.femaillayout.isErrorEnabled = true
                binding.femaillayout.setError("Email Required")
                val errorColor = ColorStateList.valueOf(Color.RED)
                binding.femaillayout.setErrorTextColor(errorColor)

                return@setOnClickListener

            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(femail1).matches()) {
                binding.femaillayout.isErrorEnabled = true
                binding.femaillayout.error = "Invalid email address"
                val errorColor = ColorStateList.valueOf(Color.RED)
                binding.femaillayout.setErrorTextColor(errorColor)

                return@setOnClickListener
            }


            if (newpasword1.isEmpty()) {
                binding.newpasswordlayout.isErrorEnabled = true
                binding.newpasswordlayout.setError("Password Cannot be Empty")
                val errorColor = ColorStateList.valueOf(Color.RED)
                binding.newpasswordlayout.setErrorTextColor(errorColor)

                return@setOnClickListener

            }

            if (newpasword1.length < 8) {
                binding.newpasswordlayout.isErrorEnabled = true
                binding.newpasswordlayout.error = "Password should be longer than 6 characters"
                val errorColor = ColorStateList.valueOf(Color.RED)
                binding.newpasswordlayout.setErrorTextColor(errorColor)

                return@setOnClickListener

            }

            if (cnewpassword1.isEmpty()) {
                binding.cnewpasswordlayout.isErrorEnabled = true
                binding.cnewpasswordlayout.setError("Password Required")
                val errorColor = ColorStateList.valueOf(Color.RED)
                binding.cnewpasswordlayout.setErrorTextColor(errorColor)

                return@setOnClickListener
            }

            if (newpasword1 != cnewpassword1) {
                binding.cnewpasswordlayout.isErrorEnabled = true
                binding.cnewpasswordlayout.setError("Password and confirm password do not match")
                val errorColor = ColorStateList.valueOf(Color.RED)
                binding.cnewpasswordlayout.setErrorTextColor(errorColor)

                return@setOnClickListener


            }
            updatepassword(femail1,newpasword1)

        }



    }
    private fun updatepassword(email:String,newPassword:String){

        var db = FirebaseDatabase.getInstance("https://raceoptima-578f4-default-rtdb.firebaseio.com")
        var databaseReference = db.getReference("Race Engineer")

        databaseReference.orderByChild("email").equalTo(email)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // Your existing code ...
                    var userFound = false

                    for (userSnapshot in dataSnapshot.children) {
                        // Your existing code ...
                        val userId = userSnapshot.key
                        if (userId != null) {
                            // Update the password in the database
                            databaseReference.child(userId).child("password").setValue(newPassword)
                                .addOnSuccessListener {
                                    // Password update successful
                                    Toast.makeText(
                                        this@MainActivity5,
                                        "Password updated successfully!, Kindly Login",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                                .addOnFailureListener {
                                    // Failed to update password
                                    showErrorMessage("Failed to update password. Please try again later.")
                                }

                            userFound = true
                            break
                        }
                    }

                    if (!userFound) {
                        showErrorMessage("No user found with the given email.")
                    }

                    // Your existing code ...
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    showErrorMessage("Database operation canceled. Please try again later.")
                }
            })
    }

    // Function to show an error message using Toast
    private fun showErrorMessage(message: String) {
        Toast.makeText(this@MainActivity5, message, Toast.LENGTH_SHORT).show()


    }




}




