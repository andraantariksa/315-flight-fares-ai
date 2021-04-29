package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AirlineFareInfo(
    @Json(name = "detailedSearchFares")
    val detailedSearchFares: List<DetailedSearchFareX>,
    @Json(name = "totalAdditionalFare")
    val totalAdditionalFare: TotalAdditionalFareX,
    @Json(name = "totalSearchFare")
    val totalSearchFare: TotalSearchFareX
)