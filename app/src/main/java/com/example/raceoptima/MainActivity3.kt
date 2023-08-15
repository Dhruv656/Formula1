package com.example.raceoptima

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.widget.CheckBox
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.raceoptima.R.color.orange
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference;


import com.example.raceoptima.databinding.ActivityMain3Binding
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase.*
import com.google.firebase.database.ValueEventListener


class MainActivity3 : AppCompatActivity() {

    private lateinit var binding: ActivityMain3Binding
    private lateinit var fullname1 : String
    private lateinit var email1 : String
    private lateinit var password1 : String
    private lateinit var confirmpassword1 : String
    private lateinit var db : FirebaseDatabase
    private lateinit var databaseReference : DatabaseReference
    private val registeredEmails = mutableSetOf<String>()



    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.fullname.addTextChangedListener {
            binding.fullnamelayout.isErrorEnabled = false
        }

        binding.email.addTextChangedListener {
            binding.emaillayout.isErrorEnabled = false
        }

        binding.password.addTextChangedListener {
            binding.passwordlayout.isErrorEnabled = false
        }

        binding.confirmpassword.addTextChangedListener {
            binding.confirmpasswordlayout.isErrorEnabled = false
        }


        binding.login.setOnClickListener{
            val intent = Intent(this,MainActivity4::class.java)
            startActivity(intent)
        }

            binding.finalsignup.setOnClickListener{
            fullname1 = binding.fullname.text.toString().trim()
            email1 = binding.email.text.toString().trim()
            password1 = binding.password.text.toString().trim()
            confirmpassword1 = binding.confirmpassword.text.toString().trim()



            if(fullname1.isEmpty()){
                binding.fullnamelayout.isErrorEnabled = true
                binding.fullnamelayout.setError("Full Name Required")
                val errorColor = ColorStateList.valueOf(Color.RED)
                binding.fullnamelayout.setErrorTextColor(errorColor)

                return@setOnClickListener

            }

            if(!fullname1.matches("^[a-zA-Z ]+\$".toRegex())){
                binding.fullnamelayout.isErrorEnabled = true
                binding.fullnamelayout.setError("Full Name should contain only alphabets")
                val errorColor = ColorStateList.valueOf(Color.RED)
                binding.fullnamelayout.setErrorTextColor(errorColor)

                return@setOnClickListener
            }


            if(email1.isEmpty()){
                binding.emaillayout.isErrorEnabled = true
                binding.emaillayout.setError("Email Required")
                val errorColor = ColorStateList.valueOf(Color.RED)
                binding.emaillayout.setErrorTextColor(errorColor)

                return@setOnClickListener

            }

            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email1).matches()){
                binding.emaillayout.isErrorEnabled = true
                binding.emaillayout.error = "Invalid email address"
                val errorColor = ColorStateList.valueOf(Color.RED)
                binding.emaillayout.setErrorTextColor(errorColor)

                return@setOnClickListener
            }




            if(password1.isEmpty()){
                binding.passwordlayout.isErrorEnabled = true
                binding.passwordlayout.setError("Password Cannot be Empty")
                val errorColor = ColorStateList.valueOf(Color.RED)
                binding.passwordlayout.setErrorTextColor(errorColor)

                return@setOnClickListener

            }

            if(password1.length <8){
                binding.passwordlayout.isErrorEnabled = true
                binding.passwordlayout.error = "Password should be longer than 6 characters"
                val errorColor = ColorStateList.valueOf(Color.RED)
                binding.passwordlayout.setErrorTextColor(errorColor)

                return@setOnClickListener

            }

            if(confirmpassword1.isEmpty()) {
                binding.confirmpasswordlayout.isErrorEnabled = true
                binding.confirmpasswordlayout.setError("Password Required")
                val errorColor = ColorStateList.valueOf(Color.RED)
                binding.confirmpasswordlayout.setErrorTextColor(errorColor)

                return@setOnClickListener
            }

            if(password1 != confirmpassword1){
                binding.confirmpasswordlayout.isErrorEnabled = true
                binding.confirmpasswordlayout.setError("Password and confirm password do not match")
                val errorColor = ColorStateList.valueOf(Color.RED)
                binding.confirmpasswordlayout.setErrorTextColor(errorColor)

                return@setOnClickListener



            }

            RegisterData()

















        }






    }


    private fun RegisterData(){

        db = getInstance("https://raceoptima-578f4-default-rtdb.firebaseio.com")
        databaseReference = db.getReference("Race Engineer")


        databaseReference.orderByChild("email").equalTo(email1)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {

                        val intent = Intent(this@MainActivity3, MainActivity8::class.java)
                        this@MainActivity3.startActivity(intent)
                    } else {

                        val newdata = databaseReference.push()
                        newdata.child("fullname").setValue(fullname1)
                        newdata.child("email").setValue(email1)
                        newdata.child("password").setValue(password1)
                            .addOnSuccessListener {

                                val sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)
                                sharedPreferences.edit().putString("fullname", fullname1).apply()

                                val intent = Intent(this@MainActivity3, MainActivity6::class.java)
                                this@MainActivity3.startActivity(intent)
                            }
                            .addOnFailureListener{

                                val intent = Intent(this@MainActivity3, MainActivity7::class.java)
                                this@MainActivity3.startActivity(intent)
                            }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Toast.makeText(this@MainActivity3, "Failed to Register", Toast.LENGTH_SHORT).show()
                }
            })


            }









    }










