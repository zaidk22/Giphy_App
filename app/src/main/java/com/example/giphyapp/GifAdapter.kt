package com.example.giphyapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class GifAdapter(val context: Context,val gifs :List<DataObject>) : RecyclerView.Adapter<GifAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_single,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val data = gifs[position]
        Glide.with(context).load(data.images.ogImage.url).into(holder.image)
    }

    override fun getItemCount(): Int {
    return gifs.size
    }
    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
val image = itemView.findViewById<ImageView>(R.id.imageView)
    }
}