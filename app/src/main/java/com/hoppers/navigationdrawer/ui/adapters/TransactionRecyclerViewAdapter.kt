package com.hoppers.navigationdrawer.ui.adapters


import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hoppers.navigationdrawer.R
import com.hoppers.navigationdrawer.model.Accounts
import com.hoppers.navigationdrawer.model.Transaction
import com.hoppers.navigationdrawer.ui.fragments.SummaryFragment.OnListFragmentInteractionListener
import com.hoppers.navigationdrawer.utils.DateUtil
import kotlinx.android.synthetic.main.item_ac_detail.view.*

class TransactionRecyclerViewAdapter(
        private val mValues: Map<String?, List<Transaction>>?,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<TransactionRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private val iterator: Iterator<Map.Entry<String?, List<Transaction>>>? = mValues?.iterator()

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Accounts
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_ac_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (iterator!!.hasNext()) {
            val key = iterator.next()
            val item = mValues?.get(key.key) as List<Transaction>
            holder.date.text = DateUtil.getDateComparativly(key.key as String)
            item.forEach {
                holder.desc.text = Html.fromHtml(it.description)
                val doubleAmount = it.amount?.toDouble()
                if (doubleAmount!! < 0.0) //Negative
                    holder.amount.text = String.format(holder.itemView.context.getString(R.string.negative_amount, Math.abs(doubleAmount)))
                else
                    holder.amount.text = String.format(holder.itemView.context.getString(R.string.positive_amount, Math.abs(doubleAmount)))

            }
        }

    }

    private fun enableClick(img: View, item: Transaction) {
        with(img) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues!!.size

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val date: TextView = mView.txt_date
        val desc: TextView = mView.txt_desc
        val amount: TextView = mView.txt_amont

    }
}
