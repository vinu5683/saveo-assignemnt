package com.avenger.saveoassignment.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.avenger.saveoassignment.R
import com.avenger.saveoassignment.datamodels.SampleResponse
import com.bumptech.glide.Glide

class SliderAdapter(val list: ArrayList<SampleResponse>, val viewPager2: ViewPager2) :
    RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mImageView = itemView.findViewById<ImageView>(R.id.ivImageItem)
        fun setImage(sampleResponse: SampleResponse) {
            try {
                Glide.with(itemView.context).load(sampleResponse.show?.image?.original)
                    .into(mImageView)
            } catch (e: Exception) {

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.viewpager_image_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.setImage(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}