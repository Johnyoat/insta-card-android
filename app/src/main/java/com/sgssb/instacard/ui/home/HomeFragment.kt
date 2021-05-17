package com.sgssb.instacard.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import coil.load
import com.google.android.material.transition.MaterialFadeThrough
import com.sgssb.instacard.R
import com.sgssb.instacard.constants.ListState
import com.sgssb.instacard.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.root.findViewById<AppCompatTextView>(R.id.title).text = getString(R.string.home)


        binding.profileBtn.setOnClickListener {
            requireActivity().findNavController(R.id.container).navigate(R.id.profileFragment)
        }

        binding.buyAirtime.setOnClickListener {
            callTransferService(ListState.BUY_AIRTIME)
        }

        binding.payBills.setOnClickListener {
            callTransferService(ListState.PAY_BILL)
        }

        binding.transferMoney.setOnClickListener {
            callTransferService(ListState.MONEY_TRANSFER)
        }

        binding.transactions.setOnClickListener {
            requireActivity().findNavController(R.id.container).navigate(R.id.transactionFragment)
        }

        val card = binding.cardLayout

        viewModel.getUserDetail().observe(viewLifecycleOwner, { user ->
            if (user == null) return@observe
            binding.userProfile.load(user.profileUrl)
            card.cardHolder.text = user.name
            card.amount.text = user.amount.toString()
        })

    }


    private fun callTransferService(listState: ListState) {
        val bundle = bundleOf("state" to listState)

        findNavController().navigate(R.id.transactionServiceFragment, bundle)
    }

}