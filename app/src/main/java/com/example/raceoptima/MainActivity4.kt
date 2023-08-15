package com.example.raceoptima

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.raceoptima.databinding.ActivityMain3Binding
import com.example.raceoptima.databinding.ActivityMain4Binding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.auth.User





class MainActivity4 : AppCompatActivity() {
    private lateinit var loginemail1: String
    private lateinit var loginpassword1: String
    private lateinit var db : FirebaseDatabase
    private lateinit var databaseReference : DatabaseReference
    private lateinit var binding: ActivityMain4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.loginemail.addTextChangedListener{
            binding.loginemaillayout.isErrorEnabled = false
        }

        binding.loginpassword.addTextChangedListener {
            binding.loginpasswordlayout.isErrorEnabled = false
        }


        binding.loginbutton.setOnClickListener {

            loginemail1 = binding.loginemail.text.toString().trim()
            loginpassword1 = binding.loginpassword.text.toString().trim()


            if (loginemail1.isEmpty()) {
                binding.loginemaillayout.isErrorEnabled = true
                binding.loginemaillayout.setError("Email Required")
                val errorColor = ColorStateList.valueOf(Color.RED)
                binding.loginemaillayout.setErrorTextColor(errorColor)

                return@setOnClickListener


            }


            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(loginemail1).matches()){
                binding.loginemaillayout.isErrorEnabled = true
                binding.loginemaillayout.error = "Invalid email address"
                val errorColor = ColorStateList.valueOf(Color.RED)
                binding.loginemaillayout.setErrorTextColor(errorColor)

                return@setOnClickListener
            }





            if (loginpassword1.isEmpty()) {
                binding.loginpasswordlayout.isErrorEnabled = true
                binding.loginpasswordlayout.setError("Password Required")
                val errorColor = ColorStateList.valueOf(Color.RED)
                binding.loginpasswordlayout.setErrorTextColor(errorColor)

                return@setOnClickListener

            }

            loginUser(loginemail1,loginpassword1)

        }

        binding.forgotpassword.setOnClickListener{
            val intent = Intent(this,MainActivity5::class.java)
            startActivity(intent)
        }


        db = FirebaseDatabase.getInstance("https://raceoptima-578f4-default-rtdb.firebaseio.com")
        databaseReference = db.getReference("Race Engineer")


    }



    private fun loginUser(email: String, password: String) {
        databaseReference.orderByChild("email").equalTo(email)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var emailFound = false
                    var passwordCorrect = false
                    for (userSnapshot in dataSnapshot.children) {
                        // Get user data as a Map and extract the password
                        val userDataMap = userSnapshot.value as? Map<*, *>
                        val storedPassword = userDataMap?.get("password") as? String
                        if (storedPassword != null) {
                            emailFound = true
                            if (storedPassword == password) {
                                // Password matches, login successful
                                // You can handle the logged-in state here, for example, redirect to another activity.
                                // Example: val intent = Intent(this@MainActivity4, HomeActivity::class.java)
                                //          startActivity(intent)
                                val intent = Intent(this@MainActivity4,MainActivity9::class.java)
                                startActivity(intent)
                                passwordCorrect = true
                                break // Stop checking for more matches after a successful login
                            }
                        }
                    }

                    if (!emailFound) {
                        // Email not found in the database
                        binding.loginemaillayout.isErrorEnabled = true
                        binding.loginemaillayout.error = "Email not Found"
                        val errorColor = ColorStateList.valueOf(Color.RED)
                        binding.loginemaillayout.setErrorTextColor(errorColor)
                    } else if (!passwordCorrect) {
                        // Incorrect password
                        binding.loginpasswordlayout.isErrorEnabled = true
                        binding.loginpasswordlayout.error = "Incorrect password"
                        val errorColor = ColorStateList.valueOf(Color.RED)
                        binding.loginpasswordlayout.setErrorTextColor(errorColor)

                    } else {
                        // Login successful

                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Error occurred while querying the database
                    Toast.makeText(this@MainActivity4, "Failed to check user credentials.", Toast.LENGTH_SHORT).show()
                    println("Database error: ${databaseError.message}")
                }
            })
    }
}
