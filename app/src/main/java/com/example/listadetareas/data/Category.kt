package com.example.listadetareas.data

data class Category(
    val id: Long,
    val title: String
)
{

    companion object {
        const val TABLE_NAME = "Categories"
        const val COLUMN_NAME_ID = "ID"
        const val COLUMN_NAME_TITLE = "title"

    }
}
