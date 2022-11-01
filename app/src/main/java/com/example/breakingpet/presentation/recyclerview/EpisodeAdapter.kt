package com.example.breakingpet.presentation.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.breakingpet.R
import com.example.breakingpet.databinding.RecyclerCharacterItemBinding
import com.example.breakingpet.databinding.RecyclerEpisodeItemBinding
import com.example.breakingpet.domain.model.characters.Character
import com.example.breakingpet.domain.model.episodes.Episode

class EpisodeAdapter (private val episodesList : ArrayList<Episode>, val context: Context) :
    RecyclerView.Adapter<EpisodeAdapter.EpisodeHolder>() {

    inner class EpisodeHolder( item: View) : RecyclerView.ViewHolder(item) {
        private val binding = RecyclerEpisodeItemBinding.bind(itemView)

        fun bind(episode: Episode) {
            binding.episodeTitle.text = episode.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_episode_item, parent, false)
        return EpisodeHolder(view)
    }

    override fun onBindViewHolder(holder: EpisodeHolder, position: Int) {
        holder.bind(episodesList[position])
    }

    override fun getItemCount(): Int {
        return episodesList.size
    }
}