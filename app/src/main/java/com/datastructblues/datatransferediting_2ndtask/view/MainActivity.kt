package com.datastructblues.datatransferediting_2ndtask.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.datastructblues.datatransferediting_2ndtask.adapter.RecyclerAdapter
import com.datastructblues.datatransferediting_2ndtask.databinding.ActivityMainBinding
import com.datastructblues.datatransferediting_2ndtask.model.ElementModel
import com.datastructblues.datatransferediting_2ndtask.viewmodel.MainViewModel
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
    private val viewModel by lazy {ViewModelProvider(this) [MainViewModel::class.java] }
    private lateinit var binding: ActivityMainBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private val bundle_element = "element"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeVM()
        launcher()
        recyclerOps()
    }

    private fun recyclerOps() {
        binding.recyclerView.layoutManager = LinearLayoutPagerManager(this,RecyclerView.HORIZONTAL,false,2)
        val recyclerAdapter = viewModel.elements.value?.let { RecyclerAdapter(it) }
        binding.recyclerView.adapter = recyclerAdapter
        recyclerAdapter?.let {
            observeLiveData(recyclerAdapter)
            recyclerAdapter.onItemClick = { elementModel ->
                activityResultLauncher.launch(Intent(this, SecondActivity::class.java).apply {
                    putExtra(bundle_element, elementModel)

                })
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun launcher() {
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                viewModel.setNewElement(result.data?.getSerializableExtra(bundle_element) as ElementModel)
                observeLiveData(binding.recyclerView.adapter as RecyclerAdapter)

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

    private fun initializeVM(){
        viewModel.createDummyData()
    }

    private fun observeLiveData(recyclerAdapter: RecyclerAdapter){
        viewModel.elements.observe(this, Observer {
                recyclerAdapter.submitList(it)
                binding.recyclerView.adapter = recyclerAdapter
        })
    }
}
