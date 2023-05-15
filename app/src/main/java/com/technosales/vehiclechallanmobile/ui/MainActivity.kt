package com.technosales.vehiclechallanmobile.ui

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.technosales.vehiclechallanmobile.R
import com.technosales.vehiclechallanmobile.adapter.ReportAdapter
import com.technosales.vehiclechallanmobile.databinding.ActivityMainBinding
import com.technosales.vehiclechallanmobile.db.itemtype.ItemType
import com.technosales.vehiclechallanmobile.db.itemtype.ItemTypeViewModel
import com.technosales.vehiclechallanmobile.model.reportresponse.ReportResponse
import com.technosales.vehiclechallanmobile.utils.GeneralUtils
import com.technosales.vehiclechallanmobile.utils.Resource
import com.technosales.vehiclechallanmobile.utils.displayLongToast
import com.technosales.vehiclechallanmobile.viewmodel.LogViewModel
import timber.log.Timber
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private var selectedItemTypePosition: Int = 0
    private lateinit var mRecyclerAdapter: ReportAdapter
    private lateinit var observeReportResource: Observer<Resource<ReportResponse?>>
    private lateinit var mBinding: ActivityMainBinding
    private val logViewModel: LogViewModel by viewModels()
    private val itemTypeViewModel: ItemTypeViewModel by viewModels()

    private var itemId: Int? = 0
    private var dateFrom: String? = null
    private var dateTo: String? = null
    private var itemTypeList: MutableList<ItemType> = mutableListOf()

    private val c = Calendar.getInstance()
    private var year = c.get(Calendar.YEAR)
    private var month = c.get(Calendar.MONTH)
    private var day = c.get(Calendar.DAY_OF_MONTH)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.clickHandler = MainActivityCLickHandler(this)
        title = "Report"
        supportActionBar?.subtitle = GeneralUtils.getAppVersionName(this)

        dateFrom = GeneralUtils.formatDate(year, month, day)
        mBinding.btnDateFrom.text = "Date from :\n$dateFrom"
        dateTo = GeneralUtils.formatDate(year, month, day)
        mBinding.btnDateTo.text = "Date to :\n$dateTo"
        mBinding.btnItemType.text = "Item type :\nAll"

        getItemType()
        initRecyclerView()
        subscribeReportResource()
        subscribeItemType()
        getReport()

        initListeners()
    }

    private fun initListeners() {

        mBinding.swipeToRefresh.setOnRefreshListener {
            getReport()
            Handler().postDelayed(Runnable {
                mBinding.swipeToRefresh.isRefreshing = false
            }, 2000)
        }
    }

    private fun subscribeItemType() {
        itemTypeViewModel.allItemType.observe(this) {
            itemTypeList.clear()
            if (it != null) {

                Log.i(TAG, "subscribeItemType: " + it.size)
                itemTypeList.add(
                    ItemType(
                        itemName = "All",
                        itemId = 0
                    )
                )
                itemTypeList.addAll(it)
            }
        }
    }

    private fun getItemType() {
        logViewModel.getItemType()
        logViewModel.getItemTypeResponse().observe(this, {
            if (it != null) {
                when (it.status) {
                    Resource.Status.ERROR -> {
                        Timber.tag(TAG).i("ERROR..................................")
//                        showProgressBar(false)
                        it.message?.let { message -> displayLongToast(message) }
                    }
                    Resource.Status.LOADING -> {
                        Timber.tag(TAG).i("LOADING..................................")
//                        showProgressBar(true)
                    }
                    Resource.Status.SUCCESS -> {
                        Timber.tag(TAG).i("SUCCESS..................................")
//                        showProgressBar(false)

                        it.data?.let { itemTypeResponse ->
                            itemTypeViewModel.deleteAll()
                            Handler().postDelayed({
                                itemTypeResponse.data?.forEach { each ->
                                    itemTypeViewModel.insert(
                                        ItemType(
                                            itemId = each.id,
                                            itemName = each.itemName,
                                            itemCode = each.itemCode
                                        )
                                    )
                                }
                            }, 500)

                        }
                    }
                }
            }
        })
    }

    private fun initRecyclerView() {
        mBinding.rvReport.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            mRecyclerAdapter = ReportAdapter(this@MainActivity, null).apply {
                setHasStableIds(true)
            }
            adapter = mRecyclerAdapter
        }
    }

    private fun getReport() {
        logViewModel.getReport(itemId, dateFrom, dateTo)
        logViewModel.getReportResponse().observe(this, observeReportResource)
    }

    private fun subscribeReportResource() {
        observeReportResource = Observer {
            if (it != null) {
                when (it.status) {
                    Resource.Status.ERROR -> {
                        Timber.tag(TAG).i("ERROR..................................")
                        showProgressBar(false)
                        it.message?.let { message -> displayLongToast(message) }
                    }
                    Resource.Status.LOADING -> {
                        Timber.tag(TAG).i("LOADING..................................")
                        showProgressBar(true)
                    }
                    Resource.Status.SUCCESS -> {
                        Timber.tag(TAG).i("SUCCESS..................................")
                        showProgressBar(false)
                        Timber.tag(TAG)
                            .i("subscribeReportResource (line 159): " + it.data)

                        it.data?.data?.let { itemTypeResponse ->
                            mRecyclerAdapter.submitList(itemTypeResponse)
                        }
                    }
                }
            }
        }
    }

    private fun showProgressBar(isVisible: Boolean) {
        if (mBinding.swipeToRefresh.isRefreshing) return

        mBinding.progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun showItemTypeDialog() {
        val listItems = arrayListOf<String>()
        itemTypeList.forEach { itemType ->
            itemType.itemName?.let { listItems.add(it) }
        }
        val mBuilder = AlertDialog.Builder(this)
        mBuilder.setTitle("Select item type")
        mBuilder.setSingleChoiceItems(listItems.toTypedArray(), -1) { dialogInterface, i ->
            mBinding.btnItemType.text = "Item type :\n${listItems[i]}"
            itemId = itemTypeList[i].itemId
            dialogInterface.dismiss()
        }
        mBuilder.setNeutralButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        val mDialog = mBuilder.create()
        mDialog.show()
    }

    inner class MainActivityCLickHandler(val context: Context) {

        fun onBackClicked(view: View) {
            onBackPressed()
        }


        fun onItemTypeClicked(view: View) {
            showItemTypeDialog()
        }

        fun onDateFromClicked(view: View) {
            val dpd = DatePickerDialog(
                context,
                { _, year, month, day ->
                    dateFrom = GeneralUtils.formatDate(year, month, day)
                    mBinding.btnDateFrom.text = "Date from :\n$dateFrom"
                },
                year, month, day
            )
            dpd.datePicker.maxDate = System.currentTimeMillis()
            dpd.show()
        }

        fun onDateToClicked(view: View) {
            val dpd = DatePickerDialog(
                context,
                { _, year, month, day ->
                    dateTo = GeneralUtils.formatDate(year, month, day)
                    mBinding.btnDateTo.text = "Date to :\n$dateTo"
                },
                year, month, day
            )
            dpd.datePicker.maxDate = System.currentTimeMillis()
            dpd.show()
        }

        fun onFilterClicked(view: View) {
            getReport()
        }
    }


}