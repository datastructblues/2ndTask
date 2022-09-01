package com.datastructblues.datatransferediting_2ndtask.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.datastructblues.datatransferediting_2ndtask.databinding.ActivitySecondBinding
import com.datastructblues.datatransferediting_2ndtask.model.ElementModel

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var newElement:ElementModel
    private val bundle_element = "element"
    private val bundle_edited_element = "editedElement"
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        newElement = getData()

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
               TODO()
            }
            else{
                TODO()
            }
        }

    }


   private fun getData():ElementModel{
        val intent = intent
        var selectedElement = intent.getSerializableExtra(bundle_element) as ElementModel
        binding.elementName.setText(selectedElement.text)
        return selectedElement
    }



    fun editData(view: View) {
        val newPosition = newElement.id
        val newText=binding.elementName.text.toString()
            val intent = Intent(this@SecondActivity, MainActivity::class.java)
            intent.putExtra(bundle_edited_element,ElementModel(newPosition,newText))
            activityResultLauncher.launch(intent)
            finish()

     }
}