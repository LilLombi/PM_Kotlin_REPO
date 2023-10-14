package com.example.registrosyactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.registrosyactivities.databinding.ActivityConfirmationBinding
import modelo.AlmacenUsuarios
import modelo.Usuario

class Confirmation : AppCompatActivity() {
    lateinit var binding: ActivityConfirmationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var nombre = intent.getStringExtra("nombre")
        var apellido = intent.getStringExtra("apellido")
        var dni = intent.getStringExtra("dni")
        var gmail = intent.getStringExtra("gmail")
        var contrasenia = intent.getStringExtra("contrasenia")

        var usuario:Usuario = Usuario(nombre, apellido, gmail, dni, contrasenia)

        AlmacenUsuarios.aniadirPersona(usuario)

        binding.txtNombre2.setText(nombre)
        binding.txtApellido2.setText(apellido)
        binding.txtDNI2.setText(dni)
        binding.txtGMail2.setText(gmail)

        binding.btnLista.setOnClickListener{
            finish()
        }

        var cadena: String = ""
        var i:Int=1
        for(p in AlmacenUsuarios.usuarios){
            cadena += " " + i + " " + p.nombre + " " + p.apellido + " " + p.dni + " " + p.gmail + "\n"
            i++
            binding.multiLine.setText(cadena)
        }

    }

}