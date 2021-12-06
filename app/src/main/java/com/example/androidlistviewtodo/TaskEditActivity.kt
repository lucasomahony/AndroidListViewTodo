package com.example.androidlistviewtodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidlistviewtodo.databinding.ActivityTaskEditBinding

class TaskEditActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTaskEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskEditBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}