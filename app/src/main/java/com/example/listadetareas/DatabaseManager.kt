package com.example.listadetareas

import android.database.sqlite.SQLiteDatabase

private fun OnDestroy(db: SQLiteDatabase){
        db.execSQL(SQL_DELETE_CATEGORY)
    }
}
