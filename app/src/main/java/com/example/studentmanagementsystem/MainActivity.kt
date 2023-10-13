package com.example.studentmanagementsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.example.studentmanagementsystem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.searchBtn.setOnClickListener {
            val name = binding.nameET.text.toString()
            val phone = binding.phoneET.text.toString()

            if (name.isNotEmpty() && phone.isNotEmpty()){
                getData(name,phone)
            }
            else{
                Toast.makeText(this@MainActivity,"Please fill all the fields",Toast.LENGTH_SHORT).show()
            }
        }



    }

    private fun getData(name: String, phone: String) {
        database = FirebaseDatabase.getInstance().getReference("Students")
        database.child(name+phone).get()
            .addOnSuccessListener {
                if (it.exists()){
                    val batch = it.child("batch").value
                    val name = it.child("name").value
                    val email = it.child("email").value
                    val phone = it.child("phone").value
                    val subject = it.child("subject").value

                    binding.batchTV.text = batch.toString()
                    binding.nameTV.text = name.toString()
                    binding.emailTV.text = email.toString()
                    binding.phoneTV.text = phone.toString()
                    binding.subjectTV.text = subject.toString()

                    binding.nameET.text?.clear()
                    binding.phoneET.text?.clear()

                    Toast.makeText(this@MainActivity,"Found",Toast.LENGTH_SHORT).show()

                }
                else{
                    Toast.makeText(this@MainActivity,"Not Found",Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this@MainActivity,"Failed",Toast.LENGTH_SHORT).show()
            }

    }
}