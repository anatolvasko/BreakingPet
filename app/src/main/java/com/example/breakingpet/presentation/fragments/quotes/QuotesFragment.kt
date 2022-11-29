package com.example.breakingpet.presentation.fragments.quotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.breakingpet.R
import com.example.breakingpet.databinding.FragmentEpisodesBinding
import com.example.breakingpet.databinding.FragmentQuotesBinding

class QuotesFragment : Fragment() {
    private lateinit var binding: FragmentQuotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentQuotesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}