package com.n1x0nj4.tipcalculator.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.n1x0nj4.tipcalculator.R
import com.n1x0nj4.tipcalculator.databinding.SavedTipCalculationsListItemBinding
import com.n1x0nj4.tipcalculator.viewmodel.TipCalculationsSummaryItem

class TipSummaryAdapter(val onItemSelected: (item: TipCalculationsSummaryItem) -> Unit)
    : RecyclerView.Adapter<TipSummaryAdapter.TipSummaryViewHolder>() {

    private val tipCalculationsSummaries = mutableListOf<TipCalculationsSummaryItem>()

    fun updateList(updates: List<TipCalculationsSummaryItem>) {
        tipCalculationsSummaries.clear()
        tipCalculationsSummaries.addAll(updates)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : TipSummaryViewHolder{
        val inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<SavedTipCalculationsListItemBinding>(
                inflater, R.layout.saved_tip_calculations_list_item, parent, false
        )

        return TipSummaryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tipCalculationsSummaries.size
    }

    override fun onBindViewHolder(holder: TipSummaryViewHolder, position: Int) {
        holder.bind(tipCalculationsSummaries[position])
    }

    inner class TipSummaryViewHolder(val binding: SavedTipCalculationsListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TipCalculationsSummaryItem) {
            binding.item = item
            binding.root.setOnClickListener { onItemSelected(item) }
            binding.executePendingBindings()
        }
    }
}