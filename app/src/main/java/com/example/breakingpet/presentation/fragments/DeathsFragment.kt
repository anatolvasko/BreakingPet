package com.example.breakingpet.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.breakingpet.R
import com.example.breakingpet.databinding.FragmentDeathsBinding
import com.example.breakingpet.databinding.FragmentQuotesBinding

class DeathsFragment : Fragment() {
    private lateinit var binding : FragmentDeathsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDeathsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}