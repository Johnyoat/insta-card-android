package com.sgssb.instacard.ui.stock

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sgssb.instacard.R

class StockFragment : Fragment() {

    companion object {
        fun newInstance() = StockFragment()
    }

    private lateinit var viewModel: StockViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.stock_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StockViewModel::class.java)
        // TODO: Use the ViewModel
    }

}