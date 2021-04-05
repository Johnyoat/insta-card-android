package com.sgssb.instacard.ui.exchange

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.transition.MaterialFadeThrough
import com.sgssb.instacard.databinding.ExchangeFragmentBinding

class ExchangeFragment : Fragment() {

    companion object {
        fun newInstance() = ExchangeFragment()
    }

    private val viewModel: ExchangeViewModel by viewModels()
    private lateinit var binding: ExchangeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ExchangeFragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough()
    }


}