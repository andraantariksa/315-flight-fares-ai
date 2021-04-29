package model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AircraftInformation(
    @Json(name = "aircraft")
    val aircraft: Aircraft?,
    @Json(name = "cabinBaggage")
    val cabinBaggage: CabinBaggage
)