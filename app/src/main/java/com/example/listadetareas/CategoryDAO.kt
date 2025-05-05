package com.example.listadetareas

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.listadetareas.manager.DatabaseManager

class CategoryDAO (private val context: Context) {

    private lateinit var db: SQLiteDatabase

    private fun open() {
        db = DatabaseManager(context).writableDatabase
    }

    private fun close() {
        db.close()
    }

    //insertar
    fun insert(category: Category) {
        // Gets the data repository in write mode
        open()

        try{

        // Create a new map of values, where column names are the keys

        val values = ContentValues()
        values.put(Category.COLUMN_NAME_TITLE, category.title)

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db.insert(Category.TABLE_NAME, null, values)
        Log.i("DATABASE", "Insert a category with id: $newRowId")
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        close()
    }
}

    //Actualizar


fun udate (category: Category){
    open()

    try{
        // Create a new map of values, where column names are the keys
        val values = ContentValues()
        values.put (Category.COLUMN_NAME_TITLE,category.title)


        //which row to update, base on titleç

        val selection = "${Category.COLUMN_NAME_ID}= ${category.id}"

        val count = db.update (Category.TABLE_NAME, values, selection, null)

    Log.i("DATABASE", "Udate category with id: $newRowId")
} catch (e: Exception) {
            e.printStackTrace()
        } finally {
            close()
        }

    //Borrar

    //Obtener un registro por Id

    //Obtener todos los Registros
}
