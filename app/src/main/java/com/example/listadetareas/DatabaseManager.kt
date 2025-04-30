package com.example.listadetareas

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class DatabaseManager (context: Context):SQliteOpenHelper(context, DATABASE_NAME)


companion Object{

    //
    const val Database_VERSION = 1
    const val Database_NAME = "ToDoList.db"

    private const val SQL_CREATE_EMTRIES =

    private const val SQL_DELETE_CATEGORY = "DROP TABLE IF EXISTS ${Category.TABLE_NAME}"
    "CREATE TABLE "

}

override fun onCreate(db:SQLiteDatabase){

    db.execSQL(SQL_CREATE_CATEGORY)
}

override fun OnUpgrade(db: SQLiteDatabase,oldVersion: Int, newVersion: Int){

    onDestroy(db)
    onCreate(db)
}