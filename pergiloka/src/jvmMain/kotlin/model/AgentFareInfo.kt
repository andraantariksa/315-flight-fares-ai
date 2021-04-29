package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AgentFareInfo(
    @Json(name = "detailedSearchFares")
    val detailedSearchFares: List<DetailedSearchFare>,
    @Json(name = "totalAdditionalFare")
    val totalAdditionalFare: Any?,
    @Json(name = "totalSearchFare")
    val totalSearchFare: TotalSearchFare
)