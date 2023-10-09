package com.example.registrosyactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.registrosyactivities.databinding.ActivityMainBinding
import com.example.registrosyactivities.databinding.ActivityVentana2Binding
import modelo.AlmacenPersonas
import modelo.Persona

class Ventana2 : AppCompatActivity() {
    lateinit var binding: ActivityVentana2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVentana2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var nombre = intent.getStringExtra("nombre")
        var edad = intent.getStringExtra("edad")
        var persona:Persona = Persona(nombre,edad)

        AlmacenPersonas.aniadirPersona(persona)

        binding.cajaNombre.setText(nombre)
        binding.cajaEdad.setText(edad)

        binding.boton.setOnClickListener{
            finish()
        }

        var cadena: String = ""
        var i:Int=1
        for(p in AlmacenPersonas.personas){
            cadena += " " + i + " " + p.nombre + " " + p.edad + "\n"
            i++
            binding.multiLine.setText(cadena)
        }

        binding.boton.setOnClickListener{
            finish()
        }
    }

}