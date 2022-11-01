package com.example.breakingpet.presentation.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.breakingpet.R
import com.example.breakingpet.databinding.RecyclerCharacterItemBinding
import com.example.breakingpet.domain.model.characters.Character

class CharacterAdapter (private val charactersList : ArrayList<Character>, val context: Context) :
    RecyclerView.Adapter<CharacterAdapter.CharacterHolder>() {

    inner class CharacterHolder( item: View) : RecyclerView.ViewHolder(item) {
        private val binding = RecyclerCharacterItemBinding.bind(itemView)

        fun bind(character: Character) {
            binding.textView.text = character.name

            Glide.with(context)
                .load(character.img)
                .placeholder(R.drawable.character_without_photo)
                .into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_character_item, parent, false)
        return CharacterHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        holder.bind(charactersList[position])
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }
}