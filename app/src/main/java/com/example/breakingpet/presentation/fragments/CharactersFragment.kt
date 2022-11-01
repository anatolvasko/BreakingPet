package com.example.breakingpet.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.breakingpet.databinding.FragmentCharactersBinding
import com.example.breakingpet.domain.model.characters.Character
import com.example.breakingpet.presentation.recyclerview.CharacterAdapter
import com.example.breakingpet.presentation.viewmodel.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding
    private val charactersViewModel:CharactersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCharactersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        charactersViewModel.getCharactersList()

        charactersViewModel.allCharacters.observe(viewLifecycleOwner) {
            val characterAdapter = CharacterAdapter(it as ArrayList<Character>, requireContext())
            val recyclerView = binding.recyclerView
            recyclerView.layoutManager = GridLayoutManager(context, 2)
            recyclerView.adapter = characterAdapter
        }

    }


}