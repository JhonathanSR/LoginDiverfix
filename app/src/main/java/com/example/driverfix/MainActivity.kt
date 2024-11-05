package com.example.driverfix

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.driverfix.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var  binding: ActivityMainBinding
    lateinit var registradosDBHelper: mySQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        registradosDBHelper = mySQLiteHelper(this)
        val  btnRegis = findViewById<Button>(R.id.btnRegis)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            loginUser()
        }
        btnRegis.setOnClickListener {
            goRegist()
        }
    }
    private fun loginUser(){
        val nombre = findViewById<EditText>(R.id.edtNombre).text.toString()
        val contrasena = findViewById<EditText>(R.id.edtPass).text.toString()

        Log.d("LoginUser", "Nombre: $nombre, Contraseña: $contrasena")

        if (nombre.isNotEmpty() && contrasena.isNotEmpty()) {
            val resultado = registradosDBHelper.obtenerDatos(nombre, contrasena)

            Log.d("LoginUser", "User login resultado: $resultado")

            if (resultado) {
                Toast.makeText(this, "Inicio de Sesión Exitoso, " +
                        "Bienvenido a DiverFix",
                    Toast.LENGTH_SHORT).show()
                findViewById<EditText>(R.id.edtNombre).setText("")
                findViewById<EditText>(R.id.edtPass).setText("")

                val i = Intent(this, MainMenu::class.java)
                startActivity(i)
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos," +
                        "Intente nuevamente",
                    Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Complete todos los campos," +
                    "de lo contrario no podra acceder",
                Toast.LENGTH_LONG).show()
        }
    }
    private fun goRegist(){
        val i = Intent(this, MainRegister::class.java)
        startActivity(i)
    }
}
