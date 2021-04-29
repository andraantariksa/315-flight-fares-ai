package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlightRescheduleInfo(
    @Json(name = "policyDetails")
    val policyDetails: List<Any>,
    @Json(name = "rescheduleInfoSummary")
    val rescheduleInfoSummary: Any?,
    @Json(name = "rescheduleStatus")
    val rescheduleStatus: String
)