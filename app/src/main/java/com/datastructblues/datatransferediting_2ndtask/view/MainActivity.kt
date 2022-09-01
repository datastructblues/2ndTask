package com.datastructblues.datatransferediting_2ndtask.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.datastructblues.datatransferediting_2ndtask.adapter.RecyclerAdapter
import com.datastructblues.datatransferediting_2ndtask.databinding.ActivityMainBinding
import com.datastructblues.datatransferediting_2ndtask.model.ElementModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var elementList:ArrayList<ElementModel>
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private val bundleEdited = "editedElement"
    private val bundleElement = "element"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createDummyData()
        launcher()
        recyclerOps()
        setNewElement()
    }

    private fun recyclerOps() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val recyclerAdapter = RecyclerAdapter(elementList)
        binding.recyclerView.adapter = recyclerAdapter
        recyclerAdapter.onItemClick = { elementModel ->
            val intent = Intent(this@MainActivity,SecondActivity::class.java)
            intent.putExtra(bundleElement, elementModel)
            activityResultLauncher.launch(intent)
            finish()
        }

    }

      private fun createDummyData() {
        val element0 = ElementModel(1, "umut1")
        val element1 = ElementModel(2, "umut2")
        val element2 = ElementModel(3, "umut3")
        val element3 = ElementModel(4, "umut4")
        val element4 = ElementModel(5, "umut5")
        val element5 = ElementModel(6, "umut6")
        val element6 = ElementModel(7, "umut7")
        val element7 = ElementModel(8, "umut8")
        val element8 = ElementModel(9, "umut9")
        val element9 = ElementModel(10, "umut10")

        elementList = arrayListOf(element0,element1,element2,
            element3,element4,
            element5,element6,
            element7,element8,element9)

    }

    private fun getNewElement(): ElementModel? {
        return intent.getSerializableExtra(bundleEdited) as ElementModel?

    }

  /*  private fun setNewElement() {
        val value = getNewElement()
        value?.let {
            for (i in 0 until elementList.size) {
                if (elementList[i].id ==value.id) {
                    elementList[i].text = value.text
                }
                println(elementList[i].text)
            }
        }
    }

   */


     private fun setNewElement() {
        val value = getNewElement()
        value?.let { elementModel ->
           val matchedElement=  elementList.find {
                it.id == elementModel.id
            }
            if (matchedElement != null) {
                matchedElement.text =elementModel.text
            }
        }
    }


    private fun launcher() {
      activityResultLauncher =
          registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
              if (result.resultCode == RESULT_OK) {
                  TODO()
              } else {
                  TODO()
              }

          }
  }
}
