package com.example.registrosyactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.registrosyactivities.databinding.ActivityMainBinding
import modelo.AlmacenUsuarios

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)
        binding.btnLista.setOnClickListener{
            if (controles()) {
                inVentana2()
            }
        }
    }

    private fun inVentana2() {
        var miIntent: Intent = Intent(this, Confirmation::class.java)
        miIntent.putExtra("nombre", binding.boxNombre.toString())
        miIntent.putExtra("apellido", binding.boxApellido.toString())
        miIntent.putExtra("nombre", binding.boxDNI.toString())
        miIntent.putExtra("edad", binding.boxContrasenia.toString())
        startActivity(miIntent)
    }

    private fun controles(): Boolean {
        var valido = true
        if (TextUtils.isEmpty(binding.boxNombre.toString())) {
            Toast.makeText(this, "El campo 'Nombre' está vacío", Toast.LENGTH_SHORT)
            valido = false
        }
        if (TextUtils.isEmpty(binding.boxApellido.toString())) {
            Toast.makeText(this, "El campo 'Apellido' está vacío", Toast.LENGTH_SHORT)
            valido = false
        }
        if (TextUtils.isEmpty(binding.boxDNI.toString())) {
            Toast.makeText(this, "El campo 'DNI' está vacío", Toast.LENGTH_SHORT)
            valido = false
        }
        if (TextUtils.isEmpty(binding.boxGMail.toString())) {
            Toast.makeText(this, "El campo 'GMail' está vacío", Toast.LENGTH_SHORT)
            valido = false
        }
        if (TextUtils.isEmpty(binding.boxContrasenia.toString())) {
            Toast.makeText(this, "El campo 'Contraseña' está vacío", Toast.LENGTH_SHORT)
            valido = false
        }
        if (TextUtils.isEmpty(binding.boxContrasenia2.toString())) {
            Toast.makeText(this, "El campo 'Repetir contraseña' está vacío", Toast.LENGTH_SHORT)
            valido = false
        }
        if (binding.boxContrasenia.toString().length < 6){
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT)
            valido = false
        }
        if (binding.boxContrasenia.toString() != binding.boxContrasenia2.toString()){
            Toast.makeText(this, "Las contraseñas introducidas no coinciden", Toast.LENGTH_SHORT)
            valido = false
        }
        for (p in AlmacenUsuarios.usuarios){
            if (binding.boxDNI.toString() == p.dni){
                Toast.makeText(this, "Ya existe un usuario registrado con ese DNI", Toast.LENGTH_SHORT)
                valido = false
            }
        }

        return valido
    }
}