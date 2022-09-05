package com.datastructblues.datatransferediting_2ndtask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.datastructblues.datatransferediting_2ndtask.databinding.RecyclerRowBinding
import com.datastructblues.datatransferediting_2ndtask.model.ElementModel



class RecyclerAdapter(private val elementList: ArrayList<ElementModel>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    var onItemClick: ((ElementModel) -> Unit)? = null


   inner class ViewHolder(val binding: RecyclerRowBinding) :RecyclerView.ViewHolder(binding.root) {
       init {
           binding.textView.setOnClickListener {
               onItemClick?.invoke(elementList[adapterPosition])
           }
       }
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textView.text = elementList[position].text
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
        return elementList.size
    }

}