package com.example.listadetareas.manager

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.listadetareas.data.Category

class DatabaseManager(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        // If you change the database schema, yu must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "ToDoList"

        private const val SQL_CREATE_CATEGORY =
            "CREATE TABLE ${Category.TABLE_NAME} (" + "${Category.COLUMN_NAME_ID} INTEGER PRIMARY KEY, " + "${Category.COLUMN_NAME_TITLE} TEXT)"

        private const val SQL_DELETE_CATEGORY = "DROP TABLE IF EXISTS ${Category.TABLE_NAME}"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_CATEGORY)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onDestroy(db)
        onCreate(db)
    }

    fun onDestroy(db: SQLiteDatabase) {
        db.execSQL(SQL_DELETE_CATEGORY)
    }
}