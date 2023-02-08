package com.example.multipleselectionrecycleview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.multipleselectionrecycleview.databinding.RawItemBinding

class TextAdapter(var list: ArrayList<TextDataClass>) :
    RecyclerView.Adapter<TextAdapter.ViewHolder>() {

    var callBack: ((TextDataClass) -> Unit)? = null

    inner class ViewHolder(var binding: RawItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(textDataClass: TextDataClass) {
            if (textDataClass.isSelected) {
                binding.textViewName.background =
                    ContextCompat.getDrawable(itemView.context, R.color.white)
                binding.textViewName.setTextColor(itemView.resources.getColor(R.color.black))
            } else {
                binding.textViewName.background =
                    ContextCompat.getDrawable(itemView.context, R.color.black)
                binding.textViewName.setTextColor(itemView.resources.getColor(R.color.white))
            }
            binding.textViewName.text = textDataClass.name
            binding.root.setOnClickListener {
                callBack?.invoke(textDataClass)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RawItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    fun Multiple(textDataClass: TextDataClass) {
        for (i in list) {
            if (i == textDataClass) {
                i.isSelected = !i.isSelected
            }
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}