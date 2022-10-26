package com.example.breakingpet.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.breakingpet.R
import com.example.breakingpet.databinding.FragmentCharactersBinding
import com.example.breakingpet.databinding.FragmentEpisodesBinding


class EpisodesFragment : Fragment() {
    private lateinit var binding : FragmentEpisodesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEpisodesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}