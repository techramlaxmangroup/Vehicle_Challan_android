package com.technosales.vehiclechallanmobile.repository

import androidx.lifecycle.LiveData
import com.technosales.vehiclechallanmobile.api.ApiClient
import com.technosales.vehiclechallanmobile.model.itemtype.ItemTypeResponse
import com.technosales.vehiclechallanmobile.model.reportresponse.ReportResponse
import com.technosales.vehiclechallanmobile.utils.Resource
import com.technosales.vehiclechallanmobile.utils.helper.Helper
import kotlinx.coroutines.*
import timber.log.Timber

class LogRepository {
    private val TAG = LogRepository::class.java.simpleName

    var job: CompletableJob? = null

    companion object {
        private var instance: LogRepository? = null
        fun getInstance(): LogRepository {
            if (instance == null) {
                instance = LogRepository()
            }
            return instance!!
        }
    }

    fun getItemType(): LiveData<Resource<ItemTypeResponse?>> {
        job = Job()
        return object : LiveData<Resource<ItemTypeResponse?>>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(Dispatchers.IO + theJob).launch {
                        withContext(Dispatchers.Main) {
                            value = Resource.loading(null)
                        }
                        try {
                            val mItemTypeResponse = ApiClient.apiInterface.getItemType()
                            withContext(Dispatchers.Main) {
                                value = Resource.success(mItemTypeResponse)
                            }
                        } catch (throwable: Throwable) {
                            withContext(Dispatchers.Main) {
                                value = Resource.error(
                                    Helper.getErrorMessage(
                                        throwable,
                                    ), null
                                )
                            }
                        }
                        theJob.complete()
                    }
                }
            }
        }
    }

    fun getReport(
        itemId: Int?,
        dateFrom: String?,
        dateTo: String?
    ): LiveData<Resource<ReportResponse?>> {
        job = Job()
        return object : LiveData<Resource<ReportResponse?>>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(Dispatchers.IO + theJob).launch {
                        withContext(Dispatchers.Main) {
                            value = Resource.loading(null)
                        }
                        try {
                            val mReportResponse = ApiClient.apiInterface.getReport(
                                itemId,
                                dateFrom,
                                dateTo
                            )

                            withContext(Dispatchers.Main) {
                                value = Resource.success(mReportResponse)
                            }
                        } catch (throwable: Throwable) {
                            Timber.tag(TAG).e("onActive: ${throwable.message}")
                            throwable.printStackTrace()
                            withContext(Dispatchers.Main) {
                                value = Resource.error(
                                    Helper.getErrorMessage(
                                        throwable,
                                    ), null
                                )
                            }
                        }
                        theJob.complete()
                    }
                }
            }
        }
    }

    fun cancelJobs() {
        job?.cancel()
    }


}