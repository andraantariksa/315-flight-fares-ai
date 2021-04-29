package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailedSearchFare(
    @Json(name = "ancillaryFare")
    val ancillaryFare: AncillaryFare,
    @Json(name = "flightRouteFares")
    val flightRouteFares: FlightRouteFares
)