package com.sgssb.instacard.ui.trade

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.MaterialFadeThrough
import com.sgssb.instacard.R
import com.sgssb.instacard.adapters.TradePageAdapter
import com.sgssb.instacard.databinding.TradeFragmentBinding
import com.sgssb.instacard.ui.crowdfunding.CrowdFundingFragment
import com.sgssb.instacard.ui.exchange.ExchangeFragment
import com.sgssb.instacard.ui.stock.StockFragment

class TradeFragment : Fragment() {

    private lateinit var binding:TradeFragmentBinding


    private val viewModel: TradeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = TradeFragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.root.findViewById<AppCompatTextView>(R.id.title).text = getString(R.string.trade)

        val fragmentList = listOf(
            ExchangeFragment(),
            StockFragment(),
            CrowdFundingFragment()
        )

        val fragmentIconList = listOf(
            Pair(R.drawable.ic_exchange,R.string.exchange),
            Pair(R.drawable.chart_line,R.string.stocks),
            Pair(R.drawable.ic_group,R.string.crowdfunding)
        )



        binding.tradeViewPager.apply {
            adapter = TradePageAdapter(requireActivity(),fragmentList)
        }

        TabLayoutMediator(binding.tradeTabLayout,binding.tradeViewPager){ tab,position ->
            tab.icon = ContextCompat.getDrawable(requireContext(),fragmentIconList[position].first)
            tab.text = getString(fragmentIconList[position].second)

        }.attach()


    }

}