package com.datastructblues.datatransferediting_2ndtask.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.datastructblues.datatransferediting_2ndtask.model.ElementModel

class MainViewModel : ViewModel() {
         val elements =MutableLiveData<ArrayList<ElementModel>>()

         fun createDummyData() {
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

                val elementList = arrayListOf(element0,element1,element2,
                        element3,element4,
                        element5,element6,
                        element7,element8,element9)
                setList(elementList)
        }


        private fun setList(elementList: ArrayList<ElementModel>){
                elements.value = elementList
        }


        fun setNewElement(resultElementModel: ElementModel) {
                resultElementModel?.let { elementModel ->
                        val matchedElement = (elements.value)?.find {
                            it.id == elementModel.id
                        }
                        if (matchedElement != null) {
                                matchedElement.text = elementModel.text
                        }
                        println(elements.value)
                }
        }

}

