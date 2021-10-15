package com.builders.detailpageproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.builders.detailpageproject.R
import com.builders.detailpageproject.model.DummyData

class EventAdapter(val context: Context) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    private lateinit var data: MutableList<DummyData>

    fun addData(data: MutableList<DummyData>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.event_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val singleItem = data[position]
        holder.eventDateTextView.text = singleItem.eventDate
        holder.eventTitleTextView.text = singleItem.eventTitle
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventImageView = itemView.findViewById<ImageView>(R.id.event_img)
        val eventDateTextView = itemView.findViewById<TextView>(R.id.event_date_tv)
        val eventTitleTextView = itemView.findViewById<TextView>(R.id.event_title_tv)
    }

}