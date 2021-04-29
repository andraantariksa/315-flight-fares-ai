package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlightData(
    @Json(name = "airline")
    val airline: Map<String, Airline>,
    @Json(name = "airport")
    val airport: Map<String, Airport>,
    @Json(name = "flightDatas")
    val flightsFromAirports: Map<String, Map<String, List<Flight>>>
)