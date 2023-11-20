package com.example.pharosapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pharosapp.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnNewTask.setOnClickListener(){
            val newTaskIntent = Intent(this, EditTask::class.java).apply {
            }
            startActivity(newTaskIntent)
        }
    }
}