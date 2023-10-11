package com.example.studentmanagementsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studentmanagementsystem.databinding.ActivityAddBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityAddBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}