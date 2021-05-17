package com.sgssb.instacard.ui.exchange

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.transition.MaterialFadeThrough
import com.sgssb.instacard.R
import com.sgssb.instacard.databinding.ExchangeFragmentBinding
import com.sgssb.instacard.databinding.ExchangeItemBinding
import com.sgssb.instacard.databinding.TransferItemBinding
import com.sgssb.instacard.models.ExchangeModel
import com.sgssb.instacard.models.TransferServiceModel
import kotlin.math.round

class ExchangeFragment(private val amount: Float = 5000f) : Fragment() {

    companion object {
        fun newInstance(amount:Float) = ExchangeFragment(amount)
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getExchange().observe(viewLifecycleOwner,{exchangeDataList ->
            binding.exchangeList.apply {
                adapter = ExchangeListAdapter(exchangeDataList, requireActivity() as AppCompatActivity)
            }
        })
    }

    inner class ExchangeListAdapter(
        private val exchangeList: List<ExchangeModel>,
        private val activity: AppCompatActivity
    ) :
        RecyclerView.Adapter<ExchangeListAdapter.ExchangeListAdapterListViewHolder>() {
        private lateinit var binding: ExchangeItemBinding

        inner class ExchangeListAdapterListViewHolder(
            itemHolder: ExchangeItemBinding
        ) : RecyclerView.ViewHolder(itemHolder.root) {
            fun setUpData(data: ExchangeModel) {
                binding.currencyImage.load(data.flag)
                binding.currencyName.text = data.name
                binding.sell.text =  "${getString(R.string.cedi)} ${data.sell}"
                binding.buy.text =  "${getString(R.string.cedi)} ${data.buy}"
                binding.convertedAmount.text =  "${data.symbol} ${round(data.buy / amount)}"
                binding.currencyCode.text = data.code
            }
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ExchangeListAdapterListViewHolder {
            binding = ExchangeItemBinding.inflate(layoutInflater, parent, false)
            return ExchangeListAdapterListViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ExchangeListAdapterListViewHolder, position: Int) {
            holder.setUpData(exchangeList[position])
        }

        override fun getItemCount(): Int {
            return exchangeList.size
        }

    }


}