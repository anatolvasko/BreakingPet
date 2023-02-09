package com.example.breakingpet.presentation.fragments.episodes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.breakingpet.R
import com.example.breakingpet.databinding.FragmentEpisodeDetailsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class EpisodeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentEpisodeDetailsBinding
    private val args: EpisodeDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodeDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigationView =
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigationView?.isVisible = false

        Glide.with(view.context)
            .load(args.episode.img)
            .placeholder(R.drawable.character_without_photo)
            .into(binding.episodePhoto)

        with(binding) {
            titleValue.text = args.episodeTitle
            airDateValue.text = args.episode.airDate
            charactersValue.text =
                args.episode.characters.toString().filter { it !in setOf('[', ']') }
            descriptionValue.text = args.episode.description
            episodeRatingValue.text = args.episode.rating
        }
    }
}