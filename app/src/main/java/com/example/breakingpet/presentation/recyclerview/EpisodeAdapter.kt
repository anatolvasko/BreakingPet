package com.example.breakingpet.presentation.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.breakingpet.R
import com.example.breakingpet.databinding.RecyclerEpisodeItemBinding
import com.example.breakingpet.domain.model.characters.Character
import com.example.breakingpet.domain.model.episodes.Episode
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import javax.inject.Inject

class EpisodeAdapter @Inject constructor(
    private val episodesList: List<Episode>,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<EpisodeAdapter.EpisodeHolder>() {

    private val imageRef = FirebaseStorage.getInstance().reference

    inner class EpisodeHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = RecyclerEpisodeItemBinding.bind(itemView)

        fun bind(episode: Episode) {
            binding.episodeTitle.text = episode.title

            val a = episode.episodeID

            imageRef
                .child("images/${a}.jpeg")
                .downloadUrl
                .addOnSuccessListener {
                    Glide.with(itemView.context)
                        .load(it.toString())
                        .placeholder(R.drawable.character_without_photo)
                        .into(binding.imageView)
                }.addOnFailureListener {
                    Toast.makeText(
                        itemView.context,
                        "Poster for ${episode.title} is not downloaded",
                        Toast.LENGTH_LONG
                    ).show()
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_episode_item, parent, false)
        return EpisodeHolder(view)
    }

    override fun onBindViewHolder(holder: EpisodeHolder, position: Int) {
        holder.bind(episodesList[position])

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(episodesList[position])
        }
    }

    override fun getItemCount(): Int {
        return episodesList.size
    }

    interface ItemClickListener {
        fun onItemClick(episode: Episode)
    }
}