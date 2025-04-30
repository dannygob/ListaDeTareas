package com.example.listadetareas

import android.content.ContentValues
import android.content.Context

class CategoryDAO (private val context: Context) {

   private lateinit var  db: SQLiteDatabase

    private fun open () {
        db = DatabaseManager(context).writableDatabase
    }

    private fun close() {
        db.close()
    }

    //insertar
    fun insert(category: Category){
    // Gets the data repository in write mode
    open()

        // Create a new map of values, where column names are the keys

        val values = ContentValues()
            values.put(Category.COLUNM_NAME_TITLE, category.title)

        // Insert the new row, returning the primary key value of the new row
        val newRowId= db.insert (Category.TABLE_NAME, null, values)
    }


    //Actualizar

    //Borrar

    //Obtener un registro por Id

    //Obtener todos los Registros
}
