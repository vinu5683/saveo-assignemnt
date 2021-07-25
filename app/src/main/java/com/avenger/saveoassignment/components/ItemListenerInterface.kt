package com.avenger.saveoassignment.components

import android.widget.ImageView
import com.avenger.saveoassignment.datamodels.SampleResponse
import com.avenger.saveoassignment.showmodel.ShowModel

interface ItemListenerInterface {
    fun onItemClick(showModel: ShowModel,imageView: ImageView)
}