package com.hoppers.navigationdrawer.ui.adapters


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.hoppers.navigationdrawer.R
import com.hoppers.navigationdrawer.model.Accounts
import com.hoppers.navigationdrawer.model.Json4Kotlin_Base
import com.hoppers.navigationdrawer.ui.fragments.SummaryFragment.OnListFragmentInteractionListener
import kotlinx.android.synthetic.main.include_item.view.*

class MySummaryRecyclerViewAdapter(
        private val mValues: Json4Kotlin_Base,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<MySummaryRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Accounts
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_summary, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues.accounts[position]
        holder.accountLabel.text = item.accountLabel
        holder.currentBalance.text = item.currentBalance
        holder.availableBalance.text = String.format(holder.mView.context.getString(R.string.available, item.availableBalance))
        holder.accountNumber.text = String.format(holder.mView.context.getString(R.string.current, item.accountNumber))
        enableClick(holder.mView, item)
        enableClick(holder.img_left, item)
        enableClick(holder.img_right, item)


    }

    private fun enableClick(img: View, item: Accounts) {
        with(img) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.accounts.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val accountLabel: TextView = mView.accountLabel
        val currentBalance: TextView = mView.currentBalance
        val availableBalance: TextView = mView.availableBalance
        val accountNumber: TextView = mView.accountNumber
        val img_right: ImageView = mView.img_right
        val img_left: ImageView = mView.img_left

    }
}
