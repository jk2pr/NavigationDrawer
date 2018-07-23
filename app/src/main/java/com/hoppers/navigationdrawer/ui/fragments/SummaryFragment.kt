package com.hoppers.navigationdrawer.ui.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.hoppers.navigationdrawer.constants.INTENT_KEYS
import com.hoppers.navigationdrawer.R
import com.hoppers.navigationdrawer.model.Accounts
import com.hoppers.navigationdrawer.model.Json4Kotlin_Base
import com.hoppers.navigationdrawer.ui.adapters.MySummaryRecyclerViewAdapter



class SummaryFragment : Fragment() {

    // TODO: Customize parameters
    private val data: Json4Kotlin_Base by lazy {
        Gson().fromJson(arguments?.getString(INTENT_KEYS.data), Json4Kotlin_Base::class.java)
    }


    private var listener: OnListFragmentInteractionListener? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_summary_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager =
                        LinearLayoutManager(context)
                adapter = MySummaryRecyclerViewAdapter(data, listener)
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: Accounts?)
    }

}
