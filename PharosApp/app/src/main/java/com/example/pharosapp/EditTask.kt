package com.example.pharosapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pharosapp.databinding.ActivityEdittaskBinding

class EditTask : AppCompatActivity() {

    lateinit var binding: ActivityEdittaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!intent.getBooleanExtra("NewTask", true)){

        }
    }
}