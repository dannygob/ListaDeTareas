package com.example.listadetareas.activities

import android.content.Intent
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.text.style.IconMarginSpan
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadetareas.R
import com.example.listadetareas.R.menu
import com.example.listadetareas.adapters.TaskAdapter
import com.example.listadetareas.data.Category
import com.example.listadetareas.data.CategoryDAO
import com.example.listadetareas.data.Task
import com.example.listadetareas.data.TaskDAO
import com.example.listadetareas.databinding.ActivityTaskListBinding

class TaskListActivity : AppCompatActivity() {

    lateinit var binding: ActivityTaskListBinding

    lateinit var categoryDAO: CategoryDAO
    lateinit var category: Category

    lateinit var taskDAO: TaskDAO
    lateinit var taskList: List<Task>

    lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityTaskListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        categoryDAO = CategoryDAO(this)
        taskDAO = TaskDAO(this)

        val id = intent.getLongExtra("CATEGORY_ID", -1)
        category = categoryDAO.findById(id)!!
        taskList = emptyList()

        adapter = TaskAdapter(taskList, {position: Int ->
            val task = taskList[position]
            val intent = Intent(this, TaskActivity::class.java)
            intent.putExtra ( "CATEGORY_ID", category. id)
            intent.putExtra( "TASK_ID", task.id)
            // He hecho click en una tarea
        }, { position ->
            val task = taskList[position]
            task.done = !task.done
            taskDAO.update(task)
            reloadData()
        }, { position, v ->
            val popup= PopupMenu(this, v)
            popup.menuInflater.inflate(menu, popup.menu)

            popup.setOnMenuItemClickListener{menuItem: MenuItem ->
            return@setOnMenuItemClickListener when (menuItem.itemId) {
                R.id.action_edit -> {
                    val intent = Intent(this, TaskActivity::class.java)
                    intent.putExtra("CATEGORY_ID", category.id)
                    intent.putExtra("TASK_ID", taskList[position].id)
                    startActivity(intent)
                    Toast.makeText( this, "Editar", Toast.LENGTH_SHORT).show()
                    println("Editar -> $position")
                   true
                }

                R.id.action_delete -> {
                    Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
                    println("Delete-> $position")
                    taskDAO.delete(task)
                    reloadData()
                    true
            }
            else -> super.onContextItemSelected(menuItem)
            }

        }
           if (popup.menu is MenuBuilder) {
               val menuBuilder = popup.menu as MenuBuilder
                menuBuilder,sertOptionalIconsVisible(true)
               for (item in menuBuilder.visibleItems){
                   val iconMarginPx =
                       TypedValue.applyDimension(
                           TypedValue.COMPLEX_UNIT_DIP, IconMargin.toFloat(),resources.displayMetrics
                                   -toInt ( )
                           if (item. icon != null) ‹ |
                   if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                       item. icon = InsetDrawable(item.icon, iconMarginPx, 0, iconMarginPx,0)
                   }
                   else {item. icon =
                       object : InsetDrawable(item.icon, iconMarginPx, 0, iconMarginPx,
                           0)
                       override fun getIntrinsicWidth(): Int { |
                       return intrinsicHeight + iconMarginPx + iconMarginPx
                       )
               }
            }

            popup.show()
        })

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        supportActionBar?.title = category.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.addTaskButton.setOnClickListener {
            val intent = Intent(this, TaskActivity::class.java)
            intent.putExtra("CATEGORY_ID", category.id)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        reloadData()
    }
    fun reloadData() {
        taskList = taskDAO.findAllByCategory(category)
        adapter.updateItems(taskList)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.task_context_menu, menu)
        return true
    }
    //then, to handle the menu item click
    override fun onContextIntemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when (item.itemId) {
            R.id.action_edit-> {
                val task = taskList[info.position]
                taskDAO.delete(task)
                reloadData()
                true
            }
            R.id.action_delete-> {
                val task = taskList[info.position]
                taskDAO.delete(task)
                reloadData()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}