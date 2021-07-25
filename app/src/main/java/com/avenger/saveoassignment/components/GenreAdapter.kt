package com.avenger.saveoassignment.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.avenger.saveoassignment.R

class GenreAdapter(val list: ArrayList<String>) : RecyclerView.Adapter<GenreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.genre_layout, parent, false)
        return GenreViewHolder(v)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val mTvGenre = itemView.findViewById<TextView>(R.id.tvGenre)

    fun setData(s: String) {
        mTvGenre.text = s
    }

}
