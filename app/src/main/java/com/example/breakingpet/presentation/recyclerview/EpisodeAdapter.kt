package com.example.breakingpet.presentation.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.breakingpet.R
import com.example.breakingpet.databinding.RecyclerEpisodeItemBinding
import com.example.breakingpet.domain.model.episodes.Episode
import javax.inject.Inject

class EpisodeAdapter @Inject constructor(
    private val episodesList: List<Episode>,
    private val itemClickListener: ItemClickListener,
    private val imagesUrlList: List<String>
) : RecyclerView.Adapter<EpisodeAdapter.EpisodeHolder>() {

    inner class EpisodeHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = RecyclerEpisodeItemBinding.bind(itemView)

        fun bind(episode: Episode) {
            binding.episodeTitle.text = episode.title

            Glide.with(itemView.context)
                .load(imagesUrlList[episode.episodeID-1])
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(R.drawable.character_without_photo)
                .into(binding.imageView)

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