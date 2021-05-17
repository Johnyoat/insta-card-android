package com.sgssb.instacard.ui.transactionService

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.firebase.FirebaseApp
import com.sgssb.instacard.R
import com.sgssb.instacard.constants.ListState
import com.sgssb.instacard.databinding.TransactionServiceFragmentBinding
import com.sgssb.instacard.databinding.TransferItemBinding
import com.sgssb.instacard.models.TransferServiceModel

class TransactionServiceActivity() : Fragment() {
    private val viewModel: TransactionServiceViewModel by viewModels()
    private lateinit var binding: TransactionServiceFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TransactionServiceFragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {






        binding.root.findViewById<AppCompatTextView>(R.id.title).text = getString(R.string.make_a_transaction)
        val bckBtn = binding.root.findViewById<AppCompatImageButton>(R.id.backBtn)

        bckBtn.isVisible = true

        bckBtn.setOnClickListener {
            findNavController().popBackStack()
        }


        val state = arguments?.get("state") as ListState


        when (state) {

            ListState.MONEY_TRANSFER -> {
                getTransactionList("momo-list",binding.momoList)
                getTransactionList("bank-list",binding.bankList)
                binding.payBillLayout.isVisible = false
                binding.utilityList.isVisible = false
                binding.buyAirtimeLayout.isVisible = false
            }

            ListState.PAY_BILL -> {
                getTransactionList("utility-list",binding.utilityList)
                binding.transferMoneyLayout.isVisible = false
                binding.buyAirtimeLayout.isVisible = false

            }

            ListState.BUY_AIRTIME -> {
                getTransactionList("network-list",binding.networkList)
                binding.transferMoneyLayout.isVisible = false
                binding.payBillLayout.isVisible = false
            }

            ListState.ALL -> {
                getTransactionList("network-list",binding.networkList)
                getTransactionList("momo-list",binding.momoList)
                getTransactionList("bank-list",binding.bankList)
                getTransactionList("utility-list",binding.utilityList)
            }
        }
    }


    private fun getTransactionList(transactionServiceName: String, recyclerView: RecyclerView) {
        viewModel.getTransactionsServiceBy(transactionServiceName)
            .observe(viewLifecycleOwner, { transferServices ->
                recyclerView.apply {
                    adapter = TransactionServiceListAdapter(
                        transferServices,
                        requireActivity() as AppCompatActivity
                    )
                }
            })
    }


    inner class TransactionServiceListAdapter(
        private val servicesList: List<TransferServiceModel>,
        private val activity: AppCompatActivity
    ) :
        RecyclerView.Adapter<TransactionServiceListAdapter.TransactionServiceListViewHolder>() {
        private lateinit var binding: TransferItemBinding

        inner class TransactionServiceListViewHolder(
            itemHolder: TransferItemBinding
        ) : RecyclerView.ViewHolder(itemHolder.root) {
            fun setUpData(data: TransferServiceModel) {
                binding.transferImage.load(data.logoUrl)
                binding.tranferName.text = data.name
            }
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): TransactionServiceListViewHolder {
            binding = TransferItemBinding.inflate(layoutInflater, parent, false)
            return TransactionServiceListViewHolder(binding)
        }

        override fun onBindViewHolder(holder: TransactionServiceListViewHolder, position: Int) {
            holder.setUpData(servicesList[position])
            holder.itemView.setOnClickListener {
                activity.findNavController(R.id.container).navigate(R.id.transferFragment)
            }
        }

        override fun getItemCount(): Int {
            return servicesList.size
        }

    }


}