package com.datastructblues.datatransferediting_2ndtask.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import com.datastructblues.datatransferediting_2ndtask.databinding.ActivitySecondBinding
import com.datastructblues.datatransferediting_2ndtask.model.ElementModel

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var newElement:ElementModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        newElement = getData()
    }

    private fun getData():ElementModel{
        val intent = intent
        var selectedElement = intent.getSerializableExtra("element") as ElementModel
        binding.elementName.setText(selectedElement.text)
        return selectedElement
    }

    fun editData(view: View) {
        val newPosition = newElement.id
        val newText=binding.elementName.text.toString()
            val intent = Intent(this@SecondActivity, MainActivity::class.java)
            intent.putExtra("editedElement",ElementModel(newPosition,newText))
            startActivity(intent)
            println("hello hello")
            finish()
        }
    }