package com.example.breakingpet.presentation.fragments.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.breakingpet.R
import com.example.breakingpet.data.database.entities.CharacterEntity
import com.example.breakingpet.databinding.FragmentCharactersBinding
import com.example.breakingpet.domain.model.characters.Character
import com.example.breakingpet.presentation.recyclerview.CharacterAdapter
import com.example.breakingpet.presentation.viewmodel.CharactersViewModel
import com.example.breakingpet.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlin.concurrent.thread

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding
    private val charactersViewModel: CharactersViewModel by viewModels()

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

        charactersViewModel.allCharacters.observe(viewLifecycleOwner) {

            val characterAdapter = CharacterAdapter(
                it.data!!,
                object : CharacterAdapter.ItemClickListener {
                    override fun onItemClick(character: Character) {

                        val directions = CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailsFragment2(character, character.name)
                        findNavController().navigate(directions = directions)

                    }
                })

            with(binding){
                recyclerView.layoutManager = GridLayoutManager(context, 2)
                recyclerView.adapter = characterAdapter
                characterProgressbar.isVisible = it is Resource.Loading && it.data.isNullOrEmpty()
                characterErrorLinearLayout.isVisible = it is Resource.Error && it.data.isNullOrEmpty()
            }

        }

        binding.refreshCharacters.setOnRefreshListener {
            charactersViewModel.getCharactersList()
            binding.refreshCharacters.isRefreshing = false
        }
    }
}