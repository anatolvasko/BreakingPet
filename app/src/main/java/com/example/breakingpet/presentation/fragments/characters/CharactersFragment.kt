package com.example.breakingpet.presentation.fragments.characters

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.breakingpet.R
import com.example.breakingpet.data.database.entities.CharacterEntity
import com.example.breakingpet.databinding.FragmentCharactersBinding
import com.example.breakingpet.domain.model.characters.Character
import com.example.breakingpet.presentation.recyclerview.CharacterAdapter
import com.example.breakingpet.presentation.viewmodel.CharactersViewModel
import com.example.breakingpet.utils.Resource
import com.google.android.material.bottomnavigation.BottomNavigationView
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
            characterAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

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

    fun BottomNavigationView.showUp() {
        animate().setDuration(200L).translationY(0f).withStartAction { visibility = View.VISIBLE }.start()
    }

    fun BottomNavigationView.hideDown() {
        animate().setDuration(200L).translationY(height.toFloat()).withEndAction { visibility = View.GONE }.start()
    }

    override fun onResume() {
        super.onResume()
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigationView?.showUp()
    }
}