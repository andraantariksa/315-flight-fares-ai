package model

import java.time.LocalDate
import java.util.*

data class Flights(
    val flightsDate: Map<LocalDate, Map<String, FlightFrom>>
)