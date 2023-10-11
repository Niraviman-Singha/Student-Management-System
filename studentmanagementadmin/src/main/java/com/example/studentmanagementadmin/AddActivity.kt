package com.example.studentmanagementadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.studentmanagementadmin.databinding.ActivityAddBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityAddBinding.inflate(layoutInflater)
    }
    private lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.saveBtn.setOnClickListener {
            val batch = binding.batchET.text.toString()
            val name = binding.nameET.text.toString()
            val email = binding.emailET.text.toString()
            val phone = binding.phoneET.text.toString()
            val subject = binding.subjectET.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Students")

            val student = Student(batch,name,email,phone,subject)

            database.child(name+phone).setValue(student).addOnSuccessListener {
                binding.batchET.text?.clear()
                binding.nameET.text?.clear()
                binding.emailET.text?.clear()
                binding.phoneET.text?.clear()
                binding.subjectET.text?.clear()

                Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
            }
                .addOnFailureListener {
                    Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
                }

        }
    }
}