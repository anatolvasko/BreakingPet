package com.example.breakingpet.presentation.fragments.episodes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.breakingpet.R
import com.example.breakingpet.databinding.FragmentEpisodeDetailsBinding
import com.example.breakingpet.presentation.fragments.characters.CharacterDetailsFragmentArgs
import com.google.firebase.storage.FirebaseStorage


class EpisodeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentEpisodeDetailsBinding
    private val args: EpisodeDetailsFragmentArgs by navArgs()
    private val imageRef = FirebaseStorage.getInstance().reference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodeDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageRef
            .child("images/${args.episode.episodeID}.jpeg")
            .downloadUrl
            .addOnSuccessListener {
                Glide.with(view.context)
                    .load(it.toString())
                    .placeholder(R.drawable.character_without_photo)
                    .into(binding.episodePhoto)
            }.addOnFailureListener {
                Toast.makeText(
                    view.context,
                    "Poster for ${args.episode.title} is not downloaded",
                    Toast.LENGTH_LONG
                ).show()
            }

        with(binding) {
            titleValue.text = args.episodeTitle
            airDateValue.text = args.episode.airDate
        }

    }

}