package com.n1x0nj4.tipcalculator.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import com.n1x0nj4.tipcalculator.R
import com.n1x0nj4.tipcalculator.viewmodel.CalculatorViewModel
import kotlinx.android.synthetic.main.saved_tip_calculations_list.view.*
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders

class LoadDialogFragment : DialogFragment() {

    interface Callback {
        fun onTipSelected(name: String)
    }

    private var loadTipCallback: Callback? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        loadTipCallback = context as? Callback
    }

    override fun onDetach() {
        super.onDetach()
        loadTipCallback = null
    }

    @SuppressLint("NewApi")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = context?.let { ctx ->
            AlertDialog.Builder(ctx)
                    .setView(createView(ctx))
                    .setNegativeButton(R.string.action_cancel, null)
                    .create()
        }

        return dialog!!
    }

    private fun createView(ctx: Context) : View {
        val rv = LayoutInflater
                .from(ctx)
                .inflate(R.layout.saved_tip_calculations_list, null)
                .recycler_saved_calculations

        rv.setHasFixedSize(true)
        rv.addItemDecoration(DividerItemDecoration(ctx,DividerItemDecoration.VERTICAL))

        val adapter = TipSummaryAdapter {
            loadTipCallback?.onTipSelected(it.locationName)
            dismiss()
        }

        rv.adapter = adapter

        val vm = ViewModelProviders.of(activity!!).get(CalculatorViewModel::class.java)

        vm.loadSavedTipCalculationSummaries().observe(this, Observer {
            if(it != null) {
                adapter.updateList(it)
            }
        })

        return rv
    }
}