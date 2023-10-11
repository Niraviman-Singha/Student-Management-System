package com.example.studentmanagementadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.studentmanagementadmin.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityDeleteBinding.inflate(layoutInflater)
    }
    private lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.deleteBtn.setOnClickListener {
            val name = binding.nameET.text.toString()
            val phone = binding.phoneET.text.toString()

            if (name.isNotEmpty() && phone.isNotEmpty()){
                deleteData(name,phone)
            }else{
                Toast.makeText(this@DeleteActivity,"Please fill all fields!!", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun deleteData(name: String, phone: String) {
        database = FirebaseDatabase.getInstance().getReference("Students")

        database.child(name+phone).removeValue()

            .addOnSuccessListener {
                Toast.makeText(this,"Deleted", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@DeleteActivity, MainActivity::class.java))
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this,"Delete Failed", Toast.LENGTH_SHORT).show()
            }

    }
}