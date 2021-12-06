package com.example.androidlistviewtodo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import com.example.androidlistviewtodo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = result.data?.getStringExtra("task_text")!!
            listItems.add(task)
            adapter.notifyDataSetChanged()
        }
    }
    private val listItems = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.longpress, menu)
    }


    private fun deleteTask(item: MenuItem){
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        listItems.removeAt(info.position)
        adapter.notifyDataSetChanged()
    }

    private fun createTask(){
        val intent = Intent(this,TaskEditActivity::class.java)
        resultLauncher.launch(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        adapter = ArrayAdapter<String>(this,R.layout.list_layout, listItems)
        binding.content.listView.adapter = adapter

        registerForContextMenu(binding.content.listView)


    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            R.id.menu_delete -> {
                deleteTask(item)
                return true
            }
        }
        return super.onContextItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.menu_insert -> {
                createTask()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}