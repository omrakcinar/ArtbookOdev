package com.omerakcinar.artbookodev

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.omerakcinar.artbookodev.databinding.FragmentShowArtBinding
import com.omerakcinar.artbookodev.databinding.RecyclerRowBinding

class ArtAdapter (var artList : ArrayList<Art>): RecyclerView.Adapter<ArtAdapter.ArtHolder>() {
    class ArtHolder (val binding : RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArtHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtHolder, position: Int) {
        holder.binding.recyclerViewTextView.text = artList.get(position).name
        holder.itemView.setOnClickListener {

            val action = ArtListFragmentDirections.actionArtListFragmentToShowArtFragment(artList.get(position).id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return artList.size
    }
}