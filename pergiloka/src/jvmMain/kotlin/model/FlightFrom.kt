package model

import java.time.LocalDateTime

data class FlightFrom (
    val airportArrival: String,
    val airline: String,
    val price: Int,
    val datetime: LocalDateTime
)