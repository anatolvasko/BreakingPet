package com.example.breakingpet.presentation.fragments.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.breakingpet.R
import com.example.breakingpet.databinding.FragmentCharacterDetailsBinding
import com.example.breakingpet.domain.model.characters.Character

class CharacterDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailsBinding
    private val args: CharacterDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(view.context)
            .load(args.character.img)
            .placeholder(R.drawable.character_without_photo)
            .into(binding.characterPhoto)

        with(binding) {
            nameValue.text = args.character.name
            nicknameValue.text = args.character.nickname
            portrayedValue.text = args.character.portrayed
            occupationValue.text = args.character.occupation.toString().filter { it !in setOf('[', ']') }
            statusValue.text = args.character.status
            appearanceValue.text = args.character.appearance.toString().filter { it !in setOf('[', ']') }
        }

    }

}