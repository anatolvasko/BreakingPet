package com.example.breakingpet.presentation.fragments.episodes

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.breakingpet.R
import com.example.breakingpet.databinding.FragmentCharactersBinding
import com.example.breakingpet.databinding.FragmentEpisodesBinding
import com.example.breakingpet.domain.model.characters.Character
import com.example.breakingpet.domain.model.episodes.Episode
import com.example.breakingpet.presentation.fragments.characters.CharactersFragmentDirections
import com.example.breakingpet.presentation.recyclerview.CharacterAdapter
import com.example.breakingpet.presentation.recyclerview.EpisodeAdapter
import com.example.breakingpet.presentation.viewmodel.CharactersViewModel
import com.example.breakingpet.presentation.viewmodel.EpisodesViewModel
import com.example.breakingpet.utils.Resource
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class EpisodesFragment : Fragment() {
    private lateinit var binding : FragmentEpisodesBinding
    private val episodesViewModel: EpisodesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEpisodesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //episodesViewModel.getCharactersList()

        episodesViewModel.allEpisodes.observe(viewLifecycleOwner){

            val episodeAdapter = EpisodeAdapter(
                it.data!!,
                object : EpisodeAdapter.ItemClickListener {
                    override fun onItemClick(episode: Episode) {

                        val directions = EpisodesFragmentDirections.actionEpisodesFragmentToEpisodeDetailsFragment2(episode, episode.title)
                        findNavController().navigate(directions = directions)

                    }

                }
            )

            with(binding){
                recyclerView.layoutManager = GridLayoutManager(context, 2)
                recyclerView.adapter = episodeAdapter
                episodesProgressbar.isVisible = it is Resource.Loading && it.data.isNullOrEmpty()
                episodesErrorLinearLayout.isVisible = it is Resource.Error && it.data.isNullOrEmpty()
            }

            val recyclerView = binding.recyclerView
            recyclerView.layoutManager = GridLayoutManager(context, 2)
            recyclerView.adapter = episodeAdapter
        }

    }

}