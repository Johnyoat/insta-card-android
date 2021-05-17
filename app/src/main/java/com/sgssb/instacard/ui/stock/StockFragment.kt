package com.sgssb.instacard.ui.stock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sgssb.instacard.R
import com.sgssb.instacard.databinding.StockFragmentBinding
import com.sgssb.instacard.databinding.StockItemBinding
import com.sgssb.instacard.models.StockModel

class StockFragment : Fragment() {

    companion object {
        fun newInstance() = StockFragment()
    }

    private val viewModel: StockViewModel by viewModels()
    private lateinit var binding: StockFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = StockFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getStock().observe(viewLifecycleOwner, { stocks ->
            binding.root.apply {
                adapter = StockListAdapter(stocks, requireActivity() as AppCompatActivity)
            }
        })
    }


    inner class StockListAdapter(
        private val stockList: List<StockModel>,
        private val activity: AppCompatActivity
    ) :
        RecyclerView.Adapter<StockListAdapter.StockListAdapterAdapterListViewHolder>() {
        private lateinit var binding: StockItemBinding

        inner class StockListAdapterAdapterListViewHolder(
            itemHolder: StockItemBinding
        ) : RecyclerView.ViewHolder(itemHolder.root) {
            fun setUpData(data: StockModel) {
                binding.stockImage.load(data.image)
                binding.stockName.text = data.name
                binding.share.text = data.share.toString()
                binding.stockCode.text = data.code
                binding.percent.text = "${data.percentage}%"
                binding.stockImage.load(data.image)

                val up = ContextCompat.getDrawable(activity, R.drawable.ic_chevron_up)
                val down = ContextCompat.getDrawable(activity, R.drawable.ic_chevron_down)
                val red = ContextCompat.getColorStateList(activity, R.color.primaryColor)
                val green = ContextCompat.getColorStateList(activity, R.color.green)

                if (data.fallen) {
                    binding.percent.setCompoundDrawablesWithIntrinsicBounds(down, null, null, null)
                    binding.percent.backgroundTintList = red
                } else {
                    binding.percent.setCompoundDrawablesWithIntrinsicBounds(up, null, null, null)
                    binding.percent.backgroundTintList = green
                }
            }
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): StockListAdapterAdapterListViewHolder {
            binding = StockItemBinding.inflate(layoutInflater, parent, false)
            return StockListAdapterAdapterListViewHolder(binding)
        }

        override fun onBindViewHolder(
            holder: StockListAdapterAdapterListViewHolder,
            position: Int
        ) {
            holder.setUpData(stockList[position])
        }

        override fun getItemCount(): Int {
            return stockList.size
        }

    }
}