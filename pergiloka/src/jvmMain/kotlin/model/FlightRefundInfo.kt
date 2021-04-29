package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlightRefundInfo(
    @Json(name = "policyDetails")
    val policyDetails: List<String>,
    @Json(name = "refundInfoSummary")
    val refundInfoSummary: String?,
    @Json(name = "refundableStatus")
    val refundableStatus: String
)