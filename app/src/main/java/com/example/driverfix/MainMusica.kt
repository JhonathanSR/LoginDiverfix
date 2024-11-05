package com.example.driverfix

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.driverfix.databinding.ActivityMainMusicaBinding

class MainMusica : AppCompatActivity() {
    lateinit var  registradosDBHelper: mySQLiteHelper
    lateinit var binding: ActivityMainMusicaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_musica)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var imgB2 = findViewById<ImageView>(R.id.imgB2)
        imgB2.setOnClickListener {
            goMenu()
        }
        var imgNext1 = findViewById<ImageView>(R.id.imgNext1)
        imgNext1.setOnClickListener {
            goDepor()
        }
        registradosDBHelper = mySQLiteHelper(this)

        val edtNameEvent = findViewById<EditText>(R.id.edtNameEvent)
        val edtLocation = findViewById<EditText>(R.id.edtLocation)
        val edtDate = findViewById<EditText>(R.id.edtDate)
        val edtInfo = findViewById<EditText>(R.id.edtInfo)
        val btnCrear = findViewById<Button>(R.id.btnCrear)
        val btnConsultar = findViewById<Button>(R.id.btnConsultar)

        btnCrear.setOnClickListener {
            val nameEvent = edtNameEvent.text.toString()
            val location = edtLocation.text.toString()
            val fecha = edtDate.text.toString()
            val edtInfo = edtInfo.text.toString()


            if (nameEvent.isNotEmpty() && location.isNotEmpty() && fecha.isNotEmpty() && edtInfo.isNotEmpty()) {
                val exito = registradosDBHelper.agregarEvento(nameEvent, location, fecha, edtInfo)
                if (exito) {
                        Toast.makeText(this, "Evento guardado correctamente", Toast.LENGTH_SHORT).show()
                } else {
                        Toast.makeText(this, "Error al guardar el evento", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                }
        }


        /*btnConsultar.setOnClickListener {
            val registrados = registradosDBHelper.obtenerEvento()
            if (registrados.isNotEmpty()) {
                for (registrados in registrados) {
                    Toast.makeText(this, MainMusica, Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "No hay eventos registrados", Toast.LENGTH_SHORT).show()
            }
        }*/


    }

    private fun goMenu(){
        val i = Intent(this, MainMenu::class.java)
        startActivity(i)
    }
    private fun goDepor(){
        val i = Intent(this, MainDeport::class.java)
        startActivity(i)
    }

}