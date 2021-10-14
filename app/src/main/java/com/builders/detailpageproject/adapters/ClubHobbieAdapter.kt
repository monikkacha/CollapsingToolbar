package com.builders.detailpageproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.builders.detailpageproject.R
import com.builders.detailpageproject.model.ClubHobbies

class ClubHobbieAdapter : RecyclerView.Adapter<ClubHobbieAdapter.ViewHolder>() {

    lateinit var dataList: MutableList<ClubHobbies>
    lateinit var context: Context

    fun addItems(context: Context, dataList: MutableList<ClubHobbies>) {
        this.context = context
        this.dataList = dataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.club_hobbies_item , parent , false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val singleHobbie = dataList[position]
        holder.hobbieText.text = singleHobbie.title!!
        holder.hobbiesIconImg.setImageResource(singleHobbie.icon!!)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hobbiesIconImg = itemView.findViewById<ImageView>(R.id.hobbies_icon_img)
        val hobbieText = itemView.findViewById<TextView>(R.id.hobbies_name_tv)
    }
}