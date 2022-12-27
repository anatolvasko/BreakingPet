package com.example.breakingpet.presentation.fragments.quotes

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.breakingpet.R
import com.example.breakingpet.databinding.FragmentEpisodesBinding
import com.example.breakingpet.databinding.FragmentQuotesBinding
import com.example.breakingpet.presentation.viewmodel.QuotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuotesFragment : Fragment() {
    private lateinit var binding: FragmentQuotesBinding
    private val quotesViewModel: QuotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentQuotesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.getRandomQuote.clickWithDebounce {
            quotesViewModel.getRandomQuote()
            binding.quoteText.isVisible = false
            binding.authorValue.isVisible = false
            binding.quoteProgressbar.isVisible = true
        }


        quotesViewModel.randomQuote.observe(viewLifecycleOwner, Observer {
            with(binding) {
                quoteText.text = it.quote
                authorValue.text = it.author
                quoteText.isVisible = true
                authorValue.isVisible = true
                quoteProgressbar.isVisible = false
            }
        })
    }

    private fun Button.clickWithDebounce(debounceTime: Long = 1000L, action: () -> Unit) {
        this.setOnClickListener(object : View.OnClickListener {
            private var lastClickTime: Long = 0

            override fun onClick(v: View) {
                if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
                else action()

                lastClickTime = SystemClock.elapsedRealtime()
            }
        })
    }
}