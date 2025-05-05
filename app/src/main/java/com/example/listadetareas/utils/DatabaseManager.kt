package com.example.listadetareas.utils

import android.database.sqlite.SQLiteDatabase

private fun OnDestroy(db: SQLiteDatabase){
        db.execSQL(SQL_DELETE_CATEGORY)
    }
}
