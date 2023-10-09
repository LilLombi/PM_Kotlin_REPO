package com.example.registrosyactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.registrosyactivities.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)
        binding.boton.setOnClickListener{
            inVentana2()
        }
    }

    private fun inVentana2() {
        var miIntent: Intent = Intent(this, Ventana2::class.java)
        miIntent.putExtra("nombre", binding.cajaNombre.toString())
        miIntent.putExtra("edad", binding.cajaEdad.toString())
        startActivity(miIntent)
    }
}