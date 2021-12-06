package com.example.androidlistviewtodo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.androidlistviewtodo.databinding.ActivityTaskEditBinding

class TaskEditActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTaskEditBinding

    fun onSaveClick(view: View){
        val intent = Intent()
        intent.putExtra("task_text", binding.edTask.text.toString())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskEditBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}