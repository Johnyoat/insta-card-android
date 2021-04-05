package com.sgssb.instacard.ui.crowdfunding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sgssb.instacard.R
import com.sgssb.instacard.databinding.CrowdFundingFragmentBinding

class CrowdFundingFragment : Fragment() {

    companion object {
        fun newInstance() = CrowdFundingFragment()
    }

    private val viewModel: CrowdFundingViewModel by viewModels()
    private lateinit var binding:CrowdFundingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CrowdFundingFragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


}