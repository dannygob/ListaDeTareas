package com.example.listadetareas

import android.app.Dialog
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding =
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        categoryDAO= CategoryDAO(this)
        categoryList = categoryDAOfind.all
        adapter = CategoryAdapter(categoryList) {

        },{   position

        }

        binding.reciclerView.adapter =adapter

        binding.reciclerView.layoutManager= LinearLayoutManager(this)
        binding.addCategoryButton.setClick
    }

    fun showCtehoryDoalo() {

        val dialogBinding = DialogCreateCategoryBinding.Inflate(layoutInflater)

        if (category.id != -1L){
            dialogBinding.title
        }
        AlertDialog.Builder(this)
            .setTitle("Create category")
            .setView(dialogBinding.root)
            .setPositiveButtonIcon(android.R.string.ok, { dialog, which->
                val title= dialogBinding.titleEditText.text.toString
                category.title = dialogBinding.titleEditText.text.toStr
//                val category =Category(-1, title)

            })
            .setNegativeButton (android.R.string.cancel, null)
            .setIcon(R.drawable.ic_add)
            .show()

        fun loadData(){
            categoryList = categoryDAO.findAll
        }


    }
}