package com.datastructblues.datatransferediting_2ndtask.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.datastructblues.datatransferediting_2ndtask.adapter.RecyclerAdapter
import com.datastructblues.datatransferediting_2ndtask.databinding.ActivityMainBinding
import com.datastructblues.datatransferediting_2ndtask.model.ElementModel
import kotlin.math.roundToInt


/*   OUTPUT
  -Main Activity'i devamlı finishlememeyi ogrendim.
  -Bundle var kullanmayı ogrendim.
  -Resultcontractlar ile diger activitylerin sonucunu bekleyebildigimi ogrendim.
  -find gibi list func kullanmayı ogrendım
  -recyclerview logicini activityden yonetmeyi ogrendim( henuz custom listener eklemeyi denemedim)
  -custom layoutmanager kullanımı gordum code copypaste(stackoverflow)
 */

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var elementList:ArrayList<ElementModel>
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private val bundle_element = "element"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createDummyData()
        launcher()
        recyclerOps()
    }

    private fun recyclerOps() {
        binding.recyclerView.layoutManager = LinearLayoutPagerManager(this,RecyclerView.HORIZONTAL,false,2)
        val recyclerAdapter = RecyclerAdapter(elementList)
        binding.recyclerView.adapter = recyclerAdapter

        recyclerAdapter.onItemClick = { elementModel ->
            activityResultLauncher.launch(Intent(this, SecondActivity::class.java).apply {
                putExtra(bundle_element, elementModel)
            })
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

   /* private fun getNewElement(): ElementModel? {
        return intent.getSerializableExtra(bundle_element) as ElementModel?

    }

    */

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


    private fun setNewElement(resultElementModel: ElementModel) {
        resultElementModel?.let { elementModel ->
            val matchedElement = elementList.find {
                it.id == elementModel.id
            }
            if (matchedElement != null) {
                matchedElement.text = elementModel.text
            }
            println(elementList)
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun launcher() {
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                setNewElement(result.data?.getSerializableExtra(bundle_element) as ElementModel)
                binding.recyclerView.adapter?.notifyDataSetChanged()
            }
        }
  }
  //customLinearLayoutManager
    class LinearLayoutPagerManager(context: Context, orientation: Int, reverseLayout: Boolean, private val itemsPerPage: Int) : LinearLayoutManager(context,orientation,reverseLayout) {

        override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
            return super.checkLayoutParams(lp) && lp!!.width == getItemSize()
        }

        override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
            return setProperItemSize(super.generateDefaultLayoutParams())
        }

        override fun generateLayoutParams(lp: ViewGroup.LayoutParams): RecyclerView.LayoutParams {
            return setProperItemSize(super.generateLayoutParams(lp))
        }

        private fun setProperItemSize(lp: RecyclerView.LayoutParams): RecyclerView.LayoutParams {
            val itemSize = getItemSize()
            if (orientation == HORIZONTAL) {
                lp.width = itemSize
            } else {
                lp.height = itemSize
            }
            return lp
        }

        private fun getItemSize(): Int {
            val pageSize = if (orientation == HORIZONTAL) width else height
            return (pageSize.toFloat() / itemsPerPage).roundToInt()
        }


    }
    //umut
}
