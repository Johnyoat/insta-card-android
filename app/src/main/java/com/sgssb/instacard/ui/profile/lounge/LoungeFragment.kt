package com.sgssb.instacard.ui.profile.lounge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.sgssb.instacard.R
import com.sgssb.instacard.databinding.LoungeFragmentBinding

class LoungeFragment : DialogFragment() {

    companion object {
        fun newInstance() = LoungeFragment()
    }

    private val viewModel: LoungeViewModel by viewModels()
    private lateinit var binding: LoungeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoungeFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.closeBtn.setOnClickListener {
            dismissAllowingStateLoss()
        }
    }


}