package com.example.studentmanagementadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.studentmanagementadmin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {
     val binding by lazy {
        ActivityUpdateBinding.inflate(layoutInflater)
    }
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.updateBtn.setOnClickListener {
            val batch = binding.batchET.text.toString()
            val name = binding.nameET.text.toString()
            val email = binding.emailET.text.toString()
            val phone = binding.phoneET.text.toString()
            val subject = binding.subjectET.text.toString()

           updateData(batch,name,email,phone,subject)


        }
    }

    private fun updateData(batch:String, name:String, email:String, phone:String, subject:String){
        database = FirebaseDatabase.getInstance().getReference("Students")

        val student = mapOf<String, String>(
            "batch" to batch,
            "name" to name,
            "email" to email,
            "phone" to phone,
            "subject" to subject,

        )

        database.child(name+phone).updateChildren(student)
            .addOnSuccessListener {
              binding.batchET.text?.clear()
              binding.nameET.text?.clear()
              binding.emailET.text?.clear()
              binding.phoneET.text?.clear()
              binding.subjectET.text?.clear()

            Toast.makeText(this@UpdateActivity,"Updated",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@UpdateActivity,MainActivity::class.java))
            finish()
        }
            .addOnFailureListener {
                Toast.makeText(this@UpdateActivity,"Update Failed",Toast.LENGTH_SHORT).show()
            }
    }
}