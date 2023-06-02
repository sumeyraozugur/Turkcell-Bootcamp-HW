package com.sumeyra.centralbank.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sumeyra.centralbank.R
import com.sumeyra.centralbank.databinding.FragmentHomeBinding
import com.sumeyra.centralbank.delegete.viewBinding
import com.sumeyra.centralbank.extension.setViewsGone
import com.sumeyra.centralbank.extension.setViewsVisible
import com.sumeyra.centralbank.model.CentralBankModel


class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()
    private val exchangeRateList = arrayListOf<CentralBankModel>()
    private lateinit var arrayAdapter: ArrayAdapter<String>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, ArrayList())
            binding.autoCompleteTextView.setAdapter(arrayAdapter)

            initObservers()
            viewModel.getBankRates()


            setViewsGone(
                tvForexBuying, tvForexBuyingValue,
                tvForexSelling, tvForexSellingValue,
                tvBanknoteBuying, tvBanknoteBuyingValue,
                tvBanknoteSelling, tvBanknoteSellingValue
            )



            binding.autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
                val selectedItem = exchangeRateList[position]

                setViewsVisible(
                    tvForexBuying, tvForexBuyingValue,
                    tvForexSelling, tvForexSellingValue,
                    tvBanknoteBuying, tvBanknoteBuyingValue,
                    tvBanknoteSelling, tvBanknoteSellingValue
                )

                tvForexBuyingValue.text = selectedItem.forexBuying
                tvForexSellingValue.text = selectedItem.forexSelling
                tvBanknoteBuyingValue.text = selectedItem.banknoteBuying
                tvBanknoteSellingValue.text = selectedItem.banknoteSelling

            }
        }
    }

    private fun initObservers() {
        viewModel.bankList.observe(viewLifecycleOwner) {bankList ->
            exchangeRateList.clear()
            exchangeRateList.addAll(bankList)
            arrayAdapter.clear()
            arrayAdapter.addAll(exchangeRateList.map { it.currencyName.lowercase() })
            arrayAdapter.notifyDataSetChanged()

        }
        viewModel.date.observe(viewLifecycleOwner) {
            binding.tvDate.text = "~ $it Turkish Republic Central Bank Rates ~"

        }
    }
}

