package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlightMetadata(
    @Json(name = "airlineId")
    val airlineId: String,
    @Json(name = "currency")
    val currency: String,
    @Json(name = "destinationAirport")
    val destinationAirport: String,
    @Json(name = "numAdult")
    val numAdult: String,
    @Json(name = "numChild")
    val numChild: String,
    @Json(name = "numInfant")
    val numInfant: String,
    @Json(name = "sourceAirport")
    val sourceAirport: String
)