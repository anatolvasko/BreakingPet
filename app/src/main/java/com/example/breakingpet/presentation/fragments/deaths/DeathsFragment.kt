package com.example.breakingpet.presentation.fragments.deaths

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.breakingpet.databinding.FragmentDeathsBinding

class DeathsFragment : Fragment() {
    private lateinit var binding : FragmentDeathsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeathsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}