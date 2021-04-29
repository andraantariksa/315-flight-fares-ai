package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailedSearchFareX(
    @Json(name = "ancillaryFare")
    val ancillaryFare: AncillaryFareX,
    @Json(name = "flightRouteFares")
    val flightRouteFares: FlightRouteFaresX
)