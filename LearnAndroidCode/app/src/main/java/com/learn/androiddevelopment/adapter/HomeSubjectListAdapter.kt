package com.learn.androiddevelopment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.learn.androiddevelopment.R
import com.learn.androiddevelopment.model.Topic


class HomeSubjectListAdapter internal constructor(
    context: Context?,
    data: MutableList<Topic>
) :
    RecyclerView.Adapter<HomeSubjectListAdapter.ViewHolder>() {
    private val mData: List<Topic>
    private val mInflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null
    private var mContext = context
    var currentBackground = R.drawable.bg_gradient_subject_5
    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = mInflater.inflate(R.layout.row_subject_item, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val subjectTopic = mData[position]
        holder.textViewSubjectCategory.text = subjectTopic.category
        holder.textViewSubjectName.text = subjectTopic.name
        holder.textViewSubjectDonePercentage.text =
            mContext!!.getString(R.string.hint_no_chapters) + ": " + subjectTopic.subTopicList.size
    }

    // total number of rows
    override fun getItemCount(): Int {
        return mData.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var textViewSubjectCategory: TextView
        var textViewSubjectName: TextView
        var textViewSubjectDonePercentage: TextView
        var progressBarPercentage: ProgressBar

        override fun onClick(view: View?) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }

        init {
            textViewSubjectCategory = itemView.findViewById(R.id.textViewSubjectCategory)
            textViewSubjectName = itemView.findViewById(R.id.textViewSubjectName)
            textViewSubjectDonePercentage =
                itemView.findViewById(R.id.textViewSubjectDonePercentage)
            progressBarPercentage = itemView.findViewById(R.id.progressBarPercentage)
            itemView.setOnClickListener(this)
        }
    }

    // convenience method for getting data at click position
    fun getItem(id: Int): Topic {
        return mData[id]
    }

    // allows clicks events to be caught
    fun setClickListener(itemClickListener: ItemClickListener?) {
        mClickListener = itemClickListener
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    // data is passed into the constructor
    init {
        mInflater = LayoutInflater.from(context)
        mData = data
    }
}