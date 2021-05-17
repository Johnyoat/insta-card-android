package com.sgssb.instacard.ui.transfer

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.viewModels
import coil.load
import com.sgssb.instacard.R
import com.sgssb.instacard.databinding.AccountsItemLayoutBinding
import com.sgssb.instacard.databinding.TransferFragmentBinding
import com.sgssb.instacard.models.TransactionModel

class TransferFragment : Fragment() {

    private lateinit var binding: TransferFragmentBinding

    companion object {
        fun newInstance() = TransferFragment()
    }

    private val viewModel: TransferViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TransferFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val items = listOf(
            "**49" to R.drawable.ic_visa,
            "**50" to R.drawable.ic_visa,
            "**61" to R.drawable.ic_visa,
            "**00" to R.drawable.ic_visa
        )

//        val transactionModel = TransactionModel()
        val adapter = CodeAdapter(requireContext(), R.layout.accounts_item_layout, items)
        (binding.accountNumber.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        binding.continueBtn.setOnClickListener {

        }

    }

    inner class CodeAdapter(
        context: Context, resource: Int, private val objects: List<Pair<String, Int>>
    ) : ArrayAdapter<Pair<String, Any>>(context, resource, objects) {

        lateinit var binding:AccountsItemLayoutBinding

        override fun getCount(): Int {
            return objects.size
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

            if (convertView == null){
                binding = AccountsItemLayoutBinding.inflate(layoutInflater,parent,false)
            }

            binding.cardNumber.text = objects[position].first
            binding.icon.load(objects[position].second)

            return binding.root
        }


    }

}