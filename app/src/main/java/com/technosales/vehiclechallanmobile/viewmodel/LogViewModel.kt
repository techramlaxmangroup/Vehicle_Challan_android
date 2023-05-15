package com.technosales.vehiclechallanmobile.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.technosales.vehiclechallanmobile.model.itemtype.ItemTypeResponse
import com.technosales.vehiclechallanmobile.model.reportresponse.ReportResponse
import com.technosales.vehiclechallanmobile.repository.LogRepository
import com.technosales.vehiclechallanmobile.utils.Resource

class LogViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG: String = LogViewModel::class.java.simpleName

    private lateinit var mItemTypeResponse: LiveData<Resource<ItemTypeResponse?>>
    private lateinit var mReportResponse: LiveData<Resource<ReportResponse?>>
    private var logRepository: LogRepository = LogRepository.getInstance()


    fun getItemType() {
        try{
        mItemTypeResponse = logRepository.getItemType()
        } catch (e:Exception){
            e.printStackTrace();
        }
    }

    fun getItemTypeResponse(): LiveData<Resource<ItemTypeResponse?>> = mItemTypeResponse

    fun getReport(
        itemId: Int?,
        dateFrom: String?,
        dateTo: String?
    ) {
        mReportResponse = logRepository.getReport(
            itemId,
            dateFrom,
            dateTo
        )
    }

    fun getReportResponse(): LiveData<Resource<ReportResponse?>> = mReportResponse


    fun cancelJobs() {
        logRepository.cancelJobs()
    }
}
