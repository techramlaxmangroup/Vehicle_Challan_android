package com.technosales.vehiclechallanmobile.model.logresponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.technosales.vehiclechallanmobile.model.reportresponse.Item

data class LogData(
    @SerializedName("ticket_number") @Expose var ticketNumber: String? = null,
    @SerializedName("vehicle_number") @Expose var vehicleNumber: String? = null,
    @SerializedName("reference_type") @Expose var referenceType: String? = null,
    @SerializedName("reference_number") @Expose var referenceNumber: String? = null,
    @SerializedName("item_id") @Expose var itemId: Int? = null,
    @SerializedName("generate_device_time") @Expose var generateDeviceTime: String? = null,
    @SerializedName("read_device_time") @Expose var readDeviceTime: String? = null,
    @SerializedName("read_by") @Expose var readBy: String? = null,
    @SerializedName("remarks") @Expose var remarks: String? = null,
    @SerializedName("vehicle_image") @Expose var vehicleImage: String? = null,
    @SerializedName("updated_at") @Expose var updatedAt: String? = null,
    @SerializedName("created_at") @Expose var createdAt: String? = null,
    @SerializedName("id") @Expose var id: Int? = null,
    @SerializedName("item") @Expose var item: Item? = null
){
    override fun toString(): String {
        return "LogData(ticketNumber=$ticketNumber, vehicleNumber=$vehicleNumber, referenceType=$referenceType, referenceNumber=$referenceNumber, itemId=$itemId, generateDeviceTime=$generateDeviceTime, readDeviceTime=$readDeviceTime, readBy=$readBy, remarks=$remarks, vehicleImage=$vehicleImage, updatedAt=$updatedAt, createdAt=$createdAt, id=$id)"
    }
}