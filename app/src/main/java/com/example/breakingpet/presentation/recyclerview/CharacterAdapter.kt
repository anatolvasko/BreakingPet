package com.example.breakingpet.presentation.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.breakingpet.domain.model.characters.Character
import com.bumptech.glide.Glide
import com.example.breakingpet.R
import com.example.breakingpet.data.database.entities.CharacterEntity
import com.example.breakingpet.databinding.RecyclerCharacterItemBinding

class CharacterAdapter(
    private val charactersList: List<Character>,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<CharacterAdapter.CharacterHolder>() {

    inner class CharacterHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = RecyclerCharacterItemBinding.bind(itemView)

        fun bind(character: Character) {
            binding.characterName.text = character.name

            Glide.with(itemView.context)
                .load(character.img)
                .placeholder(R.drawable.character_without_photo)
                .into(binding.characterPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_character_item, parent, false)
        return CharacterHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        holder.bind(charactersList[position])

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(charactersList[position])
        }
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }

    interface ItemClickListener {
        fun onItemClick(character: Character)
    }
}