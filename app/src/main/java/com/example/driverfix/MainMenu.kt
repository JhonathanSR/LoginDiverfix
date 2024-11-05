package com.example.driverfix

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var imgSalir = findViewById<ImageView>(R.id.imgSalir)
        imgSalir.setOnClickListener {
            goExit()
        }
        var imgb1 = findViewById<ImageView>(R.id.imgb1)
        imgb1.setOnClickListener {
            goRegis()
        }
        var btnMusica = findViewById<Button>(R.id.btnMusica)
        btnMusica.setOnClickListener {
            goMusic()
        }
    }
    private fun goExit(){
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
    private fun goRegis(){
        val i = Intent(this, MainRegister::class.java)
        startActivity(i)
    }
    private fun goMusic(){
        val i = Intent(this, MainMusica::class.java)
        startActivity(i)
    }

}