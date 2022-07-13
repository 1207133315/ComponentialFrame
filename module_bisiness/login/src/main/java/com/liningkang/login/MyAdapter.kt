package com.liningkang.login

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(context:Context): RecyclerView.Adapter<MyAdapter.ViewHolder>() {
   private var context=context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
       return 100
    }
    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

    }
}