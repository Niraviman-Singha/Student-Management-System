package com.example.studentmanagementadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studentmanagementadmin.databinding.ActivityAddBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityAddBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}