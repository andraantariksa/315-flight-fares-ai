package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlightRouteFares(
    @Json(name = "adminFeeTotal")
    val adminFeeTotal: AdminFeeTotal,
    @Json(name = "adultAgentFare")
    val adultAgentFare: AdultAgentFare,
    @Json(name = "childAgentFare")
    val childAgentFare: ChildAgentFare,
    @Json(name = "debugInfo")
    val debugInfo: String,
    @Json(name = "infantAgentFare")
    val infantAgentFare: InfantAgentFare,
    @Json(name = "totalFare")
    val totalFare: TotalFare
)