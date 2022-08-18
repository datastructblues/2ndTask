package com.datastructblues.datatransferediting_2ndtask.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.datastructblues.datatransferediting_2ndtask.adapter.RecyclerAdapter
import com.datastructblues.datatransferediting_2ndtask.databinding.ActivityMainBinding
import com.datastructblues.datatransferediting_2ndtask.model.ElementModel

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private lateinit var elementList: ArrayList<ElementModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createDummyData()
        recyclerOps()
        getData()

    }

    private fun recyclerOps() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        val recyclerAdapter = RecyclerAdapter(elementList)
        binding.recyclerView.adapter = recyclerAdapter
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

 /*   private fun sendElementList(){
        val intent = Intent(this@MainActivity,SecondActivity::class.java)
        intent.putExtra("list",elementList)
    }

  */

  /*  private fun getNewItem():ElementModel{
            val degisken = intent.extras
            degisken?.let {
              return degisken.get("newItem") as ElementModel
            }
        return degisken as ElementModel

        }
   */
    private fun replaceItem(){
        val id = getData()
        val newElementText = intent.getStringExtra("newText")
      newElementText?.let {
          val newElement= ElementModel(id,newElementText)
          elementList.set(id,newElement)
      }
    }
    private fun getData():Int{
        val degisken = intent.extras
        degisken?.let {
            return degisken.getInt("position",-1)
        }
        return -1
    }
}
