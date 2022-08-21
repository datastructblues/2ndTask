package com.datastructblues.datatransferediting_2ndtask.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.datastructblues.datatransferediting_2ndtask.databinding.RecyclerRowBinding
import com.datastructblues.datatransferediting_2ndtask.model.ElementModel
import com.datastructblues.datatransferediting_2ndtask.view.MainActivity
import com.datastructblues.datatransferediting_2ndtask.view.SecondActivity

class RecyclerAdapter(private val elementList: ArrayList<ElementModel>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(val binding: RecyclerRowBinding):RecyclerView.ViewHolder(binding.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textView.text = elementList[position].text

        holder.binding.textView.setOnClickListener {
            val intent = Intent(holder.itemView.context.applicationContext,SecondActivity::class.java)
            intent.putExtra("position",position)
            println(position)
            holder.itemView.context.startActivity(intent)
         //   (holder.itemView.context as Activity).finish()
        }

    }

    override fun getItemCount(): Int {
        return elementList.size
    }

}