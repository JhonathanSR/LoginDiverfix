package com.example.driverfix

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.driverfix.databinding.ActivityMainRegisterBinding

class MainRegister : AppCompatActivity() {
    lateinit var binding: ActivityMainRegisterBinding
    lateinit var registradosDBHelper: mySQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityMainRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        registradosDBHelper = mySQLiteHelper(this)
        val btnRegister: Button = findViewById(R.id.btnRegister)
        btnRegister.setOnClickListener {
            goRegisUser()
        }

        var imgBack = findViewById<ImageView>(R.id.imgBack)
        imgBack.setOnClickListener {
            goInic()
        }
    }
    private fun goRegisUser(){
        val nombre = findViewById<EditText>(R.id.edTNombre).text.toString()
        val contrasena = findViewById<EditText>(R.id.edTPass2).text.toString()

        Log.d("GoRegisterUser",  "Nombre: $nombre, Contrasena: $contrasena")
        if (nombre.isNotEmpty() && contrasena.isNotEmpty()){
            val registradosDBHelper = mySQLiteHelper(this)
            val resultado =  registradosDBHelper.anadirdato(nombre, contrasena)

            Log.d("GoRegisterUser", "User registration resultado: $resultado")
            if (resultado) {
                Toast.makeText(
                    this, "Usuario Registrado con Exito",
                    Toast.LENGTH_SHORT
                ).show()
                findViewById<EditText>(R.id.edTNombre).setText("")
                findViewById<EditText>(R.id.edTPass2).setText("")
            } else {
                Toast.makeText(
                    this, "Usuario y contraseña incorrectos," +
                            " intente nuevamente",
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            Toast.makeText(
                this, "Complete todos los campos," +
                        " por lo contrario no podra acceder",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun goInic(){
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}