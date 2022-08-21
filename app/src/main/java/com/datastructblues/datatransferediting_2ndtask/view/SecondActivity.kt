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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

  /*  fun editData(view: View){
        val newData = getData()

        newData?.let {
            val newElement = ElementModel(newData,binding.elementName.text.toString())
            val intent = Intent(this@SecondActivity,MainActivity::class.java)
            intent.putExtra("newItem",newElement)
            startActivity(intent)
        }

   */

    private fun getData():Int{
        val degisken = intent.extras
        degisken?.let {
            println(degisken.isEmpty)
            return degisken.getInt("position",-1)
        }
        return -1
    }

    fun editData(view: View) {
        val newPosition = getData() + 1
        val newText=binding.elementName.text.toString()
            val intent = Intent(this@SecondActivity, MainActivity::class.java)
            intent.putExtra("newText",newText)
            intent.putExtra("posToMain", newPosition)
            startActivity(intent)
            println("hello hello")
            finish()
        }
    }