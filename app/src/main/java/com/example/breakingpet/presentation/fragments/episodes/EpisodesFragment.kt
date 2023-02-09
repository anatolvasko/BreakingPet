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
import com.example.breakingpet.R
import com.example.breakingpet.databinding.FragmentEpisodesBinding
import com.example.breakingpet.domain.model.episodes.Episode
import com.example.breakingpet.presentation.recyclerview.EpisodeAdapter
import com.example.breakingpet.presentation.viewmodel.EpisodesViewModel
import com.example.breakingpet.utils.Resource
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodesFragment : Fragment() {

    private lateinit var binding: FragmentEpisodesBinding
    private val viewModel: EpisodesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allEpisodes.observe(viewLifecycleOwner) {

            with(binding){
                refreshEpisodes.isRefreshing = false
                episodesProgressbar.isVisible = it is Resource.Loading && it.data.isNullOrEmpty()
                episodesErrorLinearLayout.isVisible =
                    it is Resource.Error && it.data.isNullOrEmpty()
            }

            val episodeAdapter = it.data?.let { episodesList ->
                EpisodeAdapter(
                    episodesList,
                    getItemClickListener()
                )
            }

            with(binding) {
                recyclerView.layoutManager = GridLayoutManager(context, 2)
                recyclerView.adapter = episodeAdapter
            }
        }

        binding.refreshEpisodes.setOnRefreshListener {
            viewModel.updateEpisodesDatabase()
        }
    }

    private fun getItemClickListener(): EpisodeAdapter.ItemClickListener {
        return object : EpisodeAdapter.ItemClickListener {
            override fun onItemClick(episode: Episode) {
                val directions =
                    EpisodesFragmentDirections.actionEpisodesFragmentToEpisodeDetailsFragment2(
                        episode,
                        episode.title
                    )
                findNavController().navigate(directions = directions)
            }
        }
    }

    private fun BottomNavigationView.showUp() {
        animate().setDuration(200L).translationY(0f).withStartAction { visibility = View.VISIBLE }
            .start()
    }

    override fun onResume() {
        super.onResume()
        val bottomNavigationView =
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigationView?.showUp()
    }
}