package com.technosales.vehiclechallanmobile.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.technosales.vehiclechallanmobile.databinding.ItemReportBinding
import com.technosales.vehiclechallanmobile.model.logresponse.LogData
import com.technosales.vehiclechallanmobile.ui.ImageViewActivity
import com.technosales.vehiclechallanmobile.utils.TextConfig
import com.technosales.vehiclechallanmobile.utils.startNewActivity
import timber.log.Timber

class ReportAdapter(
    private val context: Context,
    private val mListener: OnViewClickedListener?
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TAG: String = "DetailAdapter"

    interface OnViewClickedListener {
        fun onViewClicked(string: String)
    }


    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LogData>() {

        override fun areItemsTheSame(oldItem: LogData, newItem: LogData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: LogData, newItem: LogData): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        println("inside on create")
        val mBinding: ItemReportBinding = ItemReportBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListViewHolder(mBinding)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ListViewHolder -> {
                val logData = differ.currentList[position]
                holder.bind(logData, position)
            }
        }
    }

    override fun getItemCount(): Int {
        println("recyclerview ko item count is ${differ.currentList.size}")
        return differ.currentList.size
    }

    override fun getItemId(position: Int): Long {
        val userId = differ.currentList[position].ticketNumber?.toLong()
        return userId ?: (position + 1000).toLong()
    }

    fun submitList(logDataList: List<LogData>) {
        println("submit List ma aayeko data ${logDataList.toString()}")
        differ.submitList(logDataList)
    }

    inner class ListViewHolder
    constructor(private val mBinding: ItemReportBinding) : RecyclerView.ViewHolder(mBinding.root) {
        fun bind(logData: LogData, position: Int) {
            println("logData ko value ${logData.toString()}")
            mBinding.logData = logData
            mBinding.sn = "${position + 1}."
            Timber.tag("ListViewHolder").i("bind (line 80): $logData")


            mBinding.ivVehicleImage.setOnClickListener {
                context.startNewActivity(ImageViewActivity::class.java) {
                    putString(TextConfig.EXTRA_IMAGE_URL, logData.vehicleImage)
                }
            }
        }
    }
}