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
        binding.btnLista.setOnClickListener{
            if (controles()) {
                inVentana2()
            }
        }
    }

    private fun inVentana2() {
        var miIntent: Intent = Intent(this, Confirmation::class.java)
        miIntent.putExtra("nombre", binding.boxNombre.text.toString())
        miIntent.putExtra("apellido", binding.boxApellido.text.toString())
        miIntent.putExtra("dni", binding.boxDNI.text.toString())
        miIntent.putExtra("gmail", binding.boxGMail.text.toString())
        miIntent.putExtra("contrasenia", binding.boxContrasenia.text.toString())
        startActivity(miIntent)
    }

    private fun controles(): Boolean {
        var valido = true
        if (TextUtils.isEmpty(binding.boxNombre.text.toString())) {
            Toast.makeText(this, "El campo 'Nombre' está vacío", Toast.LENGTH_SHORT).show()
            valido = false
        }
        if (TextUtils.isEmpty(binding.boxApellido.text.toString())) {
            Toast.makeText(this, "El campo 'Apellido' está vacío", Toast.LENGTH_SHORT).show()
            valido = false
        }
        if (TextUtils.isEmpty(binding.boxDNI.text.toString())) {
            Toast.makeText(this, "El campo 'DNI' está vacío", Toast.LENGTH_SHORT).show()
            valido = false
        }
        if (TextUtils.isEmpty(binding.boxGMail.text.toString())) {
            Toast.makeText(this, "El campo 'GMail' está vacío", Toast.LENGTH_SHORT).show()
            valido = false
        }
        if (TextUtils.isEmpty(binding.boxContrasenia.text.toString())) {
            Toast.makeText(this, "El campo 'Contraseña' está vacío", Toast.LENGTH_SHORT).show()
            valido = false
        }
        if (TextUtils.isEmpty(binding.boxContrasenia2.text.toString())) {
            Toast.makeText(this, "El campo 'Repetir contraseña' está vacío", Toast.LENGTH_SHORT).show()
            valido = false
        }
        if (binding.boxContrasenia.text.toString().length < 6){
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            valido = false
        }
        if (binding.boxContrasenia.text.toString() != binding.boxContrasenia2.text.toString()){
            Toast.makeText(this, "Las contraseñas introducidas no coinciden", Toast.LENGTH_SHORT).show()
            valido = false
        }
        for (p in AlmacenUsuarios.usuarios){
            if (binding.boxDNI.text.toString().equals(p.dni)){
                Toast.makeText(this, "Ya existe un usuario registrado con ese DNI", Toast.LENGTH_SHORT).show()
                valido = false
            }
        }

        return valido
    }
}