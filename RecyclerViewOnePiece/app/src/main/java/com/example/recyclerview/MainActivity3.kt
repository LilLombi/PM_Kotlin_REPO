package com.example.recyclerview

import Modelo.Almacen
import Modelo.Personaje
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerview.databinding.ActivityMain2Binding
import com.example.recyclerview.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    lateinit var binding: ActivityMain3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main2)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var p  = intent.getSerializableExtra("obj") as Personaje

        if (p.equals(null)){
            aniadirPersonaje()
        }else {
            modificarPersonaje(p)
        }

    }

    private fun modificarPersonaje(p: Personaje) {
        binding.btnAniadirNew.text = "Modificar"
        binding.txtNombreNew.text = p.nombre

        binding.btnAniadirNew.setOnClickListener{
            p.nombre = binding.txtNombreNew.text.toString()
            if (binding.rdgClase.checkedRadioButtonId.equals("rdbMarine")){
                p.clase = "Marine"
            }else if (binding.rdgClase.checkedRadioButtonId.equals("rdbPirata")){
                p.clase = "Pirata"
            }else{
                p.clase = "Otro"
            }
            p.imagen = p.clase.toLowerCase()
            Toast.makeText(this, "Personaje modificado con éxito", Toast.LENGTH_SHORT).show()
            finish()
        }

    }

    fun aniadirPersonaje(){
        binding.btnAniadirNew.setOnClickListener{
            if (binding.txtNombreNew.text != null && binding.rdgClase.checkedRadioButtonId != null){
                lateinit var clase: String
                var nombre = binding.txtNombreNew.text.toString()
                if (binding.rdgClase.checkedRadioButtonId.equals("rdbMarine")){
                    clase = "Marine"
                }else if (binding.rdgClase.checkedRadioButtonId.equals("rdbPirata")){
                    clase = "Pirata"
                }else{
                    clase = "Otro"
                }
                var existente = false
                for (p in Almacen.personajes){
                    if (p.nombre.equals(nombre) && p.clase.equals(clase)){
                        Toast.makeText(this,("Ya existe un " + nombre + " que es " + clase), Toast.LENGTH_SHORT).show()
                        existente = true
                    }
                }
                if (!existente){
                    var per = Personaje(nombre, clase, clase.toLowerCase())
                    finish()
                }

            } else {
                Toast.makeText(this,"Rellena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}