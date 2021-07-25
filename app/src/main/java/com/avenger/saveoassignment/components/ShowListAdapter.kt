package com.avenger.saveoassignment.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.avenger.saveoassignment.R
import com.avenger.saveoassignment.showmodel.ShowModel
import com.bumptech.glide.Glide

class ShowListAdapter(
    private val list: ArrayList<ShowModel>,
    private val itemListenerInterface: ItemListenerInterface
) : RecyclerView.Adapter<ShowListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowListViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.show_list_item_layout, parent, false)
        return ShowListViewHolder(v, itemListenerInterface)
    }

    override fun onBindViewHolder(holder: ShowListViewHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class ShowListViewHolder(
    itemView: View,
    private val itemListenerInterface: ItemListenerInterface
) : RecyclerView.ViewHolder(itemView) {

    private val mImageView: ImageView = itemView.findViewById(R.id.itemImage)

    fun setData(showModel: ShowModel) {
        Glide.with(itemView.context).load(showModel.image?.medium).into(mImageView)
        mImageView.setOnClickListener {
            itemListenerInterface.onItemClick(showModel, mImageView)
        }
    }

}
