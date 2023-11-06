package com.example.simonsays

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.simonsays.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun jugar() {
        var gameover = false
        var ronda = 1
        var secuenciaCPU = ArrayList<Int>()
        var secuenciaPlayer = ArrayList<Int>()

        while (!gameover){
            for (i in 1..ronda){
                binding.txtRonda.text = "Ronda " + ronda
                secuenciaCPU.add(Random.nextInt(1, 4))
            }
            mostrarPatronSimon(secuenciaCPU)
            //Dejar que el jugador introduzca una secuencia de colores

            if (!secuenciaCPU.equals(secuenciaPlayer)){
                binding.txtRonda.text = "Perdiste!"
                gameover = true
            } else {
                ronda++
            }
        }
    }

    private fun mostrarPatronSimon(secuenciaCPU: ArrayList<Int>) {
        for (i in secuenciaCPU.indices) {
            val button = secuenciaCPU[i]
            val delayMillis = (i + 1) * 1000L
            handler.postDelayed({
                when (button) {
                    1 -> {
                        binding.imgBtnBlue.setImageResource(R.drawable.offblue)
                        handler.postDelayed({
                            binding.imgBtnBlue.setImageResource(R.drawable.onblue)
                        }, 500)
                    }
                    2 -> {
                        binding.imgBtnGreen.setImageResource(R.drawable.offgreen)
                        handler.postDelayed({
                            binding.imgBtnGreen.setImageResource(R.drawable.ongreen)
                        }, 500)
                    }
                    3 -> {
                        binding.imgBtnRed.setImageResource(R.drawable.offred)
                        handler.postDelayed({
                            binding.imgBtnRed.setImageResource(R.drawable.onred)
                        }, 500)
                    }
                    4 -> {
                        binding.imgBtnYellow.setImageResource(R.drawable.offyellow)
                        handler.postDelayed({
                            binding.imgBtnYellow.setImageResource(R.drawable.onyellow)
                        }, 500)
                    }
                }
            }, delayMillis)
        }
    }
}