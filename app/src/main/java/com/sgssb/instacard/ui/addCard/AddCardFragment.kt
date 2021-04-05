package com.sgssb.instacard.ui.addCard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.transition.MaterialFadeThrough
import com.sgssb.instacard.R
import com.sgssb.instacard.databinding.AddCardFragmentBinding

class AddCardFragment : Fragment() {

    companion object {
        fun newInstance() = AddCardFragment()
    }

    private val viewModel: AddCardViewModel by viewModels()
    private lateinit var binding: AddCardFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddCardFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.findViewById<AppCompatTextView>(R.id.title).text = getString(R.string.addCard)
    }

}