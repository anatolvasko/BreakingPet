package com.example.breakingpet.presentation.fragments.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.breakingpet.databinding.FragmentEpisodesBinding
import com.example.breakingpet.domain.model.episodes.Episode
import com.example.breakingpet.presentation.recyclerview.EpisodeAdapter
import com.example.breakingpet.presentation.viewmodel.EpisodesViewModel
import com.example.breakingpet.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class EpisodesFragment : Fragment() {
    private lateinit var binding: FragmentEpisodesBinding
    private val viewModel: EpisodesViewModel by viewModels()

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
        binding.episodesProgressbar.isVisible = true

        viewModel.allEpisodes.observe(viewLifecycleOwner) {

            CoroutineScope(Dispatchers.IO).launch {


                if (viewModel.imagesUrlList.size != 62) {
                    viewModel.getImageList()
                }

                withContext(Dispatchers.Main){

                    val episodeAdapter = EpisodeAdapter(
                        it.data!!,
                        object : EpisodeAdapter.ItemClickListener {
                            override fun onItemClick(episode: Episode) {
                                val directions = EpisodesFragmentDirections.actionEpisodesFragmentToEpisodeDetailsFragment2(episode, episode.title)
                                findNavController().navigate(directions = directions)
                            }
                        },
                        viewModel.imagesUrlList
                    )

                    with(binding) {

                        recyclerView.layoutManager = GridLayoutManager(context, 2)
                        recyclerView.adapter = episodeAdapter
                        episodesProgressbar.isVisible = it is Resource.Loading && it.data.isNullOrEmpty()
                        episodesErrorLinearLayout.isVisible =
                            it is Resource.Error && it.data.isNullOrEmpty()
                    }

                    val recyclerView = binding.recyclerView
                    recyclerView.layoutManager = GridLayoutManager(context, 2)
                    recyclerView.adapter = episodeAdapter
                }
            }
        }
    }


}