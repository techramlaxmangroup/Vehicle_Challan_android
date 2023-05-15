package com.technosales.vehiclechallanmobile.api

import com.technosales.vehiclechallanmobile.model.itemtype.ItemTypeResponse
import com.technosales.vehiclechallanmobile.model.reportresponse.ReportResponse
import com.technosales.vehiclechallanmobile.utils.TextConfig.API_GET_ITEM_TYPE
import com.technosales.vehiclechallanmobile.utils.TextConfig.API_GET_REPORT
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiInterface {

    @GET(API_GET_ITEM_TYPE)
    suspend fun getItemType(
    ): ItemTypeResponse

    @FormUrlEncoded
    @POST(API_GET_REPORT)
    suspend fun getReport(
        @Field("item_id") itemId: Int?,
        @Field("date_from") dateFrom: String?,
        @Field("date_to") dateTo: String?
    ): ReportResponse
}
