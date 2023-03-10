package com.example.breakingpet.presentation.fragments.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.breakingpet.R
import com.example.breakingpet.databinding.FragmentCharactersBinding
import com.example.breakingpet.domain.model.characters.Character
import com.example.breakingpet.presentation.recyclerview.CharacterAdapter
import com.example.breakingpet.presentation.viewmodel.CharactersViewModel
import com.example.breakingpet.utils.Resource
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharactersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allCharacters.observe(viewLifecycleOwner) {

            with(binding) {
                refreshCharacters.isRefreshing = false
                characterProgressbar.isVisible = it is Resource.Loading && it.data.isNullOrEmpty()
                characterErrorLinearLayout.isVisible =
                    it is Resource.Error && it.data.isNullOrEmpty()
                if (it is Resource.Error && it.data.isNullOrEmpty()) {
                    errorMessage.text = it.error?.message
                }
            }

            val characterAdapter = it.data?.let { charactersList ->
                CharacterAdapter(
                    charactersList,
                    getItemClickListener()
                )
            }

            with(binding) {
                recyclerView.layoutManager = GridLayoutManager(context, 2)
                recyclerView.adapter = characterAdapter
            }
        }

        binding.refreshCharacters.setOnRefreshListener {
            viewModel.updateCharacterDatabase()
        }
    }

    private fun BottomNavigationView.showUp() {
        animate().setDuration(200L).translationY(0f).withStartAction { visibility = View.VISIBLE }
            .start()
    }

    private fun BottomNavigationView.hideDown() {
        animate().setDuration(200L).translationY(height.toFloat())
            .withEndAction { visibility = View.GONE }.start()
    }

    override fun onResume() {
        super.onResume()
        val bottomNavigationView =
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigationView?.showUp()
    }

    private fun getItemClickListener(): CharacterAdapter.ItemClickListener {
        return object : CharacterAdapter.ItemClickListener {
            override fun onItemClick(character: Character) {
                val directions =
                    CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailsFragment2(
                        character,
                        character.name
                    )
                findNavController().navigate(directions = directions)
            }
        }
    }
}