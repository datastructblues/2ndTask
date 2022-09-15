package com.datastructblues.datatransferediting_2ndtask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.datastructblues.datatransferediting_2ndtask.databinding.RecyclerRowBinding
import com.datastructblues.datatransferediting_2ndtask.model.ElementModel


class RecyclerAdapter(): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    var onItemClick: ((ElementModel) -> Unit)? = null
    private val diffCallback = object : DiffUtil.ItemCallback<ElementModel>(){

        override fun areItemsTheSame(oldItem: ElementModel, newItem: ElementModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ElementModel, newItem: ElementModel): Boolean {
              return oldItem.text == newItem.text
        }

    }
        //her seferinde copy bir object ile amac dısı kullanmıs olmuyor muyum? hep degistiği için hep update olacak?
     fun submitList(list: ArrayList<ElementModel>?) {
        val listCopy =
            mutableListOf<ElementModel>().apply {
                list?.map {
                    add(ElementModel(it.id,it.text))
                }
            }
         differ.submitList(listCopy)
         }

            //this won't work its not mutable?

    /*      fun submitList(list:ArrayList<ElementModel>){
                differ.submitList(list)
            }

     */



    private val differ = AsyncListDiffer<ElementModel>(this, diffCallback)


    inner class ViewHolder(val binding: RecyclerRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.textView.setOnClickListener {
                onItemClick?.invoke(differ.currentList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textView.text = differ.currentList[position].text
        /*     holder.binding.textView.setOnClickListener {
           // asla applicationcontext uzerınden erısılmemeli cunku cok genel bir context bu.
          //  val intent = Intent(holder.itemView.context.applicationContext,SecondActivity::class.java)
           intent.putExtra("element", elementList[position])
            println(position)
            holder.itemView.context.startActivity(intent)


        }

    */

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}