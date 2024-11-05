package com.example.driverfix

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class mySQLiteHelper (context: Context): SQLiteOpenHelper(
    context, "registrados.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val ordenCrear = "CREATE TABLE registrados (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," + "contrasena TEXT," + "nameEvent TEXT," +
                "location TEXT," + "fecha TEXT," + "edtInfo TEXT)"
        db!!.execSQL(ordenCrear)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val ordenBorrar = "DROP TABLE IF EXISTS registrados"
        db!!.execSQL(ordenBorrar)
        onCreate(db)
    }

    fun anadirdato(nombre: String, contrasena: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("nombre", nombre)
            put("contrasena", contrasena)

        }

        val result = db.insert("registrados", null, contentValues)
        Log.d("mySQLiteHelper", "Insert result: $result")
        db.close()
        return result != -1L
    }


    fun obtenerDatos(nombre: String, contrasena: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM registrados WHERE nombre = ? AND contrasena = ?"
        val cursor = db.rawQuery(query, arrayOf(nombre, contrasena))
        val count = cursor.count
        cursor.close()
        return count > 0
    }

    fun modificarDatos(nombre: String, nuevaContrasena: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("contrasena", nuevaContrasena)
        val result = db.update("registrados", contentValues, "nombre = ?", arrayOf(nombre))
        db.close()
        return result > 0
    }

    fun eliminarDatos(nombre: String): Boolean {
        val db = this.writableDatabase
        val result = db.delete("registrados", "nombre = ?", arrayOf(nombre))
        db.close()
        return result > 0
    }

    fun agregarEvento(
        nameEvent: String, location: String,
        fecha: String, edtInfo: String
    ): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("nameEvent", nameEvent)
            put("location", location)
            put("fecha", fecha)
            put("edtInfo", edtInfo)
        }
        val result = db.insert("registrados", null, contentValues)
        db.close()
        return result != -1L
    }
    fun obtenerEvento(): List<String> {
        val db = this.readableDatabase
        val eventos = mutableListOf<String>()
        val cursor = db.query("registrados", null, null, null, null, null, null)

        if (cursor.moveToFirst()) {
            do {
                val nameEvent = cursor.getString(cursor.getColumnIndexOrThrow("nameEvento"))
                val location = cursor.getString(cursor.getColumnIndexOrThrow("location"))
                val fecha = cursor.getString(cursor.getColumnIndexOrThrow("fecha"))
                val edtInfo = cursor.getString(cursor.getColumnIndexOrThrow("edtInfo"))
                eventos.add("nameEvent: $nameEvent, location: $location, Fecha: $fecha, edtInfo: $edtInfo")
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return eventos
    }


}












